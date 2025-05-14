/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Entidades;
import AgendarCitasAPP.Dominio.constantes.GeneroEnum;
import AgendarCitasAPP.Dominio.constantes.RolEnum;
import java.time.LocalDate;
import java.util.*;
/**
 *
 * @author JAVIER Y KLEVER
 */
public class Paciente extends Usuario {
    private String historialMedico;
    
    
     public Paciente(String id, String clave, String nombre, String apellido, 
                   LocalDate fechaNacimiento, GeneroEnum genero, String direccion, 
                   String correo, String telefono, RolEnum rol, String historialMedico) {
        
       
    }
}

    