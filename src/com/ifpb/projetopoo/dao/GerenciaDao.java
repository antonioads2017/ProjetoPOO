package com.ifpb.projetopoo.dao;

import com.ifpb.projetopoo.Exception.DataInvalidaException;
import com.ifpb.projetopoo.model.Comanda;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class GerenciaDao {

    private File arquivoGerencia;

    public GerenciaDao() throws IOException {
        arquivoGerencia = new File("Gerencia");
        if(!arquivoGerencia.exists()){
            arquivoGerencia.createNewFile();
        }

    }

    public Set<Comanda> getComandas() throws IOException, ClassNotFoundException {
        if(arquivoGerencia.length()>0){
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoGerencia))){
                return (Set<Comanda>) in.readObject();
            }
        }else return new HashSet<>();
    }

    public boolean addComanda(Comanda comanda) throws IOException, ClassNotFoundException {
        Set<Comanda> comandas = getComandas();
        if(comandas.add(comanda)){
            atualizarArquivo(comandas);
            return true;
        }else return false;
    }

    private void atualizarArquivo(Set<Comanda> comandas) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoGerencia))){
            out.writeObject(comandas);
        }
    }

    public String betweenn (LocalDate comeco, LocalDate fim) throws DataInvalidaException, IOException, ClassNotFoundException {
        Set<Comanda> comandas = getComandas();
        if(!comeco.isBefore(fim)) throw new DataInvalidaException("Data inicio maior que a data fim, tente de novo!");
        String resposta = "|";
        for (Comanda comanda: comandas){
            if((comanda.getData().compareTo(comeco)>=0)&&(comanda.getData().compareTo(fim)<=0)){
                resposta+=comanda;
            }
        }return resposta;
    }
}
