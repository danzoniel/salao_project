package com.mycompany.salaoproject.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import java.sql.Date;
import java.sql.Time;

public class Agendamento {
    private SimpleStringProperty emailCliente;
    private SimpleObjectProperty<Date> dataAgendamento;
    private SimpleObjectProperty<Time> horaAgendamento;

    public Agendamento() {
        this.emailCliente = new SimpleStringProperty();
        this.dataAgendamento = new SimpleObjectProperty<>();
        this.horaAgendamento = new SimpleObjectProperty<>();
    }

    public Agendamento(String emailCliente, Date dataAgendamento, Time horaAgendamento) {
        this.emailCliente = new SimpleStringProperty(emailCliente);
        this.dataAgendamento = new SimpleObjectProperty<>(dataAgendamento);
        this.horaAgendamento = new SimpleObjectProperty<>(horaAgendamento);
    }

    public String getEmailCliente() {
        return emailCliente.get();
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente.set(emailCliente);
    }

    public StringProperty emailClienteProperty() {
        return emailCliente;
    }

    public Date getDataAgendamento() {
        return dataAgendamento.get();
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento.set(dataAgendamento);
    }

    public SimpleObjectProperty<Date> dataAgendamentoProperty() {
        return dataAgendamento;
    }

    public Time getHoraAgendamento() {
        return horaAgendamento.get();
    }

    public void setHoraAgendamento(Time horaAgendamento) {
        this.horaAgendamento.set(horaAgendamento);
    }

    public SimpleObjectProperty<Time> horaAgendamentoProperty() {
        return horaAgendamento;
    }

}
