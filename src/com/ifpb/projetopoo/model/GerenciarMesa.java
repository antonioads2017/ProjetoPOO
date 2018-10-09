package com.ifpb.projetopoo.model;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.Exception.ComandaInvalidaException;
import com.ifpb.projetopoo.dao.GerenciaDao;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** A classe GerenciarMesa contêm a modelagem de metodos para gerenciar
 * comandas e pedidos dos clientes.
 * A Classe contêm um ArrayList de Comanda.
 * @author Antonio
 * @since 09-10-2018
 * @version 1.1
 */

public class GerenciarMesa {

    private static List<Comanda> mesas = new ArrayList<>();

    public static List<Comanda> getMesas() {
        return mesas;
    }

    /**
     * Este metodo retorna uma comanda pelo numero da mesa.
     * @param numeroMesa : numero inteiro que representa uma mesa única.
     * @return retorna a comanda caso a encontre
     * @return retorna null caso não encontre
     * */

    public static Comanda pegaComanda (int numeroMesa){
        for (Comanda comanda: mesas){
            if(comanda.getNumeroMesa()==numeroMesa){
                return comanda;
            }
        }return null;
    }

    /**
     * O metodo recebe o numero da mesa e abre uma comanda
     * caso ainda não exista
     * @param numeroMesa : numero inteiro que representa uma mesa única.
     * @return retorna a true caso a encontre e abre a comanda se ja não houver nenhuma
     * @return retorna null caso não encontre ou ja haja uma comanda aberta
     * */

    public static boolean abrirComanda(int numeroMesa) throws CodigoInvalidoException, ComandaInvalidaException {
        if(numeroMesa<=0){
            throw new CodigoInvalidoException("Numero de mesa invalida");
        }
        boolean temComanda = false;
        for(Comanda comanda: mesas){
            if(comanda.getNumeroMesa() == numeroMesa){
                temComanda=true;
                break;

            }
        }
        if(temComanda){
            throw new ComandaInvalidaException("Comanda já existe!");
        }
        else{
            mesas.add(new Comanda(numeroMesa));
            return true;
        }
    }


    /**
     * Metodo que faz um pedido dentro da comanda da mesa recebida e.
     * também adiciona na Cozinha.
     * @param numeroMesa recebe um numero inteiro da mesa.
     * @param pedido recebe um objeto do pedido.
     * @return true, se foi feito o pedido.
     * @return false, se não foi o pedido caso nao tenha comanda na mesa.
     */
     public static boolean fazPedido(int numeroMesa, Pedido pedido){
        for(Comanda comanda:mesas){
            if(comanda.getNumeroMesa()==numeroMesa){
                comanda.addPedido(pedido);
                Cozinha.addPedido(pedido);
                return true;
            }
        }return false;
     }

     /**
      * O metodo encerra a comanda de uma mesa
      * se todos os pedidos ja foram encerrados
      * @param numeroMesa recebe o numero da mesa que vai fechar a comanda
      * @return true se todos os pedidos ja foram atendidos e a comanda pode ser fechada
      * @return false se ainda houverem pedidos abertos e não fechar a comanda
      *
      * */

     public static boolean fecharComanda(int numeroMesa) throws IOException, ClassNotFoundException {
        int index=-1;
        for (Comanda comanda: mesas){
            if(comanda.getNumeroMesa()==numeroMesa) {
                if (comanda.Atendidos()) {
                        GerenciaDao.addComanda(comanda);
                    index = mesas.indexOf(comanda);
                    break;
                }
                else break;
            }
        }
         if(index!=-1){
            mesas.remove(index);
            return true;
         }else return false;
     }

     /**
      * O metodo modificarPedido recebe uma mesa e um pedido que precisam ser alterados
      * @param numeroMesa recebe a mesa que vai alterar o pedido
      * @param idPedido recebe a identificação do pedido a ser alterado
      * @param pedidoNovo recebe o novo pedido com as alterações feitas
      * @return true se conseguir alterar o pedido
      * @return  false se não conseguir localizar o pedido e alterá-lo
      * */

     public static boolean modificarPedido (int idPedido, int numeroMesa, Pedido pedidoNovo){
        for(Comanda comanda:mesas){
            if(comanda.getNumeroMesa()==numeroMesa){
                if(comanda.editarPedido(idPedido,pedidoNovo)){
                    return true;
                }
            }
        }return false;
     }

    /**
     * Metodo que mostra todos os pedidos da mesa.
     * @param numMesa recebe um inteiro para mesa.
     * @return a lista de pedidos da mesa.
     */
     public static List<Pedido> verPedidos(int numMesa){
         for(Comanda comanda: mesas){
             if(comanda.getNumeroMesa()==numMesa){
                 return comanda.getComanda();
             }
         }return null;
     }

    /**
     * Metodo que exclui o pedido da mesa.
     * @param numMesa recebe um inteiro para mesa.
     * @param idPedido recebe um inteiro para o id do pedido.
     * @return true se o pedido foi excluido.
     * @return false se o pedido nao foi excluido.
     * @throws CodigoInvalidoException é a exceção que trata se o numero da mesa é positivo.
     */
    public static boolean excluirPedido(int numMesa,int idPedido) throws CodigoInvalidoException {
         if(numMesa<=0){
             throw new CodigoInvalidoException("Numero de mesa deve ser positivo!");
         }
        List<Pedido> pedidos = verPedidos(numMesa);
        if(pedidos!=null){
            for (Pedido p: pedidos){
                if(p.getNumeroPedido()==idPedido){
                    pegaComanda(numMesa).removerPedido(p);
                    return true;
                }
            }
        }return false;
    }

    /**
     * Metodo que conta os pedidos de uma comanda da mesa.
     * @return valor dos pedidos.
     */
    public static int quantPedidosNaoAtendidos(){
        int quant = 0;
        for(Comanda comanda: mesas){
            for(Pedido pedido:comanda.getComanda()){
                if(!pedido.isAtendido()){
                    quant++;
                }
            }
        }
        return quant;
    }

    /**
     * Metodo que atende o pedido da comanda de uma mesa.
     * @param numMesa recebe um inteiro para a mesa.
     * @param idPedido recebe um inteiro para id do pedido.
     * @return true, se foi atendido
     * @return false, se não foi atendido
     */
    public static boolean atendePedido(int numMesa,int idPedido){
        for(Comanda comanda: mesas){
            if(comanda.getNumeroMesa()==numMesa){
                return comanda.atendePedido(idPedido);
            }
        }
        return false;
    }


}
