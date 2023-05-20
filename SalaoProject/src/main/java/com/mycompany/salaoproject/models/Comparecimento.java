package com.mycompany.salaoproject.models;

import java.sql.Timestamp;

public class Comparecimento {
    private int id_comparecimento;
    private int id_agendamento;
    private String email_cliente;
    private Timestamp data_agendamento;

    
    public Comparecimento() {
    }
    
    public Comparecimento(int id_comparecimento, int id_agendamento, String email_cliente, Timestamp data_agendamento) {
        this.id_comparecimento = id_comparecimento;
        this.id_agendamento = id_agendamento;
        this.email_cliente = email_cliente;
        this.data_agendamento = data_agendamento;
    }
    
    public int getId_comparecimento() {
        return id_comparecimento;
    }

    public void setId_comparecimento(int id_comparecimento) {
        this.id_comparecimento = id_comparecimento;
    }

    public int getId_agendamento() {
        return id_agendamento;
    }

    public void setId_agendamento(int id_agendamento) {
        this.id_agendamento = id_agendamento;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public Timestamp getData_agendamento() {
        return data_agendamento;
    }

    public void setData_agendamento(Timestamp data_agendamento) {
        this.data_agendamento = data_agendamento;
    }
    
}
