package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.Exception.PrecoInvalidoException;
import com.ifpb.projetopoo.dao.ProdutoDao;
import com.ifpb.projetopoo.model.Produto;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
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

    public TelaProduto(){
        setContentPane(contentPanel);
        setModal(true);
        setTitle("Gerencia de Produtos");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(adicionarButton);
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(nomeTextField.equals("")||descricaoTextField.equals("")){
                   JOptionPane.showMessageDialog(null,"Por favor, preencha todos os campos!","Aviso",JOptionPane.QUESTION_MESSAGE);
               }else{
                   try{
                       if(ProdutoDao.ConsultaProduto((int)codigoSpinner.getValue())!=null){
                           JOptionPane.showMessageDialog(null,"Produto com codigo "+
                                   (int)codigoSpinner.getValue()+" já existe!","Erro",JOptionPane.ERROR_MESSAGE);
                       }else{
                           try{
                               Produto novinho = new Produto((int)codigoSpinner.getValue(),nomeTextField.getText(),descricaoTextField.getText(),
                                       Float.parseFloat(precoTextField.getText().replace(',','.')));
                               try{
                                   if(ProdutoDao.AddProduto(novinho)){
                                       JOptionPane.showMessageDialog(null,"Produto adicionado com sucesso!");
                                       System.out.println(novinho);
                                   }
                               }catch (IOException ex){
                                   JOptionPane.showMessageDialog(null,"Erro na ligação com o arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                               }catch (ClassNotFoundException cx){
                                   JOptionPane.showMessageDialog(null,"Erro na classe do arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                               }
                           }catch (PrecoInvalidoException px){
                               JOptionPane.showMessageDialog(null,"O preço deve ser um valor positivo","Erro",JOptionPane.ERROR_MESSAGE);
                           }catch (CodigoInvalidoException cx){
                               JOptionPane.showMessageDialog(null,"Código Invalido","Erro",JOptionPane.ERROR_MESSAGE);
                           }
                       }
                   }catch (IOException ex){
                       JOptionPane.showMessageDialog(null,"Erro na ligação com o arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                   }catch (ClassNotFoundException cx){
                       JOptionPane.showMessageDialog(null,"Erro na classe do arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                   }
               }
            }
        });


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Produto produto = ProdutoDao.ConsultaProduto((int)codigoSpinner.getValue());
                    System.out.println(produto);
                    if(produto==null){
                        JOptionPane.showMessageDialog(null,"O produto com codigo "+
                                (int)codigoSpinner.getValue()+" não existe!","Erro",JOptionPane.ERROR_MESSAGE);
                    }else{
                        nomeTextField.setText(produto.getNome());
                        descricaoTextField.setText(produto.getDescrição());
                        precoTextField.setText(new Float(produto.getPrecoUnico()).toString().replace('.',','));
                    }
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null,"Erro na ligação com o arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                }catch (ClassNotFoundException cx){
                    JOptionPane.showMessageDialog(null,"Erro na classe do arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto pDeletar = null;
                try {
                    pDeletar = ProdutoDao.ConsultaProduto((int)codigoSpinner.getValue());
                    if(pDeletar==null){
                        JOptionPane.showMessageDialog(null,"O produto com codigo "+
                                (int)codigoSpinner.getValue()+" não existe!","Erro",JOptionPane.ERROR_MESSAGE);
                        limpar();
                    }else{
                        if(ProdutoDao.deletarProduto((int)codigoSpinner.getValue())){
                            JOptionPane.showMessageDialog(null,"Produto excluído com sucesso!");
                            limpar();
                        }else {
                            JOptionPane.showMessageDialog(null, "Não foi possivel excluir o produto", "Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                            limpar();
                        }
                    }
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null,"Erro na ligação com o arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                }catch (ClassNotFoundException cx){
                    JOptionPane.showMessageDialog(null,"Erro na classe do arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

//        atualizarButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    ProdutoDao.deletarProduto((int)codigoSpinner.getValue());
//                }catch (ClassNotFoundException | IOException e1) {
//                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
//                }
//                String nome = nomeTextField.getText();
//                String descricao = descricaoTextField.getText();
//                float preco = Float.parseFloat(precoTextField.getText().replace(',','.'));
//                try {
//                    produto = new Produto((int)codigoSpinner.getValue(),nome,descricao,preco);
//                } catch (CodigoInvalidoException e1) {
//                    e1.printStackTrace();
//                } catch (PrecoInvalidoException e1) {
//                    e1.printStackTrace();
//                }
//                try{
//                    if(ProdutoDao.AddProduto(produto)){
//                        JOptionPane.showMessageDialog(null,"Produto atualizado com sucesso");
//                        limpar();
//                    }
//                } catch (ClassNotFoundException | IOException e1) {
//                    JOptionPane.showMessageDialog(null, "Arquivo nao encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    TelaPrincipal principal = new TelaPrincipal();
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
        precoTextField.setText("0,00");
    }
}
