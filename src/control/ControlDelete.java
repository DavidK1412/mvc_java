package control;

import view.viewMain;
import access.LibroDAO;

import javax.swing.*;

public class ControlDelete {
    LibroDAO libroDAO = new LibroDAO();
    public int delete(viewMain view){
      int r = 0;
      if (view.idTF.getText().isEmpty() != true) {
          r = libroDAO.eliminarLibro(Integer.parseInt(view.idTF.getText()));
      }else{
          JOptionPane.showMessageDialog(view, "Sólo se puede eliminar con el Id del Libro!");
          return 0;
      }
      return r;
    };
}
