package com.mycompany.salaoproject;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.salaoproject.DAO.ClientesDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.models.Clientes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ListaClientesController {
    @FXML
    private TableView<Clientes> tableView;

    @FXML
    private TableColumn<Clientes, String> cEmail;

    @FXML
    private TableColumn<Clientes, String> cNome;

    @FXML
    private TextField tfNome;

    @FXML 
    private Label lbCount;

    @FXML
    private Button btnCadastro;



    @FXML
    public void initialize() {

        ClientesDAO clientesDAO = new ClientesDAO(HelperDAO.getInstance());

        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        try {
            tableView.getItems().addAll(clientesDAO.getClientes());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tfNome.textProperty().addListener((observable, oldValue, newValue) -> {
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

    private void atualizarTabela(String filtro) throws SQLException {
        ClientesDAO clienteDAO = new ClientesDAO(HelperDAO.getInstance());

        List<Clientes> clientes = clienteDAO.getNomes(filtro);
        tableView.getItems().clear();
        tableView.getItems().addAll(clientes);

        int totalRegistros = clientes.size();
        int numeroPrimeiroRegistro = clientes.isEmpty() ? 0 : 1;
        int numeroUltimoRegistro = numeroPrimeiroRegistro + totalRegistros - 1;
        lbCount.setText("Listando registros de " + numeroPrimeiroRegistro + " - " + numeroUltimoRegistro + " de um total de " + totalRegistros);
    }

    @FXML
    private void handleBtnCadastro(MouseEvent event) {
        ScreenChanger.changeScreen(btnCadastro, "cadastro_cliente.fxml");
    }


}
