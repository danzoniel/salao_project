package com.mycompany.salaoproject;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mycompany.salaoproject.DAO.ClientesDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.models.Clientes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaClientesController {
    @FXML
    private TableView<Clientes> tableView;

    @FXML
    private TableColumn<Clientes, String> emailColumn;

    @FXML
    private TableColumn<Clientes, String> nomeColumn;


    @FXML
    public void initialize() {

        ClientesDAO clientesDAO = new ClientesDAO(HelperDAO.getInstance());

        // Configurar as colunas da tabela para corresponder aos atributos da classe Clientes
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        // Preencher a tabela com os clientes do banco de dados
        try {
            tableView.getItems().addAll(clientesDAO.getClientes());
        } catch (SQLException e) {
            // Tratar exceção, se necessário
            e.printStackTrace();
        }
    }

}
