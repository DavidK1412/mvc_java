package acces;

import utils.connection;

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

    public int agregarProducto(String nom){
        int r = 0;
        String sql = "INSERT INTO producto(prod_titulo) VALUES(?)";
        try{
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            r = ps.executeUpdate();
        }catch (Exception exc){
            exc.printStackTrace();
        }

        return r; //Retorna si la inserción se hizo correctamente.
    };

}
