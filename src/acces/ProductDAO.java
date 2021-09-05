package acces;

import utils.connection;
import view.viewMain;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDAO {
    //Instanciando hacía la clase connection en el paquete utils para poder acceder a la base de datos
    connection conect = new connection();
    //Variables del paquete java.sql para facilitar
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregarProducto(String nom, viewMain view){
        int r = 0;

        String sql = "INSERT INTO producto(prod_titulo) VALUES(?)";
        try{
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            r = ps.executeUpdate();
            if (r == 1){ return 1;} else{ return 2;} //RETORNARÁ SI LA INSERCIÓN SE HIZO BIEN
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return r;
    };
}
