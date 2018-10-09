package com.ifpb.projetopoo.dao;

import com.ifpb.projetopoo.Exception.CPFInvalidoException;
import com.ifpb.projetopoo.model.Usuario;
import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Classe Dao que arquiva os usuarios do sistema.
 * Contem um arquivo Usuario e um Set de usuarios.
 * @author Antonio
 *  @since 09-10-2018
 *  @version 1.1
 */

public class UsuarioDao {

    private File arquivoUsuario;
    private Set<Usuario> usuarios;

    /**Construtor
     * @throws IOException trata a exceção de estrutura do arquivo.
     * @throws ClassNotFoundException trata a exceção da classe do objeto recebido.
     */
    public UsuarioDao() throws IOException, ClassNotFoundException {
        arquivoUsuario = new File("Usuario");
        if(!arquivoUsuario.exists()){
            arquivoUsuario.createNewFile();
        }
        else{
            if(arquivoUsuario.length()>0) {
                try (ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream(arquivoUsuario))) {
                    usuarios = (Set<Usuario>) in.readObject();
                }
            }else usuarios = new HashSet<>();
        }
    }


    /**
     * Metodo para cadastrar o usuario.
     * @param usuario recebe um Usuario.
     * @return true se o usuario foi cadastrado.
     * @return false se o usuario não foi cadastrado.
     * @throws IOException trata a exceção de estrutura do arquivo.
     * @throws CPFInvalidoException trata a exceção de uma cpf valido.
     */
    public boolean cadastrarUsuario(Usuario usuario) throws IOException, CPFInvalidoException {
        for (Usuario u: usuarios){
            if(Objects.equals(usuario.getCPF(),u.getCPF())){
                throw new CPFInvalidoException("CPF já existe!");
            }else if(Objects.equals(usuario,u)){
                return false;
            }
        }
        if(usuarios.add(usuario)){
            atualizarArquivo(usuarios);
            return true;
        }return false;
    }

    /** Metodo de login para o sistema que verifica se o email e senha estao corretos.
     * @param email recebe um email do usuario.
     * @param senha recebe uma senha de usuario.
     * @return true se foi autenticado
     * @return false se não foi autenticado, usuario incorreto.
     */
    public boolean autenticarUsuario(String email, String senha){
        for(Usuario usuario:usuarios){
            if(usuario.autenticUsuario(email,senha)){
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que consulta um usuario.
     *@param email recebe um email do usuario.
     * @param senha recebe uma senha de usuario.
     * @return o objeto do usuario.
     */
    public Usuario consultarUsuario(String email, String senha){
        for(Usuario usuario:usuarios){
            if(usuario.autenticUsuario(email,senha)){
                return usuario;
            }
        }
        return null;

    }

    /**
     * Metodo que exclui o Usuario.
     *@param emai recebe um email do usuario.
     * @param senha recebe uma senha de usuario.
     * @return true se o usuario foi deletado.
     * @return false se o usuario não foi deletado.
     *  @throws IOException trata a exceção de estrutura do arquivo.
     */
    public boolean excluirUsuario(String emai, String senha) throws IOException {
        if(usuarios.remove(consultarUsuario(emai, senha))){
            atualizarArquivo(usuarios);
            return true;
        }return false;

    }


    /**Metodo que atualiza o arquivo de Usuários.
     * @param usuarios recebe um Set de Usuarios.
     *  @throws IOException trata a exceção de estrutura do arquivo.
     */
    private void atualizarArquivo(Set<Usuario> usuarios) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoUsuario))){
            out.writeObject(usuarios);
        }
    }

    @Override
    public String toString() {
        String s = "|-------------Usuarios-------------|\n";
        if(usuarios.isEmpty()){
            return "Nao existe usuários cadastrados";
        }
        int controle = 0;
        for(Usuario usuario: usuarios){
            controle++;
            s+=controle+":    \n"+usuario.toString();
        }return s;
    }
}
