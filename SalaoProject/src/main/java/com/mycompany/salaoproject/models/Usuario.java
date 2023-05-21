package com.mycompany.salaoproject.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    private final StringProperty email;
    private String senha;
    
    public Usuario() {
        this(null, null);
    }
    
    public Usuario(String email, String senha) {
        this.email = new SimpleStringProperty(email);
        this.senha = senha;
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public StringProperty emailProperty() {
        return email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
