package com.ifpb.projetopoo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.ifpb.projetopoo.model.Pedido;

/** A classe Comanda contêm a modelagem de comanda para o dominio.
 * A Classe contêm um ArrayList de Pedidos.
 * Adiciona Pedidos e verifica o atendimento.
 * @author Antonio Miguel
 * @author Laires Pereira
 * @version 1.0
 * @since 29-07-2018
 */

public class Comanda implements Serializable {

    private List<Pedido> comanda;
    private int numeroMesa;
    private static LocalDate data;
    private static int cod;
    private static int numeroComanda;

    /**
     * Construtor
     * */

    public Comanda(int numeroMesa) {
        numeroComanda=++cod;
        comanda=new ArrayList<>();
        this.numeroMesa = numeroMesa;
        data=LocalDate.now();
    }

    public static int getCod() {
        return cod;
    }

    /**
     * Retorna a comanda
     * @return comanda
     */
    public List<Pedido> getComanda() {
        return comanda;
    }

    public void setComanda(List<Pedido> comanda) {
        this.comanda = comanda;
    }


    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public static LocalDate getData() {
        return data;
    }

    public static void setData(LocalDate data) {
        Comanda.data = data;
    }

    public static int getNumeroComanda() {
        return numeroComanda;
    }

    public static void setNumeroComanda(int numeroComanda) {
        Comanda.numeroComanda = numeroComanda;
    }

    /**
     * Procura pedido na comanda
     * @param idPedido = numero do id de um pedido
     * @return pedido, retorna um objeto pedido
     */
    public Pedido getPedido (int idPedido){
        for(Pedido pedido:comanda){
            if(pedido.getNumeroPedido()==idPedido){
                return pedido;
            }
        }return null;
    }

    /**
     * @return Valor Total retorna o valor total do pedido.
     */
    public float getValorTotal (){
         float ValorTotal=0;
         for (int i=0; i<comanda.size(); i++){
             ValorTotal+=comanda.get(i).getValorTotal();
         }
         return ValorTotal;

    }

    /**
     * Adiciona um pedido na comanda desejada.
     * @param pedido
     */
    public void addPedido(Pedido pedido){
        comanda.add(pedido);;
    }

    /**
     * Checa se todos foram atendidos
     * @return true, se todos foram atendidos.
     * @return false, se todos nao foram atendidos.
     */
    public boolean Atendidos(){
        boolean check = true;
        for (Pedido pedido: comanda){
            if(!pedido.isAtendido()){
                check=false;
                break;
            }
        }return check;
    }

    public String pedidosNaoAtendidos (){
        String s = "";
        for (Pedido pedido: comanda){
            if(!pedido.isAtendido()){
                s+=pedido.toString();
            }
        }
        if(s.equals("")){
            System.out.println("Todos os pedidos foram atendidos!");
        }return s;
    }


    @Override
    public String toString() {
        String saida = "Mesa Nº"+numeroMesa+" -- Comanda Nº"+numeroComanda+" --Data "+data+ "-- \n";
        for(Pedido pedido:comanda){
            saida+=pedido.toString();
        }saida+= "Valor da Comanda="+getValorTotal()+"\n";
        return saida;
    }
}
