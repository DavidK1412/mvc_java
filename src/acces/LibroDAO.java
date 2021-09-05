package acces;
import model.AutorModel;
import model.LibroModel;
import view.viewMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ProductModel;
import utils.connection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    //Instanciando hacía la clase connection en el paquete utils para poder acceder a la base de datos
    connection conect = new connection();
    //Variables del paquete java.sql para facilitar
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //OBJETOS PARA ACCEDER A MÉTODOS
    ProductDAO productDAO = new ProductDAO();
    AutorDAO autorDAO = new AutorDAO();
    //Método para acción "Listar todos"
    public List listarTodos(){
        List<LibroModel> datosLibros = new ArrayList<>();
        String consultaSql = "SELECT producto.prod_id, producto.prod_titulo, libro.lib_anio, autor.aut_nombre, autor.aut_apellido, autor.aut_id FROM libro JOIN producto ON producto.prod_id = libro.lib_id_fk JOIN autor ON libro.lib_autor_fk = autor.aut_id ORDER BY producto.prod_id ASC";
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
                lib.setAutor_id_fk(rs.getInt(6));
                datosLibros.add(lib);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return datosLibros;
    }

    public int agregarLibro(LibroModel lib, ProductModel pro, AutorModel aut, viewMain view){
        //Consulta si el titulo de libro no se encuentra ya registrado por ese autor
        String consultaSQL = "SELECT p.prod_titulo, p.prod_id FROM libro JOIN autor a on libro.lib_autor_fk = a.aut_id JOIN producto p on p.prod_id = libro.lib_id_fk WHERE p.prod_titulo LIKE ? AND a.aut_nombre LIKE ? AND a.aut_apellido LIKE ?";
        try{
            con = connection.getConnection();
            ps = con.prepareStatement(consultaSQL);
            ps.setString(1, "%" + lib.getTitulo() + "%");
            ps.setString(2, "%"+ aut.getAut_nombre() +"%");
            ps.setString(3, "%" + aut.getAut_apellido() + "%");
            rs = ps.executeQuery();
            if(rs.next()) {
                JOptionPane.showMessageDialog(view, "Ya existe un producto con ese titulo para ese autor!");
                return 0;
            }
        }catch (Exception exc){
            exc.printStackTrace();
        }
        int r = 0;
        int subr = productDAO.agregarProducto(pro.getProd_titulo(), view);
        int subraut = autorDAO.agregarAutor(aut.getAut_nombre(), aut.getAut_apellido());
        if (subr == 1 && subraut == 1){
            String sql = "INSERT INTO libro(lib_id_fk, lib_resenia, lib_anio, lib_autor_fk) VALUES(?,?,?,?)";
            String sql2 = "SELECT autor.aut_id FROM autor WHERE autor.aut_nombre LIKE ? AND autor.aut_apellido LIKE ?";
            String sql3 = "SELECT producto.prod_id FROM producto WHERE producto.prod_titulo LIKE ?";
            try {
                con = connection.getConnection();
                ps = con.prepareStatement(sql2);
                ps.setString(1, "%"+ aut.getAut_nombre() +"%");
                ps.setString(2, "%" + aut.getAut_apellido() + "%");
                rs = ps.executeQuery();
                if(rs.next())
                    lib.setAutor_id_fk(rs.getInt(1));
                ps = con.prepareStatement(sql3);
                ps.setString(1, "%" + pro.getProd_titulo() + "%");
                rs = ps.executeQuery();
                if(rs.next())
                    lib.setId_fk(rs.getInt(1));

                System.out.println(lib.getAutor_id_fk() + "\n" + lib.getId_fk());
                con = connection.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, lib.getId_fk());
                ps.setString(2, "...");
                ps.setInt(3, lib.getLib_anio());
                ps.setInt(4, lib.getAutor_id_fk());
                r = ps.executeUpdate();
                if (r == 1){ //COMPRUEBA SI SI SE HIZO LA INSERCIÓN
                    return 1;
                }else{
                    return 0;
                }
            }catch (Exception exc){
                exc.printStackTrace();
            }
        }
        return r;
    }


}
