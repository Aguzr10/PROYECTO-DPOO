package interfazCliente;

import parque.Administración.Cliente;
import parque.Persistencia.PersistenciaArchivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RegistroCliente extends JFrame {
    private JTextField txtId, txtNombre, txtCorreo, txtMetodoCompra;
    private JButton btnRegistrar;
    private PersistenciaArchivo persistencia;

    public RegistroCliente() {
        persistencia = new PersistenciaArchivo();
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        txtId = new JTextField(20);
        txtNombre = new JTextField(20);
        txtCorreo = new JTextField(20);
        txtMetodoCompra = new JTextField(20); 
        btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(this::registrarCliente);
    }

    private void configurarVentana() {
        setTitle("Registro de Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Registro Cliente");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(titulo, c);

        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("ID:"), c);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtId, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("Nombre:"), c);

        c.gridx = 1;
        c.gridy = 2;
        panel.add(txtNombre, c);

        c.gridx = 0;
        c.gridy = 3;
        panel.add(new JLabel("Correo:"), c);

        c.gridx = 1;
        c.gridy = 3;
        panel.add(txtCorreo, c);

        c.gridx = 0;
        c.gridy = 4;
        panel.add(new JLabel("Método de compra:"), c);

        c.gridx = 1;
        c.gridy = 4;
        panel.add(txtMetodoCompra, c); 

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        panel.add(btnRegistrar, c);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void registrarCliente(ActionEvent e) {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String metodoCompra = txtMetodoCompra.getText().trim(); 

        if (id.isEmpty() || nombre.isEmpty() || correo.isEmpty() || metodoCompra.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Cliente> clientes = persistencia.cargarClientes();

        for (Cliente c : clientes) {
            if (c.getLogin().equals(id)) {
                JOptionPane.showMessageDialog(this, "El cliente ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Cliente nuevo = new Cliente(id, nombre, metodoCompra, correo);
        clientes.add(nuevo);
        persistencia.guardarClientes(clientes);

        JOptionPane.showMessageDialog(this, "Registro exitoso");

        new VentanaCliente(nuevo).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroCliente().setVisible(true));
    }
}
