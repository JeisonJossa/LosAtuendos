package com.losatuendos.data;

import java.util.Arrays;
import java.util.List;

public class PrendaData {

    // Lista de tipos de prendas
    public static List<String> getTiposPrenda() {
        return Arrays.asList("Dama", "Caballero", "Disfraz");
    }

    // Lista de opciones para pedrería
    public static List<String> getOpcionesPedreria() {
        return Arrays.asList("Sí", "No");
    }

    // Lista de opciones para largo/corto
    public static List<String> getOpcionesLargoCorto() {
        return Arrays.asList("Largo", "Corto");
    }

    // Lista de estados de prendas
    public static List<String> getEstadosPrenda() {
        return Arrays.asList("Bueno", "Regular", "Desgastado");
    }
}
