package com.ifpb.projetopoo.dao;

import com.ifpb.projetopoo.Exception.DataInvalidaException;
import com.ifpb.projetopoo.model.Comanda;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class GerenciaDao {

    private static File arquivoGerencia = new File("Gerencia");


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

    public static boolean addComanda(Comanda comanda) throws IOException, ClassNotFoundException {
        List<Comanda> comandas = getComandas();
        if(comandas.add(comanda)){
            atualizarArquivo(comandas);
            return true;
        }else return false;
    }

    private static void atualizarArquivo(List<Comanda> comandas) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoGerencia))){
            out.writeObject(comandas);
        }
    }

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
