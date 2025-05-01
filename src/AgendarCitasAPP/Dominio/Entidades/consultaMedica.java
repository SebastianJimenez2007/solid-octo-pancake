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
public class consultaMedica {
    private int idConsulta;
    private Date fechaConsulta;
    private String sintomas;
    private String observaciones;
    
    
    public consultaMedica(int idConsulta, Date fechaConsulta, String sintomas, String observaciones){
        this.idConsulta = idConsulta;
        this.fechaConsulta = fechaConsulta;
        this.sintomas = sintomas;
        this.observaciones = observaciones;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
