package com.mycompany.salaoproject;

import com.mycompany.salaoproject.DAO.DespesaDAO;
import com.mycompany.salaoproject.DAO.FluxoCaixaDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.models.Despesa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class CadastraDespesaController {


    @FXML
    private TextField tfId;
    @FXML
    private DatePicker dpDataCompra;
    @FXML
    private TextField tfProduto;
    @FXML
    private TextField tfPrecoUn;
    @FXML
    private TextField tfQuantidade;
    @FXML
    private Label lbErrorId;
    @FXML
    private Label lbErrorProduto;
    @FXML
    private Label lbErrorPreco;
    @FXML
    private Label lbErrorQuantidade;
    @FXML
    private Label lbErrorDataCompra;
    @FXML
    private Button btnCadastrarDespesa;

    private DespesaDAO despesaDAO;

    private FluxoCaixaDAO fluxoCaixaDAO;

    public CadastraDespesaController() {
        despesaDAO = new DespesaDAO(HelperDAO.getInstance());
        fluxoCaixaDAO = new FluxoCaixaDAO(HelperDAO.getInstance());
    }

    public void initialize() throws SQLException {
        clearErrorLabels();

        int nextId = despesaDAO.getNextId();
        tfId.setText(String.valueOf(nextId));
        tfId.setDisable(true);
    }

    @FXML
    private void cadastrarDespesa(MouseEvent event) {
        clearErrorLabels();

        int id = Integer.parseInt(tfId.getText());
        String produto = tfProduto.getText();
        double precoUn = 0;
        int quantidade = 0;
        LocalDate data = LocalDate.now();
        try {
            precoUn = Double.parseDouble(tfPrecoUn.getText());
            quantidade = Integer.parseInt(tfQuantidade.getText());
            data = dpDataCompra.getValue();
            
        } catch(Exception e) {
            showMessage("Erro ao cadastrar agendamento.", Color.RED);
        }

        Timestamp timestamp = Timestamp.valueOf(data.atStartOfDay());

        boolean isValid = validateFields(id, produto, precoUn, quantidade, data);
        if (isValid) {
            try {
                Despesa despesa = new Despesa(
                    id,
                    produto,
                    precoUn,
                    quantidade,
                    timestamp
            );


                despesaDAO.addDespesa(despesa);
                fluxoCaixaDAO.addFluxoCaixaDespesa(despesa);

                clearFields();
                showMessage("Agendamento criado com sucesso!", Color.GREEN);
                System.out.println("Despesa cadastrada com sucesso!");
            } catch (NumberFormatException e) {
                showMessage("Erro ao cadastrar agendamento.", Color.RED);
                System.out.println("Erro ao converter valores numéricos.");
            } catch (SQLException e) {
                showMessage("Erro ao cadastrar agendamento.", Color.RED);
                System.out.println("Erro ao cadastrar despesa no banco de dados: " + e.getMessage());
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

    private boolean validateFields(int id, String produto, double precoUn, int quantidade, LocalDate data) {
        boolean isValid = true;
    
        if (id == 0) {
            lbErrorId.setText("O campo ID é obrigatório");
            isValid = false;
        }
    
        if (produto.isEmpty()) {
            lbErrorProduto.setText("O campo Produto é obrigatório");
            isValid = false;
        }
    
        if (precoUn == 0.0) {
            lbErrorPreco.setText("O campo Preço Unitário é obrigatório");
            isValid = false;
        }
    
        if (quantidade == 0) {
            lbErrorQuantidade.setText("O campo Quantidade é obrigatório");
            isValid = false;
        }
    
        if (data == null) {
            lbErrorDataCompra.setText("O campo Data de Compra é obrigatório");
            isValid = false;
        }
    
        return isValid;
    }

    private void clearFields() {
        tfId.clear();
        dpDataCompra.setValue(null);
        tfProduto.clear();
        tfPrecoUn.clear();
        tfQuantidade.clear();
    }

    private void clearErrorLabels() {
        lbErrorId.setText("");
        lbErrorProduto.setText("");
        lbErrorPreco.setText("");
        lbErrorQuantidade.setText("");
        lbErrorDataCompra.setText("");
    }

}
