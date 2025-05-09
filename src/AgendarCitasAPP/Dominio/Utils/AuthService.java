/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Utils;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import AgendarCitasAPP.Dominio.constantes.GeneroEnum;
import AgendarCitasAPP.Dominio.constantes.RolEnum;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.*;

/**
 *
 * @author Sebastian JB
 */
public class AuthService {
    private static final String RUTA_USUARIOS = obtenerRutaUsuarios();
    
    private List<Usuario> usuarios = new ArrayList<>();
    
    private static String obtenerRutaUsuarios() {
        // Usar barra como separador de carpetas
        String ruta = "Usuarios.json";
        if (new File(ruta).exists()) return ruta;

        // Si no encuentra, intenta con ruta absoluta
        ruta = System.getProperty("user.dir") + "/Usuarios.json";
        if (new File(ruta).exists()) return ruta;

        throw new RuntimeException("No se encontró el archivo Usuarios.json en ninguna ubicación");
    }

    public static Usuario login(String correo, String clave) {
        try {
            Type tipoListaUsuarios = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> usuarios = JsonUtils.leerJson(RUTA_USUARIOS, tipoListaUsuarios);

            if (usuarios != null) {
                for (Usuario usuario : usuarios) {
                    if (usuario.getCorreo().equalsIgnoreCase(correo) &&
                        usuario.getClave().equals(clave)) {
                        return usuario;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error crítico al leer usuarios: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Usuario registrar(String nombre, String correo, String clave, 
            String telefono, String genero, String rol) {//inicio metodo registrar
        // Validar si el correo ya existe
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                return null; // Ya está registrado
            }
        }

        // Crear nuevo usuario
        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setCorreo(correo);
        nuevo.setClave(clave);
        nuevo.setTelefono(telefono);

        // Convertir Strings a enums (con validación básica)
        try {
            nuevo.setGenero(GeneroEnum.valueOf(genero.toUpperCase()));
        } catch (IllegalArgumentException e) {
            nuevo.setGenero(GeneroEnum.OTROS); // o un valor por defecto
        }

        try {
            nuevo.setRol(RolEnum.valueOf(rol.toUpperCase()));
        } catch (IllegalArgumentException e) {
            nuevo.setRol(RolEnum.PACIENTE); // por defecto
        }

        // Guardar en lista temporal
        usuarios.add(nuevo);

        return nuevo;
    }//fin metodo register
}