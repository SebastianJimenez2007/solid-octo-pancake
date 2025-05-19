/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Controllers;

import AgendarCitasAPP.Dominio.Entidades.Medico;
import AgendarCitasAPP.Dominio.Entidades.Paciente;
import AgendarCitasAPP.Dominio.Entidades.Usuario;
import AgendarCitasAPP.Dominio.Utils.JsonUtils;
import AgendarCitasAPP.Dominio.Utils.UsuarioService;
import AgendarCitasAPP.Dominio.constantes.GeneroEnum;
import AgendarCitasAPP.Dominio.constantes.RolEnum;
import AgendarCitasAPP.view.EditarMedicoFrame;
import AgendarCitasAPP.view.EditarPacienteFrame;
import AgendarCitasAPP.view.EditarTrabajadorFrame;
import com.google.gson.reflect.TypeToken;
import java.awt.Component;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sebas
 */
public class AdminController {
    
     public static void cargarUsuariosEnTabla(DefaultTableModel modelo, RolEnum... rolesFiltro) {
        try {
            modelo.setRowCount(0); // Limpiar tabla
            
            // 1. Obtener datos del JSON
            Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> usuarios = JsonUtils.leerJson("Usuarios.json", tipoLista);
            
            // 2. Filtrar y llenar según los roles solicitados
            for (Usuario u : usuarios) {
                if (rolesFiltro.length == 0 || Arrays.asList(rolesFiltro).contains(u.getRol())) {
                    modelo.addRow(new Object[]{
                        u.getId(),
                        u.getNombre(),
                        u.getApellido(),
                        u.getClave(),
                        u.getCorreo(),
                        u.getRol().toString()
                            
                    });
                }
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar usuarios: " + e.getMessage());
        }
    }

    
   public static void editarUsuarioSeleccionado(JTable tablaUsuarios, Component parent) {
    int filaSeleccionada = tablaUsuarios.getSelectedRow();
    
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(parent, 
            "Seleccione un usuario de la tabla", 
            "Advertencia", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        String id = tablaUsuarios.getValueAt(filaSeleccionada, 0).toString();
        Usuario usuario = UsuarioService.buscarPorId(id);
        
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }

        // Verificación segura del tipo de usuario
        if (usuario instanceof Paciente paciente) {
            new EditarPacienteFrame(paciente, modelo).setVisible(true);
        } 
        else if (usuario instanceof Medico medico) {
            new EditarMedicoFrame(medico, modelo).setVisible(true);
        } 
        else if (usuario instanceof Empleado) {
           new EditarTrabajadorFrame((Empleado) usuario, modelo).setVisible(true);
        } 
       else {
           throw new Exception("Tipo de usuario no soportado: " + usuario.getClass().getSimpleName());
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(parent, 
            "Error al editar usuario: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    
    public static void actualizarUsuario(String id, String nombre, String apellido, String clave, 
                                     String fechaNacimiento, String direccion, String telefono, 
                                     String correo, String genero, String rol) throws Exception {
        // Validaciones básicas
        validarCamposObligatorios(id, nombre, apellido, correo);
        
        // Convertir y validar datos
        LocalDate fechaNac = parsearFecha(fechaNacimiento);
        GeneroEnum generoEnum = parsearGenero(genero);
        RolEnum rolEnum = parsearRol(rol);

        // Obtener y actualizar usuario
        Usuario usuario = UsuarioService.buscarPorId(id);
        actualizarDatosUsuario(usuario, nombre, apellido, clave, fechaNac, direccion, 
                             telefono, correo, generoEnum, rolEnum);
        
        // Guardar cambios
        UsuarioService.actualizarUsuario(usuario);
    }
    
    private static void validarCamposObligatorios(String id, String nombre, String apellido, String correo) 
            throws IllegalArgumentException {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío");
        }
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("Nombre y apellido son obligatorios");
        }
        if (!correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Formato de correo electrónico inválido");
        }
    }

    private static LocalDate parsearFecha(String fecha) throws IllegalArgumentException {
        try {
            return LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use YYYY-MM-DD");
        }
    }

    private static GeneroEnum parsearGenero(String genero) throws IllegalArgumentException {
        try {
            return GeneroEnum.valueOf(genero.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para género");
        }
    }

    private static RolEnum parsearRol(String rol) throws IllegalArgumentException {
        try {
            return RolEnum.valueOf(rol.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para rol");
        }
    }

    private static void actualizarDatosUsuario(Usuario usuario, String nombre, String apellido, 
                                            String clave, LocalDate fechaNac, String direccion,
                                            String telefono, String correo, GeneroEnum genero, 
                                            RolEnum rol) {
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setClave(clave);
        usuario.setFechaNacimiento(fechaNac);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuario.setCorreo(correo);
        usuario.setGenero(genero);
        usuario.setRol(rol);
    }

    private static void mostrarMensaje(Component parent, String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(parent, mensaje, titulo, tipo);
    }

    private static void mostrarError(Component parent, String mensaje) {
        mostrarMensaje(parent, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
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

    

