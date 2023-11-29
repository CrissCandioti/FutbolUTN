/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

import Entidades.Jugador;
import Service.JugadorService;
import com.formdev.flatlaf.FlatClientProperties;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author criss
 */
public class ConsultaTodosLosJugadorVista extends JPanel {

    private JTable JTableJugadores;

    public ConsultaTodosLosJugadorVista() {
        init();
    }

    private void init() {
        try {
            JugadorService js = new JugadorService();
            List<Jugador> jugadoresTablaRellenar = new ArrayList<>();
            jugadoresTablaRellenar = js.listaJugadores();
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");
            JLabel JLabel1 = new JLabel("Consulta de todos los Jugadores en la base de datos");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            panel.add(JLabel1);
            DefaultTableModel mod = new DefaultTableModel();
            mod.addColumn("DNI");
            mod.addColumn("Apellido");
            mod.addColumn("Nombre");
            JTableJugadores = new JTable(mod);
            JScrollPane scrollPane = new JScrollPane(JTableJugadores);
            scrollPane.setBounds(40, 40, 400, 200);
            Object jugador[] = null;
            for (int i = 0; i < jugadoresTablaRellenar.size(); i++) {
                mod.addRow(jugador);
                Jugador getm = (Jugador) jugadoresTablaRellenar.get(i);
                mod.setValueAt(getm.getDocumento(), i,0);
                mod.setValueAt(getm.getApellido(), i, 1);
                mod.setValueAt(getm.getNombre(), i, 2);
            }
            panel.add(scrollPane);
            add(panel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
