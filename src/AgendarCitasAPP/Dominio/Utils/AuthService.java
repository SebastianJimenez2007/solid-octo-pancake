/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Utils;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Sebastian JB
 */
public class AuthService {
    private static final String RUTA_USUARIOS = obtenerRutaUsuarios();

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
}