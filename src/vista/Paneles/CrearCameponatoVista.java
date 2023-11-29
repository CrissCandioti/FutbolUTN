/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

import Entidades.Equipo;
import Service.CampeonatoService;
import Service.EquipoService;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
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
public class CrearCameponatoVista extends JPanel {

    private JTable JTableEquipo;
    private JComboBox JComboBoxEquipo;
    private JTextField JTextFieldNombre;
    private JButton JButtonSeleccionar;
    private JButton JButtonEliminar;
    private JButton JButtonGuardar;
    public List<Equipo> equipoTablaRellenar = new ArrayList<>();

    public CrearCameponatoVista() {
        init();
    }

    private void init() {
        try {
            CampeonatoService cs = new CampeonatoService();
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");
            JTextFieldNombre = new JTextField();
            JButtonSeleccionar = new JButton("Seleccionar");
            JButtonEliminar = new JButton("Eliminar");
            JButtonGuardar = new JButton("Guardar");
            JTextFieldNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el nombre del campeonato");

            JLabel JLabel1 = new JLabel("Crear Campeonato");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            JLabel JLabel2 = new JLabel("Por favor ingrese los datos para guardar");
            JLabel2.putClientProperty(FlatClientProperties.STYLE, "");
            panel.add(JLabel1);
            panel.add(JLabel2);

            panel.add(new JLabel("Nombre"), "gapy 5");
            panel.add(JTextFieldNombre);

            panel.add(new JLabel("Seleccione los Equipos que desea agregar"), "gapy 5");
            panel.add(JComboBoxModelo());
            panel.add(JButtonSeleccionar);
            JButtonSeleccionar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Equipo equipoSeleccionado = (Equipo) JComboBoxEquipo.getSelectedItem();
                    if (equipoSeleccionado != null && !equipoTablaRellenar.contains(equipoSeleccionado)) {
                        equipoTablaRellenar.add(equipoSeleccionado);
                        refrescarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "El equipo ya se encuentra en la lista");
                    }
                }
            });

            panel.add(new JLabel("Tabla con los equipos del campeonato"), "gapy 5");
            panel.add(JTalbeModelo());
            panel.add(new JLabel("Seleccione un equipo para retirar"), "gapy 5");
            panel.add(JButtonEliminar);
            JButtonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Equipo equipoSeleccionado = (Equipo) JComboBoxEquipo.getSelectedItem();
                    if (equipoSeleccionado != null) {
                        equipoTablaRellenar.remove(equipoSeleccionado);
                        refrescarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un equipo");
                    }
                }
            });
            panel.add(JButtonGuardar);
            JButtonGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = JTextFieldNombre.getText().trim();
                    cs.crearCampeonato(nombre, equipoTablaRellenar);
                }
            });
            add(panel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Component JComboBoxModelo() {
        try {
            EquipoService es = new EquipoService();
            List<Equipo> equipoComboBox = es.listaDeEquipos();
            DefaultComboBoxModel<Equipo> modelo = new DefaultComboBoxModel<>(equipoComboBox.toArray(new Equipo[0]));
            JComboBoxEquipo = new JComboBox(modelo);
            return JComboBoxEquipo;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private Component JTalbeModelo() {
        try {
            DefaultTableModel mod = new DefaultTableModel();
            mod.addColumn("Id");
            mod.addColumn("Nombre");
            mod.addColumn("NombreDirectorTecnico");
            JTableEquipo = new JTable(mod);
            JTableEquipo.setBounds(40, 40, 400, 200);
            Object jugador[] = null;
            for (int i = 0; i < equipoTablaRellenar.size(); i++) {
                mod.addRow(jugador);
                Equipo getm = (Equipo) equipoTablaRellenar.get(i);
                mod.setValueAt(getm.getId(), i, 0);
                mod.setValueAt(getm.getNombre(), i, 1);
                mod.setValueAt(getm.getDirectorTecnico(), i, 2);
            }
            return JTableEquipo;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private void refrescarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) JTableEquipo.getModel();
        modelo.setRowCount(0); // Vaciar la tabla

        for (Equipo equipo : equipoTablaRellenar) {
            Object[] fila = {equipo.getId(), equipo.getNombre(), equipo.getDirectorTecnico()};
            modelo.addRow(fila); // Agregar fila a la tabla
        }
    }
}
