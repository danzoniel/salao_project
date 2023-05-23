package com.mycompany.salaoproject.models;

import com.mycompany.salaoproject.models.Servicos;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Agendamento {
    private SimpleStringProperty emailCliente;
    private SimpleObjectProperty<Date> dataAgendamento;
    private SimpleObjectProperty<Time> horaAgendamento;
    private List<Servicos> servicos;

    private StringProperty servicosString;

    public Agendamento() {
        this.servicos = new ArrayList<>();
        this.emailCliente = new SimpleStringProperty();
        this.dataAgendamento = new SimpleObjectProperty<>();
        this.horaAgendamento = new SimpleObjectProperty<>();
        this.servicosString = new SimpleStringProperty();
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


    public String getServicosString() {
        if (servicos != null) {
        StringBuilder sb = new StringBuilder();
        for (Servicos servico : servicos) {
            sb.append(servico.getServico()).append(", ");
        }
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
        }
        return "";   
    }

    public StringProperty servicosStringProperty() {
        servicosString.set(getServicosString());
        return servicosString;
    }

}
