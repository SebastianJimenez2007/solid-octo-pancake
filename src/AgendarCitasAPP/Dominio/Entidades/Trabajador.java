/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Dominio.Entidades;


import AgendarCitasAPP.Dominio.constantes.RolEnum;


/**e
 *
 * @author Sebastian JB
 */
public class Trabajador {
  protected String id;
    protected String clave;
    protected String nombre;
    protected String apellido;
    protected RolEnum rol;
    
    public Trabajador (String id, String clave, String nombre, String apellido, RolEnum rol){

    this.id = id;
    this.clave = clave;
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

    public RolEnum getRol() {
        return rol;
    }

    public void setRol(RolEnum rol) {
        this.rol = rol;
    }
    
    
}



