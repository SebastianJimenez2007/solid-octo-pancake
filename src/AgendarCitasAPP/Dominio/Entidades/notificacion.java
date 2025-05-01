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
public class notificacion {
    private int idNotificacion;
    private String mensaje;
    private Date fechaEnvio;
    private String canal;
    
    public notificacion(int idNotificacion, String mensaje, Date fechaEnvio, String canal){
        this.idNotificacion = idNotificacion;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.canal = canal;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public String getCanal() {
        return canal;
    }
}
