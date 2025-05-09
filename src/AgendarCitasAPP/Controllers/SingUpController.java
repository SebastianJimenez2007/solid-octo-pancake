/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Controllers;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import AgendarCitasAPP.Dominio.Utils.AuthService;
import javax.swing.*;

public class SingUpController {
    private JTextField text_nameSingUp;
    private JTextField text_correoSingUp;
    private JPasswordField text_passSingUp;
    private JPasswordField pfConfirmarClave;
    private JTextField text_cellSingUp;
    private JTextField text_generoSingUp;
    private JTextField text_rolSingUp;
    private AuthService authService;
    private JButton btn_SingUp;
    private JButton btn_Login;

    public SingUpController(JTextField text_nameSingUp, JTextField text_correoSingUp, JPasswordField text_passSingUp,
                          JPasswordField pfConfirmarClave, JTextField text_cellSingUp, 
                          JTextField text_generoSingUp, JTextField text_rolSingUp,
                          JButton btnSingUp, JButton btnLogin) {
        this.text_nameSingUp = text_nameSingUp;
        this.text_correoSingUp = text_correoSingUp;
        this.text_passSingUp = text_passSingUp;
        this.pfConfirmarClave = pfConfirmarClave;
        this.text_cellSingUp = text_cellSingUp;
        this.text_generoSingUp = text_generoSingUp;
        this.text_rolSingUp = text_rolSingUp;
        this.authService = new AuthService();
        this.btn_SingUp = btnSingUp;
        this.btn_Login = btnLogin;
    }

    public void registrarUsuario() {
        // Obtener valores de los campos
        String nombre = text_nameSingUp.getText();
        String correo = text_correoSingUp.getText();
        String clave = new String(text_passSingUp.getPassword());
        String confirmarClave = new String(pfConfirmarClave.getPassword());
        String telefono = text_cellSingUp.getText();
        String genero = text_generoSingUp.getText();
        String rol = text_rolSingUp.getText();

        // Validaciones básicas
        if (nombre.isEmpty() || correo.isEmpty() || clave.isEmpty() || 
            confirmarClave.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!clave.equals(confirmarClave)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (clave.length() < 6) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Registrar usuario
        Usuario nuevoUsuario = authService.registrar(nombre, correo, clave, telefono, genero, rol);
        
        if (nuevoUsuario != null) {
            // Ocultar botón de registro y mostrar el de login
            btn_SingUp.setVisible(false);
            btn_Login.setVisible(true);
            
            JOptionPane.showMessageDialog(null, "¡Registro exitoso! Bienvenido/a " + nombre);
            
            // Limpiar campos
            text_nameSingUp.setText("");
            text_correoSingUp.setText("");
            text_passSingUp.setText("");
            pfConfirmarClave.setText("");
            text_cellSingUp.setText("");
            text_generoSingUp.setText("");
            text_rolSingUp.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Error: El correo ya está registrado", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}