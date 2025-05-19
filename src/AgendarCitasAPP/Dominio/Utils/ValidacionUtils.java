/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Utils;

import java.time.LocalDate;

/**
 *
 * @author Sebastian JB
 */
public class ValidacionUtils {
      public static boolean esCorreoValido(String correo) {
        return correo != null && correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
    
    public static boolean esFechaValida(String fecha) {
        try {
            LocalDate.parse(fecha);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
