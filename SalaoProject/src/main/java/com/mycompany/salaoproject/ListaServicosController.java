package com.mycompany.salaoproject;

import java.sql.SQLException;
import java.util.Optional;

import com.mycompany.salaoproject.DAO.ServicosDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.DAO.ServicosAgendadosDAO;
import com.mycompany.salaoproject.models.Servicos;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ListaServicosController {

    @FXML
    private TableView<Servicos> tableView;

    @FXML
    private TableColumn<Servicos, String> cId;

    @FXML
    private TableColumn<Servicos, String> cServico;

    @FXML
    private TableColumn<Servicos, String> cPreco;

    @FXML
    private TextField filterServicos;

    @FXML
    private Label lbCount;

    @FXML
    private Button btnCadastrarServico;

    private void atualizarContagemRegistros() {
        int totalRegistros = tableView.getItems().size();
        int numeroPrimeiroRegistro = tableView.getItems().isEmpty() ? 0 : 1;
        int numeroUltimoRegistro = numeroPrimeiroRegistro + totalRegistros - 1;
        lbCount.setText("Listando registros de " + numeroPrimeiroRegistro + " - " + numeroUltimoRegistro + " de um total de " + totalRegistros);
    }


    @FXML
    public void initialize() {

        ServicosDAO servicosDAO = new ServicosDAO(HelperDAO.getInstance());

        cId.setCellValueFactory(new PropertyValueFactory<>("idServico"));
        cServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        cPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        TableColumn<Servicos, Void> cAcao = new TableColumn<>("Ação");
        cAcao.setCellFactory(param -> {
        TableCell<Servicos, Void> cell = new TableCell<>() {
        private final Button btnDelete = new Button("Excluir");

        {
            btnDelete.setOnAction(event -> {
                Servicos servico = getTableView().getItems().get(getIndex());
                excluirServico(servico);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(btnDelete);
            }
        }
    };
    filterServicos.textProperty().addListener((observable, oldValue, newValue) -> {
        atualizarContagemRegistros();
    });

    atualizarContagemRegistros();

    return cell;
});

tableView.getColumns().add(cAcao);


        filterServicos.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                atualizarTabela(servicosDAO, newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    
        try {
            atualizarTabela(servicosDAO, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void atualizarTabela(ServicosDAO servicosDAO, String filtro) throws SQLException {
        tableView.getItems().clear();
        tableView.getItems().addAll(servicosDAO.getServicos(filtro));
    }

    private void excluirServico(Servicos servico) {
        if (verificarServicoAgendado(servico.getIdServico())) {
            exibirAlertaServicoAgendado();
            return;
        }
    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Deseja realmente excluir o serviço selecionado?");
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                ServicosDAO servicosDAO = new ServicosDAO(HelperDAO.getInstance());
                ServicosAgendadosDAO servicosAgendadosDAO = new ServicosAgendadosDAO(HelperDAO.getInstance());
    
                servicosAgendadosDAO.removerServicosAgendadosPorIdServico(servico.getIdServico());
                servicosDAO.excluirServico(servico);
                tableView.getItems().remove(servico);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private boolean verificarServicoAgendado(int idServico) {
        try {
            ServicosAgendadosDAO servicosAgendadosDAO = new ServicosAgendadosDAO(HelperDAO.getInstance());
            return servicosAgendadosDAO.existeServicoAgendado(idServico, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private void exibirAlertaServicoAgendado() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Serviço Agendado");
        alert.setHeaderText(null);
        alert.setContentText("Esse serviço não pode ser excluído, pois está presente na tabela Serviços Agendados.");
    
        alert.showAndWait();
    }

    @FXML
    private void handleCadastrarServico(MouseEvent event) {
        System.out.println("entrou no cadaskaosd");
        ScreenChanger.changeScreen(btnCadastrarServico, "cadastro_servico.fxml");
    }
        
}
