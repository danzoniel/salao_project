package com.mycompany.salaoproject;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.mycompany.salaoproject.DAO.ServicosDAO;
import com.mycompany.salaoproject.DAO.DespesaDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.DAO.ServicosAgendadosDAO;
import com.mycompany.salaoproject.models.Despesa;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ListaDespesaController {

    @FXML
    private TableView<Despesa> tableView;

    @FXML
    private TableColumn<Despesa, String> cId;

    @FXML
    private TableColumn<Despesa, String> cProduto;

    @FXML
    private TableColumn<Despesa, String> cPrecoUn;

    @FXML
    private TableColumn<Despesa, String> cQuantidade;

    @FXML
    private TableColumn<Despesa, String> cDC;

    @FXML
    private TextField tfFilter;

    @FXML
    private Label lbCount;

    @FXML
    private Button btnCadastrarDespesas;


    @FXML
    public void initialize() {
        carregaDespesas();
        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                atualizarTabela(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        try {
            atualizarTabela(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregaDespesas() {
        cId.setCellValueFactory(new PropertyValueFactory<>("idDespesa"));
        cProduto.setCellValueFactory(new PropertyValueFactory<>("produtoDescricao"));
        cPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
        cQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        cDC.setCellValueFactory(new PropertyValueFactory<>("data"));

    }

    private void atualizarTabela(String filtro) throws SQLException {
        DespesaDAO despesasDAO = new DespesaDAO(HelperDAO.getInstance());

        List<Despesa> despesas = despesasDAO.getDespesas(filtro);
        tableView.getItems().clear();
        tableView.getItems().addAll(despesas);

        int totalRegistros = despesas.size();
        int numeroPrimeiroRegistro = despesas.isEmpty() ? 0 : 1;
        int numeroUltimoRegistro = numeroPrimeiroRegistro + totalRegistros - 1;
        lbCount.setText("Listando registros de " + numeroPrimeiroRegistro + " - " + numeroUltimoRegistro + " de um total de " + totalRegistros);
    }


    @FXML
    private void handleCadastrarDespesa(MouseEvent event) {
        ScreenChanger.changeScreen(btnCadastrarDespesas, "cadastro_despesa.fxml");
    }
        
}
