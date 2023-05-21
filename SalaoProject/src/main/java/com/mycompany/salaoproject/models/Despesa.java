package com.mycompany.salaoproject.models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Despesa {
    private int idDespesa;
    private String produtoDescricao;
    private double precoUnitario;
    private int quantidade;
    private Timestamp dataSaida;

    public Despesa(int idDespesa, String produtoDescricao, double precoUnitario, int quantidade, Timestamp dataSaida) {
        this.idDespesa = idDespesa;
        this.produtoDescricao = produtoDescricao;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.dataSaida = dataSaida;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Timestamp getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Timestamp dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getData() {
        LocalDate data = dataSaida.toLocalDateTime().toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("pt", "BR"));
        return data.format(formatter);
    }

    public double getValor() {
        return quantidade * precoUnitario;
    }
}