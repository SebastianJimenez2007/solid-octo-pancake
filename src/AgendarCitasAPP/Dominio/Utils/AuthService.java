/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Utils;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import AgendarCitasAPP.Dominio.constantes.GeneroEnum;
import AgendarCitasAPP.Dominio.constantes.RolEnum;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

public class AuthService {
    private static final String RUTA_USUARIOS = obtenerRutaUsuarios();
    private List<Usuario> usuarios;
    
    public AuthService() {
        this.usuarios = cargarUsuarios(); // Carga usuarios desde JSON al iniciar
    }
    
    private static String obtenerRutaUsuarios() {
        String ruta = "Usuarios.json";
        if (new File(ruta).exists()) return ruta;
        
        ruta = System.getProperty("user.dir") + "/Usuarios.json";
        return ruta; // Si no existe, se creará luego
    }
    
    private List<Usuario> cargarUsuarios() {
        try {
            Type tipoListaUsuarios = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> usuarios = JsonUtils.leerJson(RUTA_USUARIOS, tipoListaUsuarios);
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
            return new ArrayList<>(); // Lista vacía si hay error
        }
    }
    
    public Usuario registrar(String id, String nombre, String apellido, String correo, String clave, 
                           String telefono, String genero, String rol, String fechaNacimiento, String direccion ) {
        // Validar si el correo ya existe
        if (usuarios.stream().anyMatch(u -> u.getCorreo().equalsIgnoreCase(correo))) {
            return null;
        }
        
        LocalDate fecha;
    try {
        fecha = LocalDate.parse(fechaNacimiento); // Formato: yyyy-MM-dd
    } catch (Exception e) {
        System.err.println("Error: Formato de fecha inválido -> " + fechaNacimiento);
        return null;
    }
        
        // Crear nuevo usuario
        Usuario nuevo = new Usuario();
        nuevo.setId(id);
        nuevo.setNombre(nombre);
        nuevo.setCorreo(correo);
        nuevo.setApellido(apellido);
        nuevo.setClave(clave);
        nuevo.setTelefono(telefono);
        nuevo.setDireccion(direccion);
        nuevo.setGenero(GeneroEnum.fromString(genero));
        nuevo.setRol(RolEnum.fromString(rol));
        
        // Guardar en lista y persistir en JSON
        usuarios.add(nuevo);
        guardarUsuarios();
        
        return nuevo;
    }
    
    public Usuario login(String correo, String clave) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo) && 
                usuario.getClave().equals(clave)) {
                return usuario;
            }
        }
        return null;
    }
    
    private void guardarUsuarios() {
        try {
            JsonUtils.guardarJson(RUTA_USUARIOS, usuarios);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
            throw new RuntimeException("No se pudo guardar el usuario", e);
        }
    }
    
}