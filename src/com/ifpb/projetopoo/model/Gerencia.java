//package com.ifpb.projetopoo.model;
//
//import com.ifpb.projetopoo.Exception.DataInvalidaException;
//import com.ifpb.projetopoo.dao.GerenciaDao;
//
//
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///** A classe Gerencia contêm a modelagem de comanda para o dominio.
// * A Classe contêm um ArrayList de Pedidos.
// * Adiciona Pedidos e verifica o atendimento.
// * @author Antonio Miguel
// * @author Laires Pereira
// * @version 1.0
// * @since 29-07-2018
// */
//
//public class Gerencia implements Serializable {
//
//
//    /**
//     * CONSTRUTOR
//     */
//    private Set<Comanda> comandas;
//
//    public Gerencia(){
//        comandas = new HashSet<>();
//    }
//
//    public void addComanda(Comanda comanda) {
//        comandas.add(comanda);
//    }
//
//
//    public Set<Comanda> getComandas() {
//        return comandas;
//    }
//
//    public void setComandas(Set<Comanda> comandas) {
//        comandas = GerenciaDao.getComandas();
//    }
//
//    /**Metodo que recupera as comandas num certo periodo.
//     * @param comeco recebe um data inicial.
//     * @param fim recebe uma data final.
//     * @return retorna uma String das comandas do certo periodo.
//     */
//    public String betweenn (LocalDate comeco, LocalDate fim) throws DataInvalidaException {
//        if(!comeco.isBefore(fim)) throw new DataInvalidaException("Data inicio maior que a data fim, tente de novo!");
//        String resposta = "|";
//        for (Comanda comanda: comandas){
//            if((comanda.getData().compareTo(comeco)>=0)&&(comanda.getData().compareTo(fim)<=0)){
//                resposta+=comanda;
//            }
//        }return resposta;
//    }
//
//
//
//}
