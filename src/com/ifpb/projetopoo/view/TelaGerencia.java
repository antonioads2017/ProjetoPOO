package com.ifpb.projetopoo.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGerencia extends JDialog {
    private JPanel contentPanel;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JTable table1;
    private JButton visualizarButton;
    private JButton voltarButton;

    public TelaGerencia(){
        setContentPane(contentPanel);
        setTitle("Gerência");
        setModal(true);
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
