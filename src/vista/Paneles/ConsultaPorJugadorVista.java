/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

import Entidades.Jugador;
import Service.JugadorService;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
public class ConsultaPorJugadorVista extends JPanel {

    private JTextField JTextFieldDocumento;
    private JButton JButtonBuscar;
    private JTable JTablePorJugador;
    public Jugador aux = new Jugador();

    public ConsultaPorJugadorVista() {
        init();
    }

    private void init() {
        try {
            JugadorService js = new JugadorService();

            JTextFieldDocumento = new JTextField();
            JButtonBuscar = new JButton("Buscar");
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");
            JLabel JLabel1 = new JLabel("Busqueda por Jugador");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            panel.add(JLabel1);
            JLabel JLabel2 = new JLabel("Por favor ingrese el DNI");
            JLabel2.putClientProperty(FlatClientProperties.STYLE, "");
            panel.add(JLabel2);
            JTextFieldDocumento.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Documento");
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
                            actualizarTabla(aux);
                        }
                    } catch (Exception f) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numerico");
                    }
                }
            });
            panel.add(JTableModelo());
            add(panel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Component JTableModelo() {
        try {
            DefaultTableModel mod = new DefaultTableModel();
            mod.addColumn("DNI");
            mod.addColumn("Apellido");
            mod.addColumn("Nombre");
            mod.addColumn("Fecha Nacimiento");
            mod.addColumn("Posicion");
            mod.addColumn("Estado");
            JTablePorJugador = new JTable(mod);
            JScrollPane scrollPane = new JScrollPane(JTablePorJugador);
            scrollPane.setBounds(40, 40, 400, 200);
            return scrollPane;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    private void actualizarTabla(Jugador aux) {
        DefaultTableModel mod = (DefaultTableModel) JTablePorJugador.getModel();
        mod.setRowCount(0); // Limpiar todas las filas existentes en la tabla

        // Agregar los datos del jugador al modelo de la tabla
        Object[] fila = new Object[7];
        fila[0] = aux.getDocumento();
        fila[1] = aux.getApellido();
        fila[2] = aux.getNombre();
        fila[3] = aux.getFechaNacimiento();
        fila[4] = aux.getPosicion();
        fila[5] = aux.isEstado();

        mod.addRow(fila); // Agregar la fila al modelo de la tabla
    }
}
