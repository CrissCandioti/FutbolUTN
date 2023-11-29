/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

import Entidades.Campeonato;
import Entidades.Equipo;
import Service.CampeonatoService;
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
public class ModificarEliminarCampeonatoVista extends JPanel {

    private JTextField JTextFieldCodigo;
    private JTextField JTextFieldNombre;
    private JComboBox JComboBoxEquipos;
    private JButton JButtonSeleccionar;
    private JButton JButtonRetirar;
    private JButton JButtonEliminar;
    private JButton JButtonBuscar;
    private JButton JButtonModificar;
    private JTable JTablePorEquipo;
    public Campeonato aux = new Campeonato();
    public List<Equipo> equiposTablaRellenar = new ArrayList<>();
    public CampeonatoService cs = new CampeonatoService();

    public ModificarEliminarCampeonatoVista() {
        init();
        editableOn();
    }

    private void init() {
        try {
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");
            JButtonSeleccionar = new JButton("Seleccionar");
            JButtonRetirar = new JButton("Retirar");
            JButtonEliminar = new JButton("Eliminar");
            JTextFieldCodigo = new JTextField();
            JTextFieldNombre = new JTextField();
            JButtonBuscar = new JButton("Buscar");
            JButtonModificar = new JButton("Modificar");
            JTextFieldNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el nombre del campeonato");
            JLabel JLabel1 = new JLabel("Modificar u Eliminar Campeonato");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            panel.add(JLabel1);
            JLabel JLabel2 = new JLabel("Por favor ingrese el Codigo");
            JLabel2.putClientProperty(FlatClientProperties.STYLE, "");
            panel.add(JLabel2);
            JTextFieldCodigo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Codigo");
            panel.add(JTextFieldCodigo);
            panel.add(JButtonBuscar);
            JButtonBuscar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String codigo = JTextFieldCodigo.getText().trim();
                        int valorCodigo = Integer.parseInt(codigo);
                        if (cs.buscarCampeonatoPorID(valorCodigo) == null) {
                            JOptionPane.showMessageDialog(null, "No se encontro ningun campeonato");
                        } else if (cs.buscarCampeonatoPorID(valorCodigo) != null) {
                            aux = cs.buscarCampeonatoPorID(valorCodigo);
                            equiposTablaRellenar = cs.buscarCampeonatoPorID(valorCodigo).getEquipos();
                            JTextFieldNombre.setText(aux.getNombre());
                            refrescarTabla();
                            editableOff();
                        }
                    } catch (Exception f) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numerico");
                    }
                }
            });
            panel.add(new Label("Nombre Campeonato"));
            panel.add(JTextFieldNombre);
            panel.add(new Label("Seleccione los equipos que desea agregar"));
            panel.add(JComboBoxModelo());
            panel.add(JButtonSeleccionar);
            JButtonSeleccionar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Equipo equipoSeleccionado = (Equipo) JComboBoxEquipos.getSelectedItem();
                    if (equipoSeleccionado != null) {
                        boolean equipoYaEnLista = equiposTablaRellenar.stream()
                                .anyMatch(equipo -> equipo.getId() == equipoSeleccionado.getId());

                        if (!equipoYaEnLista) {
                            equiposTablaRellenar.add(equipoSeleccionado);
                            refrescarTabla();
                        } else {
                            JOptionPane.showMessageDialog(null, "El equipo ya se encuentra en la lista");
                        }
                    }

                }
            });
            panel.add(new Label("Equipos del campeonato"));
            panel.add(JTableModeloEquipos());
            panel.add(JButtonRetirar);
            JButtonRetirar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int filaSeleccionada = JTablePorEquipo.getSelectedRow();

                    if (filaSeleccionada != -1) {
                        // Obtener el valor de la columna "Codigo" de la fila seleccionada
                        Object codigoObj = JTablePorEquipo.getValueAt(filaSeleccionada, 0);

                        // Supongamos que tenemos una lista de objetos 
                        // y quieres eliminar el objeto que corresponde al código obtenido
                        for (Equipo aux : equiposTablaRellenar) {
                            if (aux.getId() == (int) codigoObj) {
                                equiposTablaRellenar.remove(aux);
                                refrescarTabla();
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un jugador");
                    }
                }
            });
            panel.add(new Label("*PJ: Partidos Jugados"));
            panel.add(JButtonModificar);
            JButtonModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String nombre = JTextFieldNombre.getText().trim();
                        cs.modificarCampeonato(aux.getId(), nombre, equiposTablaRellenar);
                        setearTabla();
                        editableOn();
                    } catch (Exception k) {
                        JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos y vuelva a intentarlo");
                    }
                }
            });
            panel.add(JButtonEliminar);
            JButtonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int id = aux.getId();
                        if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR EL CAMPEONATO", "SALIR", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
                            cs.eliminarCampeonato(id);
                            editableOn();
                        }
                    } catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(null, "No se encontro ningun campeonato para su eliminacion");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al analizar los datos");
                    }
                }
            });
            add(panel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Component JTableModeloEquipos() {
        try {
            DefaultTableModel mod = new DefaultTableModel();
            mod.addColumn("Codigo");
            mod.addColumn("Nombre");
            mod.addColumn("Puntos");
            mod.addColumn("PJ");
            JTablePorEquipo = new JTable(mod);
            JScrollPane scrollPane = new JScrollPane(JTablePorEquipo);
            scrollPane.setBounds(40, 40, 400, 200);
            Object equipo[] = null;
            for (int i = 0; i < equiposTablaRellenar.size(); i++) {
                mod.addRow(equipo);
                Equipo getm = (Equipo) equiposTablaRellenar.get(i);
                mod.setValueAt(getm.getId(), i, 0);
                mod.setValueAt(getm.getNombre(), i, 1);
                mod.setValueAt(getm.getPuntos(), i, 2);
                mod.setValueAt(getm.getPartidosJugados(), i, 2);
            }
            return scrollPane;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private void refrescarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) JTablePorEquipo.getModel();
        modelo.setRowCount(0); // Vaciar la tabla

        for (Equipo equipo : equiposTablaRellenar) {
            Object[] fila = {equipo.getId(), equipo.getNombre(), equipo.getPuntos(), equipo.getPartidosJugados()};
            modelo.addRow(fila); // Agregar fila a la tabla
        }
    }

    private Component JComboBoxModelo() {
        try {
            EquipoService es = new EquipoService();
            List<Equipo> jugadoresComboBox = es.listaDeEquipos();
            DefaultComboBoxModel<Equipo> modelo = new DefaultComboBoxModel<>(jugadoresComboBox.toArray(new Equipo[0]));
            JComboBoxEquipos = new JComboBox(modelo);
            return JComboBoxEquipos;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private void setearTabla() {
        try {
            // Limpiar la tabla después de guardar los datos
            DefaultTableModel model = (DefaultTableModel) JTablePorEquipo.getModel();
            model.setRowCount(0); // Esto eliminará todas las filas de la tabla
            // Luego, limpiar la lista de jugadoresTablaRellenar 
            equiposTablaRellenar.clear();
        } catch (Exception c) {
            System.out.println(c);
        }
    }

    private void editableOn() {
        try {
            JButtonSeleccionar.setEnabled(false);
            JButtonModificar.setEnabled(false);
            JButtonEliminar.setEnabled(false);
            JTextFieldNombre.setEnabled(false);
            JComboBoxEquipos.setEnabled(false);
            JButtonRetirar.setEnabled(false);
            JButtonModificar.setEnabled(false);
            JTablePorEquipo.setEnabled(false);
        } catch (Exception p) {
            System.out.println(p);
        }
    }

    private void editableOff() {
        try {
            JButtonSeleccionar.setEnabled(true);
            JButtonModificar.setEnabled(true);
            JButtonEliminar.setEnabled(true);
            JTextFieldNombre.setEnabled(true);
            JComboBoxEquipos.setEnabled(true);
            JButtonModificar.setEnabled(true);
            JTablePorEquipo.setEnabled(true);
            JButtonRetirar.setEnabled(true);
        } catch (Exception m) {
            System.out.println(m);
        }
    }
}
