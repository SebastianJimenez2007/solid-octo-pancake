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
public class historialMedico {
    private int idHistorial;
    private Date fechaCreacion;
    private List consultaMedica;
    
    public historialMedico(int idHistorialMedico, Date fechaCreacion,List consultaMedica){
        this.idHistorial = idHistorial;
        this.fechaCreacion = fechaCreacion;
        this.consultaMedica = consultaMedica;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setConsultaMedica(List consultaMedica) {
        this.consultaMedica = consultaMedica;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public List getConsultaMedica() {
        return consultaMedica;
    }
}
