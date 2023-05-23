package com.mycompany.salaoproject;

import java.sql.SQLException;

import com.mycompany.salaoproject.DAO.ClientesDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.models.Clientes;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CadastraClienteController {
    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNome;

    @FXML
    private Label lbErrorEmail;

    @FXML
    private Label lbErrorNome;

    @FXML
    private Button btnCadastrarCliente;

    private ClientesDAO clientesDAO;

    @FXML
    public void initialize() {
        lbErrorEmail.setVisible(false);
        lbErrorNome.setVisible(false);

        clientesDAO = new ClientesDAO(HelperDAO.getInstance());

         tfEmail.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
         tfNome.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
 
         btnCadastrarCliente.setDisable(true);
    }

    @FXML
    public void cadastrarAdministrador(MouseEvent event) {
        lbErrorEmail.setVisible(false);
        lbErrorNome.setVisible(false);

        String email = tfEmail.getText();
        String nome = tfNome.getText();

        if (!isValidEmail(email)) {
            lbErrorEmail.setVisible(true);
            lbErrorEmail.setText("formato de e-mail inválido");
            return;
        }


        try {
            Clientes cliente = new Clientes(email, nome);

            clientesDAO.addCliente(cliente);
            showMessage("Agendamento criado com sucesso!", Color.GREEN);
            clearFields();
        } catch (SQLException e) {
            showMessage("Erro ao cadastrar agendamento.", Color.RED);
            e.printStackTrace();
        }
    }

    private void showMessage(String message, Color color) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: " + toRGBCode(color));
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private void clearFields() {
        tfEmail.setText("");
        tfNome.setText("");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return email.matches(emailRegex);
    }


    private void updateButtonState() {
        String email = tfEmail.getText();
        String nome = tfNome.getText();

        boolean fieldsNotEmpty = !email.isEmpty() && !nome.isEmpty();

        btnCadastrarCliente.setDisable(!fieldsNotEmpty);
    }
}
