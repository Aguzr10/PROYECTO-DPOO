package interfazAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import consola.util.SistemaParque;
import parque.Administración.Empleados;

public class VentanaGestionUsuarios extends JFrame {

   private VentanaAdministrador ventanaPadre;
      private SistemaParque sistema;

  private JTable tablaEmpleados;
     private DefaultTableModel modeloEmpleados;
 private JButton botonAgregar;
     private JButton botonEliminar;
  private JButton botonVolver;

     public VentanaGestionUsuarios(VentanaAdministrador padre) {
       this.ventanaPadre = padre;
        this.sistema = SistemaParque.getInstancia();

       inicializarComponentes();
    configurarVentana();
        cargarEmpleados();
     }

    private void inicializarComponentes() {
     String[] columnas = {"Login", "Nombre", "Rol", "Turno", "Lugar"};
         modeloEmpleados = new DefaultTableModel(columnas, 0) {
        public boolean isCellEditable(int row, int column) {
             return false;
         }
      };

        tablaEmpleados = new JTable(modeloEmpleados);
         tablaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      botonAgregar = new JButton("Agregar Empleado");
     botonEliminar = new JButton("Eliminar Empleado");
  botonVolver = new JButton("Volver");

       botonAgregar.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) {
        mostrarFormularioAgregar();
       }
       });

      botonEliminar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             eliminarEmpleado();
         }
     });

   botonVolver.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
         volver();
     }
  });
 }

 private void configurarVentana() {
      setTitle("Gestión de Usuarios");
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   setResizable(false);

     JPanel panel = new JPanel();
   panel.setLayout(new BorderLayout());
      panel.setBackground(Color.WHITE);
   panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

       JLabel titulo = new JLabel("Gestión de Empleados");
    titulo.setFont(new Font("Arial", Font.BOLD, 16));
       titulo.setHorizontalAlignment(SwingConstants.CENTER);

    JScrollPane scroll = new JScrollPane(tablaEmpleados);
        scroll.setPreferredSize(new Dimension(600, 300));

      JPanel panelBotones = new JPanel();
    panelBotones.setLayout(new FlowLayout());
      panelBotones.setBackground(Color.WHITE);
    panelBotones.add(botonAgregar);
     panelBotones.add(botonEliminar);
     panelBotones.add(botonVolver);

       panel.add(titulo, BorderLayout.NORTH);
     panel.add(scroll, BorderLayout.CENTER);
    panel.add(panelBotones, BorderLayout.SOUTH);

      add(panel);
    pack();
       setLocationRelativeTo(ventanaPadre);
   }

 private void cargarEmpleados() {
        modeloEmpleados.setRowCount(0);
     List<Empleados> empleados = sistema.getEmpleados();

      for (Empleados emp : empleados) {
         Object[] fila = {
              emp.getLogin(),
          emp.getNombre(),
              emp.getRol(),
         emp.getTurno(),
            emp.getLugarAsignado()
         };
         modeloEmpleados.addRow(fila);
       }
   }

    private void mostrarFormularioAgregar() {
         VentanaAgregarEmpleado ventana = new VentanaAgregarEmpleado(this);
      ventana.setVisible(true);
   }

    private void eliminarEmpleado() {
      int fila = tablaEmpleados.getSelectedRow();

        if (fila == -1) {
      JOptionPane.showMessageDialog(this,
               "Seleccione un empleado para eliminar.",
          "Advertencia",
         JOptionPane.WARNING_MESSAGE);
          return;
      }

       String login = (String) modeloEmpleados.getValueAt(fila, 0);
          String nombre = (String) modeloEmpleados.getValueAt(fila, 1);

         int respuesta = JOptionPane.showConfirmDialog(this,
       "¿Está seguro que desea eliminar al empleado " + nombre + "?",
            "Confirmar eliminación",
         JOptionPane.YES_NO_OPTION);

     if (respuesta == JOptionPane.YES_OPTION) {
        boolean eliminado = sistema.eliminarEmpleado(login);

         if (eliminado) {
              JOptionPane.showMessageDialog(this,
                  "Empleado eliminado correctamente.",
                "Éxitoso",
                  JOptionPane.INFORMATION_MESSAGE);
          cargarEmpleados();
        ventanaPadre.actualizarDatos();
         } else {
             JOptionPane.showMessageDialog(this,
             "Error al eliminar el empleado.",
                "Error",
             JOptionPane.ERROR_MESSAGE);
        }
      }
  }

      private void volver() {
         this.dispose();
     }

    public void actualizarTabla() {
         cargarEmpleados();
     ventanaPadre.actualizarDatos();
    }
}
