package org.example.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/universidad_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "12345";

    //Obtener mi conexion
    public static Connection getConnection(){
        try{
            Connection conexion = DriverManager.getConnection(URL,USUARIO,PASSWORD);
            System.out.println("Conexion establecida!");
            return conexion;
        }catch(SQLException e){
            System.out.println("Error al conectar conexion");
            return null;
        }
    };

}



