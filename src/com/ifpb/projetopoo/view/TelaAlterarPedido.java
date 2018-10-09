package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.dao.ProdutoDao;
import com.ifpb.projetopoo.model.GerenciarMesa;
import com.ifpb.projetopoo.model.Pedido;
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
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(GerenciarMesa.excluirPedido(TelaVerPedidos.getNumMesa(),id)){
                        JOptionPane.showMessageDialog(null,"Pedido cancelado!");
                        dispose();
                    }
                }catch (CodigoInvalidoException e1){
                    JOptionPane.showMessageDialog(null,"Numero da mesa deve ser positivo!","Erro",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Pedido newP = new Pedido(ProdutoDao.ConsultaProduto(Integer.parseInt
                            (listProdutos.getSelectedValue().toString().split("-")[0])),(int)quantidadeSpinner1.getValue());
                    if(newP.equals(GerenciarMesa.pegaComanda(TelaVerPedidos.getNumMesa()).getPedido(id))){
                        JOptionPane.showMessageDialog(null,"Não ocorreu alteração no pedido");
                    }else{
                        if(GerenciarMesa.modificarPedido(id,TelaVerPedidos.getNumMesa(),newP)){
                            JOptionPane.showMessageDialog(null,"Pedido modificado!");
                            dispose();
                        }
                    }
                }catch (CodigoInvalidoException e1){
                    JOptionPane.showMessageDialog(null,"Quantidade tem ser um valor positivo","Erro",JOptionPane.ERROR_MESSAGE);
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null,"Erro na ligação com o arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                }catch (ClassNotFoundException cx){
                    JOptionPane.showMessageDialog(null,"Erro na classe do arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Problema com a conversão do código do produto", "Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
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
