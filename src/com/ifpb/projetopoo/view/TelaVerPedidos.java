package com.ifpb.projetopoo.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciaMesa gerenciaMesa = new GerenciaMesa();
                gerenciaMesa.pack();
                dispose();
                gerenciaMesa.setVisible(true);
            }
        });
    }
}
