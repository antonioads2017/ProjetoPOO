package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.Exception.PrecoInvalidoException;
import com.ifpb.projetopoo.dao.ProdutoDao;
import com.ifpb.projetopoo.model.Produto;
import com.ifpb.projetopoo.view.FloatField;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;


public class TelaProduto extends JDialog{
    private JPanel contentPanel;
    private JSpinner codigoSpinner;
    private JFormattedTextField nomeTextField;
    private JFormattedTextField descricaoTextField;
    private JTextField precoTextField;
    private JButton buscarButton;
    private JButton excluirButton;
    private JButton adicionarButton;
    private JButton atualizarButton;
    private JButton sairButton;
    private ProdutoDao produtoDao;
    private int codigo;
    private Produto produto;
    private TelaPrincipal principal;

    public TelaProduto(){
        setContentPane(contentPanel);
        setTitle("Gerencia de Produtos");
        setModal(true);

        try {
            produtoDao = new ProdutoDao();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigo = Integer.parseInt(codigoSpinner.getValue().toString());
                String nome = nomeTextField.getText();
                String descricao = descricaoTextField.getText();
                float preco = Float.parseFloat(precoTextField.getText().replace(',','.'));
                try {
                    produto = new Produto(codigo,nome,descricao,preco);
                } catch (CodigoInvalidoException e1) {
                    JOptionPane.showMessageDialog(null,"Codigo duplicado!","Erro",JOptionPane.ERROR_MESSAGE);
                    codigoSpinner.setBackground(Color.red);
                } catch (PrecoInvalidoException e1) {
                    e1.printStackTrace();
                }
                try{
                    if(produtoDao.AddProduto(produto)){
                        JOptionPane.showMessageDialog(null,"Produto inserido com sucesso");
                        limpar();
                    }else{
                        JOptionPane.showMessageDialog(null,"Produto ja existe!","Erro",JOptionPane.ERROR_MESSAGE);
                        limpar();
                    }
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigo = Integer.parseInt(codigoSpinner.getValue().toString());
                produto = produtoDao.ConsultaProduto(codigo);
                if(produtoDao.ConsultaProduto(codigo)==null){
                    nomeTextField.setText("");
                    descricaoTextField.setText("");
                    precoTextField.setText("0,00");
                    JOptionPane.showMessageDialog(null, "Produto com codigo "+codigo+" não existe!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    nomeTextField.setText(produto.getNome());
                    descricaoTextField.setText(produto.getDescrição());
                    precoTextField.setText(String.valueOf(produto.getPrecoUnico()).replace('.', ','));
                }
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigo = Integer.parseInt(codigoSpinner.getValue().toString());
                try {
                    if(produtoDao.deletarProduto(codigo)){
                        JOptionPane.showMessageDialog(null,"Produto excluido com sucesso");
                        limpar();
                    }
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    produtoDao.deletarProduto(codigo);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                String nome = nomeTextField.getText();
                String descricao = descricaoTextField.getText();
                float preco = Float.parseFloat(precoTextField.getText().replace(',','.'));
                try {
                    produto = new Produto(codigo,nome,descricao,preco);
                } catch (CodigoInvalidoException e1) {
                    e1.printStackTrace();
                } catch (PrecoInvalidoException e1) {
                    e1.printStackTrace();
                }
                try{
                    if(produtoDao.AddProduto(produto)){
                        JOptionPane.showMessageDialog(null,"Produto atualizado com sucesso");
                        limpar();
                    }
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    principal = new TelaPrincipal();
                    principal.pack();
                    principal.setVisible(true);
                    dispose();

            }
        });
    }


    private void createUIComponents() {
        precoTextField = new FloatField(10,1000,2);
        precoTextField.setText("0,00");
        codigoSpinner = new JSpinner();
        codigoSpinner.setModel(new SpinnerNumberModel(1,1,null,1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)codigoSpinner.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
    }
    private void limpar(){
        nomeTextField.setText("");
        descricaoTextField.setText("");
        precoTextField.setText("");
    }
}
