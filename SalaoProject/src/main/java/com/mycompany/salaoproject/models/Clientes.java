package com.mycompany.salaoproject.models;

public class Clientes {
    private String email;
    private String nome;
    
    public Clientes() {
    }
    
    public Clientes(String email, String nome) {
        this.email = email;
        this.nome = nome;
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
}
