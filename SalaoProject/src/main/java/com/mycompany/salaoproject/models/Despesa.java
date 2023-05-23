package com.mycompany.salaoproject.models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Despesa {
    private IntegerProperty idDespesa;
    private StringProperty produtoDescricao;
    private DoubleProperty precoUnitario;
    private IntegerProperty quantidade;
    private ObjectProperty<LocalDate> dataSaida;

    public Despesa(int idDespesa, String produtoDescricao, double precoUnitario, int quantidade, Timestamp dataSaida) {
        this.idDespesa = new SimpleIntegerProperty(idDespesa);
        this.produtoDescricao = new SimpleStringProperty(produtoDescricao);
        this.precoUnitario = new SimpleDoubleProperty(precoUnitario);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.dataSaida = new SimpleObjectProperty<>(dataSaida.toLocalDateTime().toLocalDate());
    }

    public int getIdDespesa() {
        return idDespesa.get();
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa.set(idDespesa);
    }

    public IntegerProperty idDespesaProperty() {
        return idDespesa;
    }

    public String getProdutoDescricao() {
        return produtoDescricao.get();
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao.set(produtoDescricao);
    }

    public StringProperty produtoDescricaoProperty() {
        return produtoDescricao;
    }

    public double getPrecoUnitario() {
        return precoUnitario.get();
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario.set(precoUnitario);
    }

    public DoubleProperty precoUnitarioProperty() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade.get();
    }

    public void setQuantidade(int quantidade) {
        this.quantidade.set(quantidade);
    }

    public IntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public LocalDate getDataSaida() {
        return dataSaida.get();
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida.set(dataSaida);
    }

    public ObjectProperty<LocalDate> dataSaidaProperty() {
        return dataSaida;
    }

    public String getData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("pt", "BR"));
        return dataSaida.get().format(formatter);
    }

    public double getValor() {
        return quantidade.get() * precoUnitario.get();
    }
}
