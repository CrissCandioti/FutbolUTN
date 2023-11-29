/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

import Entidades.Equipo;
import Service.EquipoService;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Label;
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
public class ConsultaTodosLosEquiposVista extends JPanel {

    private JTable JTableEquipos;
     

    public ConsultaTodosLosEquiposVista() {
        init();
    }

    private void init() {
        try {
            EquipoService es = new EquipoService();
            List<Equipo> equiposTablaRellenar = new ArrayList<>();
            equiposTablaRellenar = es.listaDeEquipos();
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");
            JLabel JLabel1 = new JLabel("Consulta de todos los Equipos");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            panel.add(JLabel1);
            DefaultTableModel mod = new DefaultTableModel();
            mod.addColumn("Codigo");
            mod.addColumn("Nombre");
            mod.addColumn("DT");
            mod.addColumn("Puntos");
            mod.addColumn("PJ");
            JTableEquipos = new JTable(mod);
            JScrollPane scrollPane = new JScrollPane(JTableEquipos);
            scrollPane.setBounds(40, 40, 400, 200);
            Object equipo[] = null;
            for (int i = 0; i < equiposTablaRellenar.size(); i++) {
                mod.addRow(equipo);
                Equipo getm = (Equipo) equiposTablaRellenar.get(i);
                mod.setValueAt(getm.getId(), i, 0);
                mod.setValueAt(getm.getNombre(), i, 1);
                mod.setValueAt(getm.getDirectorTecnico(), i, 2);
                mod.setValueAt(getm.getPuntos(), i, 3);
                mod.setValueAt(getm.getPartidosJugados(), i, 4);
            }
            panel.add(scrollPane);
            panel.add(new Label("*PJ: Partidos Jugados"));
            add(panel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
