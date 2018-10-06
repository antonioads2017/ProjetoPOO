package com.ifpb.projetopoo.view;

import javax.swing.*;

public class TelaAlterarPedido extends JDialog{
    private JPanel contentPanel;
    private JComboBox produtosComboBox;
    private JSpinner QuantidadeSpinner1;
    private JButton modificarButton;
    private JButton excluirButton;
    private JButton voltarButton;

    public TelaAlterarPedido(){
        setContentPane(contentPanel);
        setTitle("Alterar Pedido");
        setModal(true);
    }
}
