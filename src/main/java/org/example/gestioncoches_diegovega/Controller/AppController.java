package org.example.gestioncoches_diegovega.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.gestioncoches_diegovega.CRUD;
import org.example.gestioncoches_diegovega.Clases.Coche;
import org.example.gestioncoches_diegovega.Util.Alert;
import org.example.gestioncoches_diegovega.Util.Validar;

import java.net.URL;
import java.util.*;

public class AppController implements Initializable {

    public static ObservableList<Coche> coches = FXCollections.observableArrayList();
    public static String tipo;
    private Coche seleccionado;

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

    @FXML
    public void onBtnGuardar(ActionEvent actionEvent) {
        Coche a = new Coche(matricula.getText(), marca.getText(), modelo.getText(), tipo);
        if (!coches.contains(a)) {
            if (CRUD.añadirCoche(a)) {
                coches.add(a);
                limpiar();
            }
        } else {
            Alert.notificacion("Ya existe un coche con esa matricula");
        }
    }
    @FXML
    public void onBtnBorrar(ActionEvent event) {
        seleccionado = (Coche) tablaCoches.getSelectionModel().getSelectedItem();
        if (Alert.confirmacion("¿Seguro que desea borrar el coche seleccionado?") == 0) {
            if (CRUD.borrarCoche(seleccionado)) {
                coches.remove(seleccionado);
                Alert.notificacion("Coche borrado con exito");
            } else {Alert.notificacion("Error al intentar borrar el coche");}
        }
    }
    @FXML
    public void onBtnLimpiar(ActionEvent actionEvent) {
        limpiar();
    }
    @FXML
    public void onBtnActualizar(ActionEvent event) {
        seleccionado = (Coche) tablaCoches.getSelectionModel().getSelectedItem();

        if (Validar.matricula(seleccionado)) {
            if (Validar.marcaModelo(marca.getText(), modelo.getText())) {
                Coche c = new Coche(matricula.getText(), marca.getText(), modelo.getText(), tipo);

                CRUD.borrarCoche(seleccionado);
                coches.remove(seleccionado);

                CRUD.añadirCoche(c);
                coches.add(c);
                limpiar();
                Alert.notificacion("Coche actualizado con exito");
            } else {Alert.notificacion("Error en el formato de marca y/o modelo");}
        } else {Alert.notificacion("Error en el formato de matricula");}
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
    public void clicTabla(MouseEvent mouseEvent) {
        seleccionado = (Coche) tablaCoches.getSelectionModel().getSelectedItem();
        matricula.setText(seleccionado.getMatricula());
        marca.setText(seleccionado.getMarca());
        modelo.setText(seleccionado.getModelo());
        menuTipo.setText(seleccionado.getTipo());
    }

    public void limpiar () {
        matricula.setText("");
        marca.setText("");
        modelo.setText("");
        menuTipo.setText("");
        tipo = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            coches = CRUD.cargarCoches(); //carga los datos de la base de datos si es que los hay
        } catch (Exception e) {
            e.printStackTrace();
        }
        //asignar que campo de la clase coche le corresponde a cada columna
        clmMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        clmMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        clmModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        clmTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        //asignar la observable list a la tabla
        tablaCoches.setItems(coches);
    }

}