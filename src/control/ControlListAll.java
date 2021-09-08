package control;

import access.LibroDAO;
import model.LibroModel;
import view.viewMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControlListAll {



    //Método para el botón "Listar todos"
    public static void listAll(viewMain view, JTable table, DefaultTableModel model, LibroDAO libDAO){
        model = (DefaultTableModel)table.getModel();
        //Obteniendo lista resultados de consulta.
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
