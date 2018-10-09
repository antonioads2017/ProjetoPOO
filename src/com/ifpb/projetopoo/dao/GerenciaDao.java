package com.ifpb.projetopoo.dao;

import com.ifpb.projetopoo.model.Comanda;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/** Classe Dao que arquiva as comandas fechadas do sistema.
 * Contem um arquivo Gerencia.
 * @author Antonio
 *  @since 09-10-2018
 *  @version 1.1
 */


public class GerenciaDao {

    private static File arquivoGerencia = new File("Gerencia");


    /**
     * Metodo estatico que pega ou cria um arquivo e salva os objetos comandas.
     * @return uma lista de comandas fechadas.
     * @throws IOException trata a exceção de estrutura do arquivo.
     * @throws ClassNotFoundException trata a exceção da classe do objeto recebido.
     */
    public static List<Comanda> getComandas() throws IOException, ClassNotFoundException {
        if(!arquivoGerencia.exists()){
            arquivoGerencia.createNewFile();
        }
        if(arquivoGerencia.length()>0){
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoGerencia))){
                return (List<Comanda>) in.readObject();
            }
        }else return new ArrayList<>();
    }

    /**
     * Metodo que adiciona a comanda fechada ao arquivo.
     * @param comanda recebe um objeto comanda.
     * @return true se a comanda fechada foi armazenada.
     * @return false se a comanda fechada não foi armazena.
     * @throws IOException trata a exceção de estrutura do arquivo.
     * @throws ClassNotFoundException trata a exceção da classe do objeto recebido.
     */
    public static boolean addComanda(Comanda comanda) throws IOException, ClassNotFoundException {
        List<Comanda> comandas = getComandas();
        if(comandas.add(comanda)){
            atualizarArquivo(comandas);
            return true;
        }else return false;
    }

    /**Metodo que atualiza o arquivo das comandas.
     * @param comandas recebe uma lista de comandas.
     * @throws IOException trata a exceção de estrutura do arquivo.
     */
    private static void atualizarArquivo(List<Comanda> comandas) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoGerencia))){
            out.writeObject(comandas);
        }
    }

    /**
     * Metodo que varre as comandas de acordo com o periodo em data.
     * @param inicio recebe a data inicio.
     * @param fim recebe a data fim.
     * @return uma lista das comandas do periodo desejado.
     * @throws IOException trata a exceção de estrutura do arquivo.
     * @throws ClassNotFoundException trata a exceção da classe do objeto recebido.
     */
    public static ArrayList<Comanda> between(LocalDate inicio, LocalDate fim) throws IOException, ClassNotFoundException {
        if(inicio.compareTo(fim)>0){
            LocalDate aux = fim;
            fim = inicio;
            inicio = aux;
        }
        ArrayList<Comanda> resultado = new ArrayList<>();
        List<Comanda> comandas = getComandas();
        for (Comanda comanda: comandas){
            if((comanda.getData().compareTo(inicio)>=0) && (comanda.getData().compareTo(fim)<=0)){ //REVER
                resultado.add(comanda);
            }
        }
        return resultado;
    }
}
