package control;

import view.viewMain;
import javax.swing.table.DefaultTableModel;

public class ControlClear {
    //Método para limpiar tablas, cambiando el modelo y volviendo las filas a 0
    public static void limpiarTabla(viewMain view){
        DefaultTableModel modelo = (DefaultTableModel) view.resultadoTable.getModel();
        modelo.setRowCount(0);
    }

    public static void limpiarTF(viewMain view){
        view.tituloLibroTF.setText("");
        view.annioLibroTF.setText("");
        view.autoridTF.setText("");
        view.autorTF.setText("");
        view.idTF.setText("");
    }
}
