package com.ifpb.projetopoo.view;

import javax.swing.*;

public class TelaInicial extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JPanel panel1;

    public TelaInicial() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    private void createUIComponents() {
        panel1.setBounds(0,0,200,200);
    }
    public static void main(String[] args) {
        TelaInicial dialog = new TelaInicial();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


}
