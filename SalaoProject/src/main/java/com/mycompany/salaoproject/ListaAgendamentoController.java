package com.mycompany.salaoproject;

import com.mycompany.salaoproject.DAO.AgendamentoDAO;
import com.mycompany.salaoproject.DAO.ComparecimentoDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.DAO.ServicosAgendadosDAO;
import com.mycompany.salaoproject.models.Agendamento;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ListaAgendamentoController  {
    @FXML
    private TableView<Agendamento> tableView;
    @FXML
    private TableColumn<Agendamento, String> cEmail;
    @FXML
    private TableColumn<Agendamento, Date> cData;
    @FXML
    private TableColumn<Agendamento, Time> cHora;
    @FXML
    private Label lbCount;
    @FXML
    private TextField tfFilter;
    @FXML
    private Button btnCadastrarAgendamento;

    private AgendamentoDAO agendamentoDAO;
    private boolean colunaAcaoAdicionada = false;

    public ListaAgendamentoController() {
        agendamentoDAO = new AgendamentoDAO(HelperDAO.getInstance());
    }

    @FXML
    public void initialize() {
        carregarAgendamentos();

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
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
        List<Agendamento> agendamentos = agendamentoDAO.obterTodosAgendamentos(filtro);
        tableView.getItems().clear();
        tableView.getItems().addAll(agendamentos);

        int totalRegistros = agendamentos.size();
        int numeroPrimeiroRegistro = agendamentos.isEmpty() ? 0 : 1;
        int numeroUltimoRegistro = numeroPrimeiroRegistro + totalRegistros - 1;
        lbCount.setText("Listando registros de " + numeroPrimeiroRegistro + " - " + numeroUltimoRegistro + " de um total de " + totalRegistros);

        if (!colunaAcaoAdicionada) {
            configurarColunaExcluir();
            colunaAcaoAdicionada = true;
        }
    }

    private void carregarAgendamentos() {
        try {
            List<Agendamento> agendamentos = agendamentoDAO.obterTodosAgendamentos(null);
            ObservableList<Agendamento> listaAgendamentos = FXCollections.observableArrayList(agendamentos);
            tableView.setItems(listaAgendamentos);
            cEmail.setCellValueFactory(cellData -> cellData.getValue().emailClienteProperty());
            cData.setCellValueFactory(cellData -> cellData.getValue().dataAgendamentoProperty());
            cHora.setCellValueFactory(cellData -> cellData.getValue().horaAgendamentoProperty());
    
        } catch (SQLException e) {
            return;
        }
    }

    private void configurarColunaExcluir() {
        TableColumn<Agendamento, Void> cAcao = new TableColumn<>("Ação");
        cAcao.setCellFactory(param -> {
            ButtonCell<Agendamento> cell = new ButtonCell<>("Excluir", (agendamento) -> {
                if (tableView.getItems().size() >= 1) {
                    exibirConfirmacaoExclusao(agendamento);
                } else {
                }
            });
            return cell;
        });

        tableView.getColumns().add(cAcao);
    }

    private void exibirConfirmacaoExclusao(Agendamento agendamento) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Deseja realmente excluir o agendamento selecionado?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                if(!verificarComparecimento(agendamento)) {
                    agendamentoDAO.excluirAgendamento(agendamento);
                    atualizarTabela(null);
                } else {
                    exibirAlertaComparecido();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ButtonCell<T> extends TableCell<T, Void> {
        private final Button button;

        public ButtonCell(String buttonText, ButtonClickListener<T> listener) {
            button = new Button(buttonText);
            button.setOnAction(event -> {
                T item = getTableView().getItems().get(getIndex());
                listener.onClick(item);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    }

    private interface ButtonClickListener<T> {
        void onClick(T item);
    }

    private boolean verificarComparecimento(Agendamento agendamento) {
        try {
            ComparecimentoDAO comparecimentosDAO = new ComparecimentoDAO(HelperDAO.getInstance());
            System.out.println(agendamento.getEmailCliente());
            System.out.println(agendamento.getDataAgendamento());
            return comparecimentosDAO.existeComparecimento(agendamento);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private void exibirAlertaComparecido() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Serviço já prestado");
        alert.setHeaderText(null);
        alert.setContentText("Esse serviço não pode ser excluído pois ele já foi prestado.");
    
        alert.showAndWait();
    }

    @FXML
    private void handleCadastrarAgendamento(MouseEvent event) {
        ScreenChanger.changeScreen(btnCadastrarAgendamento, "cadastro_agendamento.fxml");
    }

}
