package com.ifpb.projetopoo.model;

import java.time.LocalDate;
import java.util.Objects;

// INICIO DA MODELAGEM
public class Usuario {

    //ATRIBUTOS PARA USUARIO
    private String email;
    private String senha;
    private String nome;
    private LocalDate dataNascimento;
    private String setor;
    private String telefone;

    //CONSTRUTOR

    public Usuario(String email, String senha, String nome, LocalDate dataNascimento, String setor, String telefone) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.setor = setor;
        this.telefone = telefone;
    }
    //GETTER's e SETTER's
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(dataNascimento, usuario.dataNascimento) &&
                Objects.equals(setor, usuario.setor) &&
                Objects.equals(telefone, usuario.telefone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email, senha, nome, dataNascimento, setor, telefone);
    }
}
