package org.example.gestioncoches_diegovega;

public class CRUD {

    public static boolean añadirCoche (Coche coche) {
        if (Validar.matricula(coche)) {
            if (Validar.marcaModelo(coche.getMarca(), coche.getModelo())) {
                if (!coche.getTipo().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
}
