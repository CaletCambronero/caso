package org.example;

import org.example.misc.Conexion;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conexion =  Conexion.getConnection();
        if (conexion != null){
            System.out.println("Conexion establecida");
        }
    }
}
