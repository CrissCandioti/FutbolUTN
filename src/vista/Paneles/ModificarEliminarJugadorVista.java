/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

import Entidades.Jugador;
import Service.JugadorService;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import vista.Complementos.Calendario;

/**
 *
 * @author criss
 */
public class ModificarEliminarJugadorVista extends JPanel {

    private JTextField JTextFieldDocumento;
    private JTextField JTextFieldApellido;
    private JTextField JTextFieldNombre;
    private JComboBox JComboBoxPosicion;
    private Calendario calendario;
    private JRadioButton JRadioButtonEstado;
    private JButton JButtonModificar;
    private JButton JButtonCalendario;
    private JButton JButtonBuscar;
    private JButton JButtonEliminar;
    public Jugador aux = new Jugador();

    public ModificarEliminarJugadorVista() {
        init();
        editableOn();
    }

    private void init() {
        try {
            JugadorService js = new JugadorService();
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JTextFieldDocumento = new JTextField();
            JTextFieldApellido = new JTextField();
            JTextFieldNombre = new JTextField();
            JComboBoxPosicion = new JComboBox();
            JRadioButtonEstado = new JRadioButton("Estado");
            JButtonModificar = new JButton("Modificar");
            JButtonCalendario = new JButton("Fecha de Nacimiento");
            JButtonBuscar = new JButton("Buscar");
            JButtonEliminar = new JButton("Eliminar");
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:280"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");

            JTextFieldDocumento.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el documento");
            JTextFieldApellido.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el apellido");
            JTextFieldNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el nombre");
            JComboBoxPosicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Arquero", "Defensa", "MedioCampo", "Delantero"}));
            JRadioButtonEstado.setSelected(false);

            JLabel JLabel1 = new JLabel("Modificar u Eliminar Jugador");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            JLabel JLabel2 = new JLabel("Por favor ingrese el documento para buscar al jugador");
            JLabel2.putClientProperty(FlatClientProperties.STYLE, "");
            JLabel JLabel3 = new JLabel("Fecha Nacimiento: ");
            JLabel2.putClientProperty(FlatClientProperties.STYLE, "");
            panel.add(JLabel1);
            panel.add(JLabel2);
            panel.add(new JLabel("Documento"), "gapy 5");
            panel.add(JTextFieldDocumento);
            panel.add(JButtonBuscar);
            JButtonBuscar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String documento = JTextFieldDocumento.getText().trim();
                        int valorDocumento = Integer.parseInt(documento);
                        if (js.buscarJugadorPorDNI(valorDocumento) == null) {
                            JOptionPane.showMessageDialog(null, "No se encontro ningun jugador");
                        } else if (js.buscarJugadorPorDNI(valorDocumento) != null) {
                            aux = js.buscarJugadorPorDNI(valorDocumento);
                            JTextFieldApellido.setText(aux.getApellido());
                            JTextFieldNombre.setText(aux.getNombre());
                            JComboBoxPosicion.setSelectedItem(aux.getPosicion());
                            JLabel3.setText("Fecha Nacimiento: " + aux.getFechaNacimiento());
                            JRadioButtonEstado.setSelected(aux.isEstado());
                            editableOff();
                        }
                    } catch (Exception f) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numerico");
                    }
                }
            });
            panel.add(new JLabel("Apellido"), "gapy 5");
            panel.add(JTextFieldApellido);
            panel.add(new JLabel("Nombre"), "gapy 5");
            panel.add(JTextFieldNombre);
            panel.add(new JLabel("Posicion"), "gapy 5");
            panel.add(JComboBoxPosicion);
            panel.add(new JLabel(""), "gapy 5");
            panel.add(JButtonCalendario);
            JButtonCalendario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendario = new Calendario();
                    calendario.setVisible(true);
                }
            });
            panel.add(JLabel3);
            panel.add(new JLabel(""), "gapy 5");
            panel.add(JRadioButtonEstado);
            panel.add(JButtonModificar, "gapy 10");
            JButtonModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String documento = JTextFieldDocumento.getText().trim();
                        int valorDocumento = Integer.parseInt(documento);
                        String apellido = JTextFieldApellido.getText().trim();
                        String nombre = JTextFieldNombre.getText().trim();
                        String posicion = (String) JComboBoxPosicion.getSelectedItem();
                        boolean estado = JRadioButtonEstado.isSelected();
                        Date fechaNac = calendario.fechaNac;
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaFormateada = formato.format(fechaNac);
                        js.modificarJugador(aux.getId(), valorDocumento, apellido, nombre, LocalDate.parse(fechaFormateada), posicion, estado);
                        JLabel3.setText("Fecha Nacimiento: ");
                        limpiar();
                        editableOn();
                    } catch (NullPointerException h) {
                        JOptionPane.showMessageDialog(null, "Por seguridad seleccione nuevamente la fecha de nacimiento");
                    } catch (NumberFormatException h) {
                        JOptionPane.showMessageDialog(null, "Recuerde ingresar correctamente los datos numericos");
                    } catch (Exception w) {
                        JOptionPane.showMessageDialog(null, "Complete toda la informacion requerida");
                    }
                }
            });
            panel.add(JButtonEliminar);
            JButtonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int id = aux.getId();
                        if (JOptionPane.showConfirmDialog(null, "ESTA SEGURO DE ELIMINAR EL JUGADOR", "SALIR", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
                            js.eliminarJugador(id);
                            editableOn();
                            JLabel3.setText("Fecha Nacimiento: ");
                            limpiar();
                        }
                    } catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(null, "No se encontro ningun jugador para su eliminacion");
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

    private void editableOn() {
        try {
            JTextFieldApellido.setEditable(false);
            JTextFieldNombre.setEditable(false);
            JRadioButtonEstado.setEnabled(false);
            JButtonModificar.setEnabled(false);
            JButtonCalendario.setEnabled(false);
            JButtonEliminar.setEnabled(false);
            JComboBoxPosicion.setEnabled(false);
        } catch (Exception g) {
            System.out.println(g);
        }
    }

    private void editableOff() {
        try {
            JTextFieldApellido.setEditable(true);
            JTextFieldNombre.setEditable(true);
            JRadioButtonEstado.setEnabled(true);
            JButtonModificar.setEnabled(true);
            JButtonCalendario.setEnabled(true);
            JButtonEliminar.setEnabled(true);
            JComboBoxPosicion.setEnabled(true);
        } catch (Exception g) {
            System.out.println(g);
        }
    }

    private void limpiar() {
        try {
            JTextFieldDocumento.setText("");
            JTextFieldApellido.setText("");
            JTextFieldNombre.setText("");
            JRadioButtonEstado.setSelected(false);
            JComboBoxPosicion.setSelectedItem("");
        } catch (Exception g) {
            System.out.println(g);
        }
    }
}
