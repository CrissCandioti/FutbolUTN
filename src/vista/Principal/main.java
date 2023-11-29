/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.Principal;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Component;
import vista.Complementos.EventMenu;
import vista.Complementos.MainForm;
import vista.Complementos.MenuItem;
import vista.Paneles.ConsultaPorCampeonatoVista;
import vista.Paneles.ConsultaPorEquipoVista;
import vista.Paneles.ConsultaPorJugadorVista;
import vista.Paneles.ConsultaTodosLosCampeonatosVista;
import vista.Paneles.ConsultaTodosLosEquiposVista;
import vista.Paneles.ConsultaTodosLosJugadorVista;
import vista.Paneles.CrearCameponatoVista;
import vista.Paneles.CrearEquipoVista;
import vista.Paneles.CrearJugadorVista;
import vista.Paneles.ModificarEliminarCampeonatoVista;
import vista.Paneles.ModificarEliminarEquipoJugadorVista;
import vista.Paneles.ModificarEliminarEquipoVista;
import vista.Paneles.ModificarEliminarJugadorVista;
import vista.Paneles.PartidoVista;

public class main extends javax.swing.JFrame {

    public main() {
        initComponents();
        //Los utilizamos para que el programa se ejecute en el centro de la pantalla
        setLocationRelativeTo(null);
        //Llamamos al metodo que cambie el tema
        menu2.addEventMenuSelected(new EventMenu() {
            @Override
            public void mainMenuSelected(MainForm mainForm, int index, MenuItem menuItem) {

            }

            @Override
            public void subMenuSelected(MainForm mainForm, int index, int subMenuIndex, Component menuItem) {
                System.out.println(index + " " + subMenuIndex);
                if (mainForm != null) {
                    if (index == 0 && subMenuIndex == 0) {
                        mainForm.displayForm(new CrearJugadorVista());
                    } else if (index == 0 && subMenuIndex == 1) {
                        mainForm.displayForm(new CrearEquipoVista());
                    } else if (index == 0 && subMenuIndex == 2) {
                        mainForm.displayForm(new CrearCameponatoVista());
                    } else if (index == 1 && subMenuIndex == 0) {
                        mainForm.displayForm(new ConsultaTodosLosJugadorVista());
                    } else if (index == 1 && subMenuIndex == 1) {
                        mainForm.displayForm(new ConsultaTodosLosEquiposVista());
                    } else if (index == 1 && subMenuIndex == 2) {
                        mainForm.displayForm(new ConsultaTodosLosCampeonatosVista());
                    } else if (index == 2 && subMenuIndex == 0) {
                        mainForm.displayForm(new ConsultaPorJugadorVista());
                    } else if (index == 2 && subMenuIndex == 1) {
                        mainForm.displayForm(new ConsultaPorEquipoVista());
                    } else if (index == 2 && subMenuIndex == 2) {
                        mainForm.displayForm(new ConsultaPorCampeonatoVista());
                    } else if (index == 3 && subMenuIndex == 0) {
                        mainForm.displayForm(new ModificarEliminarJugadorVista());
                    } else if (index == 3 && subMenuIndex == 1) {
                        mainForm.displayForm(new ModificarEliminarEquipoVista());
                    } else if (index == 3 && subMenuIndex == 2) {
                        mainForm.displayForm(new ModificarEliminarEquipoJugadorVista());
                    } else if (index == 3 && subMenuIndex == 3) {
                        mainForm.displayForm(new ModificarEliminarCampeonatoVista());
                    } else if (index == 4 && subMenuIndex == 0) {
                        mainForm.displayForm(new PartidoVista());
                    }
                }

            }
        });
        //Lo utilizamos para que el programa inicie seleccionando el primer item del menu.
        menu2.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subMenuPanel1 = new vista.Complementos.SubMenuPanel();
        menu2 = new vista.Complementos.Menu();
        jLabel2 = new javax.swing.JLabel();
        menuItem1 = new vista.Complementos.MenuItem();
        menuItem2 = new vista.Complementos.MenuItem();
        menuItem3 = new vista.Complementos.MenuItem();
        menuItem4 = new vista.Complementos.MenuItem();
        menuItem5 = new vista.Complementos.MenuItem();
        mainForm1 = new vista.Complementos.MainForm();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        subMenuPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(80, 1, 1, 1));
        subMenuPanel1.setForeground(new java.awt.Color(255, 255, 255));
        subMenuPanel1.setOpacity(1.0F);

