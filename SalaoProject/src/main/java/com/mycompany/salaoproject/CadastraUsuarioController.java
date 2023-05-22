package com.mycompany.salaoproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

            lbSuccess.setText("Usuário cadastrado com sucesso!");
            lbSuccess.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
