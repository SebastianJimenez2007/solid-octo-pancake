/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Controllers;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import AgendarCitasAPP.Dominio.Utils.JsonUtils;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Sebastian JB
 */
public class UsuarioController {
    private static final String RUTA_JSON = "src/AgendarCitasAPP/Data/Usuarios.json";
    private Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();

    public List<Usuario> obtenerTodos() throws IOException {
        return JsonUtils.leerJson(RUTA_JSON, tipoLista);
    }

    public void guardarTodos(List<Usuario> usuarios) throws IOException {
        JsonUtils.guardarJson(RUTA_JSON, usuarios);
    }
}
