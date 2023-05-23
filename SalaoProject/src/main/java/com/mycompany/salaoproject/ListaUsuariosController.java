package com.mycompany.salaoproject;

import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.DAO.UsuarioDAO;
import com.mycompany.salaoproject.models.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ListaUsuariosController {

    @FXML
    private TableView<Usuario> tableView;

    @FXML
    private TableColumn<Usuario, String> cEmail;

    @FXML
    private TextField tfFilter;

    @FXML
    private Label lbCount;

    @FXML
    private Button btnCadastrarAdmin;

    @FXML
    private void handleBtnCadastrarAdmin(MouseEvent event){
        ScreenChanger.changeScreen(btnCadastrarAdmin, "cadastro_usuario.fxml");
    }

    private UsuarioDAO usuarioDAO;
    private boolean colunaAcaoAdicionada = false;

    public ListaUsuariosController() {
        usuarioDAO = new UsuarioDAO(HelperDAO.getInstance());
    }

    @FXML
    public void initialize() {
        carregaUsuarios();
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

    private void carregaUsuarios() {
        cEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    }

    private void atualizarTabela(String filtro) throws SQLException {
        List<Usuario> usuarios = usuarioDAO.getUsuarios(filtro);
        tableView.getItems().clear();
        tableView.getItems().addAll(usuarios);

        int totalRegistros = usuarios.size();
        int numeroPrimeiroRegistro = usuarios.isEmpty() ? 0 : 1;
        int numeroUltimoRegistro = numeroPrimeiroRegistro + totalRegistros - 1;
        lbCount.setText("Listando registros de " + numeroPrimeiroRegistro + " - " + numeroUltimoRegistro + " de um total de " + totalRegistros);

        if (!colunaAcaoAdicionada) {
            configurarColunaExcluir();
            colunaAcaoAdicionada = true;
        }
    }

    private void configurarColunaExcluir() {
        TableColumn<Usuario, Void> cAcao = new TableColumn<>("Ação");
        cAcao.setCellFactory(param -> {
            ButtonCell<Usuario> cell = new ButtonCell<>("Excluir", (usuario) -> {
                if (tableView.getItems().size() == 1) {
                    exibirAlertaUnicoUsuario();
                } else {
                    exibirConfirmacaoExclusao(usuario);
                }
            });
            return cell;
        });

        tableView.getColumns().add(cAcao);
    }

    private void exibirConfirmacaoExclusao(Usuario usuario) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de exclusão");
        alert.setHeaderText(null);
        alert.setContentText("Deseja realmente excluir o usuário selecionado?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                usuarioDAO.excluirUsuario(usuario);
                atualizarTabela(tfFilter.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void exibirAlertaUnicoUsuario() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ação não permitida");
        alert.setHeaderText(null);
        alert.setContentText("Não é possível excluir o usuário. Existe apenas um usuário registrado.");

        alert.showAndWait();
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
}
