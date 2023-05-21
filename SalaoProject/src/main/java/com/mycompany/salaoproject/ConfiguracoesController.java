package com.mycompany.salaoproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ConfiguracoesController {

    @FXML
    private AnchorPane apServicos;

    @FXML
    private Button btnServicos;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnAgendamentos;

    @FXML
    private Button btnDespesas;

    @FXML
    private Button btnClientes;
    
    @FXML
    void handleApServicos(MouseEvent event) {
        ScreenChanger.changeScreen(btnServicos, "lista_servico.fxml");
    }

    @FXML
    void handlebtnUsuarios(MouseEvent event) {
        ScreenChanger.changeScreen(btnUsuarios, "lista_usuarios.fxml");
    }

    @FXML
    void handlebtnAgendamentos(MouseEvent event) {
        ScreenChanger.changeScreen(btnAgendamentos, "lista_agendamentos.fxml");
    }

    @FXML
    void handlebtnDespesas(MouseEvent event) {
        ScreenChanger.changeScreen(btnDespesas, "lista_despesas.fxml");
    }

    @FXML
    void handlebtnClientes(MouseEvent event) {
        ScreenChanger.changeScreen(btnClientes, "lista_clientes.fxml");
    }
        
}
