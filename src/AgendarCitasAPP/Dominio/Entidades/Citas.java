/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Entidades;
import java.util.*;
/**
 *
 * @author JAVIER Y KLEVER
 */
public class Citas {
    //SEDE, CONSULTORIO, MEDICO, PACIENTE
    private int idCita;
    private Date fecha;
    private Date hora;
    private String estado;
    private String prioridadUrgencia;
    
    public Citas(int idCita, Date fecha, Date hora, String estado, String prioridadUrgencia){
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.prioridadUrgencia = prioridadUrgencia;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPrioridadUrgencia(String prioridadUrgencia) {
        this.prioridadUrgencia = prioridadUrgencia;
    }

    public int getIdCita() {
        return idCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public Date getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public String getPrioridadUrgencia() {
        return prioridadUrgencia;
    }
}
