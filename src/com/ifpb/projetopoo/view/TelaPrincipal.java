package com.ifpb.projetopoo.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JDialog {
    private JLabel logoImage;
    private JButton gerenciaButton;
    private JButton menuButton;
    private JButton minhaContaButton;
    private JButton cozinhaButton;
    private JButton sairButton;
    private JButton mesasButton;
    private JPanel contentPanel;

    public TelaPrincipal(){
        setContentPane(contentPanel);
        setTitle("Tela Principal");
        setModal(true);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        minhaContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConta telaConta = new TelaConta();
                telaConta.pack();
                dispose();
                telaConta.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        logoImage = new JLabel();
        ImageIcon logo = new ImageIcon("imagens/logo.png");
        logoImage.setIcon(logo);
    }
}
