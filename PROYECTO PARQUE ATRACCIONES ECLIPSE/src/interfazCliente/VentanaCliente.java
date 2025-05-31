package interfazCliente;

import parque.Administración.Cliente;
import parque.Atracción.Atracciones;
import parque.Tiquetes.Tiquete;
import consola.util.SistemaParque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaCliente extends JFrame {

    private Cliente cliente;
    private SistemaParque sistema;

    private JTable tablaAtracciones;
    private DefaultTableModel modeloAtracciones;
    private JTextArea areaHistorial;

    private JButton botonSalir;
    private JButton botonInfoPersonal;
    private JButton botonHistorialCompras;

    public VentanaCliente(Cliente cliente) {
        this.cliente = cliente;
        this.sistema = SistemaParque.getInstancia();

        inicializarComponentes();
        configurarVentana();
        cargarDatos();
    }

    private void inicializarComponentes() {
        String[] columnasAtracciones = {"Nombre", "Tipo", "Exclusiva"};
        modeloAtracciones = new DefaultTableModel(columnasAtracciones, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaAtracciones = new JTable(modeloAtracciones);
        tablaAtracciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        areaHistorial = new JTextArea(6, 40);
        areaHistorial.setEditable(false);
        areaHistorial.setBackground(Color.WHITE);
        areaHistorial.setBorder(BorderFactory.createLoweredBevelBorder());

        botonSalir = new JButton("Salir");
        botonSalir.setPreferredSize(new Dimension(120, 30));
        botonSalir.addActionListener((ActionEvent e) -> salir());

        botonInfoPersonal = new JButton("Ver Información Personal");
        botonInfoPersonal.setPreferredSize(new Dimension(250, 30));
        botonInfoPersonal.addActionListener((ActionEvent e) -> mostrarInfoPersonal());

        botonHistorialCompras = new JButton("Ver Historial de Compras");
        botonHistorialCompras.setPreferredSize(new Dimension(250, 30));
        botonHistorialCompras.addActionListener((ActionEvent e) -> mostrarHistorialCompras());
    }

    private void configurarVentana() {
        setTitle("Ventana Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelBienvenida = new JLabel("Bienvenido, " + cliente.getNombre());
        labelBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        labelBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.add(labelBienvenida);
        panelSuperior.add(Box.createRigidArea(new Dimension(0, 15)));

        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.add(botonInfoPersonal);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 10)));
        panelBotones.add(botonHistorialCompras);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);
        panelCentral.add(panelBotones);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCentral.add(crearSeccionAtracciones());
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCentral.add(crearSeccionHistorial());

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBackground(Color.WHITE);
        panelInferior.add(botonSalir);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel crearSeccionAtracciones() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Atracciones");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollTabla = new JScrollPane(tablaAtracciones);
        scrollTabla.setPreferredSize(new Dimension(400, 120));

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearSeccionHistorial() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Historial de Compras");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollArea = new JScrollPane(areaHistorial);
        scrollArea.setPreferredSize(new Dimension(400, 120));

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scrollArea, BorderLayout.CENTER);

        return panel;
    }

    private void cargarDatos() {
        cargarAtracciones();
        cargarHistorial();
    }

    private void cargarAtracciones() {
        modeloAtracciones.setRowCount(0);
        List<Atracciones> atracciones = sistema.getAtracciones();

        for (Atracciones atraccion : atracciones) {
            Object[] fila = {
                    atraccion.getNombre(),
                    atraccion.getClass().getSimpleName(),
                    atraccion.getNivelExclusividad()
            };
            modeloAtracciones.addRow(fila);
        }
    }

    private void cargarHistorial() {
        StringBuilder texto = new StringBuilder();
        List<Tiquete> historial = cliente.getHistorialCompras();

        if (historial.isEmpty()) {
            texto.append("No hay compras registradas.\n");
        } else {
            for (Tiquete t : historial) {
                texto.append("• ").append(t.toString()).append("\n");
            }
        }

        areaHistorial.setText(texto.toString());
        areaHistorial.setCaretPosition(0);
    }

    private void mostrarInfoPersonal() {
        String infoPersonal = "<html><body style='width: 300px;'>";
        infoPersonal += "<b>Información Personal:</b><br><br>";
        infoPersonal += "<b>ID:</b> " + cliente.getLogin() + "<br><br>";
        infoPersonal += "<b>Nombre:</b> " + cliente.getNombre() + "<br><br>";
        infoPersonal += "<b>Correo:</b> " + cliente.getCorreo() + "<br><br>";
        infoPersonal += "<b>Método de Compra:</b> " + cliente.getMetodoCompra() + "<br><br>";
        infoPersonal += "</body></html>";
        
        JOptionPane.showMessageDialog(this, infoPersonal, "Información Personal", JOptionPane.INFORMATION_MESSAGE);
    }


    private void mostrarHistorialCompras() {
       
        VentanaHistorialCompras ventanaHistorial = new VentanaHistorialCompras(cliente);
        ventanaHistorial.setVisible(true);
    }


    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea salir?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            sistema.guardarDatos();
            System.exit(0);
        }
    }
}
