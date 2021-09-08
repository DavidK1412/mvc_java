package access;

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

    public int eliminarProducto(int id){
        int r = 0;
        String sql = "DELETE FROM producto WHERE prod_id = ?;";
        try{
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            r = ps.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return r;
    }

    //MÉTODO PARA ACTUALIZAR EL TITULO DEL PRODUCTO
    public int actualizarTitulo(String titulo, int idProd){
        int r = 0;
        String actualizarSql = "UPDATE producto SET producto.prod_titulo = ? WHERE producto.prod_id = ?";
        try {
            con = connection.getConnection();
            ps = con.prepareStatement(actualizarSql);
            ps.setString(1, titulo);
            ps.setInt(2, idProd);
            r = ps.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return r;
    };

}
