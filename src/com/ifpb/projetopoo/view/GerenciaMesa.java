package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.Exception.ComandaInvalidaException;
import com.ifpb.projetopoo.dao.GerenciaDao;
import com.ifpb.projetopoo.model.Comanda;
import com.ifpb.projetopoo.model.GerenciarMesa;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.io.IOException;
import java.text.DecimalFormat;


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

    public GerenciaMesa(){
        setContentPane(contentPanel);
        setTitle("Gerenciar Mesas");
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        gerenciarMesa = new GerenciarMesa();
        novaComandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   GerenciarMesa.abrirComanda((int)mesaSpinner1.getValue());
                   JOptionPane.showMessageDialog(null,"Comanda aberta para mesa "+
                           (int)mesaSpinner1.getValue());
               }catch (CodigoInvalidoException e1){
                   JOptionPane.showMessageDialog(null,"Numero de Mesa Invalido","Erro",JOptionPane.ERROR_MESSAGE);
               }catch (ComandaInvalidaException e2){
                   JOptionPane.showMessageDialog(null,"Ja existe uma comanda para mesa "+(int)mesaSpinner1.getValue(),"Erro",JOptionPane.ERROR_MESSAGE);
               }
            }
        });


        fazerPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GerenciarMesa.pegaComanda((int)mesaSpinner1.getValue())==null){
                    JOptionPane.showMessageDialog(null,"Não existe comanda aberta para mesa "+
                                    (int)mesaSpinner1.getValue(),"Erro",JOptionPane.ERROR_MESSAGE);
                }else {
                    TelaFazePedido.setNumMesa((int)mesaSpinner1.getValue());
                    TelaFazePedido telaFazePedido = new TelaFazePedido();
                    telaFazePedido.pack();
                    telaFazePedido.setVisible(true);
                }
            }
        });
        verPedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GerenciarMesa.pegaComanda((int)mesaSpinner1.getValue())==null){
                    JOptionPane.showMessageDialog(null,"Não existe comanda aberta para mesa "+
                            (int)mesaSpinner1.getValue(),"Erro",JOptionPane.ERROR_MESSAGE);
                }else{
                    TelaVerPedidos.setNumMesa((int)mesaSpinner1.getValue());
                    TelaVerPedidos telaVerPedidos = new TelaVerPedidos();
                    telaVerPedidos.pack();
                    telaVerPedidos.setVisible(true);
                }
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        fecharComandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comanda comanda = GerenciarMesa.pegaComanda((int)mesaSpinner1.getValue());
                if(GerenciarMesa.pegaComanda((int)mesaSpinner1.getValue())==null){
                    JOptionPane.showMessageDialog(null,"Não existe comanda aberta para mesa "+
                            (int)mesaSpinner1.getValue(),"Erro",JOptionPane.ERROR_MESSAGE);
                }else{
                    if(comanda.Atendidos()){
                        try {
                            if(GerenciarMesa.fecharComanda((int)mesaSpinner1.getValue())){
                                JOptionPane.showMessageDialog(null,
                                        "Comanda Encerrada com Sucesso!\nValor Total R$"+
                                                new DecimalFormat("0.00").format(comanda.getValorTotal()));
                            }
                        }catch (IOException ex){
                            JOptionPane.showMessageDialog(null,"Erro na ligação com o arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                        }catch (ClassNotFoundException cx){
                            JOptionPane.showMessageDialog(null,"Erro na classe do arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"A comanda contêm pedidos não atendidos","Erro",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }


    private void createUIComponents() {
        mesaSpinner1 = new JSpinner();
        mesaSpinner1.setModel(new SpinnerNumberModel(1, 1, null, 1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) mesaSpinner1.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
    }
}
