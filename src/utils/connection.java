package utils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.*;
import org.json.simple.parser.*;

public class connection {
    public static Connection getConnection(){
        JSONParser parser = new JSONParser();
        Connection conn = null;
        //Conexión a MySQL
        try{
            //Obteniendo dirección del json con credenciales
            String pathCre = System.getProperty("user.dir") + "/src/utils/credenciales.json";
            JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(pathCre));

            //Conectando
            String host = (String)jsonObject.get("db_ip");
            String port = (String)jsonObject.get("db_port");
            String username = (String)jsonObject.get("db_user");
            String password = (String)jsonObject.get("db_pass");
            String dbURL = "jdbc:mysql://"+host+":"+port+"/libreriareto";
            conn = DriverManager.getConnection(dbURL, username, password);
        }catch( SQLException | FileNotFoundException ex ) {
            ex.printStackTrace();
        }catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
