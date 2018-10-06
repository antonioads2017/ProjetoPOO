package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.Exception.PrecoInvalidoException;
import com.ifpb.projetopoo.dao.ProdutoDao;
import com.ifpb.projetopoo.model.Cozinha;
import com.ifpb.projetopoo.model.GerenciarMesa;
import com.ifpb.projetopoo.model.Pedido;
import com.ifpb.projetopoo.model.Produto;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class TelaFazePedido extends JDialog{
    private JPanel contentPanel;
    private JSpinner quantidadeSpinner1;
    private JButton pedirButton;
    private JComboBox produtoscomboBox;
    private JButton voltarButton;
    private ProdutoDao produtos;
    private GerenciarMesa gerenciarMesa;
    private Cozinha cozinha;
    private ProdutoDao produtoDao;
    private Pedido pedido;

    public TelaFazePedido(){
        cozinha = new Cozinha();
        setContentPane(contentPanel);
        setTitle("Fazer Pedidos");
        setModal(true);
        gerenciarMesa = new GerenciarMesa();
        try {
            produtos = new ProdutoDao();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            for (Produto p : produtos.pegarProdutos()) {
                produtoscomboBox.addItem(p);
            }
        }catch( ClassNotFoundException | IOException ex){
            JOptionPane.showMessageDialog(null,"Erro na listagem dos produtos","Erro",JOptionPane.ERROR_MESSAGE);
        }


        pedirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedido = (new Pedido(((Produto) produtoscomboBox.getSelectedItem()),
                        Integer.parseInt(quantidadeSpinner1.getValue().toString())));
                System.out.println(pedido);
                if(gerenciarMesa.fazPedido(GerenciaMesa.getMesa(),pedido)){
                        JOptionPane.showMessageDialog(null,"Pedido adicionado com sucesso");
                    }else{
                        JOptionPane.showMessageDialog(null,"Pedido nao adicionado","Erro",JOptionPane.ERROR_MESSAGE);
                    }
                System.out.println(gerenciarMesa.pegaComanda(GerenciaMesa.getMesa()));

            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciaMesa gerenciaMesa = new GerenciaMesa();
                gerenciaMesa.pack();
                dispose();
                gerenciaMesa.setVisible(true);
            }
        });
    }
    private void listarProdutos() throws IOException, ClassNotFoundException {
        Produto produtos[] = new Produto[produtoDao.pegarProdutos().size()];
        int k = 0;
        int selecao = 0;
        for(Produto produto : produtoDao.pegarProdutos()){
                produtos[k++] = produto;
                if(pedido!=null){
                    if(produto==pedido.getProduto()){
                        selecao=k-1;
                    }
                }

        }
    }

    private void createUIComponents() {
        quantidadeSpinner1 = new JSpinner();
        quantidadeSpinner1.setModel(new SpinnerNumberModel(1,1,null,1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)quantidadeSpinner1.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
    }


}
