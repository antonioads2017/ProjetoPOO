package com.ifpb.projetopoo.view;


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


        pedirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pedido = (String) listProdutos.getSelectedValue();

                Pedido p = new Pedido(ProdutoDao.ConsultaProduto(Integer.parseInt(pedido.split("-")[0])), (int) quantidadeSpinner1.getValue());
                System.out.println(p);
                if (GerenciarMesa.fazPedido(numMesa, p)) {
                    JOptionPane.showMessageDialog(null, "Pedido efetuado com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Pedido não efetuado", "Erro", JOptionPane.ERROR_MESSAGE);
                }

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
