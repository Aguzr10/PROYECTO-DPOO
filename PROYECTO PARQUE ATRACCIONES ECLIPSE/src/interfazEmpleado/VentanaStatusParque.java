package interfazEmpleado;

import javax.swing.*;
import java.awt.*;

public class VentanaStatusParque extends JFrame {

    private JComboBox<String> comboEstado;
    private JButton botonConfirmar;
    private JButton botonSalir;

    public VentanaStatusParque() {
        setTitle("Estado del Parque");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        comboEstado = new JComboBox<>(new String[]{"Abierto", "Cerrado"});
        botonConfirmar = new JButton("Confirmar");
        botonSalir = new JButton("Salir");

        botonConfirmar.addActionListener(e -> {
            String estado = (String) comboEstado.getSelectedItem();
            JOptionPane.showMessageDialog(this, "El parque está ahora: " + estado);
        });

        botonSalir.addActionListener(e -> dispose());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        panelPrincipal.add(new JLabel("Seleccione el estado del parque:"));
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(comboEstado);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(botonConfirmar);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(botonSalir);

        getContentPane().add(panelPrincipal);

        pack();
        setLocationRelativeTo(null);
    }
}
