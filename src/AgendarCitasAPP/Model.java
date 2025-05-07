/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AgendarCitasAPP;

import java.io.File;

/**
 *
 * @author Sebastian JB
 */
public class Model {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Directorio actual: " + System.getProperty("user.dir"));
        System.out.println("Archivo encontrado: " + new File("Usuarios.json").exists());
        
    }
    
}
