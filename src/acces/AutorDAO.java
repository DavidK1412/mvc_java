package acces;

import utils.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AutorDAO {
    //Instanciando hacía la clase connection en el paquete utils para poder acceder a la base de datos
    connection conect = new connection();
    //Variables del paquete java.sql para facilitar
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregarAutor(String nom, String apellido){
        String nacionalidad = "none";
        int r = 0;
        String comprobarSQL = "SELECT autor.aut_id FROM autor WHERE autor.aut_nombre LIKE ? AND autor.aut_apellido LIKE ?";
        try{   //COMPROBANDO SI EL AUTOR YA EXISTE PARA EVITAR CREAR MUCHOS
            con = connection.getConnection();
            ps = con.prepareStatement(comprobarSQL);
            ps.setString(1, "%"+nom+"%");
            ps.setString(2, "%" +apellido+ "%");
            rs = ps.executeQuery();
            if(rs.next()) {
                return 1;
            }
        }catch (Exception exc){
            exc.printStackTrace();
        }
        String sql = "INSERT INTO autor(aut_nombre, aut_apellido, aut_nacionalidad) VALUES(?,?,?)";
        try{ //INTENTA AÑADIR AL AUTOR
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, apellido);
            ps.setString(3, nacionalidad);
            r = ps.executeUpdate();
            if (r == 1){ return 1;} else{ return 0;} //RETORNARÁ SI LA INSERCIÓN SE HIZO BIEN
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return r;
    };
}
