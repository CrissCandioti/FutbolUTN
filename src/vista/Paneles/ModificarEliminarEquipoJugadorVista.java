/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

import Entidades.Equipo;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author criss
 */
public class ModificarEliminarEquipoJugadorVista extends JPanel {

    private JTable JTableJugadores;
    private JComboBox JComboBoxJugadores;
    private JTextField JTextFieldBusqueda;
    private JButton JButtonSeleccionar;
    private JButton JButtonBusqueda;
    private JButton JButtonEliminar;
    private JButton JButtonModificar;
    public List<Jugador> jugadoresTablaRellenar = new ArrayList<>();
    public Equipo aux = new Equipo();

    public ModificarEliminarEquipoJugadorVista() {
        init();
        editableOn();
    }

    private void init() {
        try {
            EquipoService es = new EquipoService();
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JButtonBusqueda = new JButton("Busqueda");
            JTextFieldBusqueda = new JTextField();
            JButtonSeleccionar = new JButton("Seleccionar");
            JButtonEliminar = new JButton("Retirar");
            JButtonModificar = new JButton("Modificar");
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");
            JTextFieldBusqueda.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el codigo");

            JLabel JLabel1 = new JLabel("Modificar u Eliminar Equipo");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            JLabel JLabel2 = new JLabel("Por favor ingrese el codigo para buscar el equipo");
            JLabel2.putClientProperty(FlatClientProperties.STYLE, "");
            panel.add(JLabel1);
            panel.add(JLabel2);
            panel.add(JTextFieldBusqueda, "gapy 5");
            panel.add(JButtonBusqueda, "gapy 5");
            JButtonBusqueda.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String id = JTextFieldBusqueda.getText().trim();
                        int codigoID = Integer.parseInt(id);
                        if (es.buscarEquipoPorID(codigoID) == null) {
                            JOptionPane.showMessageDialog(null, "No se encontro ningun equipo");
                        } else if (es.buscarEquipoPorID(codigoID) != null) {
                            aux = es.buscarEquipoPorID(codigoID);
                            jugadoresTablaRellenar = aux.getJugadores();
                            refrescarTabla();
                            editableOff();
                        }
                    } catch (NumberFormatException f) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numerico para su busqueda");
                    } catch (Exception s) {
                        JOptionPane.showMessageDialog(null, "Surgio un error vuelva a intentarlo");
                    }
                }
            });
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
            panel.add(JButtonModificar);
            JButtonModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        es.modificarEquipo(aux.getId(), aux.getNombre(), aux.getDirectorTecnico(), jugadoresTablaRellenar, aux.getPuntos(), aux.getPartidosJugados());
                        // Limpiar la tabla después de guardar los datos
                        DefaultTableModel model = (DefaultTableModel) JTableJugadores.getModel();
                        model.setRowCount(0); // Esto eliminará todas las filas de la tabla
                        // Luego, limpiar la lista de jugadoresTablaRellenar 
                        jugadoresTablaRellenar.clear();
                        refrescarComboBox();
                        editableOn();
                    } catch (Exception ñ) {
                        JOptionPane.showMessageDialog(null, "Se produjo un error, verifique y vuelva a intentarlo");
                    }
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
            mod.addColumn("Codigo");
            mod.addColumn("Apellido");
            mod.addColumn("Nombre");
            mod.addColumn("Posicion");
            mod.addColumn("Estado");
            JTableJugadores = new JTable(mod);
            JScrollPane scrollPane = new JScrollPane(JTableJugadores);
            scrollPane.setBounds(40, 40, 400, 200);
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
            return scrollPane;
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

    private void editableOn() {
        try {
            JButtonSeleccionar.setEnabled(false);
            JButtonModificar.setEnabled(false);
            JButtonEliminar.setEnabled(false);
            JComboBoxJugadores.setEnabled(false);
            JTableJugadores.setEnabled(false);
        } catch (Exception g) {
            System.out.println(g);
        }
    }

    private void editableOff() {
        try {
            JButtonSeleccionar.setEnabled(true);
            JButtonModificar.setEnabled(true);
            JButtonEliminar.setEnabled(true);
            JComboBoxJugadores.setEnabled(true);
            JTableJugadores.setEnabled(true);
        } catch (Exception m) {
            System.out.println(m);
        }
    }

    // Método para refrescar el JComboBox con la lista actualizada de jugadores disponibles
    private void refrescarComboBox() {
        try {
            EquipoService es = new EquipoService();
            List<Jugador> jugadoresComboBox = es.jugadoresSinEquipo();
            DefaultComboBoxModel<Jugador> modelo = new DefaultComboBoxModel<>(jugadoresComboBox.toArray(new Jugador[0]));
            JComboBoxJugadores.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
