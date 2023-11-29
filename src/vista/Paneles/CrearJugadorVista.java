/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

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

public class CrearJugadorVista extends JPanel {

    private JTextField JTextFieldDocumento;
    private JTextField JTextFieldApellido;
    private JTextField JTextFieldNombre;
    private JComboBox JComboBoxPosicion;
    private Calendario calendario;
    private JRadioButton JRadioButtonEstado;
    private JButton JButtonGuardar;
    private JButton JButtonCalendario;

    public CrearJugadorVista() {
        init();
    }

    private void init() {
        try {
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JTextFieldDocumento = new JTextField();
            JTextFieldApellido = new JTextField();
            JTextFieldNombre = new JTextField();
            JComboBoxPosicion = new JComboBox();
            JRadioButtonEstado = new JRadioButton("Estado");
            JButtonGuardar = new JButton("Guardar");
            JButtonCalendario = new JButton("Fecha de Nacimiento");
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:280"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");

            JTextFieldDocumento.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el documento");
            JTextFieldApellido.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el apellido");
            JTextFieldNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el nombre");
            JComboBoxPosicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Arquero", "Defensa", "MedioCampo", "Delantero"}));
            JRadioButtonEstado.setSelected(true);

            JLabel JLabel1 = new JLabel("Crear Jugador");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            JLabel JLabel2 = new JLabel("Por favor ingrese los datos para guardar");
            JLabel2.putClientProperty(FlatClientProperties.STYLE, "");
            JLabel JLabel3 = new JLabel("Fecha Nacimiento: ");
            JLabel2.putClientProperty(FlatClientProperties.STYLE, "");
            panel.add(JLabel1);
            panel.add(JLabel2);
            panel.add(new JLabel("Documento"), "gapy 5");
            panel.add(JTextFieldDocumento);
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
            panel.add(JButtonGuardar, "gapy 10");
            JButtonGuardar.addActionListener(new ActionListener() {
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
                        JugadorService js = new JugadorService();
                        js.crearJugador(valorDocumento, apellido, nombre, LocalDate.parse(fechaFormateada), posicion, estado);
                        JLabel3.setText("Fecha Nacimiento: " + LocalDate.parse(fechaFormateada));
                    } catch (NullPointerException h) {
                        JOptionPane.showMessageDialog(null, "Seleccione la fecha de nacimiento");
                    } catch (NumberFormatException h) {
                        JOptionPane.showMessageDialog(null, "Recuerde ingresar correctamente los datos numericos");
                    } catch (Exception w) {
                        JOptionPane.showMessageDialog(null, "Complete toda la informacion requerida");
                    }
                }
            });
            add(panel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
