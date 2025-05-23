package AgendarCitasAPP.Dominio.Utils;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JsonUtils {
    
    private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
    .setPrettyPrinting()
    .create();

  

    public static <T> List<T> leerJson(String rutaArchivo, Type tipoLista) throws IOException {
        File file = new File(rutaArchivo);
        if (!file.exists()) {
            throw new FileNotFoundException("Archivo no encontrado: " + rutaArchivo);
        }

        try (InputStreamReader reader = new InputStreamReader(
             new FileInputStream(file), StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, tipoLista);
        } catch (JsonSyntaxException e) {
            throw new IOException("Error en la sintaxis del JSON: " + e.getMessage(), e);
        }
    }

    public static <T> void guardarJson(String rutaArchivo, List<T> datos) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(
             new FileOutputStream(rutaArchivo), StandardCharsets.UTF_8)) {
            gson.toJson(datos, writer);
        }
    }

    public static void actualizarUsuario(Usuario usuarioActualizado) throws IOException {
        String rutaArchivo = "Usuarios.json";
        Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
        List<Usuario> usuarios = leerJson(rutaArchivo, tipoLista);
        
        boolean encontrado = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuarioActualizado.getId())) {
                usuarios.set(i, usuarioActualizado);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            throw new IOException("Usuario con ID " + usuarioActualizado.getId() + " no encontrado");
        }
        
        guardarJson(rutaArchivo, usuarios);
    }

    public static void eliminarUsuario(String idUsuario) throws IOException {
        if (idUsuario == null || idUsuario.isEmpty()) {
            throw new IllegalArgumentException("El ID del usuario no puede estar vacío");
        }

        String rutaArchivo = "Usuarios.json";
        Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
        List<Usuario> usuarios = leerJson(rutaArchivo, tipoLista);
        
        boolean eliminado = usuarios.removeIf(usuario -> usuario.getId().equals(idUsuario));
        
        if (!eliminado) {
            throw new IllegalArgumentException("No se encontró ningún usuario con ID: " + idUsuario);
        }
        
        guardarJson(rutaArchivo, usuarios);
    }

    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext context) {
            return date == null ? null : new JsonPrimitive(formatter.format(date));
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context) 
            throws JsonParseException {
            try {
                return json == null ? null : LocalDate.parse(json.getAsString(), formatter);
            } catch (Exception e) {
                throw new JsonParseException("Formato de fecha inválido. Use yyyy-MM-dd: " + json.getAsString());
            }
        }
    }

}