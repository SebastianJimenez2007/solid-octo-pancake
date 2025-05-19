/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AgendarCitasAPP.view;

import AgendarCitasAPP.Controllers.AdminController;
import AgendarCitasAPP.Dominio.Entidades.Paciente;
import AgendarCitasAPP.Dominio.Utils.ValidacionUtils;
import AgendarCitasAPP.Dominio.constantes.GeneroEnum;
import AgendarCitasAPP.Dominio.constantes.RolEnum;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sebas
 */
public class EditarPacienteFrame extends javax.swing.JFrame {
        private final Paciente paciente;
    private final DefaultTableModel modeloTabla;

    public EditarPacienteFrame(Paciente paciente, DefaultTableModel modeloTabla) {
        this.paciente = paciente;
        this.modeloTabla = modeloTabla;
        
        initComponents();
        configurarInterfaz();
        cargarDatosPaciente();
        configurarEventos();
    }

    private void configurarInterfaz() {
        
        setLocationRelativeTo(null);
        
        // Configurar combobox de género
        cmbGenero.removeAllItems();
        for (GeneroEnum genero : GeneroEnum.values()) {
            cmbGenero.addItem(genero.toString());
        }
        
        // Configurar combobox de rol (solo lectura si es necesario)
        cmbRol.removeAllItems();
        cmbRol.addItem(RolEnum.PACIENTE.toString());
        cmbRol.setEnabled(false); // El rol no se puede modificar
    }

    private void cargarDatosPaciente() {
        // Datos básicos
        txtId.setText(paciente.getId());
        txtNombre.setText(paciente.getNombre());
        txtApellido.setText(paciente.getApellido());
        txtClave.setText(paciente.getClave());
        txtCorreo.setText(paciente.getCorreo());
        
        // Fecha de nacimiento
        if (paciente.getFechaNacimiento() != null) {
            txtFechaNacimiento.setText(paciente.getFechaNacimiento().toString());
        }
        
        // Otros campos
        txtDireccion.setText(paciente.getDireccion());
        txtTele.setText(paciente.getTelefono());
        
        // Seleccionar género y rol
        if (paciente.getGenero() != null) {
            cmbGenero.setSelectedItem(paciente.getGenero().toString());
        }
        cmbRol.setSelectedItem(paciente.getRol().toString());
        
       
    }

    private void configurarEventos() {
        BtnCancelar.addActionListener(e -> this.dispose());
        
        BtnEditar.addActionListener(e -> {
            try {
                guardarCambios();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al guardar: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void guardarCambios() throws Exception {
        validarCampos();
        actualizarDatosPaciente();
        
        AdminController.actualizarUsuario(
            paciente.getId(),
            paciente.getNombre(),
            paciente.getApellido(),
            paciente.getClave(),
            paciente.getFechaNacimiento().toString(),
            paciente.getDireccion(),
            paciente.getTelefono(),
            paciente.getCorreo(),
            paciente.getGenero().toString(),
            paciente.getRol().toString()
        );
        
        // Actualizar tabla
        AdminController.cargarUsuariosEnTabla(modeloTabla);
        
        JOptionPane.showMessageDialog(this,
            "Paciente actualizado correctamente",
            "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
        
        this.dispose();
    }

    private void validarCampos() throws Exception {
        if (txtNombre.getText().trim().isEmpty() || 
            txtApellido.getText().trim().isEmpty()) {
            throw new Exception("Nombre y apellido son obligatorios");
        }
        
        if (!ValidacionUtils.esCorreoValido(txtCorreo.getText())) {
            throw new Exception("El correo electrónico no es válido");
        }
        
        try {
            LocalDate.parse(txtFechaNacimiento.getText());
        } catch (Exception e) {
            throw new Exception("Formato de fecha inválido. Use YYYY-MM-DD");
        }
    }

    private void actualizarDatosPaciente() {
        paciente.setNombre(txtNombre.getText().trim());
        paciente.setApellido(txtApellido.getText().trim());
        paciente.setClave(txtClave.getText());
        paciente.setCorreo(txtCorreo.getText().trim());
        paciente.setFechaNacimiento(LocalDate.parse(txtFechaNacimiento.getText()));
        paciente.setDireccion(txtDireccion.getText());
        paciente.setTelefono(txtTele.getText());
        paciente.setGenero(GeneroEnum.valueOf(cmbGenero.getSelectedItem().toString()));
        
    

    // Actualizar campo específico de Paciente
    //paciente.setHistorialMedico(txtHistorialMedico.getText());
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        LabelEditarUsuario = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtClave = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtFechaNacimiento = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtTele = new javax.swing.JTextField();
        cmbRol = new javax.swing.JComboBox<>();
        cmbGenero = new javax.swing.JComboBox<>();
        BtnEditar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(49, 82, 192));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Planifica+");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, -370, 300, -1));

        LabelEditarUsuario.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        LabelEditarUsuario.setForeground(new java.awt.Color(49, 82, 192));
        LabelEditarUsuario.setText("Editar Usuario");
        jPanel3.add(LabelEditarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtId.setEditable(false);
        txtId.setForeground(new java.awt.Color(153, 153, 153));
        txtId.setText("Id");
        txtId.setBorder(null);
        jPanel3.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 234, 34));

        txtClave.setForeground(new java.awt.Color(153, 153, 153));
        txtClave.setText("correo");
        txtClave.setBorder(null);
        jPanel3.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 234, 34));

        txtNombre.setForeground(new java.awt.Color(153, 153, 153));
        txtNombre.setText("contraseña");
        txtNombre.setBorder(null);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 234, 34));

        txtApellido.setForeground(new java.awt.Color(153, 153, 153));
        txtApellido.setText("telefono");
        txtApellido.setBorder(null);
        jPanel3.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 234, 34));

        txtFechaNacimiento.setForeground(new java.awt.Color(153, 153, 153));
        txtFechaNacimiento.setText("telefono");
        txtFechaNacimiento.setBorder(null);
        jPanel3.add(txtFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 234, 34));

        txtDireccion.setForeground(new java.awt.Color(153, 153, 153));
        txtDireccion.setText("telefono");
        txtDireccion.setBorder(null);
        jPanel3.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 234, 34));

        txtCorreo.setForeground(new java.awt.Color(153, 153, 153));
        txtCorreo.setBorder(null);
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        jPanel3.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 234, 34));

        txtTele.setForeground(new java.awt.Color(153, 153, 153));
        txtTele.setBorder(null);
        jPanel3.add(txtTele, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 234, 34));

        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PACIENTE", "EMPLEADO", "MEDICO", "ADMIN" }));
        jPanel3.add(cmbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 230, 30));

        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO" }));
        jPanel3.add(cmbGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 230, 30));

        BtnEditar.setBackground(new java.awt.Color(49, 82, 192));
        BtnEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnEditar.setForeground(new java.awt.Color(255, 255, 255));
        BtnEditar.setText("Editar");
        BtnEditar.setBorder(null);
        jPanel3.add(BtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 234, 34));

        BtnCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BtnCancelar.setForeground(new java.awt.Color(49, 82, 192));
        BtnCancelar.setText("Cancelar");
        BtnCancelar.setBorder(null);
        jPanel3.add(BtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 340, 234, 34));

        jScrollPane1.setViewportView(jPanel3);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 800, 430));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JLabel LabelEditarUsuario;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTele;
    // End of variables declaration//GEN-END:variables
}
