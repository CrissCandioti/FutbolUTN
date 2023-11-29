/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

import Entidades.Jugador;
import Service.EquipoService;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author criss
 */
public class CrearEquipoVista extends JPanel {

    private JTable JTableJugadores;
    private JComboBox JComboBoxJugadores;
    private JTextField JTextFieldNombre;
    private JTextField JTextFieldNombreDirectorTecnico;
    private JButton JButtonSeleccionar;
    private JButton JButtonEliminar;
    private JButton JButtonGuardar;
    public List<Jugador> jugadoresTablaRellenar = new ArrayList<>();

    public CrearEquipoVista() {
        init();
    }

    private void init() {
        try {
            EquipoService es = new EquipoService();
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JTextFieldNombre = new JTextField();
            JTextFieldNombreDirectorTecnico = new JTextField();
            JButtonSeleccionar = new JButton("Seleccionar");
            JButtonEliminar = new JButton("Eliminar");
            JButtonGuardar = new JButton("Guardar");
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");
            JTextFieldNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el nombre del equipo");
            JTextFieldNombreDirectorTecnico.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el nombre del director tecnico");

            JLabel JLabel1 = new JLabel("Crear Equipo");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            JLabel JLabel2 = new JLabel("Por favor ingrese los datos para guardar");
            JLabel2.putClientProperty(FlatClientProperties.STYLE, "");
            panel.add(JLabel1);
            panel.add(JLabel2);
            panel.add(new JLabel("Nombre"), "gapy 5");
            panel.add(JTextFieldNombre);
            panel.add(new JLabel("Director Tecnico"), "gapy 5");
            panel.add(JTextFieldNombreDirectorTecnico);
            panel.add(new JLabel("Seleccione los Jugadores a agregar"), "gapy 5");
            panel.add(JComboBoxModelo());
            panel.add(JButtonSeleccionar);
            JButtonSeleccionar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Jugador jugadorSeleccionado = (Jugador) JComboBoxJugadores.getSelectedItem();
                    if (jugadorSeleccionado != null && !jugadoresTablaRellenar.contains(jugadorSeleccionado)) {
                        jugadoresTablaRellenar.add(jugadorSeleccionado);
                        refrescarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "El jugador ya se encuentra en la lista");
                    }
                }
            });
            panel.add(new JLabel("Tabla con los jugadores en su equipo"), "gapy 5");
            panel.add(JTalbeModelo());
            panel.add(new JLabel("Seleccione el jugador para retirar"), "gapy 5");
            panel.add(JButtonEliminar);
            JButtonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int filaSeleccionada = JTableJugadores.getSelectedRow();

                    if (filaSeleccionada != -1) {
                        // Obtener el valor de la columna "Codigo" de la fila seleccionada
                        Object codigoObj = JTableJugadores.getValueAt(filaSeleccionada, 0);

                        // Supongamos que tenemos una lista de objetos 
                        // y quieres eliminar el objeto que corresponde al código obtenido
                        for (Jugador jugador : jugadoresTablaRellenar) {
                            if (jugador.getId() == (int) codigoObj) {
                                jugadoresTablaRellenar.remove(jugador);
                                refrescarTabla();
                                break;
                            }
                        }
                    } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un jugador");
                    }
                }
            });
            panel.add(JButtonGuardar);
            JButtonGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = JTextFieldNombre.getText().trim();
                    String nombreDirectorTecnico = JTextFieldNombreDirectorTecnico.getText().trim();
                    int puntos = 0;
                    int partidosJugados = 0;
                    es.crearEquipo(nombre, nombreDirectorTecnico, jugadoresTablaRellenar, puntos, partidosJugados);
                }
            });
            panel.add(new Label("Nota: Se mostraran los jugadores"));
            panel.add(new Label("que no esten en ningun equipo"));
            add(panel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Component JComboBoxModelo() {
        try {
            EquipoService es = new EquipoService();
            List<Jugador> jugadoresComboBox = es.jugadoresSinEquipo();
            DefaultComboBoxModel<Jugador> modelo = new DefaultComboBoxModel<>(jugadoresComboBox.toArray(new Jugador[0]));
            JComboBoxJugadores = new JComboBox(modelo);
            return JComboBoxJugadores;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private Component JTalbeModelo() {
        try {
            DefaultTableModel mod = new DefaultTableModel();
            mod.addColumn("Id");
            mod.addColumn("Apellido");
            mod.addColumn("Nombre");
            mod.addColumn("Posicion");
            mod.addColumn("Estado");
            JTableJugadores = new JTable(mod);
            JTableJugadores.setBounds(40, 40, 400, 200);
            Object jugador[] = null;
            for (int i = 0; i < jugadoresTablaRellenar.size(); i++) {
                mod.addRow(jugador);
                Jugador getm = (Jugador) jugadoresTablaRellenar.get(i);
                mod.setValueAt(getm.getId(), i, 0);
                mod.setValueAt(getm.getApellido(), i, 1);
                mod.setValueAt(getm.getNombre(), i, 2);
                mod.setValueAt(getm.getPosicion(), i, 3);
                mod.setValueAt(getm.isEstado(), i, 4);
            }
            return JTableJugadores;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private void refrescarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) JTableJugadores.getModel();
        modelo.setRowCount(0); // Vaciar la tabla

        for (Jugador jugador : jugadoresTablaRellenar) {
            Object[] fila = {jugador.getId(), jugador.getApellido(), jugador.getNombre(), jugador.getPosicion(), jugador.isEstado()};
            modelo.addRow(fila); // Agregar fila a la tabla
        }
    }
}
