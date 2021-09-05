package acces;
import model.LibroModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.connection;

import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    //Instanciando hacía la clase connection en el paquete utils para poder acceder a la base de datos
    connection conect = new connection();
    //Variables del paquete java.sql para facilitar
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Método para acción "Listar todos"
    public List listarTodos(){
        List<LibroModel> datosLibros = new ArrayList<>();
        String consultaSql = "SELECT producto.prod_id, producto.prod_titulo, libro.lib_anio, autor.aut_nombre, autor.aut_apellido, autor.aut_id FROM libro JOIN producto ON producto.prod_id = libro.lib_id_fk JOIN autor ON autor.aut_id = libro.lib_autor_fk ORDER BY producto.prod_id ASC";
        try {
            //conectando y consultando
            con = conect.getConnection();
            ps = con.prepareStatement(consultaSql);
            rs = ps.executeQuery();
            while (rs.next()){
                LibroModel lib = new LibroModel();
                String aut_nombre = rs.getString(4) + " " + rs.getString(5);
                lib.setId_fk(rs.getInt(1));
                lib.setTitulo(rs.getString(2));
                lib.setLib_anio(rs.getInt(3));
                lib.setAutor(aut_nombre);
                lib.setAutor_id_fk(6);
                datosLibros.add(lib);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return datosLibros;
    }


}
