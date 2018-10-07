package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.Exception.PrecoInvalidoException;
import com.ifpb.projetopoo.dao.ProdutoDao;
import com.ifpb.projetopoo.model.Produto;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Produto produto;
    private TelaPrincipal principal;

    public TelaProduto(){
        setContentPane(contentPanel);
        setTitle("Gerencia de Produtos");
        setModal(true);


        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeTextField.getText();
                String descricao = descricaoTextField.getText();
                float preco = Float.parseFloat(precoTextField.getText().replace(',','.'));
                try {
                    produto = new Produto((int)codigoSpinner.getValue(),nome,descricao,preco);
                } catch (CodigoInvalidoException e1) {
                    JOptionPane.showMessageDialog(null,"Codigo duplicado!","Erro",JOptionPane.ERROR_MESSAGE);
                    codigoSpinner.setBackground(Color.red);
                } catch (PrecoInvalidoException e1) {
                    e1.printStackTrace();
                }
                try{
                    if(ProdutoDao.AddProduto(produto)){
                        JOptionPane.showMessageDialog(null,"Produto inserido com sucesso");
                        limpar();
                    }else{
                        JOptionPane.showMessageDialog(null,"Produto ja existe!","Erro",JOptionPane.ERROR_MESSAGE);
                        limpar();
                    }
                } catch (ClassNotFoundException | IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    produto = ProdutoDao.ConsultaProduto((int)codigoSpinner.getValue());

                }catch (ClassNotFoundException | IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                if(produto==null){
                    System.out.println((int)codigoSpinner.getValue());
                    nomeTextField.setText("");
                    descricaoTextField.setText("");
                    precoTextField.setText("0,00");
                    JOptionPane.showMessageDialog(null, "Produto com codigo "+(int)codigoSpinner.getValue()+" não existe!","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    nomeTextField.setText(produto.getNome());
                    descricaoTextField.setText(produto.getDescrição());
                    precoTextField.setText(new Float(produto.getPrecoUnico()).toString().replace('.', ','));
                }

            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(ProdutoDao.deletarProduto((int)codigoSpinner.getValue())){
                        JOptionPane.showMessageDialog(null,"Produto excluido com sucesso");
                        limpar();
                    }
                }catch (ClassNotFoundException | IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProdutoDao.deletarProduto((int)codigoSpinner.getValue());
                }catch (ClassNotFoundException | IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                }
                String nome = nomeTextField.getText();
                String descricao = descricaoTextField.getText();
                float preco = Float.parseFloat(precoTextField.getText().replace(',','.'));
                try {
                    produto = new Produto((int)codigoSpinner.getValue(),nome,descricao,preco);
                } catch (CodigoInvalidoException e1) {
                    e1.printStackTrace();
                } catch (PrecoInvalidoException e1) {
                    e1.printStackTrace();
                }
                try{
                    if(ProdutoDao.AddProduto(produto)){
                        JOptionPane.showMessageDialog(null,"Produto atualizado com sucesso");
                        limpar();
                    }
                } catch (ClassNotFoundException | IOException e1) {
                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    principal = new TelaPrincipal();
                    principal.pack();
                    dispose();
                    principal.setVisible(true);


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
