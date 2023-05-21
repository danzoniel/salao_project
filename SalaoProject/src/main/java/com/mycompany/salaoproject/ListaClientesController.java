package com.mycompany.salaoproject;

import java.sql.SQLException;

import com.mycompany.salaoproject.DAO.ClientesDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.models.Clientes;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaClientesController {
    @FXML
    private TableView<Clientes> tableView;

    @FXML
    private TableColumn<Clientes, String> columnEmail;

    @FXML
    private TableColumn<Clientes, String> columnNome;



    @FXML
    public void initialize() {

        ClientesDAO clientesDAO = new ClientesDAO(HelperDAO.getInstance());

        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        try {
            tableView.getItems().addAll(clientesDAO.getClientes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
