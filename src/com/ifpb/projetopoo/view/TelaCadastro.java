package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.Exception.CPFInvalidoException;
import com.ifpb.projetopoo.Exception.DataInvalidaException;
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
import java.time.format.DateTimeParseException;

public class TelaCadastro extends JDialog {
    private JFormattedTextField EmailTextField;
    private JFormattedTextField NomeTextField;
    private JPasswordField passwordField;
    private JFormattedTextField CPFTextField;
    private JFormattedTextField DataTextField;
    private JComboBox SetorcomboBox;
    private JFormattedTextField TelefoneTextField;
    private JPanel contentPanel;
    private JButton cancelarButton;
    private JButton OKButton;
    private JPanel botoesPanel;
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
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(OKButton);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = EmailTextField.getText();
                String senha = new String(passwordField.getPassword());
                String nome = NomeTextField.getText();
                Setor setor = (Setor) SetorcomboBox.getSelectedItem();
                String cpf = CPFTextField.getText();
                LocalDate nascimento = null;

                try{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    nascimento = LocalDate.parse(DataTextField.getText(),formatter);
                }catch (DateTimeParseException e1){
                    JOptionPane.showMessageDialog(null,"Formato de Data incorreta","ERRO",JOptionPane.ERROR_MESSAGE);
                }


                String telefone = TelefoneTextField.getText();

                if(email.equals("")||senha.equals("")||nome.equals("")||cpf.equals("")){
                    JOptionPane.showMessageDialog(null,"Preencha todos os campos","Aviso",JOptionPane.WARNING_MESSAGE);
                }else{
                    try {
                        Usuario usuario = new Usuario(email,senha,cpf,nome,nascimento,setor,telefone);
                        if(usuarios.cadastrarUsuario(usuario)){
                            JOptionPane.showMessageDialog(null,"Usuario cadastrado com sucesso");
                        }else{
                            JOptionPane.showMessageDialog(null, "Usuario ja existe","ERRO",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                    } catch (CPFInvalidoException e1) {
                        JOptionPane.showMessageDialog(null, "CPF INVÁLIDO","ERRO",JOptionPane.ERROR_MESSAGE);
                        CPFTextField.setBackground(Color.red);
                    }catch (DataInvalidaException e2){
                        JOptionPane.showMessageDialog(null, "DATA INVALIDA","ERRO",JOptionPane.ERROR_MESSAGE);
                    }

                }



            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.pack();
                dispose();
                telaLogin.setVisible(true);
            }
        });
    }

    private void createUIComponents() throws ParseException {

        MaskFormatter formatter = new MaskFormatter("##/##/####");
        DataTextField = new JFormattedTextField();
        formatter.install(DataTextField);

        MaskFormatter cpfFormatter = new MaskFormatter("###.###.###-##");
        CPFTextField = new JFormattedTextField();
        cpfFormatter.install(CPFTextField);

        SetorcomboBox = new JComboBox(Setor.values());

        MaskFormatter formatter2 = new MaskFormatter("(##)#####-####");
        TelefoneTextField = new JFormattedTextField();
        formatter2.install(TelefoneTextField);
    }
}
