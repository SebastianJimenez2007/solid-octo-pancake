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
import java.time.LocalDate;
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
    
   public static void actualizarUsuario(String id, String nombre, String apellido, String clave, String fechaNacimiento, String direccion, String telefono, String correo, String genero, String rol) throws Exception {
    
    // Crear objeto Usuario con los datos actualizados
    Usuario usuarioActualizado = new Usuario();
    usuarioActualizado.setId(id);
    usuarioActualizado.setNombre(nombre);
    usuarioActualizado.setApellido(apellido);
    usuarioActualizado.setClave(clave);
    usuarioActualizado.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
    usuarioActualizado.setDireccion(direccion);
    usuarioActualizado.setTelefono(telefono);
    usuarioActualizado.setCorreo(correo);
    
    // Guardar en la base de datos
    JsonUtils.actualizarUsuario(usuarioActualizado);
}
    
    public static void eliminarUsuario(String idUsuario)throws IOException{
        //lee los usuarios
        Type tipoLista = new TypeToken<List<Usuario>> () {}.getType();
        List<Usuario> usuarios = JsonUtils.leerJson("/Usuarios.jason", tipoLista);
        
        //filtra y elimina usuarios
        usuarios.removeIf(u -> u.getId().equals(idUsuario));
        
        //Gusrda cambios
        
        JsonUtils.guardarJson("/Usuarios.json", usuarios);
        
    }
    
    // Nuevo método para validación
    public static boolean validarUsuario(Usuario usuario) {
        return usuario != null && 
               !usuario.getNombre().isEmpty() && 
               !usuario.getApellido().isEmpty();
    }
}

    

