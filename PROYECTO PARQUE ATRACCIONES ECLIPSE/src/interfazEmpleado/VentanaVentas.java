package interfazEmpleado;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class VentanaVentas extends JFrame {

    private JTextField idClienteField;
    private JTable tablaHistorialVentas;
    private DefaultTableModel modeloHistorialVentas;
    private JButton botonSalir;

    public VentanaVentas() {
        setTitle("Ventas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        idClienteField = new JTextField(20);

        JButton botonVender = new JButton("Vender Tiquete");
        botonVender.addActionListener(e -> venderTiquete());

        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(e -> dispose());

        String[] columnasHistorial = {"ID Cliente", "Fecha", "Tipo Tiquete", "Cantidad"};
        modeloHistorialVentas = new DefaultTableModel(columnasHistorial, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaHistorialVentas = new JTable(modeloHistorialVentas);
        tablaHistorialVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panelFormulario = new JPanel(new FlowLayout());
        panelFormulario.add(new JLabel("ID Cliente:"));
        panelFormulario.add(idClienteField);
        panelFormulario.add(botonVender);

        JScrollPane scrollTabla = new JScrollPane(tablaHistorialVentas);
        scrollTabla.setPreferredSize(new Dimension(500, 150));

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.add(botonSalir);

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void configurarVentana() {
        setLocationRelativeTo(null);
    }

    private void venderTiquete() {
        String id = idClienteField.getText().trim();
        if (!id.isEmpty()) {
            modeloHistorialVentas.addRow(new Object[]{id, "2025-05-30", "General", "1"});
            idClienteField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un ID de cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
