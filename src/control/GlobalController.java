package control;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import model.LibroModel;
import acces.LibroDAO;
import view.viewMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GlobalController implements ActionListener {
    //Instanciando clases
    LibroDAO libDAO = new LibroDAO();
    LibroModel lib = new LibroModel();
    viewMain view;
    DefaultTableModel model = new DefaultTableModel();

    //Constructor GlobalController
    public GlobalController(viewMain view){
        this.view = view;
        this.view.btnShowAll.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    //CONTROLAR CADA VEZ QUE SE DA CLICK
        if(e.getSource() == this.view.getBtnShowAll()){
            try{
                listAll(view.resultadoTable);
                view.status.setForeground(Color.GREEN); //Coloreando mensajes
                view.status.setText("Operación realizada con éxito!");
            }catch (Exception exp){
                view.errorLabel.setForeground(Color.RED);
                view.errorLabel.setText("Error!: " + exp.getMessage());
            }
        }
    }

    //Método para el botón "Listar todos"
    public void listAll(JTable table){
        model = (DefaultTableModel)table.getModel();
        List<LibroModel> listAll = libDAO.listarTodos();
        Object[] obj = new Object[5];
        for (int i = 0; i < listAll.size(); i++){
            obj[0] = listAll.get(i).getId_fk();
            obj[1] = listAll.get(i).getTitulo();
            obj[2] = listAll.get(i).getLib_anio();
            obj[3] = listAll.get(i).getAutor();
            obj[4] = listAll.get(i).getAutor_id_fk();
            model.addRow(obj);
        }
        view.resultadoTable.setModel(model);
    }
}
