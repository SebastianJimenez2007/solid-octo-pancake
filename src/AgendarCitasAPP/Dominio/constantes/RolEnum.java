/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package AgendarCitasAPP.Dominio.constantes;

/**
 *
 * @author Sebastian JB
 */
public enum RolEnum {
    PACIENTE("Paciente"),
    EMPLEADO("Empleado"),
    MEDICO("Médico"),
    ADMIN("Administrador");

    private final String nombre;

    RolEnum(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static RolEnum fromString(String text) {
        for (RolEnum rol : RolEnum.values()) {
            if (rol.name().equalsIgnoreCase(text) ||
                rol.getNombre().equalsIgnoreCase(text)) {
                return rol;
            }
        }
        return null; // Ya no se devuelve OTRO por defecto
    }
}

