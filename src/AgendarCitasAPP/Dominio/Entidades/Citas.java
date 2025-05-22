/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Entidades;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
/**
 *
 * @author JAVIER Y KLEVER
 */
public class Citas {
    //SEDE, CONSULTORIO, MEDICO, PACIENTE
    private int idCita;//pendiente
    private String especialidad;
    private String fechaTexto;
    private String horaTexto;
    private String estado;//pendiente
    private String medicoId;//pendiente
    private String generoPaciente;
    private String descripcion;
    private String motivo;
    private String medicamentos;
    private String especificaDolor;
    private String presenciaMalestar;

    public String getFechaTexto() {
        return fechaTexto;
    }

    public void setFechaTexto(String fechaTexto) {
        this.fechaTexto = fechaTexto;
    }

    public String getHoraTexto() {
        return horaTexto;
    }

    public void setHoraTexto(String horaTexto) {
        this.horaTexto = horaTexto;
    }
    private String malestarPrevio;
    private String intensidad;
    private String ubicacionDolor;

    public Citas(int idCita,String especialidad, String fechaTexto, String horaTexto, String estado, 
            String medicoId, String generoPaciente, String motivo, String descripcion, String medicamentos, 
            String especificaDolor, String presenciaMalestar, String malestarPrevio, String intensidad, String ubicacionDolor){
        this.idCita = idCita;//pendiente
        this.especialidad = especialidad;
        this.fechaTexto = fechaTexto;
        this.horaTexto = horaTexto;
        this.generoPaciente = generoPaciente;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.medicamentos = medicamentos;
        this.especificaDolor = especificaDolor;
        this.presenciaMalestar = presenciaMalestar;
        this.malestarPrevio = malestarPrevio;
        this.intensidad = intensidad;
        this.ubicacionDolor = ubicacionDolor;
        this.estado = estado;//pendiente
        this.medicoId = medicoId;//pendiente
    }

    public String getGeneroPaciente() {
        return generoPaciente;
    }

    public void setGeneroPaciente(String generoPaciente) {
        this.generoPaciente = generoPaciente;
    }

    public String getEspecificaDolor() {
        return especificaDolor;
    }

    public void setEspecificaDolor(String especificaDolor) {
        this.especificaDolor = especificaDolor;
    }
    
    public Citas() {
    // Constructor vacío para poder crear una cita sin pasar parámetros
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getPresenciaMalestar() {
        return presenciaMalestar;
    }

    public void setPresenciaMalestar(String presenciaMalestar) {
        this.presenciaMalestar = presenciaMalestar;
    }

    public String getMalestarPrevio() {
        return malestarPrevio;
    }

    public void setMalestarPrevio(String malestarPrevio) {
        this.malestarPrevio = malestarPrevio;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    public String getUbicacionDolor() {
        return ubicacionDolor;
    }

    public void setUbicacionDolor(String ubicacionDolor) {
        this.ubicacionDolor = ubicacionDolor;
    }
    
    //Inicio
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }
    //Fin
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    //inicio
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(String medicoId) {
        this.medicoId = medicoId;
    }
    //fin
}