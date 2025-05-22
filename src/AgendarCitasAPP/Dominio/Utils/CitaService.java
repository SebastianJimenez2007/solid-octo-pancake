/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Utils;
/**
 *
 * @author JAVIER Y KLEVER
 */// AgendarCitasAPP/Utils/GestorJSON.java
import AgendarCitasAPP.Dominio.Entidades.Citas;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CitaService {
    private static final String RUTA_CITAS = "Citas.json";
    private List<Citas> citas;

    public CitaService() {
        this.citas = cargarCitas();
    }

    private List<Citas> cargarCitas() {
        try {
            Type tipoListaCitas = new TypeToken<List<Citas>>(){}.getType();
            List<Citas> citas = JsonUtils.leerJson(RUTA_CITAS, tipoListaCitas);
            return citas != null ? citas : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error al cargar citas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean agendarCita(Citas nuevaCita) {
        // Validar solapamiento de horario (opcional)
        if (citas.stream().anyMatch(c -> 
            c.getFechaTexto().equals(nuevaCita.getFechaTexto()) && 
            c.getHoraTexto().equals(nuevaCita.getHoraTexto()) &&
            c.getMedicoId().equals(nuevaCita.getMedicoId()))) {
            return false; // Horario ocupado
        }

        citas.add(nuevaCita);
        guardarCitas();
        return true;
    }

    public List<Citas> obtenerCitasPorPaciente(String pacienteId) {
        return citas.stream()
            .filter(c -> c.getMedicoId().equals(pacienteId)) // Ajusta según tu lógica
            .toList();
    }

    private void guardarCitas() {
        try {
            JsonUtils.guardarJson(RUTA_CITAS, citas);
        } catch (IOException e) {
            System.err.println("Error al guardar citas: " + e.getMessage());
            throw new RuntimeException("No se pudo guardar las citas", e);
        }
    }
}