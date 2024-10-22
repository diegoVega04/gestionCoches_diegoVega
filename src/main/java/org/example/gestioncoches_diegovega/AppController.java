package org.example.gestioncoches_diegovega;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class AppController implements Initializable {
    @FXML
    public MenuItem SUV;
    @FXML
    public MenuButton menuTipo;
    @FXML
    public MenuItem familiar;
    @FXML
    public MenuItem deportivo;
    @FXML
    public MenuItem furgoneta;
    @FXML
    public Button btnGuardar;
    @FXML
    public Button btnLimpiar;
    @FXML
    public Button btnActualizar;
    @FXML
    public Button btnBorrar;
    @FXML
    public TextField matricula;
    @FXML
    public TextField marca;
    @FXML
    public TextField modelo;
    @FXML
    public TableView tablaCoches;
    @FXML
    public TableColumn clmMatricula;
    @FXML
    public TableColumn clmMarca;
    @FXML
    public TableColumn clmModelo;
    @FXML
    public TableColumn clmTipo;
    public static ObservableList<Coche> coches = FXCollections.observableArrayList();
    public static String tipo;




    @FXML
    public void onBtnGuardar(ActionEvent actionEvent) {
        Coche a = new Coche(matricula.getText(), marca.getText(), modelo.getText(), tipo);
        if (!coches.contains(a)) {
            if (CRUD.añadirCoche(a)) {
                coches.add(a);
                limpiar();
            } else {
                System.out.println("Error al añadir coche");
            }
        } else {
            System.out.println("Ya existe un coche con esa matricula");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //asignar que campo de la clase coche le corresponde a cada columna
        clmMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        clmMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        clmModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        clmTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        //asignar la observable list a la tabla
        tablaCoches.setItems(coches);
    }
    @FXML
    public void tipoFamiliar(ActionEvent actionEvent) {
        tipo = "familiar";
        menuTipo.setText("familiar");
    }
    @FXML
    public void tipoDeportivo(ActionEvent actionEvent) {
        tipo = "deportivo";
        menuTipo.setText("deportivo");
    }
    @FXML
    public void tipoSUV(ActionEvent actionEvent) {
        tipo = "SUV";
        menuTipo.setText("SUV");
    }
    @FXML
    public void tipoFurgoneta(ActionEvent actionEvent) {
        tipo = "furgoneta";
        menuTipo.setText("furgoneta");
    }

    @FXML
    public void onBtnLimpiar(ActionEvent actionEvent) {
        limpiar();
    }

    public void limpiar () {
        matricula.setText("");
        marca.setText("");
        modelo.setText("");
        menuTipo.setText("");
    }
}