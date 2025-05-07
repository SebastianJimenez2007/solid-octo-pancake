/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Controllers;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import AgendarCitasAPP.Dominio.Utils.AuthService;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class LoginController {
    private JTextField tfCorreo;
    private JPasswordField pfClave;
    private AuthService authService;
    private JTabbedPane tabPanePrincipal; // Referencia al JTabbedPane
    private JButton BtnLogin; // Referencia al botón de login

    // Constructor modificado para recibir los nuevos componentes
    public LoginController(JTextField tfCorreo, JPasswordField pfClave, 
                          JTabbedPane tabPanePrincipal, JButton BtnLogin) {
        this.tfCorreo = tfCorreo;
        this.pfClave = pfClave;
        this.authService = new AuthService();
        this.tabPanePrincipal = tabPanePrincipal;
        this.BtnLogin = BtnLogin;
    }

    public void login() {
        String correo = tfCorreo.getText();
        String clave = new String(pfClave.getPassword());

        Usuario usuario = AuthService.login(correo, clave);
        if (usuario != null) {
            // 1. Cambiar a la pestaña deseada (índice 0)
            tabPanePrincipal.setSelectedIndex(0);
            
            // 2. Ocultar el botón de login
            BtnLogin.setVisible(false);
            
            JOptionPane.showMessageDialog(null, "¡Bienvenido, " + usuario.getNombre() + "!");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Credenciales incorrectas", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}