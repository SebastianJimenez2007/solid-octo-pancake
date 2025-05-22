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
        // Verificar si el archivo existe
        File file = new File(rutaArchivo);
        if (!file.exists()) {
            throw new FileNotFoundException("Archivo no encontrado: " + rutaArchivo);
        }

        try (InputStreamReader reader = new InputStreamReader(
             new FileInputStream(file), StandardCharsets.UTF_8)) {
             
            return gson.fromJson(reader, tipoLista);
        } catch (JsonSyntaxException | JsonIOException e) {
            throw new IOException("Error al parsear JSON: " + e.getMessage(), e);
        }
    }

    public static <T> void guardarJson(String rutaArchivo, List<T> datos) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(
             new FileOutputStream(rutaArchivo), StandardCharsets.UTF_8)) {
             
            gson.toJson(datos, writer);
        }
    }

   public static void actualizarUsuario(Usuario usuarioActualizado) throws IOException {
    // 1. Definir la ruta del archivo JSON (deberías tenerla configurada)
    String rutaArchivo = "Usuarios.json"; // Cambia esto por tu ruta real
    
    // 2. Definir el Type para la lista de usuarios
    Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
    
    // 3. Leer todos los usuarios existentes
    List<Usuario> usuarios = leerJson(rutaArchivo, tipoLista);
    
    // 4. Buscar y actualizar el usuario
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
    
    // 5. Guardar la lista actualizada
    guardarJson(rutaArchivo, usuarios);
}

    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(date));
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context) 
            throws JsonParseException {
            try {
                return LocalDate.parse(json.getAsString(), formatter);
            } catch (Exception e) {
                throw new JsonParseException("Error al parsear fecha: " + json.getAsString(), e);
            }
        }
    }
    
    public static void eliminarUsuario(String idUsuario) throws IOException {
    // Validación básica
    if (idUsuario == null || idUsuario.isEmpty()) {
        throw new IllegalArgumentException("El ID del usuario no puede estar vacío");
    }

    // 1. Obtener la ruta del archivo (deberías tener esto configurado)
    String rutaArchivo = "Usuarios.json"; // Reemplaza con tu ruta real
    
    // 2. Leer todos los usuarios existentes
    Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();
    List<Usuario> usuarios = leerJson(rutaArchivo, tipoLista);
    
    // 3. Buscar y eliminar el usuario
    boolean eliminado = usuarios.removeIf(usuario -> usuario.getId().equals(idUsuario));
    
    if (!eliminado) {
        throw new IllegalArgumentException("No se encontró ningún usuario con ID: " + idUsuario);
    }
    
    // 4. Guardar la lista actualizada
    guardarJson(rutaArchivo, usuarios);
    }
}