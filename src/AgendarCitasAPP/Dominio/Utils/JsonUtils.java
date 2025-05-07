package AgendarCitasAPP.Dominio.Utils;

import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JsonUtils {
    // Configuración centralizada de Gson con adaptadores
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .setPrettyPrinting()
        .create();

    // --- Métodos principales --- //
    public static <T> List<T> leerJson(String rutaArchivo, Type tipoLista) {
        try (FileReader reader = new FileReader(rutaArchivo)) {
            return gson.fromJson(reader, tipoLista);
        } catch (Exception e) {
            System.err.println("Error al leer JSON: " + e.getMessage());
            return null;
        }
    }

    public static <T> void guardarJson(String rutaArchivo, List<T> datos) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(datos, writer);
        } catch (Exception e) {
            System.err.println("Error al guardar JSON: " + e.getMessage());
        }
    }

    // --- Adaptador para LocalDate --- //
    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(date));
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
            return LocalDate.parse(json.getAsString(), formatter);
        }
    }
}
