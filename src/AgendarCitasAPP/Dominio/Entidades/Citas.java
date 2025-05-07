/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Entidades;
import java.time.LocalDate;
import java.util.*;
/**
 *
 * @author JAVIER Y KLEVER
 */
public class Citas {
    //SEDE, CONSULTORIO, MEDICO, PACIENTE
    private int idCita;
    private String especialidad;
    private LocalDate fecha;
    private String estado;
    private String prioridadUrgencia;
    
    public Citas(int idCita,String especialidad, LocalDate fecha, Date hora, String estado, String prioridadUrgencia){
        this.idCita = idCita;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.estado = estado;
        this.prioridadUrgencia = prioridadUrgencia;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridadUrgencia() {
        return prioridadUrgencia;
    }

    public void setPrioridadUrgencia(String prioridadUrgencia) {
        this.prioridadUrgencia = prioridadUrgencia;
    }
    
    

}