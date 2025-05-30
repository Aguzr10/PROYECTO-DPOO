package interfazAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import consola.util.SistemaParque;
import parque.Atracción.Atracciones;
import parque.Atracción.AtraccionMecanica;
import parque.Atracción.AtraccionCultural;
import parque.Atracción.RestriccionMedica;
import parque.Categorías.Categoria;
import parque.Categorías.Temporada;

public class VentanaGestionAtracciones extends JFrame {
    
    private VentanaAdministrador ventanaPadre;
    private SistemaParque sistema;
    
    private JTable tablaAtracciones;
    private DefaultTableModel modeloTabla;
    
    private JTextField campoNombre;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboCategoria;
    private JButton botonAgregar;
    private JButton botonEditar;
    private JButton botonEliminar;
    private JButton botonCerrar;
    
    public VentanaGestionAtracciones(VentanaAdministrador padre) {
        this.ventanaPadre = padre;
        this.sistema = SistemaParque.getInstancia();
        
        inicializarComponentes();
        configurarVentana();
        cargarDatos();
    }
    
    private void inicializarComponentes() {
        String[] columnas = {"Nombre", "Tipo", "Categoría", "Temporada"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaAtracciones = new JTable(modeloTabla);
        tablaAtracciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tablaAtracciones.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarAtraccionSeleccionada();
            }
        });
        
        campoNombre = new JTextField(20);
        
        comboTipo = new JComboBox<>();
        comboTipo.addItem("AtraccionMecanica");
        comboTipo.addItem("AtraccionCultural");
        
        comboCategoria = new JComboBox<>();
        comboCategoria.addItem("BASICO");
        comboCategoria.addItem("FAMILIAR");
        comboCategoria.addItem("ORO");
        comboCategoria.addItem("DIAMANTE");
        
        botonAgregar = new JButton("Agregar");
        botonEditar = new JButton("Editar");
        botonEliminar = new JButton("Eliminar");
        botonCerrar = new JButton("Cerrar");
        
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAtraccion();
            }
        });
        
        botonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarAtraccion();
            }
        });
        
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAtraccion();
            }
        });
        
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        });
        
        botonEditar.setEnabled(false);
        botonEliminar.setEnabled(false);
    }
    
    private void configurarVentana() {
        setTitle("Gestión de Atracciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.WHITE);
        JLabel titulo = new JLabel("Atracciones");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitulo.add(titulo);
        
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);
        JScrollPane scrollTabla = new JScrollPane(tablaAtracciones);
        scrollTabla.setPreferredSize(new Dimension(600, 200));
        panelTabla.add(scrollTabla, BorderLayout.CENTER);
        
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(3, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de la atracción"));
        panelFormulario.setBackground(Color.WHITE);
        
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel("Tipo:"));
        panelFormulario.add(comboTipo);
        panelFormulario.add(new JLabel("Categoría:"));
        panelFormulario.add(comboCategoria);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.WHITE);
        panelBotones.add(botonAgregar);
        panelBotones.add(botonEditar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonCerrar);
        
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);
        
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.setBackground(Color.WHITE);
        panelSur.add(panelFormulario, BorderLayout.NORTH);
        panelSur.add(panelBotones, BorderLayout.SOUTH);
        
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);
        
        add(panelPrincipal);
        pack();
        setLocationRelativeTo(ventanaPadre);
    }
    
    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        
        List<Atracciones> atracciones = sistema.getAtracciones();
        
        for (Atracciones atraccion : atracciones) {
            String temporadaStr = "Todo el año";
            
            if (atraccion.getTemporada() != null && atraccion.getTemporada() != Temporada.TODO_EL_AÑO) {
                temporadaStr = atraccion.getTemporada().getFechaInicio() + " - " + atraccion.getTemporada().getFechaFin();
            }
            
            Object[] fila = {
                atraccion.getNombre(),
                atraccion.getClass().getSimpleName(),
                atraccion.getTipoTiquete().toString(),
                temporadaStr
            };
            modeloTabla.addRow(fila);
        }
    }
    
    private void cargarAtraccionSeleccionada() {
        int fila = tablaAtracciones.getSelectedRow();
        if (fila >= 0) {
            String nombre = (String) modeloTabla.getValueAt(fila, 0);
            String tipo = (String) modeloTabla.getValueAt(fila, 1);
            String categoria = (String) modeloTabla.getValueAt(fila, 2);
            
            campoNombre.setText(nombre);
            comboTipo.setSelectedItem(tipo);
            comboCategoria.setSelectedItem(categoria);
            
            botonEditar.setEnabled(true);
            botonEliminar.setEnabled(true);
        } else {
            limpiarFormulario();
            botonEditar.setEnabled(false);
            botonEliminar.setEnabled(false);
        }
    }
    
    private void limpiarFormulario() {
        campoNombre.setText("");
        comboTipo.setSelectedIndex(0);
        comboCategoria.setSelectedIndex(0);
    }
    
    private void agregarAtraccion() {
        if (campoNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String nombre = campoNombre.getText().trim();
        String tipo = (String) comboTipo.getSelectedItem();
        String categoriaStr = (String) comboCategoria.getSelectedItem();
        
        if (existeAtraccion(nombre)) {
            JOptionPane.showMessageDialog(this, "Ya existe una atracción con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Atracciones nuevaAtraccion = crearAtraccion(nombre, tipo, categoriaStr);
        if (nuevaAtraccion != null) {
            sistema.getAtracciones().add(nuevaAtraccion);
            sistema.guardarAtracciones(sistema.getAtracciones());
            cargarDatos();
            limpiarFormulario();
            ventanaPadre.actualizarDatos();
            
            JOptionPane.showMessageDialog(this, "Atracción agregada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void editarAtraccion() {
        int fila = tablaAtracciones.getSelectedRow();
        if (fila < 0) return;
        
        if (campoNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String nombreOriginal = (String) modeloTabla.getValueAt(fila, 0);
        String nuevoNombre = campoNombre.getText().trim();
        
        if (!nombreOriginal.equals(nuevoNombre) && existeAtraccion(nuevoNombre)) {
            JOptionPane.showMessageDialog(this, "Ya existe una atracción con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        List<Atracciones> atracciones = sistema.getAtracciones();
        for (Atracciones atraccion : atracciones) {
            if (atraccion.getNombre().equals(nombreOriginal)) {
                atraccion.setNombre(nuevoNombre);
                break;
            }
        }
        
        sistema.guardarAtracciones(atracciones);
        cargarDatos();
        limpiarFormulario();
        ventanaPadre.actualizarDatos();
        
        JOptionPane.showMessageDialog(this, "Atracción actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void eliminarAtraccion() {
        int fila = tablaAtracciones.getSelectedRow();
        if (fila < 0) return;
        
        String nombre = (String) modeloTabla.getValueAt(fila, 0);
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que quiere eliminar la atracción '" + nombre + "'?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            List<Atracciones> atracciones = sistema.getAtracciones();
            for (int i = 0; i < atracciones.size(); i++) {
                if (atracciones.get(i).getNombre().equals(nombre)) {
                    atracciones.remove(i);
                    break;
                }
            }
            
            sistema.guardarAtracciones(atracciones);
            cargarDatos();
            limpiarFormulario();
            ventanaPadre.actualizarDatos();
            
            JOptionPane.showMessageDialog(this, "Atracción eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private boolean existeAtraccion(String nombre) {
        List<Atracciones> atracciones = sistema.getAtracciones();
        for (Atracciones atraccion : atracciones) {
            if (atraccion.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
    
    private Atracciones crearAtraccion(String nombre, String tipo, String categoriaStr) {
        try {
            Categoria categoria = Categoria.valueOf(categoriaStr);
            
            List<String> tipoPersonal = new ArrayList<>();
            tipoPersonal.add("Operador");
            
            if (tipo.equals("AtraccionMecanica")) {
                RestriccionMedica restriccion = new RestriccionMedica(false, false, false);
                
                return new AtraccionMecanica(
                    "Atracción Mecánica",
                    tipoPersonal,
                    nombre,
                    "Descripción por definir",
                    50,
                    2,
                    true,
                    categoria,
                    Temporada.TODO_EL_AÑO,
                    1.0,
                    2.0,
                    20.0,
                    120.0,
                    restriccion,
                    "Bajo"
                );
            } else {
                return new AtraccionCultural(
                    "Atracción Cultural",
                    tipoPersonal,
                    nombre,
                    "Descripción por definir",
                    100,
                    1,
                    false,
                    categoria,
                    Temporada.TODO_EL_AÑO,
                    false
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al crear la atracción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private void cerrarVentana() {
        dispose();
    }
}
