/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Equipo;
import Entidades.Jugador;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author criss
 */
public final class EquipoDAO extends DAO<Equipo> {

    @Override
    public void persistirEntidad(Equipo object) {
        super.persistirEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void actualizarEntidad(Equipo object) {
        super.actualizarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void borrarEntidad(Equipo object) {
        super.borrarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public List<Equipo> listaDeEquipos() {
        try {
            conectar();
            return em.createQuery("SELECT DISTINCT e FROM Equipo e LEFT JOIN FETCH e.jugadores", Equipo.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error en el metodo listaDeEquipos() de la clase EquipoDAO: " + e);
        } finally {
            desconectar();
        }
        return null;
    }

    public Equipo buscarEquipoPorID(int idEquipo) {
        try {
            conectar();
            Equipo equipo = em.find(Equipo.class, idEquipo); // Carga el equipo por su ID

            // Carga ansiosa (eager loading) de los jugadores
            equipo.getJugadores().size(); // Carga los jugadores 

            return equipo; // Devuelve el equipo con la información de jugadores cargada
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarEquipoPorID() en la clase EquipoDAO: " + e);
        } finally {
            desconectar();
        }
        return null;
    }

    public List<Jugador> jugadoresSinEquipo() {
        try {
            conectar();
            /**
             * TypedQuery ofrece una forma segura de ejecutar consultas al
             * especificar el tipo de entidad que se espera en los resultados de
             * la consulta. Al usar TypedQuery, se garantiza que los resultados
             * devueltos por la consulta coincidan con el tipo esperado.
             */
            TypedQuery<Jugador> query = em.createQuery("SELECT j FROM Jugador j WHERE j NOT IN (SELECT DISTINCT j FROM Equipo e JOIN e.jugadores j)", Jugador.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error en el método jugadoresSinEquipo() de la clase JugadorDAO: " + e);
        } finally {
            desconectar();
        }
        return null;
    }
}
