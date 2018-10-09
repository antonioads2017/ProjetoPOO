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
    private JButton voltarButton;
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
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Setor setor = (Setor) setorComboBox.getSelectedItem();

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login = emailTextField.getText();
                senha = new String(passwordField.getPassword());
                usuario = usuarioDao.consultarUsuario(login, senha);
                try {
                nomeTextField.setText(usuario.getNome());
                cpfTextField.setText(usuario.getCPF());
                setorComboBox.setSelectedItem(usuario.getSetor());
                telefoneTextField.setText(usuario.getTelefone());

                    String[] dataN = usuario.getDataNascimento().toString().split("-");
                    dataTextField.setText(dataN[2]+dataN[1]+dataN[0]);
                } catch (DataInvalidaException e1) {
                    JOptionPane.showMessageDialog(null, "DATA INVALIDA","ERRO",JOptionPane.ERROR_MESSAGE);
                }catch (NullPointerException e2){
                    JOptionPane.showMessageDialog(null,"Email e senha incorretos, corrija-os e busque!","Erro",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   if(usuarioDao.excluirUsuario(usuario.getEmail(),usuario.getSenha())){
                       JOptionPane.showMessageDialog(null,"Usuario excluido com sucesso");
                   }
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"Erro no arquivo usuario","Erro",JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(null,"Erro no arquivo usuario","Erro",JOptionPane.ERROR_MESSAGE);
                }
                login = emailTextField.getText();
                senha = new String(passwordField.getPassword());
                String nome = nomeTextField.getText();
                Setor setor = (Setor) setorComboBox.getSelectedItem();
                String cpf = cpfTextField.getText();
                LocalDate nascimento = null;
                try{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    nascimento = LocalDate.parse(dataTextField.getText(),formatter);
                }catch (DateTimeParseException e1){
                    JOptionPane.showMessageDialog(null,"Formato de Data incorreta","ERRO",JOptionPane.ERROR_MESSAGE);
                }

                String telefone = telefoneTextField.getText();


                try {
                    Usuario usuarioNovo = new Usuario(login,senha,cpf,nome,nascimento,setor,telefone);
                    usuarioDao.cadastrarUsuario(usuarioNovo);
                    JOptionPane.showMessageDialog(null,"Usuario atualizado");
                }  catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                } catch (CPFInvalidoException e1) {
                    JOptionPane.showMessageDialog(null, "CPF INVÁLIDO","ERRO",JOptionPane.ERROR_MESSAGE);
                    cpfTextField.setBackground(Color.red);
                }catch (DataInvalidaException e2){
                    JOptionPane.showMessageDialog(null, "DATA INVALIDA","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.pack();
                dispose();
                telaPrincipal.setVisible(true);
            }
        });
    }


    private void createUIComponents() throws ParseException {
        MaskFormatter formatter = new MaskFormatter("##/##/####");
        dataTextField = new JFormattedTextField();
        formatter.install(dataTextField);
        setorComboBox = new JComboBox(Setor.values());
        MaskFormatter formatter2 = new MaskFormatter("(##)#####-####");
        telefoneTextField = new JFormattedTextField();
        formatter2.install(telefoneTextField);
    }
}
