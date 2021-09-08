package control;
import acces.LibroDAO;
import model.AutorModel;
import model.LibroModel;
import model.ProductModel;
import view.viewMain;

import javax.swing.*;

public class ControlAdd {
    LibroDAO libroDAO = new LibroDAO();
    LibroModel libroModel = new LibroModel();
    AutorModel autorModel = new AutorModel();
    ProductModel productModel = new ProductModel();
    public int add(viewMain view){
        //No es necesario llenar las ID ya que son autoincrementables, en caso de que se llenen tomarán valores
        if((view.autoridTF.getText().isEmpty() != true && view.idTF.getText().isEmpty() != true) ) {
            int autId = Integer.parseInt(view.autoridTF.getText());
            int libId = Integer.parseInt(view.idTF.getText());
            libroModel.setAutor_id_fk(autId);
            libroModel.setId_fk(libId);
            autorModel.setAut_id(autId);
            productModel.setProd_id(libId);
        }
        int comprobacionCampos = comprobarVacios(view);
        if(comprobacionCampos == 0) {
            return 0;
        }else {
            String libTitulo = view.tituloLibroTF.getText();
            int libAnio = Integer.parseInt(view.annioLibroTF.getText());
            String autLibro = view.autorTF.getText();
            if(autLibro.contains(" ")) { //Al la tabla autor tener columnas nombre y apellido, hay que separar el nombre y el apellido del autor
                String[] nombreAutor = autLibro.split(" ", 2);
                autorModel.setAut_nombre(nombreAutor[0]);
                autorModel.setAut_apellido(nombreAutor[1]);
            }else{
                autorModel.setAut_nombre(autLibro);
                autorModel.setAut_apellido(" ");
            }
            libroModel.setLib_anio(libAnio);
            productModel.setProd_titulo(libTitulo);
        }
        int r = libroDAO.agregarLibro(libroModel, productModel, autorModel, view);
        return 1;
    }

    //VERIFICANDO SI CAMPOS ESTÁN VACIOS
    public int comprobarVacios(viewMain view){
        if(view.tituloLibroTF.getText().isEmpty() == true){
            JOptionPane.showMessageDialog(view, "Por favor llenar campo de titulo");
            return 0;
        }else if (view.annioLibroTF.getText().isEmpty() == true){
            JOptionPane.showMessageDialog(view, "Por favor llenar campo del año");
            return 0;
        }else if (view.autorTF.getText().isEmpty() == true){
            JOptionPane.showMessageDialog(view, "Por favor llenar campo de Nombre Autor");
            return 0;
        }
        return 1;
    }
}
