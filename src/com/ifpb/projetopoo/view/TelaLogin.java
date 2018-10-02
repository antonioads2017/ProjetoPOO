package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.dao.UsuarioDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TelaLogin extends JFrame {
    private JPanel contentPane;
    private JButton LoginButton;
    private JButton CadastrarButton;
    private JFormattedTextField formattedTextField1;
    private JPasswordField passwordField1;
    private UsuarioDao usuarioDao;

    public TelaLogin() {
        try {
            usuarioDao = new UsuarioDao();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setContentPane(contentPane);
        getRootPane().setDefaultButton(LoginButton);
        setTitle("Tela de Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro telaCadastro = new TelaCadastro();
                telaCadastro.pack();
                telaCadastro.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        TelaLogin dialog = new TelaLogin();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
