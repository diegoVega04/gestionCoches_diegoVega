package org.example.gestioncoches_diegovega.Modelo;

import java.util.Objects;

public class Coche {
    private String matricula, marca, modelo, tipo;
    private String _id; //necesario para evitar problemas con mongoDB

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this._id = matricula;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coche coche = (Coche) o;
        return Objects.equals(matricula, coche.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }
}
