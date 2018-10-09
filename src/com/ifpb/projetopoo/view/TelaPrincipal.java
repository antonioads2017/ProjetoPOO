package com.ifpb.projetopoo.view;

import javax.swing.*;
import java.awt.*;
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
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(0,0,1366,768);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.pack();
                dispose();
                telaLogin.setVisible(true);
            }
        });
        minhaContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConta telaConta = new TelaConta();
                telaConta.pack();
                telaConta.setVisible(true);
            }
        });
        gerenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaGerencia telaGerencia = new TelaGerencia();
                telaGerencia.pack();
                telaGerencia.setVisible(true);
            }
        });
        mesasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciaMesa gerenciaMesa = new GerenciaMesa();
                gerenciaMesa.pack();
                gerenciaMesa.setVisible(true);
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaProduto telaProduto = new TelaProduto();
                telaProduto.pack();
                telaProduto.setVisible(true);
            }
        });
        cozinhaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCozinha telaCozinha = new TelaCozinha();
                telaCozinha.pack();
                telaCozinha.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        logoImage = new JLabel();
        ImageIcon logo = new ImageIcon("imagens/logo.png");
        logoImage.setIcon(logo);
    }
}
