package com.ifpb.projetopoo.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaVerPedidos telaVerPedidos = new TelaVerPedidos();
                telaVerPedidos.pack();
                dispose();
                telaVerPedidos.setVisible(true);
            }
        });
    }
}
