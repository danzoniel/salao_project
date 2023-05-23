package com.mycompany.salaoproject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.sql.SQLException;

import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.DAO.UsuarioDAO;
import com.mycompany.salaoproject.models.Usuario;

public class CadastraUsuarioController {
    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfSenha;

    @FXML
    private PasswordField tfConfirmarSenha;

    @FXML
    private Label lbErrorEmail;

    @FXML
    private Label lbErrorSenha;

    @FXML
    private Label lbErrorConfirmarSenha;

    @FXML
    private Label lbSuccess;

    @FXML
    private Button btnCadastrarAdmin;

    private UsuarioDAO usuarioDAO;

    @FXML
    public void initialize() {
        lbErrorEmail.setVisible(false);
        lbErrorSenha.setVisible(false);
        lbErrorConfirmarSenha.setVisible(false);

        usuarioDAO = new UsuarioDAO(HelperDAO.getInstance());

         tfEmail.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
         tfSenha.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
         tfConfirmarSenha.textProperty().addListener((observable, oldValue, newValue) -> updateButtonState());
 
         btnCadastrarAdmin.setDisable(true);
    }

    @FXML
    public void cadastrarAdministrador(MouseEvent event) {
        lbErrorEmail.setVisible(false);
        lbErrorSenha.setVisible(false);
        lbErrorConfirmarSenha.setVisible(false);

        String email = tfEmail.getText();
        String senha = tfSenha.getText();
        String confirmarSenha = tfConfirmarSenha.getText();

        if (!isValidEmail(email)) {
            lbErrorEmail.setVisible(true);
            lbErrorEmail.setText("formato de e-mail inválido");
            return;
        }


        if (!senha.equals(confirmarSenha)) {
            lbErrorConfirmarSenha.setVisible(true);
            lbErrorConfirmarSenha.setText("confirmar senha é diferente da senha fornecida");
            return;
        }

        try {
            Usuario usuario = new Usuario(email, senha);

            usuarioDAO.addUsuario(usuario);
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
        tfSenha.setText("");
        tfConfirmarSenha.setText("");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return email.matches(emailRegex);
    }


    private void updateButtonState() {
        String email = tfEmail.getText();
        String senha = tfSenha.getText();
        String confirmarSenha = tfConfirmarSenha.getText();

        boolean fieldsNotEmpty = !email.isEmpty() && !senha.isEmpty() && !confirmarSenha.isEmpty();

        btnCadastrarAdmin.setDisable(!fieldsNotEmpty);
    }

}
