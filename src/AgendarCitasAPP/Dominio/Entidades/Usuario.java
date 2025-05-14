/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Entidades;

import AgendarCitasAPP.Dominio.constantes.GeneroEnum;
import AgendarCitasAPP.Dominio.constantes.RolEnum;
import java.time.LocalDate;

/**
 *
 * @author Sebastian JB
 */
public class Usuario {

    protected String id;
    protected String clave;
    protected String nombre;
    protected String apellido;
    protected LocalDate fechaNacimiento;
    protected GeneroEnum genero;
    protected String direccion;
    protected String correo;
    protected String telefono;
    protected RolEnum rol;
    
     // Constructor completo
    public Usuario(String id, String clave, String nombre, String apellido, 
                  LocalDate fechaNacimiento, GeneroEnum genero, String direccion, 
                  String correo, String telefono, RolEnum rol) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.rol = rol;
    }
    
    // Constructor mínimo (para casos simples)
    public Usuario(String id, String clave, String nombre) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
    }

    public Usuario() {
    }

    public Usuario(String id, String nombre, String apellido, RolEnum rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public RolEnum getRol() {
        return rol;
    }

    public void setRol(RolEnum rol) {
        this.rol = rol;
    }
    
  
}
