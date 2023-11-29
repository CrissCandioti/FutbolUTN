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
public class ConsultaPorEquipoVista extends JPanel {

    private JTextField JTextFieldCodigo;
    private JButton JButtonBuscar;
    private JTable JTablePorEquipo;
    private JTable JTablePorJugadores;
    public Equipo aux = new Equipo();
    public List<Jugador> jugadoresTablaRellenar = new ArrayList<>();
    public EquipoService es = new EquipoService();

    public ConsultaPorEquipoVista() {
        init();
    }

    private void init() {
        try {
            JTextFieldCodigo = new JTextField();
            JButtonBuscar = new JButton("Buscar");
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:20;");
            JLabel JLabel1 = new JLabel("Busqueda por Equipo");
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
                        if (es.buscarEquipoPorID(valorCodigo) == null) {
                            JOptionPane.showMessageDialog(null, "No se encontro ningun equipo");
                        } else if (es.buscarEquipoPorID(valorCodigo) != null) {
                            aux = es.buscarEquipoPorID(valorCodigo);
                            actualizarTabla(aux);
                            jugadoresTablaRellenar = es.buscarEquipoPorID(valorCodigo).getJugadores();
                            refrescarTabla();
                        }
                    } catch (Exception f) {
                        JOptionPane.showMessageDialog(null, "Ingrese un valor numerico");
                    }
                }
            });
            panel.add(new Label("Equipo"));
            panel.add(JTableModelo());
            panel.add(new Label("Jugadores del equipo"));
            panel.add(JTableModeloJugadores());
            panel.add(new Label("*PJ: Partidos Jugados"));
            add(panel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Component JTableModelo() {
        try {
            DefaultTableModel mod = new DefaultTableModel();
            mod.addColumn("Codigo");
            mod.addColumn("Nombre");
            mod.addColumn("DT");
            mod.addColumn("Puntos");
            mod.addColumn("PJ");
            JTablePorEquipo = new JTable(mod);
            JScrollPane scrollPane = new JScrollPane(JTablePorEquipo);
            scrollPane.setBounds(40, 40, 400, 200);
            return scrollPane;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private void actualizarTabla(Equipo aux) {
        DefaultTableModel mod = (DefaultTableModel) JTablePorEquipo.getModel();
        mod.setRowCount(0); // Limpiar todas las filas existentes en la tabla

        // Agregar los datos del jugador al modelo de la tabla
        Object[] fila = new Object[7];
        fila[0] = aux.getId();
        fila[1] = aux.getNombre();
        fila[2] = aux.getDirectorTecnico();
        fila[3] = aux.getPuntos();
        fila[4] = aux.getPartidosJugados();

        mod.addRow(fila); // Agregar la fila al modelo de la tabla
    }

    private Component JTableModeloJugadores() {
        try {
            DefaultTableModel mod = new DefaultTableModel();
            mod.addColumn("Documento");
            mod.addColumn("Apellido");
            mod.addColumn("Nombre");
            mod.addColumn("Posicion");
            JTablePorJugadores = new JTable(mod);
            JScrollPane scrollPane = new JScrollPane(JTablePorJugadores);
            scrollPane.setBounds(40, 40, 400, 200);
            Object equipo[] = null;
            for (int i = 0; i < jugadoresTablaRellenar.size(); i++) {
                mod.addRow(equipo);
                Jugador getm = (Jugador) jugadoresTablaRellenar.get(i);
                mod.setValueAt(getm.getDocumento(), i, 0);
                mod.setValueAt(getm.getApellido(), i, 1);
                mod.setValueAt(getm.getNombre(), i, 2);
                mod.setValueAt(getm.getPosicion(), i, 3);
            }
            return scrollPane;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    private void refrescarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) JTablePorJugadores.getModel();
        modelo.setRowCount(0); // Vaciar la tabla

        for (Jugador jugador : jugadoresTablaRellenar) {
            Object[] fila = {jugador.getId(), jugador.getApellido(), jugador.getNombre(), jugador.getPosicion(), jugador.isEstado()};
            modelo.addRow(fila); // Agregar fila a la tabla
        }
    }
}
