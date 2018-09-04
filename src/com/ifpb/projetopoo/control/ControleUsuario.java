package com.ifpb.projetopoo.control;

import com.ifpb.projetopoo.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/** A classe Pedido contêm a modelagem de controle de Usuario.
 * @author Antonio Miguel
 * @author Laires Pereira
 * @version 1.0
 * @since 29-07-2018
 */

public class ControleUsuario {

    private List<Usuario> cadastros;

    /**
     * CONSTRUTOR
     */
    public ControleUsuario(){
        cadastros=new ArrayList<>();
    }

    public List<Usuario> getCadastros(){
        return cadastros;
    }

    /** Metodo para ler os dados e armazenar o usuario.
     * @return retorna um objeto de Usuario.
     */

    public Usuario lerDadosUsuario(){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Informe o CPF");
        String CPF = scanner.next();
        System.out.println("Informe seu email");
        String email=scanner.next();
        System.out.println("Informe o seu nome:");
        String nome = scanner.next();
        System.out.println("Informe seu telefone");
        String telefone = scanner.next();
        System.out.println("Informe sua data de nascimento:");
        String data = scanner.next();
        LocalDate nascimento = LocalDate.parse(data,formatter);
        System.out.println("Selecione o seu setor pelo numero: 1 - Atendimento\n2 - Cozinha\n3 - Caixa\n4- Gerencia");
        int setorUser=scanner.nextInt();
        Setor setor = null;
        switch (setorUser) {
                case 1:
                    setor = Setor.ATENDIMENTO;
                    break;

                case 2:
                    setor = Setor.COZINHA;
                    break;

                case 3:
                    setor = Setor.CAIXA;
                    break;

                case 4:
                    setor = Setor.GERENCIA;
                    break;
                default: setor = Setor.ATENDIMENTO;
            }
        System.out.println("Informe sua senha:");
        String senha = scanner.next();
        Usuario newUser = new Usuario(email,senha,CPF,nome,nascimento,setor,telefone);
        return newUser;
    }
    /**Metodo que pega o objeto usuario e armazena na lista.
     * @return retorna um boolean que diz que foi cadastrado o usuario.
     */

    public boolean cadastrarUsuario(){
        Usuario u = lerDadosUsuario();
        cadastros.add(u);
        return true;
    }

    /** Metodo que autentica um usuario.
     * @param email recebe um email
     * @param senha recebe uma senha
     * @return retorna um boolean se existe ou nao um usuario.
     */
    public boolean autenticarUsuario(String email, String senha){
        for(Usuario usuario:cadastros){
            if(usuario.autenticUsuario(email,senha)){
                return true;
            }
        }return false;
    }

    /**Metodo de consulta de um Usuario.
     * @param email recebe um email.
     * @param senha recebe uma senha.
     * @return retorna uma string do usuario.
     */
    public Usuario consultarUsuario (String email, String senha){
        for(Usuario usuario : cadastros){
            if(usuario.autenticUsuario(email,senha)){
                System.out.println(usuario);
                return usuario;
            }
        }
        System.out.println("Usuario inexistente");
        return null;
    }

    /**Metodo que atualiza o usuario
     * @param indice recebe o indice do usuario na lista.
     * @return um boolean que o usuario foi ou nao atualizado.
     */
    public boolean atualizarUsuario(int indice){
        if(indice>cadastros.size()-1){
            return false;
        }
        else{
            Usuario substituto = lerDadosUsuario();
            cadastros.add(indice,substituto);
            return true;
        }
    }

    /**Metodo que verifica se a atualização é do usuario logado.
     * @param email recebe o email do usuario.
     * @param senha recebe a senha do usuario.
     * @return retorn o string dos dados e também ja chama o metodo para sobrecresver(atualizar).
     */
    public boolean atualizarEsseUsuario(String email, String senha){
        Usuario antigo = consultarUsuario(email,senha);
        return atualizarUsuario(cadastros.indexOf(antigo));
    }
    /**Metodo que atualiza o usuario
     * @param indice recebe o indice do usuario na lista.
     * @return um boolean que o usuario foi ou nao excluido.
     */

    public boolean excluirUsuario(int indice){
        if(indice>cadastros.size()-1){
            return false;
        }
        else cadastros.remove(indice);
        return true;
    }
    /**Metodo que verifica se a exclusão é do usuario logado.
     * @param email recebe o email do usuario.
     * @param senha recebe a senha do usuario.
     * @return retorn o string dos dados e também ja chama o metodo de exclusão.
     */

    public boolean excluirEsseUsuario(String email, String senha){
        Usuario excluido = consultarUsuario(email,senha);
        return excluirUsuario(cadastros.indexOf(excluido));
    }

    @Override
    public String toString() {
        if (cadastros.isEmpty()){
            return "Cadastros Vazios!";
        }
            String s = "|-------------Usuarios-------------|\n";
            int controle = 0;
            for(Usuario usuario: cadastros){
                controle++;
                s+=controle+":    \n"+usuario.toString();
            }return s;
        }
}
