package com.mycompany.salaoproject;

import com.mycompany.salaoproject.DAO.AgendamentoDAO;
import com.mycompany.salaoproject.DAO.ClientesDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.DAO.ServicosAgendadosDAO;
import com.mycompany.salaoproject.DAO.ServicosDAO;
import com.mycompany.salaoproject.models.Agendamento;
import com.mycompany.salaoproject.models.Servicos;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CadastrarAgendamentoController {
    @FXML
    private DatePicker dpData;
    @FXML
    private TextField tfHora;
    @FXML
    private Label lbErrorEmail;
    @FXML
    private Label lbErrorData;
    @FXML
    private Label lbErrorHora;
    @FXML
    private Label lvErrorServicos;
    @FXML
    private Button btnCadastrarAgendamento;

    @FXML
    private ComboBox<String> cbEmail;
    @FXML
    private ComboBox<String> cbServicos;

    private List<String> servicoNames;

    private AgendamentoDAO agendamentoDAO;
    private ServicosDAO servicosDAO;
    private ClientesDAO clienteDAO;
    private ServicosAgendadosDAO servicosAgendadosDAO;

    public CadastrarAgendamentoController() {
        this.agendamentoDAO = new AgendamentoDAO(HelperDAO.getInstance());
        this.servicosDAO = new ServicosDAO(HelperDAO.getInstance());
        this.clienteDAO = new ClientesDAO(HelperDAO.getInstance());
        this.servicosAgendadosDAO = new ServicosAgendadosDAO(HelperDAO.getInstance());
    }

    @FXML
    private void initialize() {
        // checkCamposPreenchidos();
        lbErrorEmail.setText("");
        lbErrorData.setText("");
        lbErrorHora.setText("");
        lvErrorServicos.setText("");

        updateServicosList();
        updateEmailList();
    }

    private void updateEmailList() {
        try {
            List<String> emails = clienteDAO.getEmailString();
            cbEmail.getItems().addAll(emails);
        } catch (SQLException e) {
            showMessage("Erro ao obter a lista de emails cadastrados.", Color.RED);
            e.printStackTrace();
        }
    }

    @FXML
    private void cadastrarAgendamento(MouseEvent event) {
        lbErrorEmail.setText("");
        lbErrorData.setText("");
        lbErrorHora.setText("");

        String email = cbEmail.getValue();
        LocalDate data = dpData.getValue();
        String hora = tfHora.getText();

        boolean isValid = true;

        if (email.isEmpty()) {
            lbErrorEmail.setText("Campo obrigatório");
            isValid = false;
        }

        if (data == null) {
            lbErrorData.setText("Campo obrigatório");
            isValid = false;
        }

        if (hora.isEmpty()) {
            lbErrorHora.setText("Campo obrigatório");
            isValid = false;
        }

        if (!hora.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            lbErrorHora.setText("Formato inválido (HH:mm)");
            isValid = false;
        }

        if (isValid) {
            Date dataAgendamento = Date.valueOf(data);
            Time horaAgendamento = Time.valueOf(hora+":00");


            Agendamento agendamento = new Agendamento(email, dataAgendamento, horaAgendamento);

            String servicoSelecionado = cbServicos.getValue();
            
            try {
                List<Servicos> servicos = servicosDAO.getServicos(servicoSelecionado);
                if (!servicos.isEmpty()) {
                    Servicos servico = servicos.get(0);
                    agendamentoDAO.criarAgendamento(agendamento);
                    servicosAgendadosDAO.addServicoAgendado(servico, agendamento);
                    showMessage("Agendamento criado com sucesso!", Color.GREEN);
                    clearFields();
                } else {
                    showMessage("Serviço não encontrado.", Color.RED);
                }
            } catch (SQLException e) {
                showMessage("Erro ao cadastrar agendamento.", Color.RED);
                e.printStackTrace();
            }
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
        cbEmail.setValue(null);
        dpData.setValue(null);
        tfHora.clear();
    }

    private void updateServicosList() {
        try {
            List<Servicos> servicos = servicosDAO.getServicos(null);
            List<String> servicoNames = servicos.stream()
                    .map(Servicos::getServico)
                    .collect(Collectors.toList());
            cbServicos.getItems().addAll(servicoNames);
        } catch (SQLException e) {
            showMessage("Erro ao obter a lista de serviços.", Color.RED);
            e.printStackTrace();
        }
    }

    @FXML
    private void checkCamposPreenchidos() {
        boolean camposPreenchidos = cbEmail.getValue() != null &&
            dpData.getValue() != null &&
            !tfHora.getText().isEmpty();

        btnCadastrarAgendamento.setDisable(!camposPreenchidos);
    }
}
