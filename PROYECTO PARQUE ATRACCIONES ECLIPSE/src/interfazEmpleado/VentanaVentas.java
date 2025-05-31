package interfazEmpleado;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.table.DefaultTableModel;
import parque.Tiquetes.*;
import parque.Categorías.Categoria;
import com.google.zxing.WriterException;
import java.io.IOException;
import java.io.InputStream;

import consola.util.CategoriaUtils;

public class VentanaVentas extends JFrame {

    private JTextField idClienteField;
    private JTable tablaHistorialVentas;
    private DefaultTableModel modeloHistorialVentas;
    private JButton botonSalir;

    public VentanaVentas() throws IOException {
        setTitle("Ventas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() throws IOException {
        idClienteField = new JTextField(20);

        JButton botonVender = new JButton("Vender Tiquete");
        botonVender.addActionListener(e -> venderTiquete());

        JButton botonImprimir = new JButton("Imprimir Tiquete");
        botonImprimir.addActionListener(e -> {
			imprimirTiqueteSeleccionado();
		});

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
        panelFormulario.add(botonImprimir); // Agrego el botón aquí

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

    private void imprimirTiqueteSeleccionado() {
        int filaSeleccionada = tablaHistorialVentas.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un tiquete para imprimir.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idCliente = (String) modeloHistorialVentas.getValueAt(filaSeleccionada, 0);
        String fechaExpedicion = (String) modeloHistorialVentas.getValueAt(filaSeleccionada, 1);
        Categoria categoria = CategoriaUtils.obtenerCategoriaAleatoria();

        Tiquete tiqueteSimulado = new Tiquete(categoria, false, 0) {
            private static final long serialVersionUID = 1L;
            @Override public String getId() { return idCliente + "-" + filaSeleccionada; }
        };

        try {
            BufferedImage imagenBase;
            try (InputStream is = getClass().getResourceAsStream("/parque/Tiquetes/TIQUETE.jpeg")) {
                if (is == null) throw new IOException("No se encontró la imagen del tiquete base");
                imagenBase = ImageIO.read(is);
            }

            String textoQR = "Tiquete categoría: " + categoria.toString() + "\n"
                            + "ID: " + tiqueteSimulado.getId() + "\n"
                            + "Fecha expedición: " + fechaExpedicion;

           
            BufferedImage imagenQR = QRs.generarQRImagen(textoQR, 114, 118);

            BufferedImage imagenFinal = new BufferedImage(imagenBase.getWidth(), imagenBase.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = imagenFinal.createGraphics();
            g.drawImage(imagenBase, 0, 0, null);

           
         
            int x = 222; 
            int y = (imagenBase.getHeight() - imagenQR.getHeight()) / 2;


            g.drawImage(imagenQR, x, y, null);
            g.dispose();

            JLabel label = new JLabel(new ImageIcon(imagenFinal));
            JOptionPane.showMessageDialog(this, label, "Tiquete Impreso - Categoría: " + categoria, JOptionPane.INFORMATION_MESSAGE);

        } catch (WriterException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al generar QR o cargar imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }







}
