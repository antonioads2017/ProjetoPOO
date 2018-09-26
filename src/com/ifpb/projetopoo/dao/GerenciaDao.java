package com.ifpb.projetopoo.dao;

import com.ifpb.projetopoo.model.Comanda;
import com.ifpb.projetopoo.model.Gerencia;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciaDao {

    private File arquivoGerencia;
    private Gerencia gerencia;

    public GerenciaDao() throws IOException, ClassNotFoundException {
        arquivoGerencia = new File("Gerencia");

        if(!arquivoGerencia.exists()){
            arquivoGerencia.createNewFile();
            gerencia = new Gerencia();
        }else{
            if(arquivoGerencia.length()>0) {
                try (ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream(arquivoGerencia))) {
                    gerencia = (Gerencia) in.readObject();
                }
            }else gerencia = new Gerencia();
        }
    }

    public void addGerencia(Comanda comanda) throws IOException {
        gerencia.addComanda(comanda);
        atualizarArquivo();
    }


    public void atualizarArquivo() throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoGerencia))){
            out.writeObject(gerencia);
        }
    }

    public String between(LocalDate inicio, LocalDate fim){
        return gerencia.betweenn(inicio,fim);
    }

}
