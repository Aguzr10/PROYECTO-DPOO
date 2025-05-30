package interfazAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import consola.util.SistemaParque;
import parque.Administración.Administrador;
import parque.Administración.Empleados;
import parque.Atracción.Atracciones;

public class VentanaAdministrador extends JFrame {
    
    private Administrador administrador;
    private SistemaParque sistema;
    
    private JButton botonVerUsuarios;
    private JButton botonVerAtracciones;
    private JButton botonVerTiquetes;
    private JButton botonSalir;
    
    private JTable tablaAtracciones;
    private DefaultTableModel modeloAtracciones;
    private JTextArea areaUsuarios;
    
    public VentanaAdministrador(Administrador admin) {
        this.administrador = admin;
        this.sistema = SistemaParque.getInstancia();
        
        inicializarComponentes();
        configurarVentana();
        cargarDatos();
    }
    
    private void inicializarComponentes() {
        botonVerUsuarios = new JButton("Ver usuarios");
        botonVerAtracciones = new JButton("Ver atracciones");
        botonVerTiquetes = new JButton("Ver tiquetes");
        botonSalir = new JButton("Salir");

        botonVerUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarUsuarios();
            }
        });
        
        botonVerAtracciones.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               mostrarAtracciones();
           }
        });
        
        botonVerTiquetes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarTiquetes();
            }
        });
        
        botonSalir.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               salir();
           }
        });
        
        botonVerUsuarios.setPreferredSize(new Dimension(120, 30));
        botonVerAtracciones.setPreferredSize(new Dimension(120, 30));
        botonVerTiquetes.setPreferredSize(new Dimension(120, 30));
        botonSalir.setPreferredSize(new Dimension(120, 30));
        
        String[] columnasAtracciones = {"Nombre", "Tipo", "Exclusiva"};
        modeloAtracciones = new DefaultTableModel(columnasAtracciones, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaAtracciones = new JTable(modeloAtracciones);
        tablaAtracciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        areaUsuarios = new JTextArea(6, 40);
        areaUsuarios.setEditable(false);
        areaUsuarios.setBackground(Color.WHITE);
        areaUsuarios.setBorder(BorderFactory.createLoweredBevelBorder());
    }
    
    private void configurarVentana() {
        setTitle("Ventana Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBackground(Color.WHITE);
        
        JLabel labelBienvenida = new JLabel("Bienvenido, [administrador]");
        labelBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        labelBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(Color.WHITE);
        panelBotones.add(botonVerUsuarios);
        panelBotones.add(botonVerAtracciones);
        panelBotones.add(botonVerTiquetes);
        
        panelSuperior.add(labelBienvenida);
        panelSuperior.add(Box.createRigidArea(new Dimension(0, 15)));
        panelSuperior.add(panelBotones);
        
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);
       
        JPanel panelAtracciones = crearSeccionAtracciones();
        JPanel panelUsuarios = crearSeccionUsuarios();
        
        panelCentral.add(panelAtracciones);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        panelCentral.add(panelUsuarios);
        
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
    
    private JPanel crearSeccionUsuarios() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("Usuarios");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        
        JScrollPane scrollArea = new JScrollPane(areaUsuarios);
        scrollArea.setPreferredSize(new Dimension(400, 120));
        
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scrollArea, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void cargarDatos() {
        cargarAtracciones();
        cargarUsuarios();
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
    
    private void cargarUsuarios() {
        StringBuilder texto = new StringBuilder();
        List<Empleados> empleados = sistema.getEmpleados();
        
        texto.append("=== EMPLEADOS ===\n");
        if (empleados.isEmpty()) {
            texto.append("No hay empleados registrados.\n");
        } else {
           for (Empleados emp : empleados) {
               texto.append(String.format("• %s (%s) - %s\n", 
                   emp.getNombre(), emp.getLogin(), emp.getRol()));
           }
        }
        
        areaUsuarios.setText(texto.toString());
        areaUsuarios.setCaretPosition(0);
    }
    
    private void mostrarUsuarios() {
        new VentanaGestionUsuarios(this).setVisible(true);
    }
    
    private void mostrarAtracciones() {
        new VentanaGestionAtracciones(this).setVisible(true);
    }
    
    private void mostrarTiquetes() {
        JOptionPane.showMessageDialog(this, 
            "Falta implementar", 
            "Información", 
            JOptionPane.INFORMATION_MESSAGE);
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
    
    public void actualizarDatos() {
        cargarDatos();
    }
}