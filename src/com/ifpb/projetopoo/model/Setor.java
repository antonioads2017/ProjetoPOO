package com.ifpb.projetopoo.model;
/** A classe Pedido contêm a modelagem de pedidos dos clientes
 * @author Antonio
 * @since 09-10-2018
 * @version 1.1
 */
public enum Setor {

    ATENDIMENTO(1),
    COZINHA(2),
    CAIXA(3),
    GERENCIA(4);

    public final int codSetor;

    /**CONSTRUTOR
     * @param codSetor recebe o codigo do setor.
     */
    Setor (int codSetor){
        this.codSetor=codSetor;
    }

    public int getCodSetor() {
        return codSetor;
    }
}
