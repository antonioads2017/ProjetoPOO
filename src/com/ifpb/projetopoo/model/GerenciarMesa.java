package com.ifpb.projetopoo.model;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.dao.GerenciaDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/** A classe GerenciarMesa contêm a modelagem de metodos para gerenciar
 * comandas e pedidos dos clientes.
 * A Classe contêm um ArrayList de Comanda.
 * @author Antonio Miguel
 * @author Laires Pereira
 * @version 1.0
 * @since 29-07-2018
 */

public class GerenciarMesa {




    private List<Comanda> mesas;
    private Cozinha cozinha;

    /**
     * Construtor
     * */

    public GerenciarMesa() {
        mesas = new ArrayList<Comanda>();
    }

    public List<Comanda> getMesas() {
        return mesas;
    }

    public void setMesas(List<Comanda> mesas) {
        this.mesas = mesas;
    }

    /**
     * Este metodo retorna uma comanda aberta pelo numero da mesa
     * recebido por parametro
     *
     * @param numeroMesa : numero inteiro que representa uma mesa única.
     * @return retorna a comanda caso a encontre
     * @return retorna null caso não encontre
     * */

    public Comanda pegaComanda (int numeroMesa){
        for (Comanda comanda: mesas){
            if(comanda.getNumeroMesa()==numeroMesa){
                return comanda;
            }
        }return null;
    }

    /**
     * O metodo abrirComanda recebe o numero da mesa e abre uma comanda
     * caso ainda não exista
     * @param numeroMesa : numero inteiro que representa uma mesa única.
     * @return retorna a true caso a encontre e abre a comanda se ja não houver nenhuma
     * @return retorna null caso não encontre ou ja haja uma comanda aberta
     * */

    public boolean abrirComanda(int numeroMesa) throws CodigoInvalidoException {
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
        if(!temComanda){
            Comanda comanda= new Comanda(numeroMesa);
            mesas.add(comanda);
            return true;
        }
        else return false;
    }


    /**
     * O metodo editarPedido recebe os parametros para edição
     * de um pedido ja feito, caso seja necessario
     *
     * @param idPedido numero inteiro que representa o pedido a ser editado
     * @param numeroMesa numero da mesa onde esta o pedido
     * @param novoPedido novo pedido que irá substituir o antigo
     * @return true se conseguir editar
     * @return false caso não consiga editar
     * */

    public boolean editarPedido(int numeroMesa, int idPedido, Pedido novoPedido) {
        for(Comanda comanda: mesas) {
            if(comanda.getNumeroMesa() == numeroMesa) {
                List<Pedido> mesa = comanda.getComanda();
                // Editar
                for(Pedido pedido: mesa) {
                    if(pedido.getId() == idPedido) {
                        int index = mesa.indexOf(pedido);
                        mesa.add(index,pedido);
                        return true;
                    }
                }

            }
        } return false;
    }


    public void pedir(int numeroMesa, Pedido pedido){
        for (Comanda comanda: mesas){
            comanda.addPedido(pedido);
        }
    }

    /**
     * Exibe os pedidos de uma mesa
     * @param numeroMesa identifica a mesa que se deseja ver os pedidos
     * */
     public void viewPedido(int numeroMesa) {
         for (Comanda comanda : mesas) {
             if (comanda.getNumeroMesa() == numeroMesa) {
                 System.out.println(comanda.toString());
             }
         }
     }

    /**
     * Este metodo cria um pedido para uma mesa específica
     * @param numeroMesa identifica a mesa que fez o pedido
     * @param pedido contém as informações das escolhas do cliente
     * @return true se conseguir abrir o pedido
     * @return  false se não conseguir abrir o pedido
     *
     * */

     public boolean fazPedido(int numeroMesa, Pedido pedido){
         cozinha = new Cozinha();
        for(Comanda comanda:mesas){
            if(comanda.getNumeroMesa()==numeroMesa){
                comanda.addPedido(pedido);
                cozinha.addPedido(pedido);
                return true;
            }break;
        }return false;
     }

     /**
      * O metodo fecharComanda encerra a comanda de uma mesa
      * se todos os pedidos ja foram encerrados
      *
      * @param numeroMesa recebe o numero da mesa que vai fechar a comanda
      * @return true se todos os pedidos ja foram atendidos e a comanda pode ser fechada
      * @return false se ainda houverem pedidos abertos e não fechar a comanda
      *
      * */

     public boolean fecharComanda(int numeroMesa, GerenciaDao gerencia) throws IOException {
        int index=-1;
        for (Comanda comanda: mesas){
            if(comanda.getNumeroMesa()==numeroMesa) {
                if (comanda.Atendidos()) {
                    try {
                        gerencia.addComanda(comanda);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    index = mesas.indexOf(comanda);
                    System.out.println("Valor Total: " + comanda.getValorTotal());
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

     public boolean modificarPedido (int idPedido, int numeroMesa, Pedido pedidoNovo){
        for(Comanda comanda:mesas){
            if(comanda.getNumeroMesa()==numeroMesa){
                List<Pedido> mesa = comanda.getComanda();
                for (Pedido pedido:mesa){
                    int index = mesa.indexOf(pedido);
                    mesa.add(index,pedidoNovo);
                    return true;
                }
            }
        }return false;
     }



}
