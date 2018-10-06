package com.ifpb.projetopoo.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCozinha extends JDialog {
    private JPanel contentPanel;
    private JButton atenderButton;
    private JButton voltarButton;
    private JComboBox pedidosComboBox;

    public TelaCozinha(){
        setContentPane(contentPanel);
        setModal(true);
        setTitle("Cozinha");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.pack();
                dispose();
                telaPrincipal.setVisible(true);
            }
        });
    }
}