        menu2.setBackground(new java.awt.Color(255, 255, 255));
        menu2.setMainForm(mainForm1);
        menu2.setOpacity(1.0F);
        menu2.setSubMenuPanel(subMenuPanel1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/Iconos/logo.png"))); // NOI18N
        menu2.add(jLabel2);

        menuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/Iconos/menu1.png"))); // NOI18N
        menuItem1.setText("menuItem1");
        menuItem1.setMenuIcon(new javax.swing.AbstractListModel() {
            String[] strings = { "vista/Iconos/s2_2.png", "vista/Iconos/s4_1.png", "vista/Iconos/s3_1.png" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menuItem1.setMenuModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Crear Jugador", "Crear Equipo", "Crear Campeonato" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menu2.add(menuItem1);

        menuItem2.setBackground(new java.awt.Color(35, 171, 234));
        menuItem2.setForeground(new java.awt.Color(242, 31, 154));
        menuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/Iconos/menu2.png"))); // NOI18N
        menuItem2.setText("menuItem2");
        menuItem2.setMenuIcon(new javax.swing.AbstractListModel() {
            String[] strings = { "vista/Iconos/s2_2.png", "vista/Iconos/s4_1.png", "vista/Iconos/s3_1.png" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menuItem2.setMenuModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Consulta todos los Jugador", "Consulta todos los Equipo", "Consulta todos los Campeonatos" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menu2.add(menuItem2);

        menuItem3.setBackground(new java.awt.Color(113, 66, 221));
        menuItem3.setForeground(new java.awt.Color(203, 209, 48));
        menuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/Iconos/menu3.png"))); // NOI18N
        menuItem3.setText("menuItem3");
        menuItem3.setMenuIcon(new javax.swing.AbstractListModel() {
            String[] strings = { "vista/Iconos/s2_2.png", "vista/Iconos/s4_1.png", "vista/Iconos/s3_1.png" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menuItem3.setMenuModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Consulta por Jugador", "Consulta por Equipo", "Consulta por Campeonato" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menu2.add(menuItem3);

        menuItem4.setBackground(new java.awt.Color(16, 79, 163));
        menuItem4.setForeground(new java.awt.Color(12, 158, 158));
        menuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/Iconos/menu4.png"))); // NOI18N
        menuItem4.setText("menuItem4");
        menuItem4.setMenuIcon(new javax.swing.AbstractListModel() {
            String[] strings = { "vista/Iconos/s2_2.png", "vista/Iconos/s4_1.png", "vista/Iconos/s4_1.png", "vista/Iconos/s3_1.png" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menuItem4.setMenuModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Modificar y Eliminar Jugador", "Modificar y Eliminar Equipo", "Modificar y Eliminar Equipo-Jugador", "Modificar y Eliminar Campeonato" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menu2.add(menuItem4);

        menuItem5.setBackground(new java.awt.Color(3, 33, 153));
        menuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/Iconos/menu5.png"))); // NOI18N
        menuItem5.setText("menuItem5");
        menuItem5.setMenuIcon(new javax.swing.AbstractListModel() {
            String[] strings = { "vista/Iconos/s1_1.png" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menuItem5.setMenuModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Jugar Partido" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        menu2.add(menuItem5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(subMenuPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainForm1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subMenuPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
            .addComponent(menu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainForm1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("vista.Estilo");
        FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private vista.Complementos.MainForm mainForm1;
    private vista.Complementos.Menu menu2;
    private vista.Complementos.MenuItem menuItem1;
    private vista.Complementos.MenuItem menuItem2;
    private vista.Complementos.MenuItem menuItem3;
    private vista.Complementos.MenuItem menuItem4;
    private vista.Complementos.MenuItem menuItem5;
    private vista.Complementos.SubMenuPanel subMenuPanel1;
    // End of variables declaration//GEN-END:variables
}
