/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Paneles;

import Entidades.Campeonato;
import Service.CampeonatoService;
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
public class ConsultaTodosLosCampeonatosVista extends JPanel {

    private JTable JTableCampeonatos;

    public ConsultaTodosLosCampeonatosVista() {
        init();
    }

    private void init() {
        try {
            CampeonatoService cs = new CampeonatoService();
            List<Campeonato> campeonatoTablaRellenar = new ArrayList<>();
            campeonatoTablaRellenar = cs.listaDeCampeonatos();
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");
            JLabel JLabel1 = new JLabel("Consulta de todos los Campeonatos");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            panel.add(JLabel1);
            DefaultTableModel mod = new DefaultTableModel();
            mod.addColumn("Codigo");
            mod.addColumn("Nombre");
            JTableCampeonatos = new JTable(mod);
            JScrollPane scrollPane = new JScrollPane(JTableCampeonatos);
            scrollPane.setBounds(40, 40, 400, 200);
            Object jugador[] = null;
            for (int i = 0; i < campeonatoTablaRellenar.size(); i++) {
                mod.addRow(jugador);
                Campeonato getm = (Campeonato) campeonatoTablaRellenar.get(i);
                mod.setValueAt(getm.getId(), i, 0);
                mod.setValueAt(getm.getNombre(), i, 1);
            }
            panel.add(scrollPane);
            add(panel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
