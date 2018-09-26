package com.ifpb.projetopoo.dao;

import com.ifpb.projetopoo.model.Comanda;
import com.ifpb.projetopoo.model.Gerencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciaDao {

    private File arquivoGerencia;
    private List<Comanda> comandas;

    public GerenciaDao() throws IOException, ClassNotFoundException {
        arquivoGerencia = new File("Gerencia");

        if(!arquivoGerencia.exists()){
            arquivoGerencia.createNewFile();
        }else{
            if(arquivoGerencia.length()>0) {
                try (ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream(arquivoGerencia))) {
                    comandas = (List<Comanda>) in.readObject();
                    Gerencia.setComandas(comandas);
                }
            }else comandas = new ArrayList<>();
        }
    }

    public void addGerencia(Comanda comanda) throws NullPointerException, IOException {
        Gerencia.addComanda(comanda);
        comandas.add(comanda);
        atualizarArquivo();
    }


    public void atualizarArquivo() throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoGerencia))){
            out.writeObject(comandas);
        }
    }

}
