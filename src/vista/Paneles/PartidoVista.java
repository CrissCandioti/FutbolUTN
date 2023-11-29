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
import java.awt.Label;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author criss
 */
public class PartidoVista extends JPanel {

    private JTextField JTextFieldCodigo;
    private JButton JButtonBuscar;
    private JButton JButtonJugar;
    private JComboBox JComboBoxEquipos1;
    private JComboBox JComboBoxEquipos2;
    private JLabel JLabelEquipo1;
    private JLabel JLabelEquipo2;
    private JLabel JLabelCampeonato;
    public Campeonato aux = new Campeonato();
    public CampeonatoService cs = new CampeonatoService();
    public EquipoService es = new EquipoService();

    public PartidoVista() {
        init();
    }

    private void init() {
        try {
            setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
            JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:295"));
            panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;");

            JTextFieldCodigo = new JTextField();
            JButtonBuscar = new JButton("Buscar");
            JButtonJugar = new JButton("Comenzar Partido");
            JLabel JLabel1 = new JLabel("Jugar Partido");
            JLabel1.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");
            JLabel JLabel2 = new JLabel("Por favor ingrese el codigo para seleccionar el campeonato");
            JLabel2.putClientProperty(FlatClientProperties.STYLE, "");
            JLabelEquipo1 = new JLabel();
            JLabelEquipo2 = new JLabel();
            JLabelCampeonato = new JLabel();
            JTextFieldCodigo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Codigo");

            panel.add(JLabel1);
            panel.add(JLabel2);
            panel.add(JTextFieldCodigo);
            panel.add(JButtonBuscar);

            //ActionListener al botón para manejar la búsqueda
            JButtonBuscar.addActionListener(e -> realizarBusquedas()
            );
            panel.add(JLabelCampeonato);
            //JComboBox inicialmente vacío al panel
            JComboBoxEquipos1 = new JComboBox();
            JComboBoxEquipos2 = new JComboBox();
            panel.add(new Label("Seleccione el Primer equipo"));
            panel.add(JComboBoxEquipos1);
            panel.add(new Label("Seleccione el Segundo equipo"));
            panel.add(JComboBoxEquipos2);
            panel.add(JButtonJugar);
            JButtonJugar.addActionListener(e -> logicaPartido()
            );
            panel.add(JLabelEquipo1);
            panel.add(JLabelEquipo2);
            add(panel);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void buscarInformacion() {
        try {
            String codigo = JTextFieldCodigo.getText().trim();
            int valorCodigo = Integer.parseInt(codigo);

            // Obtengo los datos de la base de datos
            CampeonatoService cs = new CampeonatoService();
            List<Equipo> equipos = cs.buscarCampeonatoPorID(valorCodigo).getEquipos();

            // Actualizo el modelo del JComboBox con los datos obtenidos
            DefaultComboBoxModel<Equipo> modelo = new DefaultComboBoxModel<>(equipos.toArray(new Equipo[0]));
            JComboBoxEquipos1.setModel(modelo);
            JComboBoxEquipos2.setModel(modelo);
        } catch (Exception ef) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo para buscar el campeonato o no se encontro el campeonato en la base de datos");
        }
    }

    private void buscarInformacion2() {
        try {
            String codigo = JTextFieldCodigo.getText().trim();
            int valorCodigo = Integer.parseInt(codigo);

            // Obtengo los datos de la base de datos
            CampeonatoService cs = new CampeonatoService();
            List<Equipo> equipos = cs.buscarCampeonatoPorID(valorCodigo).getEquipos();
            JLabelCampeonato.setText("Campeonato: " + cs.buscarCampeonatoPorID(valorCodigo).getNombre());
            // Actualizo el modelo del JComboBox con los datos obtenidos
            DefaultComboBoxModel<Equipo> modelo = new DefaultComboBoxModel<>(equipos.toArray(new Equipo[0]));
            JComboBoxEquipos2.setModel(modelo);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void realizarBusquedas() {
        buscarInformacion();
        buscarInformacion2();
    }

    private void logicaPartido() {
        try {
            // Creo una instancia de la clase Random
            Random random = new Random();

            // Genero un número aleatorio entre 1 y 3 (ambos inclusive)
            int numeroAleatorio = random.nextInt(3) + 1;
            System.out.println(numeroAleatorio);

            Equipo aux = (Equipo) JComboBoxEquipos1.getSelectedItem();//Primer equipo
            Equipo index = (Equipo) JComboBoxEquipos2.getSelectedItem();//Segundo equipo

            if (aux.getId() == index.getId()) {
                JOptionPane.showMessageDialog(null, "No se puede enfrentar el mismo equipo, selecione un segundo equipo diferente");
            } else if (JComboBoxEquipos1.getSelectedItem() != JComboBoxEquipos2.getSelectedItem()) {
                switch (numeroAleatorio) {
                    case 1:
                        System.out.println("Ganador: " + aux.getNombre());
                        System.out.println("Perdedor: " + index.getNombre());
                        es.modificarEquipoSinJugadores(aux.getId(), aux.getNombre(), aux.getDirectorTecnico(), aux.getJugadores(), aux.getPuntos() + 3, aux.getPartidosJugados() + 1);
                        es.modificarEquipoSinJugadores(index.getId(), index.getNombre(), index.getDirectorTecnico(), index.getJugadores(), index.getPuntos(), index.getPartidosJugados() + 1);
                        JOptionPane.showMessageDialog(null, "Equipo Ganador: " + aux.getNombre() + " Equipo Perdedor: " + index.getNombre());
                        JLabelEquipo1.setText("Ganador: " + aux.getNombre());
                        JLabelEquipo2.setText("Perdedor: " + index.getNombre());
                        return;
                    case 2:
                        System.out.println("Empate: " + aux.getNombre());
                        System.out.println("Empate: " + index.getNombre());
                        es.modificarEquipoSinJugadores(aux.getId(), aux.getNombre(), aux.getDirectorTecnico(), aux.getJugadores(), aux.getPuntos() + 1, aux.getPartidosJugados() + 1);
                        es.modificarEquipoSinJugadores(index.getId(), index.getNombre(), index.getDirectorTecnico(), index.getJugadores(), index.getPuntos() + 1, index.getPartidosJugados() + 1);
                        JOptionPane.showMessageDialog(null, "Equipo Empate: " + aux.getNombre() + " Equipo Empate: " + index.getNombre());
                        JLabelEquipo1.setText("Empate: " + aux.getNombre());
                        JLabelEquipo2.setText("Empate: " + index.getNombre());
                        return;
                    case 3:
                        System.out.println("Perdedor: " + aux.getNombre());
                        System.out.println("Ganador: " + index.getNombre());
                        es.modificarEquipoSinJugadores(aux.getId(), aux.getNombre(), aux.getDirectorTecnico(), aux.getJugadores(), aux.getPuntos(), aux.getPartidosJugados() + 1);
                        es.modificarEquipoSinJugadores(index.getId(), index.getNombre(), index.getDirectorTecnico(), index.getJugadores(), index.getPuntos() + 3, index.getPartidosJugados() + 1);
                        JOptionPane.showMessageDialog(null, "Equipo Perdedor: " + aux.getNombre() + " Equipo Ganador: " + index.getNombre());
                        JLabelEquipo1.setText("Perdedor: " + aux.getNombre());
                        JLabelEquipo2.setText("Ganador: " + index.getNombre());
                        return;
                }
            }
        } catch (Exception ej) {
            System.out.println(ej);
        }
    }

}
