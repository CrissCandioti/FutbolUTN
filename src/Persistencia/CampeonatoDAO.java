/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Campeonato;
import Entidades.Equipo;
import java.util.List;

/**
 *
 * @author criss
 */
public final class CampeonatoDAO extends DAO<Campeonato> {

    @Override
    public void persistirEntidad(Campeonato object) {
        super.persistirEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void actualizarEntidad(Campeonato object) {
        super.actualizarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void borrarEntidad(Campeonato object) {
        super.borrarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Campeonato buscarCampeonatoPorID(int id) {
        try {
            conectar();
            Campeonato campeonato = em.find(Campeonato.class, id);
            campeonato.getEquipos().forEach(equipo -> {
                equipo.getJugadores().size();
            });
            return campeonato;
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarCampeonatoPorID() de la clase CampeonatoDAO: " + e);
        } finally {
            desconectar();
        }
        return null;
    }

    public List<Campeonato> listaCompletaDeCampeonatos() {
        try {
            conectar();
            List<Campeonato> campeonatos = em.createQuery("SELECT DISTINCT c FROM Campeonato c LEFT JOIN FETCH c.equipos", Campeonato.class).getResultList();
            for (Campeonato campeonato : campeonatos) {
                for (Equipo equipo : campeonato.getEquipos()) {
                    equipo.getJugadores().size();
                }
            }
            return campeonatos;
        } catch (Exception e) {
            System.out.println("Error en el método listaCompletaDeCampeonatos() de la clase CampeonatoDAO: " + e);
        } finally {
            desconectar();
        }
        return null;
    }

}
