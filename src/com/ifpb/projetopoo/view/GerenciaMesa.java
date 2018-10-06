package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.dao.GerenciaDao;
import com.ifpb.projetopoo.model.GerenciarMesa;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.io.IOException;


public class GerenciaMesa extends JDialog {

    private JPanel contentPanel;
    private JSpinner mesaSpinner1;
    private JButton novaComandaButton;
    private JButton verPedidosButton;
    private JButton fazerPedidoButton;
    private JButton fecharComandaButton;
    private JButton voltarButton;
    private GerenciarMesa gerenciarMesa;
    private static int numMesa;
    private GerenciaDao gerencia;

    public GerenciaMesa(){
        setContentPane(contentPanel);
        setTitle("Gerenciar Mesas");
        setModal(true);
        gerenciarMesa = new GerenciarMesa();
        try {
            gerencia = new GerenciaDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
        novaComandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    numMesa = Integer.parseInt(mesaSpinner1.getValue().toString());
                try {
                    if(gerenciarMesa.abrirComanda(numMesa)){
                        JOptionPane.showMessageDialog(null,"Comanda aberta para mesa "+numMesa);
                    }
                } catch (CodigoInvalidoException e1) {
                    JOptionPane.showMessageDialog(null,"Numero de mesa Invalida","Erro",JOptionPane.ERROR_MESSAGE);
                }

            }
        });


        fazerPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numMesa=Integer.parseInt(mesaSpinner1.getValue().toString());
                TelaFazePedido telaFazePedido = new TelaFazePedido();
                telaFazePedido.pack();
                dispose();
                telaFazePedido.setVisible(true);

            }
        });
        verPedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaVerPedidos telaVerPedidos = new TelaVerPedidos();
                telaVerPedidos.pack();
                dispose();
                telaVerPedidos.setVisible(true);
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
        fecharComandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numMesa=Integer.parseInt(mesaSpinner1.getValue().toString());
                try {
                    if(gerenciarMesa.fecharComanda(numMesa,gerencia)){
                        JOptionPane.showMessageDialog(null,"Comanda fechada na mesa "+numMesa);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    public static int getMesa(){
        return numMesa;
    }

    private void createUIComponents() {
        mesaSpinner1 = new JSpinner();
        mesaSpinner1.setModel(new SpinnerNumberModel(1, 1, null, 1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) mesaSpinner1.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
    }
}
