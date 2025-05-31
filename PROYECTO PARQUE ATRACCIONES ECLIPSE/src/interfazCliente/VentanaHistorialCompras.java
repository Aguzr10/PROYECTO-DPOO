package interfazCliente;

import parque.Administración.Cliente;
import parque.Tiquetes.Tiquete;
import consola.util.SistemaParque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaHistorialCompras extends JFrame {

    private Cliente cliente;
    private SistemaParque sistema;
    
    private JTable tablaHistorial;
    private DefaultTableModel modeloHistorial;

    private JButton botonSalir;

    public VentanaHistorialCompras(Cliente cliente) {
        this.cliente = cliente;
        this.sistema = SistemaParque.getInstancia();

        inicializarComponentes();
        configurarVentana();
        cargarHistorialCompras();
    }

    private void inicializarComponentes() {
        String[] columnasHistorial = {"Fecha", "Tipo de Tiquete", "Precio"};
        modeloHistorial = new DefaultTableModel(columnasHistorial, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaHistorial = new JTable(modeloHistorial);
        tablaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        botonSalir = new JButton("Salir");
        botonSalir.setPreferredSize(new Dimension(120, 30));
        botonSalir.addActionListener((e) -> salir());
    }

    private void configurarVentana() {
        setTitle("Historial de Compras");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelHistorial = new JLabel("Historial de Compras de " + cliente.getNombre());
        labelHistorial.setFont(new Font("Arial", Font.BOLD, 16));
        labelHistorial.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.add(labelHistorial);
        panelSuperior.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);
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

    private JPanel crearSeccionHistorial() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Historial de Compras");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollTabla = new JScrollPane(tablaHistorial);
        scrollTabla.setPreferredSize(new Dimension(400, 120));

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);

        return panel;
    }

    private void cargarHistorialCompras() {
        modeloHistorial.setRowCount(0);
        List<Tiquete> historial = cliente.getHistorialCompras();

        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay compras registradas.", "Historial Vacío", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Tiquete t : historial) {
                Object[] fila = {
                    
                    t.getClass().getSimpleName(), 
                    t.getPrecio() 
                };
                modeloHistorial.addRow(fila);
            }
        }
    }

    private void salir() {
        dispose(); // Cierra la ventana actual
    }
}
