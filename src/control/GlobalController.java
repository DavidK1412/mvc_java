package control;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import model.LibroModel;
import acces.LibroDAO;
import view.viewMain;

import javax.swing.table.DefaultTableModel;

public class GlobalController implements ActionListener {
    //Instanciando clases
    LibroDAO libDAO = new LibroDAO();
    LibroModel lib = new LibroModel();
    viewMain view;
    DefaultTableModel model = new DefaultTableModel();
    ControlAdd controlAdd = new ControlAdd();
    //Constructor GlobalController, Instancia la vista/Interfaz y crea Listeners para cada botón
    public GlobalController(viewMain view){
        this.view = view;
        this.view.btnShowAll.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
        this.view.btnBorrar.addActionListener(this);
        this.view.btnActualizar.addActionListener(this);
        this.view.btnCrear.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    //CONTROLAR CADA VEZ QUE SE DA CLICK
        if(e.getSource() == this.view.getBtnShowAll()){
            System.out.println("Click");
            try{
                ControlClear.limpiarTabla(view);
                ControlListAll.listAll(view ,view.resultadoTable, model, libDAO); //Método para listar todos los elementos
                view.status.setForeground(Color.GREEN); //Coloreando mensajes
                view.status.setText("Busqueda realizada con éxito!");
                ControlClear.limpiarTF(view);
            }catch (Exception exp) {
                view.status.setForeground(Color.RED); //Coloreando mensajes
                view.status.setText("Error!");
                view.errorLabel.setForeground(Color.RED); //En caso de que hayan errores, se escriben en pantalla
                view.errorLabel.setText("Error!: " + exp.getMessage());
            }
        }else if(e.getSource() == this.view.getBtnCrear() ){
            System.out.println("Click");
            try {
                ControlClear.limpiarTabla(view);
                int errAdd = controlAdd.add(view);
                if(errAdd == 1) {
                    view.status.setForeground(Color.GREEN); //Coloreando mensajes
                    view.status.setText("Agregado con éxito!");
                }else{
                    view.status.setForeground(Color.RED); //Coloreando mensajes
                    view.status.setText("No se pudo agregar");
                }
                ControlClear.limpiarTF(view);
            }catch (Exception exc){
                view.status.setForeground(Color.RED); //Coloreando mensajes
                view.status.setText("Error!");
                view.errorLabel.setForeground(Color.RED); //En caso de que hayan errores, se escriben en pantalla
                view.errorLabel.setText("Error!: " + exc.getMessage());
            }
        }else if(e.getSource() == this.view.getBtnBuscar()){
            System.out.println("Click");
        }else if(e.getSource() == this.view.getBtnActualizar()){
            System.out.println("Click");
        }else if(e.getSource() == this.view.getBtnBorrar()){
            System.out.println("Click");
        }
    }


}
