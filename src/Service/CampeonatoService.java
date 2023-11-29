/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entidades.Campeonato;
import Entidades.Equipo;
import Persistencia.CampeonatoDAO;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author criss
 */
public class CampeonatoService {
    
    public void crearCampeonato(String nombre, List<Equipo> equiposParticipantes) {
        try {
            CampeonatoDAO dao = new CampeonatoDAO();
            int equiposReglamentarios = equiposParticipantes.size();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del nombre no puede estar vacia");
                return;
            }
            if (nombre.length() < 3) {
                JOptionPane.showMessageDialog(null, "El nombre no pueden tener menos de 3 caracteres");
                return;
            }
            if (equiposReglamentarios != 2) {
                JOptionPane.showMessageDialog(null, "Por reglamento en un campeonato deben participar minimamente 2 equipos");
                return;
            }
            dao.persistirEntidad(new Campeonato(nombre, equiposParticipantes));
            JOptionPane.showMessageDialog(null, "Se creo con exito el campeonato");
        } catch (Exception e) {
            System.out.println("Error en el metodo crearCampeonato() de la clase CampeonatoService: " + e);
        }
    }
    
    public void modificarCampeonato(int id, String nombre, List<Equipo> equiposParticipantes) {
        try {
            CampeonatoDAO dao = new CampeonatoDAO();
            Campeonato aux = dao.buscarCampeonatoPorID(id);
            aux.setNombre(nombre);
            aux.setEquipos(equiposParticipantes);
            dao.actualizarEntidad(aux);
            JOptionPane.showMessageDialog(null, "Se modifico con exito el campeonato");
        } catch (Exception e) {
            System.out.println("Error en el metodo modificarCampeonato() de la clase CampeonatoService: " + e);
        }
    }
    
    public void eliminarCampeonato(int id) {
        try {
            CampeonatoDAO dao = new CampeonatoDAO();
            Campeonato aux = dao.buscarCampeonatoPorID(id);
            dao.borrarEntidad(aux);
            JOptionPane.showMessageDialog(null, "Se elimino con exito el campeonato");
        } catch (Exception e) {
            System.out.println("Error en el metodo eliminarCampeonato() de la clase CampeonatoService: " + e);
        }
    }
    
    public Campeonato buscarCampeonatoPorID(int id) {
        try {
            CampeonatoDAO dao = new CampeonatoDAO();
            return dao.buscarCampeonatoPorID(id);
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarCampeonatoPorID() de la clase CampeonatoService: " + e);
        }
        return null;
    }
    
    public List<Campeonato> listaDeCampeonatos() {
        try {
            CampeonatoDAO dao = new CampeonatoDAO();
            return dao.listaCompletaDeCampeonatos();
        } catch (Exception e) {
            System.out.println("Error en el metodo listaDeCampeonatos() de la clase CampeonatoService: " + e);
        }
        return null;
    }
}
