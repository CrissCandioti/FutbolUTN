/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

import Entidades.Equipo;
import Service.EquipoService;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author criss
 */
public class ModificarEliminarEquipoVista extends JPanel {

    private JTextField JTextFieldNombre;
    private JTextField JTextFieldPuntos;
    private JTextField JTextFieldPartidosJugados;
    private JTextField JTextFieldBusqueda;
    private JTextField JTextFieldNombreDirectorTecnico;
    private JButton JButtonBusqueda;
    private JButton JButtonEliminar;
    private JButton JButtonModificar;
    public Equipo aux = new Equipo();

    public ModificarEliminarEquipoVista() {
        init();
        editableOn();
    }

    private void init() {
        try {
            EquipoService es = new EquipoService();
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JButtonBusqueda = new JButton("Busqueda");
            JTextFieldBusqueda = new JTextField();
            JTextFieldPuntos = new JTextField();
            JTextFieldPartidosJugados = new JTextField();
            JTextFieldNombre = new JTextField();
            JTextFieldNombreDirectorTecnico = new JTextField();
            JButtonEliminar = new JButton("Eliminar");
            JButtonModificar = new JButton("Modificar");
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");
            JTextFieldNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el nombre del equipo");
            JTextFieldNombreDirectorTecnico.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el nombre del director tecnico");
            JTextFieldBusqueda.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Codigo");
            JTextFieldPartidosJugados.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese los partidos jugados");
            JTextFieldPuntos.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese los puntos");

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
                            JTextFieldNombre.setText(aux.getNombre());
                            JTextFieldNombreDirectorTecnico.setText(aux.getDirectorTecnico());
                            JTextFieldPartidosJugados.setText(aux.getPartidosJugados() + "");
                            JTextFieldPuntos.setText(aux.getPuntos() + "");
                            editableOff();
                        }
                    } catch (NumberFormatException f) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numerico para su busqueda");
                    } catch (Exception s) {
                        JOptionPane.showMessageDialog(null, "Surgio un error vuelva a intentarlo");
                    }
                }
            });
            panel.add(new JLabel("Nombre"), "gapy 5");
            panel.add(JTextFieldNombre);
            panel.add(new JLabel("Director Tecnico"), "gapy 5");
            panel.add(JTextFieldNombreDirectorTecnico);
            panel.add(new Label("Puntos"), " gapy 5");
            panel.add(JTextFieldPuntos);
            panel.add(new Label("Partidos Jugados"), " gapy 5");
            panel.add(JTextFieldPartidosJugados);
            panel.add(JButtonModificar);
            JButtonModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String nombre = JTextFieldNombre.getText().trim();
                        String nombreDirectorTecnico = JTextFieldNombreDirectorTecnico.getText().trim();
                        String puntos = JTextFieldPuntos.getText().trim();
                        String partidosJugados = JTextFieldPartidosJugados.getText().trim();
                        int puntosValor = Integer.parseInt(puntos);
                        int partidosJugadosValor = Integer.parseInt(partidosJugados);
                        es.modificarEquipoSinJugadores(aux.getId(), nombre, nombreDirectorTecnico, aux.getJugadores(), puntosValor, partidosJugadosValor);
                        editableOn();
                        limpiar();
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
                        if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR EL EQUIPO", "SALIR", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
                            es.eliminarEquipo(id);
                            editableOn();
                            limpiar();
                        }
                    } catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(null, "No se encontro ningun equipo para su eliminacion");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al analizar los datos");
                    }

                }
            });
            add(panel);
        } catch (HeadlessException e) {
            System.out.println(e);
        }
    }

    private void editableOn() {
        try {
            JTextFieldNombreDirectorTecnico.setEditable(false);
            JTextFieldNombre.setEditable(false);
            JTextFieldPuntos.setEditable(false);
            JTextFieldPartidosJugados.setEditable(false);
            JButtonModificar.setEnabled(false);
            JButtonEliminar.setEnabled(false);
        } catch (Exception g) {
            System.out.println(g);
        }
    }

    private void editableOff() {
        try {
            JTextFieldNombreDirectorTecnico.setEditable(true);
            JTextFieldNombre.setEditable(true);
            JTextFieldPuntos.setEditable(true);
            JTextFieldPartidosJugados.setEditable(true);
            JButtonModificar.setEnabled(true);
            JButtonEliminar.setEnabled(true);
        } catch (Exception g) {
            System.out.println(g);
        }
    }

    public void limpiar() {
        try {
            JTextFieldNombre.setText("");
            JTextFieldPartidosJugados.setText("");
            JTextFieldPuntos.setText("");
            JTextFieldBusqueda.setText("");
            JTextFieldNombreDirectorTecnico.setText("");
        } catch (Exception b) {
            System.out.println(b);
        }
    }
}
