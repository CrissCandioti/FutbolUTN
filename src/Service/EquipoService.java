/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entidades.Equipo;
import Entidades.Jugador;
import Persistencia.EquipoDAO;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author criss
 */
public class EquipoService {

    public void crearEquipo(String nombre, String directorTecnico, List<Jugador> jugadores, int puntos, int partidosJugados) {
        try {
            EquipoDAO dao = new EquipoDAO();
            int jugadoresReglamentario = jugadores.size();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del nombre no puede estar vacia");
                return;
            }
            if (directorTecnico.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del Director Tecnico no puede estar vacia");
                return;
            }
            if (jugadoresReglamentario != 11) {
                JOptionPane.showMessageDialog(null, "Por reglamento los equipos deben contener minimo 11 jugadores");
                return;
            }
            if (nombre.length() < 3 || directorTecnico.length() < 3) {
                JOptionPane.showMessageDialog(null, "El nombre del equipo u el nombre del director Tecnico no pueden tener menos de 3 caracteres");
                return;
            }
            char primerCaracterN = nombre.charAt(0);
            char primerCaracterA = directorTecnico.charAt(0);
            if (Character.isDigit(primerCaracterN)) {
                JOptionPane.showMessageDialog(null, "El nombre no puede comenzar con numeros");
                return;
            }
            if (Character.isDigit(primerCaracterA)) {
                JOptionPane.showMessageDialog(null, "El nombre del director Tecnico no puede comenzar con numeros");
                return;
            }
            for (char caracter : nombre.toCharArray()) {
                if (Character.isDigit(caracter)) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede contener caracteres numericos");
                    return;
                }
            }
            for (char caracter : directorTecnico.toCharArray()) {
                if (Character.isDigit(caracter)) {
                    JOptionPane.showMessageDialog(null, "El nombre del director Tecnico no puede contener caracteres numericos");
                    return;
                }
            }
            dao.persistirEntidad(new Equipo(nombre, directorTecnico, jugadores, puntos, partidosJugados));
            JOptionPane.showMessageDialog(null, "Se creo con exito el equipo");
        } catch (Exception e) {
            System.out.println("Error en el metodo crearEquipo() de la clase EquipoService: " + e);
        }
    }

    public void modificarEquipo(int id, String nombre, String directorTecnico, List<Jugador> jugadores, int puntos, int partidosJugados) {
        try {
            EquipoDAO dao = new EquipoDAO();
            int jugadoresReglamentario = jugadores.size();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del nombre no puede estar vacia");
                return;
            }
            if (directorTecnico.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del Director Tecnico no puede estar vacia");
                return;
            }
            if (jugadoresReglamentario != 11) {
                JOptionPane.showMessageDialog(null, "Por reglamento los equipos deben contener minimo 11 jugadores");
                return;
            }
            if (nombre.length() < 3 || directorTecnico.length() < 3) {
                JOptionPane.showMessageDialog(null, "El nombre del equipo u el nombre del director Tecnico no pueden tener menos de 3 caracteres");
                return;
            }
            char primerCaracterN = nombre.charAt(0);
            char primerCaracterA = directorTecnico.charAt(0);
            if (Character.isDigit(primerCaracterN)) {
                JOptionPane.showMessageDialog(null, "El nombre no puede comenzar con numeros");
                return;
            }
            if (Character.isDigit(primerCaracterA)) {
                JOptionPane.showMessageDialog(null, "El nombre del director Tecnico no puede comenzar con numeros");
                return;
            }
            for (char caracter : nombre.toCharArray()) {
                if (Character.isDigit(caracter)) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede contener caracteres numericos");
                    return;
                }
            }
            for (char caracter : directorTecnico.toCharArray()) {
                if (Character.isDigit(caracter)) {
                    JOptionPane.showMessageDialog(null, "El nombre del director Tecnico no puede contener caracteres numericos");
                    return;
                }
            }
            Equipo aux = dao.buscarEquipoPorID(id);
            aux.setNombre(nombre);
            aux.setDirectorTecnico(directorTecnico);
            aux.setJugadores(jugadores);
            aux.setPuntos(puntos);
            aux.setPartidosJugados(partidosJugados);
            dao.actualizarEntidad(aux);
            JOptionPane.showMessageDialog(null, "Se modifico con exito el equipo");
        } catch (Exception e) {
            System.out.println("Error en el metodo modificarEquipo() de la clase EquipoService: " + e);
        }
    }

    public void modificarEquipoSinJugadores(int id, String nombre, String directorTecnico, List<Jugador> jugadores, int puntos, int partidosJugados) {
        try {
            EquipoDAO dao = new EquipoDAO();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del nombre no puede estar vacia");
                return;
            }
            if (directorTecnico.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del Director Tecnico no puede estar vacia");
                return;
            }
            if (nombre.length() < 3 || directorTecnico.length() < 3) {
                JOptionPane.showMessageDialog(null, "El nombre del equipo u el nombre del director Tecnico no pueden tener menos de 3 caracteres");
                return;
            }
            char primerCaracterN = nombre.charAt(0);
            char primerCaracterA = directorTecnico.charAt(0);
            if (Character.isDigit(primerCaracterN)) {
                JOptionPane.showMessageDialog(null, "El nombre no puede comenzar con numeros");
                return;
            }
            if (Character.isDigit(primerCaracterA)) {
                JOptionPane.showMessageDialog(null, "El nombre del director Tecnico no puede comenzar con numeros");
                return;
            }
            for (char caracter : nombre.toCharArray()) {
                if (Character.isDigit(caracter)) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede contener caracteres numericos");
                    return;
                }
            }
            for (char caracter : directorTecnico.toCharArray()) {
                if (Character.isDigit(caracter)) {
                    JOptionPane.showMessageDialog(null, "El nombre del director Tecnico no puede contener caracteres numericos");
                    return;
                }
            }
            Equipo aux = dao.buscarEquipoPorID(id);
            aux.setNombre(nombre);
            aux.setDirectorTecnico(directorTecnico);
            aux.setJugadores(jugadores);
            aux.setPuntos(puntos);
            aux.setPartidosJugados(partidosJugados);
            dao.actualizarEntidad(aux);
            JOptionPane.showMessageDialog(null, "Se modifico con exito el equipo");
        } catch (Exception e) {
            System.out.println("Error en el metodo modificarEquipo() de la clase EquipoService: " + e);
        }
    }

    public void eliminarEquipo(int ID) {
        try {
            EquipoDAO dao = new EquipoDAO();
            Equipo aux = dao.buscarEquipoPorID(ID);
            dao.borrarEntidad(aux);
            JOptionPane.showMessageDialog(null, "El Equipo se elimino con exito");
        } catch (Exception e) {
            System.out.println("Error en el metodo eliminarEquipo() de la clase EquipoService: " + e);
        }
    }

    public Equipo buscarEquipoPorID(int ID) {
        try {
            EquipoDAO dao = new EquipoDAO();
            return dao.buscarEquipoPorID(ID);
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarEquipoPorID() de la clase EquipoService: " + e);
        }
        return null;
    }

    public List<Equipo> listaDeEquipos() {
        try {
            EquipoDAO dao = new EquipoDAO();
            return dao.listaDeEquipos();
        } catch (Exception e) {
            System.out.println("Error en el metodo listaDeEquipos() de la clase EquipoService: " + e);
        }
        return null;
    }

    public List<Jugador> jugadoresSinEquipo() {
        try {
            EquipoDAO dao = new EquipoDAO();
            return dao.jugadoresSinEquipo();
        } catch (Exception e) {
            System.out.println("Error en el metodo jugadoresSinEquipo() de la clase EquipoService: " + e);
        }
        return null;
    }

}
