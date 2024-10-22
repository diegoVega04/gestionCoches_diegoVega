package org.example.gestioncoches_diegovega.Clases;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ConBD {

    public static MongoClient conectar() {
        try {
            final MongoClient conexion = new MongoClient(new MongoClientURI("mongodb://admin:1234@localhost:27017/?authSource=admin"));
            System.out.println("Conectado correctamente");
            return conexion;
        } catch (Exception e) {
            System.out.println("Conexion Fallida");
            System.out.println(e);
            return null;
        }
    }

    public static void desconectar(MongoClient con) {
        con.close();
        System.out.println("Conexion cerrada correctamente");
    }
}