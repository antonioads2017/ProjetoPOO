package com.ifpb.projetopoo.model;

import com.ifpb.projetopoo.Exception.DataInvalidaException;
import com.ifpb.projetopoo.dao.UsuarioDao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/** A classe Pedido contêm a modelagem de pedidos dos clientes
 * @author Antonio Miguel
 * @author Laires Pereira
 * @version 1.0
 * @since 29-07-2018
 */

public class Usuario implements Serializable {


    //ATRIBUTOS PARA USUARIO
    private String email;
    private String senha;
    private String CPF;
    private String nome;
    private LocalDate dataNascimento;
    private Setor setor;
    private String telefone;

    /**
     * Construtor
     * */

    public Usuario(String email, String senha, String CPF, String nome, LocalDate dataNascimento, Setor setor, String telefone) throws DataInvalidaException {
        if(dataNascimento==LocalDate.now()){
            throw  new DataInvalidaException("Data Invalida");
        }
        this.email = email;
        this.senha = senha;
        this.CPF=CPF;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.setor = setor;
        this.telefone = telefone;
    }
    //GETTER's e SETTER's

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

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

    public LocalDate getDataNascimento() throws DataInvalidaException {
        if(dataNascimento==LocalDate.now()){
            throw  new DataInvalidaException("Data Invalida");
        }
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    /**
     * Metodo que seta o setor pelo codigo.
     * @param setor
     */
    public void setSetor(int setor) {
        if (setor == Setor.ATENDIMENTO.getCodSetor()) {
            setSetor(Setor.ATENDIMENTO);
        } else if (setor == Setor.COZINHA.getCodSetor()) {
            setSetor(Setor.COZINHA);
        } else if (setor == Setor.CAIXA.getCodSetor()) {
            setSetor(Setor.CAIXA);
        } else if (setor == Setor.GERENCIA.getCodSetor()){
            setSetor(Setor.GERENCIA);
        }
    }



    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**Metodo que valida email e senha do usuário criado.
     * @param email
     * @param senha
     * @return return a comparação de igualdade do email e senha.
     */
    public boolean autenticUsuario(String email, String senha){
        return Objects.equals(this.email,email)&& Objects.equals(this.senha, senha);
    }


    @Override
    public String toString() {
        return "|------Usuario "+nome+"------|\n"+
                "CPF: "+CPF+
                "\nEmail: "+email+
                "\nData de Nascimento: "+dataNascimento+
                "\n Setor: "+setor+
                "\n-----------------------------";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(CPF, usuario.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, senha, CPF);
    }
}
