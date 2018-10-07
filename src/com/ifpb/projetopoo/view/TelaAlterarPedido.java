package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.dao.ProdutoDao;
import com.ifpb.projetopoo.model.GerenciarMesa;
import com.ifpb.projetopoo.model.Produto;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TelaAlterarPedido extends JDialog{
    private JPanel contentPanel;
    private JSpinner quantidadeSpinner1;
    private JButton modificarButton;
    private JButton excluirButton;
    private JButton voltarButton;
    private JList listProdutos;
    private static int id;

    public TelaAlterarPedido(){
        setContentPane(contentPanel);
        setTitle("Alterar Pedido");
        setModal(true);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaVerPedidos telaVerPedidos = new TelaVerPedidos();
                telaVerPedidos.pack();
                dispose();
                telaVerPedidos.setVisible(true);
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GerenciarMesa.excluirPedido(GerenciaMesa.getMesa(),id)){
                    JOptionPane.showMessageDialog(null, "Pedido excluido com sucesso!", "Mensagem de Confirmação",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            }
        });
    }

    public static void setId(int idPedido){
        id=idPedido;
    }

    private void createUIComponents() {
        quantidadeSpinner1 = new JSpinner();
        quantidadeSpinner1.setModel(new SpinnerNumberModel(1,1,null,1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)quantidadeSpinner1.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        try{
            for(Produto p: ProdutoDao.pegarProdutos()){
                listModel.addElement(p.getCodigo()+"-"+p.getNome());

            }
            listProdutos = new JList();
            listProdutos.setModel(listModel);
            listProdutos.setSelectedIndex(0);
            listProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }catch (ClassNotFoundException | IOException ex){
            JOptionPane.showMessageDialog(null, "Falha na conexão com o arquivo", "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
