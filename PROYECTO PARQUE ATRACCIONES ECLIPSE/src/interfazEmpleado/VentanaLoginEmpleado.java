package interfazEmpleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import consola.util.Autenticador;
import consola.util.SistemaParque;
import parque.Administración.Empleados;

public class VentanaLoginEmpleado extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoPassword;
    private JButton botonLogin;
    private JLabel etiquetaError;

    public VentanaLoginEmpleado() {
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        campoUsuario = new JTextField(20);
        campoPassword = new JPasswordField(20);
        botonLogin = new JButton("Iniciar sesión");
        etiquetaError = new JLabel(" ");

        etiquetaError.setForeground(Color.RED);
        etiquetaError.setFont(new Font("Arial", Font.PLAIN, 12));

        botonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                intentarLogin();
            }
        });

        campoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                intentarLogin();
            }
        });

        campoPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                intentarLogin();
            }
        });
    }

    private void configurarVentana() {
        setTitle("Inicio de sesión de Empleado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Inicio de sesión de Empleado");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(titulo, c);

        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Usuario:"), c);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoUsuario, c);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Contraseña:"), c);

        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoPassword, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        panel.add(etiquetaError, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        panel.add(botonLogin, c);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void intentarLogin() {
        String usuario = campoUsuario.getText().trim();
        String password = new String(campoPassword.getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {
            etiquetaError.setText("Complete todos los campos");
            return;
        }

        Empleados empleado = Autenticador.autenticarEmpleado(usuario, password);

        if (empleado != null) {
            this.dispose();
            new VentanaEmpleado(empleado).setVisible(true);  // <-- aquí se pasa el objeto empleado
        } else {
            etiquetaError.setText("Credenciales inválidas");
            campoPassword.setText("");
        }
    }

   

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SistemaParque sistema = SistemaParque.getInstancia();
        sistema.cargarDatos();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaLoginEmpleado().setVisible(true);
            }
        });
    }
}
