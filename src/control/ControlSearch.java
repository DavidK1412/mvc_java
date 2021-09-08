package control;
import access.LibroDAO;
import model.LibroModel;
import view.viewMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class ControlSearch {
    LibroDAO libroDAO = new LibroDAO();

    public int buscarLibro(viewMain view, JTable table, DefaultTableModel model){
        int r = 1;
        List<LibroModel> list;
        if(view.tituloLibroTF.getText().isEmpty() != true){ //Comprobando qué buscar
            try {
                list = libroDAO.buscarPorTitulo(view.tituloLibroTF.getText());
                listarResultados(view, table, model, list);
                return r;
            }catch (Exception ex){
                return 0;
            }
        }else if(view.annioLibroTF.getText().isEmpty() != true){
            try {
                list = libroDAO.buscarPorAnnio(Integer.parseInt(view.annioLibroTF.getText()));
                listarResultados(view, table, model, list);
                return r;
            }catch (Exception ex){
                return 0;
            }
        }else if(view.autorTF.getText().isEmpty() != true){
            String autLibro = view.autorTF.getText();
            String nombre;
            if(autLibro.contains(" ")) {
                String[] nombreAutor = autLibro.split(" ", 2);
                nombre = nombreAutor[0];
            }else{
                nombre = autLibro;
            }
            try{
                list = libroDAO.buscarPorAutor(nombre);
                listarResultados(view, table, model, list);
                return r;
            }catch (Exception e){
                return 0;
            }
        }else if(view.autoridTF.getText().isEmpty() != true){
            try {
                list = libroDAO.buscarPorAutorId(Integer.parseInt(view.autoridTF.getText()));
                listarResultados(view, table, model, list);
                return r;
            }catch (Exception ex){
                return 0;
            }
        }else if (view.idTF.getText().isEmpty() != true){
            try {
                list = libroDAO.buscarPorId(Integer.parseInt(view.idTF.getText()));
                listarResultados(view, table, model, list);
                return r;
            }catch (Exception ex){
                return 0;
            }
        }
        return r;
    }

    public void listarResultados(viewMain view, JTable table, DefaultTableModel model, List<LibroModel> listAll){
        model = (DefaultTableModel)table.getModel();
        //Obteniendo lista resultados de consulta.
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
