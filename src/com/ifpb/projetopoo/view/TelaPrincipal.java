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
        gerenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaGerencia telaGerencia = new TelaGerencia();
                telaGerencia.pack();
                dispose();
                telaGerencia.setVisible(true);
            }
        });
        mesasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciaMesa gerenciaMesa = new GerenciaMesa();
                gerenciaMesa.pack();
                dispose();
                gerenciaMesa.setVisible(true);
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaProduto telaProduto = new TelaProduto();
                telaProduto.pack();
                dispose();
                telaProduto.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        logoImage = new JLabel();
        ImageIcon logo = new ImageIcon("imagens/logo.png");
        logoImage.setIcon(logo);
    }
}
