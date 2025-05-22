/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Controllers;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import AgendarCitasAPP.Dominio.Utils.AuthService;
import java.time.LocalDate;
import javax.swing.*;

public class SingUpController {
    private JTextField text_idSingUp;
    private JTextField text_nameSingUp;
    private JTextField text_apellidoSingUp;
    private JTextField text_correoSingUp;
    private JPasswordField text_passSingUp;
    private JPasswordField pfConfirmarClave;
    private JTextField text_cellSingUp;
    private JComboBox<String> combo_genero;
    private JComboBox<String> combo_rol;
    private JTextField text_fechaNacimientoSingUp;
    private JTextField text_direccionSingUp;
    private AuthService authService;
    private JButton btn_SingUp;
    private JButton btn_Login;

    public SingUpController(JTextField text_idSingUp, JTextField text_nameSingUp, JTextField text_apellidoSingUp, 
                          JTextField text_correoSingUp, JPasswordField text_passSingUp,
                          JPasswordField pfConfirmarClave, JTextField text_cellSingUp, 
                          JComboBox<String> combo_genero, JComboBox<String> combo_rol,
                          JTextField text_fechaNacimientoSingUp, JTextField text_direccionSingUp,
                          JButton btnSingUp, JButton btnLogin) {
        this.text_idSingUp =text_idSingUp;
        this.text_nameSingUp = text_nameSingUp;
        this.text_apellidoSingUp =text_apellidoSingUp;
        this.text_correoSingUp = text_correoSingUp;
        this.text_passSingUp = text_passSingUp;
        this.pfConfirmarClave = pfConfirmarClave;
        this.text_cellSingUp = text_cellSingUp;
        this.combo_genero = combo_genero;
        this.combo_rol = combo_rol;
        this.text_fechaNacimientoSingUp = text_fechaNacimientoSingUp;
        this.text_direccionSingUp = text_direccionSingUp;
        this.authService = new AuthService();
        this.btn_SingUp = btnSingUp;
        this.btn_Login = btnLogin;
    }

    public void registrarUsuario() {
        // Obtener valores de los campos
        String id = text_idSingUp.getText();
        String nombre = text_nameSingUp.getText();
        String apellido = text_apellidoSingUp.getText();
        String correo = text_correoSingUp.getText();
        String clave = new String(text_passSingUp.getPassword());
        String confirmarClave = new String(pfConfirmarClave.getPassword());
        String telefono = text_cellSingUp.getText();
        String genero = (String) combo_genero.getSelectedItem().toString();
        String rol = (String) combo_rol.getSelectedItem().toString();
        String fechaNacimiento = text_fechaNacimientoSingUp.getText();
        
                try {
            LocalDate.parse(fechaNacimiento); // formato debe ser yyyy-MM-dd
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fecha inválida. Usa el formato: yyyy-MM-dd", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String direccion = text_direccionSingUp.getText();

        // Validaciones básicas
        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || clave.isEmpty() || 
            confirmarClave.isEmpty() || telefono.isEmpty() || fechaNacimiento.isEmpty() || direccion.isEmpty()) {
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
        Usuario nuevoUsuario = authService.registrar(id, nombre, apellido, correo, clave, 
                telefono, genero, rol, fechaNacimiento, direccion);
        
        if (nuevoUsuario != null) {
            // Ocultar botón de registro y mostrar el de login
            btn_SingUp.setVisible(false);
            btn_Login.setVisible(true);
            
            JOptionPane.showMessageDialog(null, "¡Registro exitoso! Bienvenido/a " + nombre);
            
            // Limpiar campos
            text_idSingUp.setText("");
            text_nameSingUp.setText("");
            text_apellidoSingUp.setText("");
            text_correoSingUp.setText("");
            text_passSingUp.setText("");
            pfConfirmarClave.setText("");
            text_cellSingUp.setText("");
            text_fechaNacimientoSingUp.setText("");
            text_direccionSingUp.setText("");
            combo_genero.setSelectedItem(0);
            combo_rol.setSelectedItem(0);
        } else {
            JOptionPane.showMessageDialog(null, "Error: El correo ya está registrado", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}