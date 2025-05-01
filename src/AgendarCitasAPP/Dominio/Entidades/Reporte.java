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
public class Reporte {
    private int idReporte;
    private String tipoReporte;
    private Date fechaGeneracio;
    private String contenido;
    
    public Reporte(int idReporte, String tipoReporte, Date fechaGeneracio, String contenido){
        this.idReporte = idReporte;
        this.tipoReporte = tipoReporte;
        this.fechaGeneracio = fechaGeneracio;
        this.contenido = contenido;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public void setFechaGeneracio(Date fechaGeneracio) {
        this.fechaGeneracio = fechaGeneracio;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public Date getFechaGeneracio() {
        return fechaGeneracio;
    }

    public String getContenido() {
        return contenido;
    }
}
