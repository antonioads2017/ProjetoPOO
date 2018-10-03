package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.Exception.CPFInvalidoException;
import com.ifpb.projetopoo.dao.UsuarioDao;
import com.ifpb.projetopoo.model.Setor;
import com.ifpb.projetopoo.model.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaConta extends JDialog {
    private JPanel contentPanel;
    private JFormattedTextField emailTextField;
    private JPasswordField passwordField;
    private JFormattedTextField nomeTextField;
    private JFormattedTextField cpfTextField;
    private JFormattedTextField dataTextField;
    private JFormattedTextField telefoneTextField;
    private JComboBox setorComboBox;
    private JButton excluirButton;
    private JButton atualizarButton;
    private JButton buscarButton;
    private UsuarioDao usuarioDao;
    private Usuario usuario;
    private String login;
    private String senha;


    public TelaConta(){
        try {
            usuarioDao = new UsuarioDao();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setContentPane(contentPanel);
        setTitle("Minha Conta");
        setModal(true);
        Setor setor = (Setor) setorComboBox.getSelectedItem();

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login = emailTextField.getText();
                senha = new String(passwordField.getPassword());
                usuario = usuarioDao.consultarUsuario(login, senha);
                nomeTextField.setText(usuario.getNome());
                cpfTextField.setText(usuario.getCPF());
                setorComboBox.setSelectedItem(usuario.getSetor());

                telefoneTextField.setText(usuario.getTelefone());
                dataTextField.setText(new String(usuario.getDataNascimento().toString()));
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(usuarioDao.excluirUsuario(usuario.getEmail(),usuario.getSenha()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    usuarioDao.excluirUsuario(usuario.getEmail(),usuario.getSenha());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                login = emailTextField.getText();
                senha = new String(passwordField.getPassword());
                String nome = nomeTextField.getText();
                Setor setor = (Setor) setorComboBox.getSelectedItem();
                String cpf = cpfTextField.getText();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate nascimento = LocalDate.parse(dataTextField.getText(),formatter);
                String telefone = telefoneTextField.getText();
                Usuario usuarioNovo = new Usuario(login,senha,cpf,nome,nascimento,setor,telefone);

                try {
                    usuarioDao.cadastrarUsuario(usuarioNovo);
                }  catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                } catch (CPFInvalidoException e1) {
                    JOptionPane.showMessageDialog(null, "CPF INVÁLIDO","ERRO",JOptionPane.ERROR_MESSAGE);
                    cpfTextField.setBackground(Color.red);
                }
            }
        });
    }


    private void createUIComponents() throws ParseException {
        MaskFormatter formatter = new MaskFormatter("##/##/####");
        dataTextField = new JFormattedTextField();
        formatter.install(dataTextField);
        setorComboBox = new JComboBox(Setor.values());
    }
}
