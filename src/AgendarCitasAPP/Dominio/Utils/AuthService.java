/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Utils;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Sebastian JB
 */
public class AuthService {
     private static final String RUTA_USUARIOS = "src/AgendarCitasAPP/Data/Usuarios.json";
    
    public static Usuario login(String correo, String clave) {
        try {
            Type tipoListaUsuarios = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> usuarios = JsonUtils.leerJson(RUTA_USUARIOS, tipoListaUsuarios);
            
            if (usuarios != null) {
                System.out.println("Usuarios cargados: " + usuarios); // Debug
                for (Usuario usuario : usuarios) {
                    if (usuario.getCorreo().equalsIgnoreCase(correo) && 
                        usuario.getClave().equals(clave)) {
                        return usuario;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
