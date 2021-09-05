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
        //No es necesario llenar las ID ya que son autoincrementables
        if((view.autoridTF.getText().isEmpty() != true && view.idTF.getText().isEmpty() != true) ) {
            int autId = Integer.parseInt(view.autoridTF.getText());
            int libId = Integer.parseInt(view.idTF.getText());
            libroModel.setAutor_id_fk(autId);
            libroModel.setId_fk(libId);
            autorModel.setAut_id(autId);
            productModel.setProd_id(libId);
        }

        if(view.tituloLibroTF.getText().isEmpty() == true){
            JOptionPane.showMessageDialog(view, "Por favor llenar campo de titulo");
            return 0;
        }else if (view.annioLibroTF.getText().isEmpty() == true){
            JOptionPane.showMessageDialog(view, "Por favor llenar campo del año");
            return 0;
        }else if (view.autorTF.getText().isEmpty() == true){
            JOptionPane.showMessageDialog(view, "Por favor llenar campo de Nombre Autor");
            return 0;
        }else {
            String libTitulo = view.tituloLibroTF.getText();
            int libAnio = Integer.parseInt(view.annioLibroTF.getText());
            String autLibro = view.autorTF.getText();
            String[] nombreAutor = autLibro.split(" ", 2);
            libroModel.setLib_anio(libAnio);
            autorModel.setAut_nombre(nombreAutor[0]);
            autorModel.setAut_apellido(nombreAutor[1]);
            productModel.setProd_titulo(libTitulo);
            int r = libroDAO.agregarLibro(libroModel, productModel, autorModel, view);
            return r;
        }
    }
}
