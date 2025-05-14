/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package AgendarCitasAPP.Dominio.constantes;

/**
 *
 * @author Sebastian JB
 */
public enum GeneroEnum {

    MASCULINO("Masculino"),
    FEMENINO("Femenino"),
    OTROS("Otros");

    private final String nombre;

    GeneroEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static GeneroEnum fromString(String text) {
        for (GeneroEnum genero : GeneroEnum.values()) {
            if (genero.name().equalsIgnoreCase(text) || 
                genero.getNombre().equalsIgnoreCase(text)) {
                return genero;
            }
        }
        return OTROS; // Valor por defecto
    }
}



