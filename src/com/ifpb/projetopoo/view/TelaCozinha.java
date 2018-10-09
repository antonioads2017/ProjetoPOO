package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.model.Comanda;
import com.ifpb.projetopoo.model.GerenciarMesa;
import com.ifpb.projetopoo.model.Pedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCozinha extends JDialog {
    private JPanel contentPanel;
    private JButton atenderButton;
    private JButton voltarButton;
    private JComboBox pedidosComboBox;

    public TelaCozinha(){
        setContentPane(contentPanel);
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cozinha");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.pack();
                dispose();
                telaPrincipal.setVisible(true);
            }
        });
        atenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String[] pedido = ((String)pedidosComboBox.getSelectedItem()).split(" ");
                    try {
                        if(GerenciarMesa.atendePedido(Integer.parseInt(pedido[4]),Integer.parseInt(pedido[1]))){
                            JOptionPane.showMessageDialog(null,"Pedido atendido");
                            atualizarPedidos();
                        }else{
                            JOptionPane.showMessageDialog(null,"Pedido não atendido","Erro",JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(ArrayIndexOutOfBoundsException | NullPointerException ex){
                        JOptionPane.showMessageDialog(null,
                                "Erro na hora de seelcionar o pedido para atender!","Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(null,
                            "Não existem pedidos para se atender!","Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void atualizarPedidos(){
        pedidosComboBox.removeItemAt(pedidosComboBox.getSelectedIndex());
    }

    private void createUIComponents() {
       if(GerenciarMesa.quantPedidosNaoAtendidos()>0){
           String[] pedidos = new String[GerenciarMesa.quantPedidosNaoAtendidos()];
           int quantidade=0;
           for(Comanda comanda: GerenciarMesa.getMesas()){
               for(Pedido pedido : comanda.getComanda()){
                   if(!pedido.isAtendido()){
                       pedidos[quantidade++]="Pedido "+pedido.getNumeroPedido()+" | Mesa "+comanda.getNumeroMesa();
                   }
               }
           }pedidosComboBox = new JComboBox(pedidos);
       }else {
           pedidosComboBox = new JComboBox();
       }
    }
}
