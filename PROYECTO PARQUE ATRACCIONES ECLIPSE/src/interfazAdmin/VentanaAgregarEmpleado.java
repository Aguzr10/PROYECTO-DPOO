package interfazAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import consola.util.SistemaParque;
import parque.Servicios.Cajero;
import parque.Administración.Empleados;

public class VentanaAgregarEmpleado extends JFrame {

 private VentanaGestionUsuarios ventanaPadre;
   private SistemaParque sistema;

     private JTextField campoLogin;
      private JPasswordField campoPassword;
   private JTextField campoNombre;
      private JComboBox<String> comboTurno;
  private JTextField campoLugar;
     private JComboBox<String> comboRol;
       private JTextField campoDescuento;
    private JTextField campoTipoFuncion;
     private JButton botonGuardar;
       private JButton botonCancelar;
         private JLabel labelError;

   public VentanaAgregarEmpleado(VentanaGestionUsuarios padre) {
     this.ventanaPadre = padre;
         this.sistema = SistemaParque.getInstancia();

       inicializarComponentes();
       configurarVentana();
   }

   private void inicializarComponentes() {
     campoLogin = new JTextField(15);
   campoPassword = new JPasswordField(15);
      campoNombre = new JTextField(15);
       campoLugar = new JTextField(15);
     campoDescuento = new JTextField(15);
     campoTipoFuncion = new JTextField(15);

     String[] turnos = {"Apertura", "Cierre"};
     comboTurno = new JComboBox<String>(turnos);

     String[] roles = {"Cajero", "Cocinero", "Operador Atracciones", "Servicio general"};
     comboRol = new JComboBox<String>(roles);

    botonGuardar = new JButton("Guardar");
       botonCancelar = new JButton("Cancelar");

      labelError = new JLabel(" ");
   labelError.setForeground(Color.RED);
       labelError.setFont(new Font("Arial", Font.PLAIN, 12));

     botonGuardar.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
            guardarEmpleado();
        }
     });

      botonCancelar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            cancelar();
        }
     });

    comboRol.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           actualizarCamposSegunRol();
        }
     });
   }

   private void configurarVentana() {
     setTitle("Agregar Nuevo Empleado");
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     setResizable(false);

     JPanel panel = new JPanel();
      panel.setLayout(new GridBagLayout());
     panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    GridBagConstraints c = new GridBagConstraints();
     c.insets = new Insets(5, 5, 5, 5);
       c.anchor = GridBagConstraints.WEST;

     JLabel titulo = new JLabel("Crear Nuevo Empleado");
     titulo.setFont(new Font("Arial", Font.BOLD, 16));
       c.gridx = 0;
        c.gridy = 0;
         c.gridwidth = 2;
     c.anchor = GridBagConstraints.CENTER;
       panel.add(titulo, c);

     c.gridwidth = 1;
     c.anchor = GridBagConstraints.WEST;

    c.gridx = 0; c.gridy = 1;
     panel.add(new JLabel("Login:"), c);
    c.gridx = 1;
      panel.add(campoLogin, c);

     c.gridx = 0; c.gridy = 2;
      panel.add(new JLabel("Contraseña:"), c);
      c.gridx = 1;
      panel.add(campoPassword, c);

     c.gridx = 0; c.gridy = 3;
      panel.add(new JLabel("Nombre:"), c);
      c.gridx = 1;
     panel.add(campoNombre, c);

     c.gridx = 0; c.gridy = 4;
      panel.add(new JLabel("Turno:"), c);
     c.gridx = 1;
     panel.add(comboTurno, c);

     c.gridx = 0; c.gridy = 5;
       panel.add(new JLabel("Lugar asignado:"), c);
        c.gridx = 1;
     panel.add(campoLugar, c);

     c.gridx = 0; c.gridy = 6;
        panel.add(new JLabel("Rol:"), c);
        c.gridx = 1;
     panel.add(comboRol, c);

     c.gridx = 0; c.gridy = 7;
      panel.add(new JLabel("Descuento (%):"), c);
      c.gridx = 1;
     panel.add(campoDescuento, c);

     c.gridx = 0; c.gridy = 8;
     panel.add(new JLabel("Tipo función:"), c);
      c.gridx = 1;
     panel.add(campoTipoFuncion, c);

     c.gridx = 0; c.gridy = 9;
     c.gridwidth = 2;
     c.anchor = GridBagConstraints.CENTER;
     panel.add(labelError, c);

     JPanel panelBotones = new JPanel();
     panelBotones.setLayout(new FlowLayout());
     panelBotones.setBackground(Color.WHITE);
     panelBotones.add(botonGuardar);
     panelBotones.add(botonCancelar);

     c.gridx = 0; c.gridy = 10;
     c.gridwidth = 2;
     panel.add(panelBotones, c);

     add(panel);
     pack();
     setLocationRelativeTo(ventanaPadre);

     actualizarCamposSegunRol();
   }

   private void actualizarCamposSegunRol() {
     String rol = (String) comboRol.getSelectedItem();

     if (rol.equals("Cajero")) {
         campoDescuento.setEnabled(true);
         campoTipoFuncion.setEnabled(false);
         campoTipoFuncion.setText("");
     } else {
         campoDescuento.setEnabled(false);
         campoDescuento.setText("");
         campoTipoFuncion.setEnabled(true);
     }
   }

   private void guardarEmpleado() {
     labelError.setText(" ");
       if (!validarCampos()) {
        return;
       }

     try {
        String login = campoLogin.getText().trim();
       String password = new String(campoPassword.getPassword());
       String nombre = campoNombre.getText().trim();
         String turno = (String) comboTurno.getSelectedItem();
        String lugar = campoLugar.getText().trim();
        String rol = (String) comboRol.getSelectedItem();

       if (sistema.existeEmpleado(login)) {
         labelError.setText("Ya existe un empleado con ese login");
         return;
       }

       Empleados empleado;

        if (rol.equals("Cajero")) {
           double descuento = Double.parseDouble(campoDescuento.getText().trim());
            if (descuento < 0 || descuento > 100) {
               labelError.setText("El descuento debe estar entre 0 y 100");
               return;
            }

           empleado = new Cajero(login, password, null, "manual", nombre,
                  turno, lugar, "Cajero", descuento, "Venta de tiquetes");
        } else {
            String tipoFuncion = campoTipoFuncion.getText().trim();
            empleado = new Empleados(login, password, null, "manual", nombre,
                  turno, lugar, tipoFuncion, 0.0);
        }

       sistema.agregarEmpleado(empleado);

        JOptionPane.showMessageDialog(this,
           "Empleado agregado correctamente.",
           "Éxito",
           JOptionPane.INFORMATION_MESSAGE);

       ventanaPadre.actualizarTabla();
       this.dispose();

     } catch (NumberFormatException e) {
         labelError.setText("El descuento debe ser un número válido");
     } catch (Exception e) {
         labelError.setText("Error inesperado: " + e.getMessage());
     }
   }

   private boolean validarCampos() {
     String login = campoLogin.getText().trim();
     String password = new String(campoPassword.getPassword());
     String nombre = campoNombre.getText().trim();
     String lugar = campoLugar.getText().trim();
     String rol = (String) comboRol.getSelectedItem();

     if (login.isEmpty()) {
       labelError.setText("El login es obligatorio");
       campoLogin.requestFocus();
       return false;
     }

     if (password.isEmpty()) {
       labelError.setText("La contraseña es obligatoria");
       campoPassword.requestFocus();
       return false;
     }

     if (nombre.isEmpty()) {
       labelError.setText("El nombre es obligatorio");
       campoNombre.requestFocus();
       return false;
     }

     if (lugar.isEmpty()) {
       labelError.setText("El lugar asignado es obligatorio");
       campoLugar.requestFocus();
       return false;
     }

     if (rol.equals("Cajero")) {
       String descuento = campoDescuento.getText().trim();
         if (descuento.isEmpty()) {
           labelError.setText("El descuento es obligatorio para cajeros");
           campoDescuento.requestFocus();
           return false;
         }
     } else {
       String tipoFuncion = campoTipoFuncion.getText().trim();
       if (tipoFuncion.isEmpty()) {
         labelError.setText("El tipo de función es obligatorio");
         campoTipoFuncion.requestFocus();
         return false;
       }
     }

     return true;
   }

   private void cancelar() {
     int respuesta = JOptionPane.showConfirmDialog(this,
        "¿Está seguro que desea cancelar? Se perderán los datos ingresados.",
        "Confirmar cancelación",
        JOptionPane.YES_NO_OPTION);

     if (respuesta == JOptionPane.YES_OPTION) {
         this.dispose();
     }
   }
}
