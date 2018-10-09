package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.dao.GerenciaDao;
import com.ifpb.projetopoo.model.Comanda;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TelaGerencia extends JDialog {
    private JPanel contentPanel;
    private JFormattedTextField inicioTextField1;
    private JFormattedTextField fimTextField2;
    private JTable comandasTable1;
    private JButton visualizarButton;
    private JButton voltarButton;
    private JLabel calendarInicio;
    private JLabel calendarFim;
    private DefaultTableModel modelo;

    public TelaGerencia(){
        setContentPane(contentPanel);
        setTitle("Gerência");
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate dataFim = null;
                LocalDate dataInicio = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String inicio = inicioTextField1.getText();
                String fim = fimTextField2.getText();

                if(inicioTextField1.getText().length()<10 || fimTextField2.getText().length()<10){
                    JOptionPane.showMessageDialog(null,"Preencha todos os campos corretamente","Aviso",JOptionPane.WARNING_MESSAGE);
                }else{
                    try{
                        dataInicio = LocalDate.parse(inicio,formatter);
                        dataFim = LocalDate.parse(fim,formatter);
                        if(dataInicio.isAfter(dataFim)){
                            JOptionPane.showMessageDialog(null,"Intervalo incorreto, insera na ordem certa","Erro",JOptionPane.ERROR_MESSAGE);
                            limpar();
                        }else{
                            try{
                                ArrayList<Comanda> comandascloseds = GerenciaDao.between(dataInicio,dataFim);
                                if(comandascloseds.isEmpty()){
                                    JOptionPane.showMessageDialog(null,"Não contem comandas nesse intervalo","Aviso",JOptionPane.QUESTION_MESSAGE);
                                    limpar();
                                }else{
                                    limpar();
                                    for(Comanda comanda:comandascloseds){
                                        String[] quebraData = comanda.getData().toString().split("-");
                                        String data = quebraData[2]+"/"+quebraData[1]+"/"+quebraData[0];
                                        modelo.addRow(new String[]{data,"Comanda "+comanda.getNumeroComanda(),
                                                "R$ "+new DecimalFormat("0.00").format(comanda.getValorTotal())});
                                    }
                                }
                            }catch (IOException ex){
                                JOptionPane.showMessageDialog(null,"Erro na ligação com o arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                            }catch (ClassNotFoundException cx){
                                JOptionPane.showMessageDialog(null,"Erro na classe do arquivo","Erro",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }catch(NullPointerException ex){
                        JOptionPane.showMessageDialog(null,
                                "Problema com valor nulo", "Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }catch(DateTimeParseException ex ){
                        JOptionPane.showMessageDialog(null,
                                "Foi informada uma data inválida!", "Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    private void limpar(){
        int quantidadeLinhas = modelo.getRowCount();
        if(quantidadeLinhas>0){
            for(int i =0;i<quantidadeLinhas;i++){
                modelo.removeRow(0);
            }
        }
    }

    private void createUIComponents() {
        MaskFormatter formatter1 = null;
        try {
            formatter1 = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        inicioTextField1=new JFormattedTextField(formatter1);
        fimTextField2=new JFormattedTextField(formatter1);

        ImageIcon icon = new ImageIcon("imagens/calendar.png");
        calendarInicio = new JLabel(icon);
        calendarFim = new JLabel(icon);

        comandasTable1 = new JTable(){
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };

        comandasTable1.removeEditor();
        modelo = new DefaultTableModel();
        modelo.addColumn("Data");
        modelo.addColumn("Comanda");
        modelo.addColumn("Valor");
        comandasTable1.setModel(modelo);
    }
}
