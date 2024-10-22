package org.example.gestioncoches_diegovega;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;
import org.example.gestioncoches_diegovega.Clases.ConBD;

import java.io.IOException;

public class App extends Application {
    public static MongoClient con;
    public static MongoCollection<Document> collection=null;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            con = ConBD.conectar();
            MongoDatabase database = con.getDatabase("concesionario");
            collection = database.getCollection("coches");
        } catch (Exception e) {
            e.printStackTrace();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 580, 640);
        stage.setTitle("Gestion Coches");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

        //evento que salta al cerrar la aplicacion
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (con != null) {
                ConBD.desconectar(con);
            }
        }));
    }
}