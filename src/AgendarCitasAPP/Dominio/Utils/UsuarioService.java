/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Utils;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Sebastian JB
 */
public class UsuarioService {
    private static final String JSON_FILE = "Usuarios.json";
    
    public static Usuario buscarPorId(String id) throws Exception {
        Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
        List<Usuario> usuarios = JsonUtils.leerJson(JSON_FILE, tipoLista);
        
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
    }
    
    public static void actualizarUsuario(Usuario usuarioActualizado) throws Exception {
        Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
        List<Usuario> usuarios = JsonUtils.leerJson(JSON_FILE, tipoLista);
        
        // Reemplazar el usuario actualizado
        usuarios = usuarios.stream()
                .map(u -> u.getId().equals(usuarioActualizado.getId()) ? usuarioActualizado : u)
                .collect(Collectors.toList());
        
        JsonUtils.guardarJson(JSON_FILE, usuarios);
    }
}