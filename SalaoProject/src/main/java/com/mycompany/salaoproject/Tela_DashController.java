package com.mycompany.salaoproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import com.mycompany.salaoproject.DAO.ComparecimentoDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.DAO.DespesaDAO;
import com.mycompany.salaoproject.DAO.FluxoCaixaDAO;
import com.mycompany.salaoproject.models.Comparecimento;
import com.mycompany.salaoproject.models.Despesa;
import com.mycompany.salaoproject.models.ItemModel;



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
    @FXML
    private BarChart<String, Number> barChartLucro;

    public void initialize() {
        createMonthlyProfitChart();
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
            barChartDespesas.setLegendVisible(false);
            seriesDespesas.setName("Despesas");
            seriesDespesas.setData(dadosDespesas);
            barChartDespesas.getXAxis().setLabel("Mês");
            barChartDespesas.getYAxis().setLabel("Despesa R$");
    
            barChartDespesas.getData().add(seriesDespesas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createMonthlyProfitChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chartLucro = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Profit");
    
        FluxoCaixaDAO fluxoCaixaDAO = new FluxoCaixaDAO(HelperDAO.getInstance());
    
        try {
            ResultSet resultSet = fluxoCaixaDAO.getMonthlyProfit();
    
            while (resultSet.next()) {
                String yearMonth = resultSet.getString("year") + "-" + resultSet.getString("month");
                double profit = resultSet.getDouble("profit");
                series.getData().add(new XYChart.Data<>(yearMonth, profit));
            }
    
            chartLucro.getData().add(series);
    
            barChartLucro.getData().clear();

            barChartLucro.setLegendVisible(false);
            barChartLucro.getXAxis().setLabel("Ano-Mês");
            barChartLucro.getYAxis().setLabel("Lucro R$");
    
            for (XYChart.Series<String, Number> dataSeries : chartLucro.getData()) {
                barChartLucro.getData().add(dataSeries);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}