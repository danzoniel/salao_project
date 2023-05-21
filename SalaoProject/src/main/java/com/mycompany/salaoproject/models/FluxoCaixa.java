package com.mycompany.salaoproject.models;

public class FluxoCaixa {
    private int idFluxo;
    private int idDespesa;
    private int idComparecimento;
    private String email;
    private double valorMovimentadoSaida;
    private double valorMovimentadoEntrada;


    public int getIdFluxo() {
        return idFluxo;
    }

    public void setIdFluxo(int idFluxo) {
        this.idFluxo = idFluxo;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public int getIdComparecimento() {
        return idComparecimento;
    }

    public void setIdComparecimento(int idComparecimento) {
        this.idComparecimento = idComparecimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getValorMovimentadoSaida() {
        return valorMovimentadoSaida;
    }

    public void setValorMovimentadoSaida(double valorMovimentadoSaida) {
        this.valorMovimentadoSaida = valorMovimentadoSaida;
    }

    public double getValorMovimentadoEntrada() {
        return valorMovimentadoEntrada;
    }

    public void setValorMovimentadoEntrada(double valorMovimentadoEntrada) {
        this.valorMovimentadoEntrada = valorMovimentadoEntrada;
    }
}
