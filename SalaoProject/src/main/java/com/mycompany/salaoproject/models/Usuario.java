package com.mycompany.salaoproject.models;


public class Usuario {
    private String email;
    private String senha;
    
    // construtor vazio
    public Usuario() {
    }
    
    // construtor com todos os campos
    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    
    // getters e setters para todos os campos
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    

}
