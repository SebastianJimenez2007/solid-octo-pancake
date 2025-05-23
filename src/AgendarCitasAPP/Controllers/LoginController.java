/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendarCitasAPP.Controllers;

import AgendarCitasAPP.Dominio.Entidades.Usuario;
import AgendarCitasAPP.Dominio.Entidades.Paciente;
import AgendarCitasAPP.Dominio.Entidades.Medico;
import AgendarCitasAPP.Dominio.Utils.JsonUtils;
import AgendarCitasAPP.view.Admin;
import AgendarCitasAPP.view.inicio;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.swing.*;

public class LoginController {
    private JTextField tfCorreo;
    private JPasswordField pfClave;
    private JComboBox<String> comboRol;
    private JTabbedPane tabPanePrincipal;
    private JButton BtnLogin;
    private JButton BtnCerrarSesion;
    private JFrame ventanaInicio;

  public LoginController(JTextField tfCorreo, JPasswordField pfClave, JComboBox<String> comboRol1,
                       JTabbedPane tabPanePrincipal, JButton BtnLogin, JButton BtnCerrarSesion) {
    this.tfCorreo = tfCorreo;
    this.pfClave = pfClave;
    this.comboRol = comboRol1;
    this.tabPanePrincipal = tabPanePrincipal;
    this.BtnLogin = BtnLogin;
    this.BtnCerrarSesion = BtnCerrarSesion;
    
}
    public void login() {
        String correo = tfCorreo.getText();
        String clave = new String(pfClave.getPassword());
        String rolSeleccionado = comboRol.getSelectedItem().toString();

        boolean accesoConcedido = false;

        try {
            switch (rolSeleccionado.toLowerCase()) {
                case "paciente":
                    Type tipoPacientes = new TypeToken<List<Paciente>>(){}.getType();
                    List<Paciente> pacientes = JsonUtils.leerJson("Pacientes.json", tipoPacientes);
                    for (Paciente p : pacientes) {
                        if (p.getCorreo().equalsIgnoreCase(correo) && p.getClave().equals(clave)) {
                            accesoConcedido = true;
                            tabPanePrincipal.setSelectedIndex(0); // Pestaña de Pacientes
                            break;
                        }
                    }
                    break;
                case "medico":
                    Type tipoMedicos = new TypeToken<List<Medico>>(){}.getType();
                    List<Medico> medicos = JsonUtils.leerJson("Medicos.json", tipoMedicos);
                    for (Medico m : medicos) {
                        if (m.getCorreo().equalsIgnoreCase(correo) && m.getClave().equals(clave)) {
                            accesoConcedido = true;
                            tabPanePrincipal.setSelectedIndex(2); // Pestaña de Médicos
                            break;
                        }
                    }
                    break;
                case "admin":
                    Type tipoAdmins = new TypeToken<List<Usuario>>(){}.getType();
                    List<Usuario> admins = JsonUtils.leerJson("Admins.json", tipoAdmins);
                    for (Usuario u : admins) {
                        if (u.getCorreo().equalsIgnoreCase(correo) && u.getClave().equals(clave)) {
                            accesoConcedido = true;

                            // Mostrar ventana de administrador
                            new Admin().setVisible(true);
                            

                            // Ocultar botón de login y mostrar botón de cerrar sesión
                            BtnLogin.setVisible(false);
                            BtnCerrarSesion.setVisible(true);

            break;
        }
    }
    break;
            }

            if (accesoConcedido) {
                JOptionPane.showMessageDialog(null, "¡Bienvenido!");
                BtnLogin.setVisible(false);
                BtnCerrarSesion.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas o rol no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error durante el login: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}