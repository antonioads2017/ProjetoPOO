package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.Exception.CPFInvalidoException;
import com.ifpb.projetopoo.dao.UsuarioDao;
import com.ifpb.projetopoo.model.Setor;
import com.ifpb.projetopoo.model.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaCadastro extends JDialog {
    private JFormattedTextField EmailTextField;
    private JFormattedTextField NomeTextField;
    private JPasswordField passwordField;
    private JFormattedTextField CPFTextField;
    private JFormattedTextField DataTextField;
    private JComboBox SetorcomboBox;
    private JFormattedTextField TelefoneTextField;
    private JPanel contentPanel;
    private JButton limparButton;
    private JButton OKButton;
    private UsuarioDao usuarios;

    public TelaCadastro(){
        try {
            usuarios = new UsuarioDao();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Falha ao abrir Arquivo","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setContentPane(contentPanel);
        setTitle("Tela de Cadastro");
        setModal(true);
        getRootPane().setDefaultButton(OKButton);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = EmailTextField.getText();
                String senha = new String(passwordField.getPassword());
                String nome = NomeTextField.getText();
                Setor setor = (Setor) SetorcomboBox.getSelectedItem();
                String cpf = CPFTextField.getText();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate nascimento = LocalDate.parse(DataTextField.getText(),formatter);
                String telefone = TelefoneTextField.getText();
                Usuario usuario = new Usuario(email,senha,cpf,nome,nascimento,setor,telefone);

                try {
                    if(usuarios.cadastrarUsuario(usuario)){
                        JOptionPane.showMessageDialog(null,"Usuario cadastrado com sucesso");
                    }else{
                        JOptionPane.showMessageDialog(null, "Usuario ja existe","ERRO",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                } catch (CPFInvalidoException e1) {
                    JOptionPane.showMessageDialog(null, "CPF INVÁLIDO","ERRO",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    private void createUIComponents() {
        MaskFormatter formatter = null;

        try{
            formatter = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DataTextField = new JFormattedTextField();

        if(formatter!=null){
            formatter.install(DataTextField);
        }

        SetorcomboBox = new JComboBox(Setor.values());
    }
}
