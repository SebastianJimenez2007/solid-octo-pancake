/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Entidades;

/**
 *
 * @author JAVIER Y KLEVER
 */
public class Medico {
    private int idMedico;
    private String nombre;
    private String apellido;
    private String genero;
    private String disponibilidad;
    private String especialidad;
    private String correo;
    private String telefono;
    
    public Medico(int idMedico, String nombre, String apellido, String genero, String disponibilidad, String especialidad, String correo, String telefono){
    this.idMedico = idMedico;
    this.nombre = nombre;
    this.apellido = apellido;
    this.genero = genero;
    this.disponibilidad = disponibilidad;
    this.especialidad = especialidad;
    this.correo = correo;
    this.telefono = telefono;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getGenero() {
        return genero;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }
}
