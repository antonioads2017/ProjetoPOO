package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.dao.UsuarioDao;
import com.ifpb.projetopoo.model.Usuario;

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
    private JLabel imageLogin;
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
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro telaCadastro = new TelaCadastro();
                telaCadastro.pack();
                dispose();
                telaCadastro.setVisible(true);
            }
        });
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = formattedTextField1.getText();
                String senha = new String(passwordField1.getPassword());
                Usuario usuario = null;
                usuario = usuarioDao.consultarUsuario(login,senha);

                if(usuario!=null){
                    if(usuarioDao.autenticarUsuario(login,senha)){
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.pack();
                        dispose();
                        principal.setVisible(true);
                        System.exit(0);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Usuario ou senha invalidos",
                                "Mensagem de erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Usuário não cadastrado",
                            "Mensagem de erro",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public static void main(String[] args) {
        TelaLogin dialog = new TelaLogin();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void createUIComponents() {
        imageLogin = new JLabel();

        ImageIcon iconLogin = new ImageIcon("imagens/imagemLogin.png");
        imageLogin.setIcon(iconLogin);
    }
}
