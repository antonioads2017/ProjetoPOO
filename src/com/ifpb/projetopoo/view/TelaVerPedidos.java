package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.model.GerenciarMesa;
import com.ifpb.projetopoo.model.Pedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

public class TelaVerPedidos extends JDialog{
    private JPanel contentPanel;
    private JList listPedidos;
    private JButton OKButton;
    private JButton editarButton;
    private static int numMesa=0;

    public TelaVerPedidos(){
        setContentPane(contentPanel);
        setTitle("Ver Pedidos");
        setModal(true);
        setLocationRelativeTo(null);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GerenciarMesa.verPedidos(numMesa)==null){
                    JOptionPane.showMessageDialog(null,"Não existe pedidos nessa mesa","Erro",JOptionPane.ERROR_MESSAGE);
                }else {
                    try{
                        if(GerenciarMesa.pegaComanda(numMesa).getPedido
                                (Integer.parseInt(listPedidos.getSelectedValue().toString().split("-")[0])).isAtendido()){
                            JOptionPane.showMessageDialog(null,"Pedido atendido, não permitido a edição","Erro",JOptionPane.ERROR_MESSAGE);
                        }else{
                            TelaAlterarPedido.setId(Integer.parseInt(listPedidos.getSelectedValue().toString().split("-")[0]));
                            TelaAlterarPedido telaAlterarPedido = new TelaAlterarPedido();
                            telaAlterarPedido.pack();
                            telaAlterarPedido.setVisible(true);
                            updatePedidos();
                        }
                    }catch (NullPointerException e1){
                        JOptionPane.showMessageDialog(null,"Nenhuma pedido selecionado","Erro",JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }

    public static void setNumMesa(int mesa){
        numMesa = mesa;
    }

    public static int getNumMesa(){
        return numMesa;
    }

    private void updatePedidos(){
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<Pedido> pedidos = GerenciarMesa.verPedidos(numMesa);
        if(pedidos!=null){
            DecimalFormat fm = new DecimalFormat("0.00");
            for(Pedido p:pedidos) {
                listModel.addElement(p.getNumeroPedido()+"-"+p.getProduto().getNome()+"Quant.: "+p.getQuantidade()+" Subtotal:"+fm.format(p.getValorTotal()));
            }
            listPedidos.setModel(listModel);
        }else listPedidos.setModel(null);
    }

    private void createUIComponents() {
        listPedidos = new JList();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<Pedido> pedidos = GerenciarMesa.verPedidos(numMesa);
        if(pedidos!=null){
            DecimalFormat fm = new DecimalFormat("0.00");
            for(Pedido p:pedidos) {
                listModel.addElement(p.getNumeroPedido()+"-"+p.getProduto().getNome()+"Quant.: "+p.getQuantidade()+" Subtotal:"+fm.format(p.getValorTotal()));
            }
            listPedidos.setModel(listModel);
            listPedidos.setSelectedIndex(0);
            listPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
    }
}
