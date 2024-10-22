package org.example.gestioncoches_diegovega;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.gestioncoches_diegovega.Clases.Coche;
import org.example.gestioncoches_diegovega.Util.*;
import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

public class CRUD {
    static Gson gson = new Gson();
    static String cocheJSON;

    public static boolean añadirCoche (Coche coche)     {
        if (Validar.matricula(coche)) {
            if (Validar.marcaModelo(coche.getMarca(), coche.getModelo())) {
                if (coche.getTipo() != null) {
                    cocheJSON = gson.toJson(coche); //convertir el coche a JSON
                    App.collection.insertOne(Document.parse(cocheJSON)); //parsear JSON a Document y meterlo en la BD
                    return true;
                } else {Alert.notificacion("Seleccione un tipo");}
            } else {Alert.notificacion("Formato de marca y/o modelo no permitido");}
        } else {Alert.notificacion("Formato de la matricula no permitido");}
        return false;
    }

    public static boolean borrarCoche (Coche coche) {
        cocheJSON = gson.toJson(coche);
        try {
            App.collection.deleteOne(Document.parse(cocheJSON));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ObservableList<Coche> cargarCoches () {
        ObservableList<Coche> coches = FXCollections.observableArrayList();
        MongoCursor<Document> iterador = App.collection.find().iterator(); //iterador para recorrer la tabla

        while (iterador.hasNext()) { //leemos la tabla y cargamos los coches
            Coche a =gson.fromJson(iterador.next().toJson(), Coche.class);
            coches.add(a);
        }
        return coches;
    }
}
