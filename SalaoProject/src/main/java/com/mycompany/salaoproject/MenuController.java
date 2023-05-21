package com.mycompany.salaoproject;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class MenuController {

    @FXML
    private Label btnSair;

    @FXML
    private Label btnDash;

    @FXML
    private Label btnConfig;

    @FXML
    private Label btnHistAtend;

    @FXML
    private Label btnHistDesp;


    @FXML
    void handleSair(MouseEvent event) {
        ScreenChanger.changeScreen(btnSair, "telalogin.fxml");
    }

    @FXML
    void handleDash(MouseEvent event) {
        ScreenChanger.changeScreen(btnDash, "tela_dash.fxml");

    }

    @FXML
    void handleConfig(MouseEvent event) {
        ScreenChanger.changeScreen(btnConfig, "configuracoes.fxml");

    }

    @FXML
    void handleHistAtend(MouseEvent event) {
        ScreenChanger.changeScreen(btnHistAtend, "lista_atendimentos.fxml");

    }

    @FXML
    void handleHistDesp(MouseEvent event) {
        ScreenChanger.changeScreen(btnHistDesp, "lista_despesas.fxml");
    }
}
