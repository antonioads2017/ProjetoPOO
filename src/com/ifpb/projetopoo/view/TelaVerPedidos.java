package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.model.GerenciarMesa;
import com.ifpb.projetopoo.model.Pedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaVerPedidos extends JDialog{
    private JPanel contentPanel;
    private JList listPedidos;
    private JTextField totaltextField1;
    private JButton OKButton;
    private JButton editarButton;
    private static int numMesa=0;

    public TelaVerPedidos(){
        setContentPane(contentPanel);
        setTitle("Ver Pedidos");
        setModal(true);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciaMesa gerenciaMesa = new GerenciaMesa();
                gerenciaMesa.pack();
                dispose();
                gerenciaMesa.setVisible(true);
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GerenciarMesa.verPedidos(GerenciaMesa.getMesa())==null){
                    JOptionPane.showMessageDialog(null,"Não existe pedidos nessa mesa","Erro",JOptionPane.ERROR_MESSAGE);
                }else {
                    TelaAlterarPedido.setId(Integer.parseInt(listPedidos.getSelectedValue().toString().split("-")[0]));
                    setVisible(false);
                    TelaAlterarPedido telaAlterarPedido = new TelaAlterarPedido();
                    telaAlterarPedido.pack();
                    telaAlterarPedido.setVisible(true);
                    setVisible(true);
                }
            }
        });
    }

    public static void setNumMesa(int mesa){
        numMesa = mesa;
    }

    public static int getNumMesa(){
        return GerenciaMesa.getMesa();
    }

    private void updatePedidos(){
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<Pedido> pedidos = GerenciarMesa.verPedidos(GerenciaMesa.getMesa());
        if(pedidos!=null){
            for(Pedido p:pedidos){
                listModel.addElement(p.getNumeroPedido()+"-"+p.getProduto().getNome()+" Subtotal R$ "+p.getValorTotal());
            }
            listPedidos.setModel(listModel);
        }else listPedidos.setModel(null);
    }

    private void createUIComponents() {
        listPedidos = new JList();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<Pedido> pedidos = GerenciarMesa.verPedidos(GerenciaMesa.getMesa());
        if(pedidos!=null){
            for(Pedido p:pedidos) {
                listModel.addElement(p.getNumeroPedido()+"-"+p.getProduto().getNome()+" Subtotal:"+p.getValorTotal());
            }
            listPedidos.setModel(listModel);
            listPedidos.setSelectedIndex(0);
            listPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
    }
}
