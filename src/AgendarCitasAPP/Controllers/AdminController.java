/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Controllers;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import AgendarCitasAPP.Dominio.Utils.JsonUtils;
import AgendarCitasAPP.Dominio.constantes.GeneroEnum;
import AgendarCitasAPP.Dominio.constantes.RolEnum;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
                        u.getApellido(),
                        u.getClave(),
                        u.getFechaNacimiento(),
                        u.getDireccion(),
                        u.getTelefono(),
                        u.getCorreo(),
                        u.getRol().toString(),
                        
                    });
                }
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar pacientes: " + e.getMessage());
        }
    }
    
  public static void actualizarUsuario(String id, String nombre, String apellido, String clave, 
                                   String fechaNacimiento, String direccion, String telefono, 
                                   String correo, String genero, String rol) throws Exception {
    
    // 1. Validaciones básicas
    if (id == null || id.isEmpty()) {
        throw new IllegalArgumentException("El ID no puede estar vacío");
    }
    
    if (nombre == null || nombre.isEmpty()) {
        throw new IllegalArgumentException("El nombre es obligatorio");
    }
    
    // Validar formato de correo
    if (!correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        throw new IllegalArgumentException("Formato de correo electrónico inválido");
    }

    // 2. Parseo seguro de la fecha
    LocalDate fechaNac;
    try {
        fechaNac = LocalDate.parse(fechaNacimiento);
    } catch (DateTimeParseException e) {
        throw new IllegalArgumentException("Formato de fecha inválido. Use YYYY-MM-DD");
    }

    // 3. Conversión de género y rol a Enum
    GeneroEnum generoEnum;
    RolEnum rolEnum;
    try {
        generoEnum = GeneroEnum.valueOf(genero.toUpperCase());
        rolEnum = RolEnum.valueOf(rol.toUpperCase());
    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Valor inválido para género o rol");
    }

    // 4. Creación del objeto Usuario
    Usuario usuarioActualizado = new Usuario();
    usuarioActualizado.setId(id);
    usuarioActualizado.setNombre(nombre);
    usuarioActualizado.setApellido(apellido);
    usuarioActualizado.setClave(clave);
    usuarioActualizado.setFechaNacimiento(fechaNac);
    usuarioActualizado.setDireccion(direccion);
    usuarioActualizado.setTelefono(telefono);
    usuarioActualizado.setCorreo(correo);
    usuarioActualizado.setGenero(generoEnum);
    usuarioActualizado.setRol(rolEnum);

    // 5. Actualización en JSON
   
    JsonUtils.actualizarUsuario(usuarioActualizado);
    
}
    
   public static void eliminarUsuario(String idUsuario, DefaultTableModel modeloTabla) throws Exception {
    try {
        // 1. Validación adicional si es necesaria
        if (idUsuario == null || idUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("ID de usuario inválido");
        }
        
        // 2. Eliminar del archivo JSON
        JsonUtils.eliminarUsuario(idUsuario);
        
        // 3. Actualizar la tabla (opcional, si estás pasando el modelo)
        if (modeloTabla != null) {
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                if (idUsuario.equals(modeloTabla.getValueAt(i, 0).toString())) {
                    modeloTabla.removeRow(i);
                    break;
                }
            }
        }
    } catch (IOException e) {
        throw new Exception("Error al eliminar el usuario: " + e.getMessage(), e);
    }
}
    
    // Nuevo método para validación
    public static boolean validarUsuario(Usuario usuario) {
        return usuario != null && 
               !usuario.getNombre().isEmpty() && 
               !usuario.getApellido().isEmpty();
    }
}

    

