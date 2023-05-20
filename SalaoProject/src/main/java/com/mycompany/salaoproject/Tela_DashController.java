package com.mycompany.salaoproject;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import com.mycompany.salaoproject.DAO.ComparecimentoDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.DAO.DespesaDAO;
import com.mycompany.salaoproject.models.Comparecimento;
import com.mycompany.salaoproject.models.Despesa;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Tela_DashController {

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;



    public void initialize() {
        geraComparecimentoChart();
        geraDespesaChart();
    }

    private void geraComparecimentoChart() {
    
        try {
            ComparecimentoDAO comparecimentoDAO = new ComparecimentoDAO(HelperDAO.getInstance());

            List<Comparecimento> comparecimentos = comparecimentoDAO.getComparecimentos();

            ObservableList<XYChart.Data<String, Integer>> dados = FXCollections.observableArrayList();

            for (Month month : Month.values()) {
                int comparecimentosPorMes = 0;
                for (Comparecimento comparecimento : comparecimentos) {
                    if (comparecimento.getData_agendamento().toLocalDateTime().getMonth() == month) {
                        comparecimentosPorMes++;
                    }
                }
                dados.add(new XYChart.Data<>(month.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")), comparecimentosPorMes));
            }

            xAxis.setCategories(FXCollections.observableArrayList(
                Month.JANUARY.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.FEBRUARY.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.MARCH.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.APRIL.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.MAY.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.JUNE.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.JULY.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.AUGUST.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.SEPTEMBER.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.OCTOBER.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.NOVEMBER.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")),
                Month.DECEMBER.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR"))
            ));

            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName("Comparecimentos");
            series.setData(dados);

            barChart.getData().add(series);
            barChart.setLegendVisible(false);
            barChart.getXAxis().setLabel("Mês");
            barChart.getYAxis().setLabel("Número de Comparecimentos");

            NumberAxis yAxis = new NumberAxis();


            if (yAxis != null) {
                yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis) {
                    @Override
                    public String toString(Number object) {
                        return String.format("%d", object.intValue());
                    }
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }

    @FXML
    private BarChart<String, Integer> barChartDespesas;
    @FXML
    private CategoryAxis xAxisDespesas;
    @FXML
    private NumberAxis yAxisDespesas;

    private void geraDespesaChart() {
        try {
            DespesaDAO despesasDAO = new DespesaDAO(HelperDAO.getInstance());
    
            List<Despesa> despesas = despesasDAO.getDespesas();
    
            ObservableList<XYChart.Data<String, Integer>> dadosDespesas = FXCollections.observableArrayList();
    
            for (Month month : Month.values()) {
                int despesasPorMes = 0;
                for (Despesa despesa : despesas) {
                    LocalDateTime despesaData = despesa.getDataSaida().toLocalDateTime();
                    String mesDespesa = despesaData.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, new Locale("pt", "BR"));
                    if (mesDespesa.equalsIgnoreCase(month.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("pt", "BR")))) {
                        despesasPorMes += despesa.getValor();
                    }
                }
                dadosDespesas.add(new XYChart.Data<>(month.getDisplayName(TextStyle.SHORT_STANDALONE, new Locale("pt", "BR")), despesasPorMes));
            }
    
            XYChart.Series<String, Integer> seriesDespesas = new XYChart.Series<>();
            seriesDespesas.setName("Despesas");
            seriesDespesas.setData(dadosDespesas);
    
            barChartDespesas.getData().add(seriesDespesas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


