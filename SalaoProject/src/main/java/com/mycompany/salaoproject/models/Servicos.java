package com.mycompany.salaoproject.models;

public class Servicos {
    private int idServico;
    private String servico;
    private double preco;

    public Servicos(int idServico, String servico, double preco) {
        this.idServico = idServico;
        this.servico = servico;
        this.preco = preco;
    }


    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
