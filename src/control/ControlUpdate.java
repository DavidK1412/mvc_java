package control;

import access.AutorDAO;
import access.LibroDAO;
import access.ProductDAO;
import view.viewMain;

import javax.swing.*;

public class ControlUpdate {
    LibroDAO libroDAO = new LibroDAO();
    ProductDAO productDAO = new ProductDAO();
    public int delete(viewMain view){
      int r = 0;
      int response = comprobarEspacios(view);
      switch (response){
          case 0:
              return r;
          case 1:
            r = productDAO.actualizarTitulo(view.tituloLibroTF.getText(), Integer.parseInt(view.idTF.getText()));
            return r;
          case 2:
            r = libroDAO.actualizarAnnio(Integer.parseInt(view.annioLibroTF.getText()), Integer.parseInt(view.idTF.getText()));
            return r;
          case 3:
            r = libroDAO.actualizarAutorId(Integer.parseInt(view.autoridTF.getText()), Integer.parseInt(view.idTF.getText()));
            return r;
          case 4:
            r = productDAO.actualizarTitulo(view.tituloLibroTF.getText(), Integer.parseInt(view.idTF.getText()));
            r = libroDAO.actualizarAnnio(Integer.parseInt(view.annioLibroTF.getText()), Integer.parseInt(view.idTF.getText()));
            r = libroDAO.actualizarAutorId(Integer.parseInt(view.autoridTF.getText()), Integer.parseInt(view.idTF.getText()));
            return r;
          case 5:
            r = productDAO.actualizarTitulo(view.tituloLibroTF.getText(), Integer.parseInt(view.idTF.getText()));
            r = libroDAO.actualizarAnnio(Integer.parseInt(view.annioLibroTF.getText()), Integer.parseInt(view.idTF.getText()));
            return r;
          case 6:
            r = productDAO.actualizarTitulo(view.tituloLibroTF.getText(), Integer.parseInt(view.idTF.getText()));
            r = libroDAO.actualizarAutorId(Integer.parseInt(view.autoridTF.getText()), Integer.parseInt(view.idTF.getText()));
            return r;
          case 7:
            r = libroDAO.actualizarAutorId(Integer.parseInt(view.autoridTF.getText()), Integer.parseInt(view.idTF.getText()));
            r = libroDAO.actualizarAnnio(Integer.parseInt(view.annioLibroTF.getText()), Integer.parseInt(view.idTF.getText()));
            return r;
      }
      return r;
    };


    private int comprobarEspacios(viewMain view){
        int r = 0;
        if(view.idTF.getText().isEmpty() != true){ //COMPRUEBA SI EL CAMPO DE ID ESTÁ LLENO, PARA SELECCIONARLA
            if(view.tituloLibroTF.getText().isEmpty() != true && view.annioLibroTF.getText().isEmpty() != true && view.autoridTF.getText().isEmpty() != true){
                return 4;
            } else if (view.tituloLibroTF.getText().isEmpty() != true && view.annioLibroTF.getText().isEmpty() != true) {
                return  5;
            }else if(view.tituloLibroTF.getText().isEmpty() != true && view.autoridTF.getText().isEmpty() != true){
                return 6;
            }else if(view.annioLibroTF.getText().isEmpty() != true && view.autoridTF.getText().isEmpty() != true){
                return 7;
            }else if (view.tituloLibroTF.getText().isEmpty() != true) {
                return 1;
            } else if (view.annioLibroTF.getText().isEmpty() != true) {
                return 2;
            } else if (view.autoridTF.getText().isEmpty() != true) {
                return 3;
            }
        }else{
            JOptionPane.showMessageDialog(view, "El campo del ID del libro debe estar lleno!");
        }
        return r;
    }
}
