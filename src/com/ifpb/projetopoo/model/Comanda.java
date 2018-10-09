package com.ifpb.projetopoo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;

/** A classe Comanda contêm a modelagem de comanda para o dominio.
 * A Classe contêm um ArrayList de Pedidos.
 * Adiciona Pedidos e verifica o atendimento.
 * @author Antonio Miguel
 * @version 1.1
 * @since 09-10-2018
 */

public class Comanda implements Serializable {

    private List<Pedido> comanda;
    private int numeroMesa;
    private LocalDate data;
    private static int contador;
    private int numeroComanda;

    /**
     * Construtor
     * */

    public Comanda(int numeroMesa) throws CodigoInvalidoException {
        if(numeroMesa<=0){
            throw new CodigoInvalidoException("Numero deve ser positivo");
        }
        numeroComanda=++contador;
        comanda=new ArrayList<>();
        this.numeroMesa = numeroMesa;
        data=LocalDate.now();
    }

    public int getCod() {
        return contador;
    }

    public List<Pedido> getComanda() {
        return comanda;
    }

    public void setComanda(List<Pedido> comanda) {
        this.comanda = comanda;
    }


    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) throws CodigoInvalidoException {
        if(numeroMesa<=0){
            throw new CodigoInvalidoException("Numero deve ser positivo");
        }
        this.numeroMesa = numeroMesa;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getNumeroComanda() {
        return numeroComanda;
    }

    public void setNumeroComanda(int numeroComanda) {
        this.numeroComanda = getContador();
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Comanda.contador = contador;
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
     * Metodo para calcular o valor total da comanda.
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
        comanda.add(pedido);
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


    /**
     * Metodo que envia os pedidos nao atendidos.
     * @return pedidoList, retorna os pedidos na comanda, null se estiver sem pedido.
     */
    public List<Pedido> pedidosNaoAtendidos (){
        List<Pedido> pedidoList = new ArrayList<>();
        for(Pedido pedido:comanda){
            if(!pedido.isAtendido()){
                pedidoList.add(pedido);
            }
        }if(pedidoList.isEmpty()){
            return null;
        }return pedidoList;
    }

    /**
     * Metodo que adiciona pedido na comanda.
     * @param pedido metodo recebe um pedido.
     */
    public void adicionarPedido(Pedido pedido){
        comanda.add(pedido);
    }

    /**
     * Metodo que deleta o pedido na comanda.
     * @param pedido metoto recebe um pedido.
     */
    public void removerPedido(Pedido pedido){
        comanda.remove(pedido);
    }

    /**
     * Metodo que edita o pedido na comanda.
     * @param idPedidoAntigo metodo recebe o id do pedido.
     * @param novo metodo recebe o novo pedido.
     * @return true, se foi o id existe e substitue o pedido.
     * @return false, se o id não existe.
     */
    public boolean editarPedido(int idPedidoAntigo,Pedido novo){
        if(comanda.size()==0){
            return false;
        }else{
            for (Pedido p:comanda){
                if(p.getNumeroPedido()==idPedidoAntigo){
                    comanda.set(comanda.indexOf(p),novo);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodo para atender um pedido.
     * @param idPedido metodo recebe id do pedido.
     * @return true, se foi atendido.
     * @return false, se não foi atendido.
     */
    public boolean atendePedido(int idPedido){
        if(comanda.size()>0){
            for(Pedido pedido:comanda){
                if(pedido.getNumeroPedido()==idPedido){
                    pedido.setAtendido(true);
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        String saida = "Mesa Nº"+numeroMesa+" -- Comanda Nº"+numeroComanda+" --Data "+data+ "-- \n";
        for(Pedido pedido:comanda){
            saida+=pedido.toString();
        }return saida+= "Valor da Comanda="+getValorTotal()+"\n";

    }
}
