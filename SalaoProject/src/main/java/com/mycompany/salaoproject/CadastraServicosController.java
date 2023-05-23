package com.mycompany.salaoproject;

import java.sql.SQLException;

import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.DAO.ServicosDAO;
import com.mycompany.salaoproject.models.Servicos;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CadastraServicosController {

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfServico;

    @FXML
    private TextField tfPreco;

    @FXML
    private Label lbErrorId;

    @FXML
    private Label lbErrorServicos;

    @FXML
    private Label lbErrorPreco;


    private ServicosDAO servicosDAO;

    public void initialize() throws SQLException {
        servicosDAO = new ServicosDAO(HelperDAO.getInstance());
        lbErrorId.setVisible(false);
        lbErrorServicos.setVisible(false);
        lbErrorPreco.setVisible(false);

        int nextId = servicosDAO.getNextId();
        tfId.setText(String.valueOf(nextId));
        tfId.setDisable(true);
    }

    public void cadastrarServico(MouseEvent event) {
        lbErrorId.setVisible(false);
        lbErrorServicos.setVisible(false);
        lbErrorPreco.setVisible(false);

        try {
            int id;
            try {
                id = Integer.parseInt(tfId.getText());
            } catch (NumberFormatException e) {
                lbErrorId.setText("ID inválido");
                lbErrorId.setVisible(true);
                return;
            }

            String servico = tfServico.getText();
            double preco;
            try {
                preco = Double.parseDouble(tfPreco.getText());
            } catch (NumberFormatException e) {
                lbErrorPreco.setText("Preço inválido");
                lbErrorPreco.setVisible(true);
                return;
            }

            boolean isValid = true;

            if (servico.isEmpty()) {
                lbErrorServicos.setText("Campo obrigatório");
                lbErrorServicos.setVisible(true);
                isValid = false;
            }

            if (preco <= 0) {
                lbErrorPreco.setText("O preço deve ser maior que zero");
                lbErrorPreco.setVisible(true);
                isValid = false;
            }

            if (isValid) {
                Servicos novoServico = new Servicos(id, servico, preco);

                try {
                    servicosDAO.cadastrarServico(novoServico);
                    System.out.println("Serviço cadastrado com sucesso!");
                    showMessage("Agendamento criado com sucesso!", Color.GREEN);
                    clearFields();
                } catch (SQLException e) {
                    showMessage("Erro ao cadastrar agendamento.", Color.RED);
                    e.printStackTrace();
                    System.out.println("Erro ao cadastrar o serviço. Por favor, tente novamente.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro. Por favor, tente novamente.");
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
        tfId.setText("");
        tfServico.setText("");
        tfPreco.setText("");
    }

    public void setServicosDAO(ServicosDAO servicosDAO) {
        this.servicosDAO = servicosDAO;
    }
}
