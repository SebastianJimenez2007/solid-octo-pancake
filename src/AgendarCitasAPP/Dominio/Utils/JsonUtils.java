package AgendarCitasAPP.Dominio.Utils;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import com.google.gson.*;
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

    public static void actualizarUsuario(Usuario usuarioActualizado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
}