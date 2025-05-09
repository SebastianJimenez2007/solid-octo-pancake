/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Controllers;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import AgendarCitasAPP.Dominio.Utils.JsonUtils;
import AgendarCitasAPP.Dominio.constantes.RolEnum;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sebas
 */
public class AdminController {
    
    // Método que recibe el modelo como parámetro
    public static void cargarPacientesEnTabla(DefaultTableModel modelo) {
        try {
            modelo.setRowCount(0); // Limpiar tabla
            
            // 1. Obtener datos del JSON (usando tu JsonUtils)
            Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> usuarios = JsonUtils.leerJson("Usuarios.json", tipoLista);
            
            // 2. Llenar solo pacientes
            for (Usuario u : usuarios) {
                if (u.getRol() == RolEnum.PACIENTE) {
                    modelo.addRow(new Object[]{
                        u.getId(),
                        u.getNombre(),
                        u.getCorreo(),
                        u.getRol().toString(),
                        
                    });
                }
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar pacientes: " + e.getMessage());
        }
    }
}
    

