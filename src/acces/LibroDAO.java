package acces;
import model.AutorModel;
import model.LibroModel;
import view.viewMain;

import java.sql.*;

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
                String aut_nombre = rs.getString(4);
                String ape = rs.getString(5);
                if(ape != null) { //Verificando si autor tiene apellido
                    aut_nombre = rs.getString(4) + " " + ape;
                }
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
    //MÉTODO PARA AGREGAR LIBROS
    public int agregarLibro(LibroModel lib, ProductModel pro, AutorModel aut, viewMain view){
        int r = 0;
        int consultaTit = consultaTitulo(pro, aut, view);
        if(consultaTit == 0) //COMPRUEBA SI EL AUTOR YA TIENE UN LIBRO DEL MISMO TITULO
            return consultaTit;
        int autR = autorDAO.agregarAutor(aut.getAut_nombre(), aut.getAut_apellido());
        if(autR == 1){
            try{
                int prodR = productDAO.agregarProducto(pro.getProd_titulo());
                String sqlPrimera = "SELECT MAX(prod_id) AS id FROM producto"; //Obteniendo ultima ID creada.
                con = connection.getConnection();
                Statement statement = con.createStatement();
                rs = statement.executeQuery(sqlPrimera);
                if (rs.next())
                    lib.setId_fk(rs.getInt(1));
                String sqlSegunda = "SELECT autor.aut_id FROM autor WHERE autor.aut_nombre = ? AND autor.aut_apellido = ?";
                if (aut.getAut_apellido() == " "){ //COMPRUEBA SI EL USUARIO INGRESO APELLIDOS
                    sqlSegunda = "SELECT autor.aut_id FROM autor WHERE autor.aut_nombre = ? AND autor.aut_nacionalidad = ?";
                }
                ps = con.prepareStatement(sqlSegunda);
                ps.setString(1, aut.getAut_nombre());
                if (aut.getAut_apellido() != " ") { //COMPRUEBA SI EL USUARIO INGRESO APELLIDOS
                    ps.setString(2, aut.getAut_apellido());
                }else{
                    ps.setString(2, "Exists");
                }
                rs = ps.executeQuery();
                if(rs.next() == true)
                    lib.setAutor_id_fk(rs.getInt(1));
                String sqlTercera = "INSERT INTO libro(lib_id_fk, lib_resenia, lib_anio, lib_autor_fk) VALUES(?,?,?,?)";
                ps = con.prepareStatement(sqlTercera);
                ps.setInt(1, lib.getId_fk());
                ps.setString(2, "...");
                ps.setInt(3, lib.getLib_anio());
                ps.setInt(4, lib.getAutor_id_fk());
                r = ps.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return r;
    };
    //MÉTODO PARA VERIFICAR SI EL LIBRO YA EXISTE DENTRO DEL MISMO AUTOR
    private int consultaTitulo(ProductModel pro, AutorModel aut, viewMain view){
        try {
            String consultaSQL = "SELECT p.prod_titulo, p.prod_id FROM libro JOIN autor a on libro.lib_autor_fk = a.aut_id JOIN producto p on p.prod_id = libro.lib_id_fk WHERE p.prod_titulo = ? AND a.aut_nombre = ? AND a.aut_apellido = ?";
            if (aut.getAut_apellido() == " ") { //COMPRUEBA SI EL AUTOR TIENE APELLIDO
                consultaSQL = "SELECT p.prod_titulo, p.prod_id FROM libro JOIN autor a on libro.lib_autor_fk = a.aut_id JOIN producto p on p.prod_id = libro.lib_id_fk WHERE p.prod_titulo = ? AND a.aut_nombre = ? AND a.aut_nacionalidad = ?";
            }
            con = connection.getConnection();
            ps = con.prepareStatement(consultaSQL);
            ps.setString(1, pro.getProd_titulo());
            ps.setString(2, aut.getAut_nombre());
            if (aut.getAut_apellido() != " ") { //COMPRUEBA SI EL USUARIO INGRESO APELLIDOS
                ps.setString(3, aut.getAut_apellido());
            }else{ //SI NO TIENE APELLIDO INSERTADO, SE EVITA REPETIR MEDIANTE LA NACIONALIDAD
                ps.setString(3, "Exists");
            }
            rs = ps.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(view, "Ya existe un producto con ese titulo para ese autor!");
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    };


}
