package com.mycompany.salaoproject.models;

public class Clientes {
    private String email;
    private String nome;
    
    // construtor vazio
    public Clientes() {
    }
    
    // construtor com todos os campos
    public Clientes(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }
    
    // getters e setters para todos os campos
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
