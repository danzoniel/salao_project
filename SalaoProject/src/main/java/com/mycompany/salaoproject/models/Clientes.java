package com.mycompany.salaoproject.models;

public class Clientes {
    private String email;
    private String nome;
    private int frequencia;
    private String cupom;
    
    public Clientes() {
    }
    
    public Clientes(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCupom() {
        return cupom;
    }
    
    public void setCupom(String cupom) {
        this.cupom = cupom;
    }
}
