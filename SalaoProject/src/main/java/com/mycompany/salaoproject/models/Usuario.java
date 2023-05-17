/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.salaoproject.models;

/**
 *
 * @author danie
 */
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
