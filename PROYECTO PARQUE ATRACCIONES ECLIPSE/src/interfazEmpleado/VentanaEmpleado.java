package interfazEmpleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import parque.Atracción.Atracciones;
import parque.Administración.Empleados;
import consola.util.SistemaParque;

public class VentanaEmpleado extends JFrame {

    private Empleados empleado;
    private SistemaParque sistema;

    private JButton botonStatusParque;
    private JButton botonVerVentas;
    private JButton botonSalir;

    private JTable tablaAtracciones;
    private DefaultTableModel modeloTabla;

    private JTextArea areaHistorial;

    public VentanaEmpleado(Empleados empleado) {
        this.empleado = empleado;
        this.sistema = SistemaParque.getInstancia();
        inicializarComponentes();
        configurarVentana();
        cargarAtracciones();
    }

    private void inicializarComponentes() {
        botonStatusParque = new JButton("Status parque");
        botonVerVentas = new JButton("Ventas");
        botonSalir = new JButton("Salir");

        botonSalir.addActionListener(e -> System.exit(0));

        botonStatusParque.addActionListener(e -> {
            VentanaStatusParque ventanaStatus = new VentanaStatusParque();
            ventanaStatus.setVisible(true);
        });

        botonVerVentas.addActionListener(e -> {
            VentanaVentas ventanaVentas = null;
			try {
				ventanaVentas = new VentanaVentas();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            ventanaVentas.setVisible(true);
        });

        areaHistorial = new JTextArea(5, 40);
        areaHistorial.setEditable(false);
        areaHistorial.setBorder(BorderFactory.createTitledBorder("Historial ventas"));

        String[] columnas = {"Nombre", "Tipo", "Exclusiva"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tablaAtracciones = new JTable(modeloTabla);
        tablaAtracciones.setPreferredScrollableViewportSize(new Dimension(400, 80));
    }

    private void configurarVentana() {
        setTitle("Ventana Empleado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JLabel labelBienvenida = new JLabel("Bienvenido, " + empleado.getNombre());
        labelBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        labelBienvenida.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonStatusParque);
        panelBotones.add(botonVerVentas);

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Atracciones"));
        panelTabla.add(new JScrollPane(tablaAtracciones), BorderLayout.CENTER);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.add(panelTabla);
        panelCentral.add(Box.createVerticalStrut(10));
        panelCentral.add(areaHistorial);

        JPanel panelSalir = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSalir.add(botonSalir);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(labelBienvenida, BorderLayout.NORTH);
        getContentPane().add(panelBotones, BorderLayout.CENTER);

        JPanel panelSur = new JPanel();
        panelSur.setLayout(new BorderLayout());
        panelSur.add(panelCentral, BorderLayout.CENTER);
        panelSur.add(panelSalir, BorderLayout.SOUTH);

        getContentPane().add(panelSur, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void cargarAtracciones() {
        modeloTabla.setRowCount(0);
        List<Atracciones> atracciones = sistema.getAtracciones();
        for (Atracciones a : atracciones) {
            Object[] fila = {
                a.getNombre(),
                a.getClass().getSimpleName(),
                a.getNivelExclusividad()
            };
            modeloTabla.addRow(fila);
        }
    }
}
