/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author criss
 */
@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String directorTecnico;
    @OneToMany
    @JoinTable(
            name = "equipo_jugadores", // Nombre de la tabla de relación
            joinColumns = @JoinColumn(name = "equipo_id"), // Columna de Equipo en la tabla de relación
            inverseJoinColumns = @JoinColumn(name = "jugador_id") // Columna de Jugador en la tabla de relación
    )
    private List<Jugador> jugadores;
    private int puntos;
    private int partidosJugados;

    public Equipo() {
    }

    public Equipo(String nombre, String directorTecnico, List<Jugador> jugadores, int puntos, int partidosJugados) {
        this.nombre = nombre;
        this.directorTecnico = directorTecnico;
        this.jugadores = jugadores;
        this.puntos = puntos;
        this.partidosJugados = partidosJugados;
    }

    public Equipo(int id, String nombre, String directorTecnico, List<Jugador> jugadores, int puntos, int partidosJugados) {
        this.id = id;
        this.nombre = nombre;
        this.directorTecnico = directorTecnico;
        this.jugadores = jugadores;
        this.puntos = puntos;
        this.partidosJugados = partidosJugados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirectorTecnico() {
        return directorTecnico;
    }

    public void setDirectorTecnico(String directorTecnico) {
        this.directorTecnico = directorTecnico;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    @Override
    public String toString() {
        //return "Equipo{" + "id=" + id + ", nombre=" + nombre + ", directorTecnico=" + directorTecnico + ", jugadores=" + jugadores + ", puntos=" + puntos + ", partidosJugados=" + partidosJugados + '}';
        return " Nombre: " + nombre + ", Director Tecnico: " + directorTecnico;
    }

}
