package control;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import model.LibroModel;
import access.LibroDAO;
import view.viewMain;

import javax.swing.table.DefaultTableModel;

public class GlobalController implements ActionListener {
    //Instanciando clases
    LibroDAO libDAO = new LibroDAO();
    LibroModel lib = new LibroModel();
    viewMain view;
    DefaultTableModel model = new DefaultTableModel();
    ControlAdd controlAdd = new ControlAdd();
    ControlSearch controlSearch = new ControlSearch();
    ControlDelete controlDelete = new ControlDelete();
    ControlUpdate controlUpdate = new ControlUpdate();
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
            try{
                ControlClear.limpiarTabla(view);
                ControlListAll.listAll(view ,view.resultadoTable, model, libDAO); //Método para listar todos los elementos
                anuncioPantalla(view, "Listado", 1);
                ControlClear.limpiarTF(view);
            }catch (Exception exp) {
                mostrarError(exp);
            }
        }else if(e.getSource() == this.view.getBtnCrear() ){
            try {
                ControlClear.limpiarTabla(view);
                int errAdd = controlAdd.add(view);
                anuncioPantalla(view, "Creado", errAdd);
                ControlListAll.listAll(view, view.resultadoTable, model, libDAO);
            }catch (Exception exc){
                mostrarError(exc);
            }
        }else if(e.getSource() == this.view.getBtnBuscar()){
            try {
                ControlClear.limpiarTabla(view);
                int errAdd =controlSearch.buscarLibro(view ,view.resultadoTable, model);
                anuncioPantalla(view, "Busqueda", errAdd);
            }catch (Exception exc){
                mostrarError(exc);
            }
        }else if(e.getSource() == this.view.getBtnActualizar()){
            try {
                ControlClear.limpiarTabla(view);
                int status = controlUpdate.delete(view);
                anuncioPantalla(view, "Actualizado", status);
                ControlListAll.listAll(view, view.resultadoTable, model, libDAO);
                ControlClear.limpiarTF(view);
            }catch (Exception exc){
                mostrarError(exc);
            }
        }else if(e.getSource() == this.view.getBtnBorrar()){
            try {
                ControlClear.limpiarTabla(view);
                int result = controlDelete.delete(view);
                anuncioPantalla(view, "Eliminado", result);
                ControlListAll.listAll(view ,view.resultadoTable, model, libDAO);
            }catch (Exception exc){
                mostrarError(exc);
            }
        }
    }

    //MÉTODOS PARA PONER MENSAJES EN PANTALLA
    private void anuncioPantalla(viewMain view, String resultado, int status){
        if(status == 1) {
            view.status.setForeground(Color.GREEN);
            view.status.setText(resultado + " con éxito!");
        }else{
            view.status.setForeground(Color.RED); //Coloreando mensajes
            view.status.setText("Operación sin exito!");
        }
    }

    private void mostrarError(Exception exc){
        view.status.setForeground(Color.RED); //Coloreando mensajes
        view.status.setText("Error!");
        view.errorLabel.setForeground(Color.RED); //En caso de que hayan errores, se escriben en pantalla
        view.errorLabel.setText("Error!: " + exc.getMessage());
    }
}
