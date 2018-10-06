package com.ifpb.projetopoo.view;

import javax.swing.*;

public class TelaVerPedidos extends JDialog{
    private JPanel contentPanel;
    private JList list1;
    private JTextField totaltextField1;
    private JButton OKButton;
    private JButton editarButton;

    public TelaVerPedidos(){
        setContentPane(contentPanel);
        setTitle("Ver Pedidos");
        setModal(true);
    }
}
