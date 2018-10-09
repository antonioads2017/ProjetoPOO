package com.ifpb.projetopoo.view;


import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.dao.ProdutoDao;
import com.ifpb.projetopoo.model.*;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class TelaFazePedido extends JDialog{
    private JPanel contentPanel;
    private JSpinner quantidadeSpinner1;
    private JButton pedirButton;
    private JButton voltarButton;
    private JList listProdutos;
    private static int numMesa = 0;

    private Pedido pedido;

    public TelaFazePedido(){
        setContentPane(contentPanel);
        setTitle("Fazer Pedidos");
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        pedirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pedido = (String)listProdutos.getSelectedValue();
                try {
                    Pedido pd = new Pedido(ProdutoDao.ConsultaProduto(Integer.parseInt(pedido.split("-")[0])),(int)quantidadeSpinner1.getValue());
                    if(GerenciarMesa.fazPedido(numMesa,pd)){
                        JOptionPane.showMessageDialog(null,"Pedido efetuado");
                    }else {
                        JOptionPane.showMessageDialog(null,"Pedido não foi efetuado","Erro",JOptionPane.ERROR_MESSAGE);
                    }
                }catch (CodigoInvalidoException e1){
                    JOptionPane.showMessageDialog(null,"Quantidade tem ser um valor positivo","Erro",JOptionPane.ERROR_MESSAGE);
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null,"Erro na ligação com o arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                }catch (ClassNotFoundException cx){
                    JOptionPane.showMessageDialog(null,"Erro na classe do arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }

    public static void setNumMesa(int mesa) {
        numMesa = mesa;
    }

    private void createUIComponents() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        try{
            for(Produto p: ProdutoDao.pegarProdutos()){
                listModel.addElement(p.getCodigo()+"-"+p.getNome());
            }
        }catch (ClassNotFoundException | IOException ex){
            JOptionPane.showMessageDialog(null, "Falha na conexão com o arquivo", "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        listProdutos = new JList();
        listProdutos.setModel(listModel);
        listProdutos.setSelectedIndex(0);
        listProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        quantidadeSpinner1 = new JSpinner();
        quantidadeSpinner1.setModel(new SpinnerNumberModel(1,1,null,1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)quantidadeSpinner1.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
    }


}
