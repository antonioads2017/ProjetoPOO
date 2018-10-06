package com.ifpb.projetopoo.view;

import javax.swing.*;

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
    }
}
