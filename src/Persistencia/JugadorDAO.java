/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Jugador;
import java.util.List;

/**
 * Las clases de entidades DAO heredan los metodos de DAO,la cual consiste en la
 * coneccion a la base de datos, la desconecion a la base de datos, y los
 * metodos de consultas propios de DAO. Esta clase ademas de heredar se declara
 * como "final", debido a que no queremos que continue con la jerarquia de la
 * herencia. Esta clase de alumnoDAO va a contener los metodos necesarios para
 * la comunicacion de la base de datos.
 *
 */
public final class JugadorDAO extends DAO<Jugador> {

    /*
     * El metodo persistirEntidad es la encargadar de comunicarse con la base de
     * datos, e insertar y llamar al metodo
     * persistirEntidad(Jugador object) de la clase que hereda.
     */
    @Override
    public void persistirEntidad(Jugador object) {
        super.persistirEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /*
     * Este metodo se encarga de actualizar o mejor dicho modificar al jugador
     * que esta en la base de datos.
     */
    @Override
    public void actualizarEntidad(Jugador object) {
        super.actualizarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /*
     * //El metodo borrarEntidad se encarga de eliminar el la entidad de la base de datos
     */
    @Override
    public void borrarEntidad(Jugador object) {
        super.borrarEntidad(object); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /*
     * Este metodo recibe por parametro un dato entero llamado id, la cual se
     * encarga de buscar al Jugador y retornarlo.
     */
    public Jugador buscarJugadorPorID(int ID) {
        try {
            conectar();
            return em.find(Jugador.class, ID);
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarJugadorPorID() de la clase JugadorDAO: " + e);
        } finally {
            desconectar();
        }
        return null;
    }

    /*
     * Este metodo es muy parecido al anterior con la modificacion que busca al jugador por medio del documento
     */
    public Jugador buscarJugadorPorDNI(int documento) {
        try {
            conectar();
            return (Jugador) em.createQuery("SELECT f FROM Jugador f WHERE f.documento = :documento").setParameter("documento", documento).getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarJugadorPorDNI() de la clase JugadorDAO: " + e);
        } finally {
            desconectar();
        }
        return null;
    }

    /*
     * Este metodo me retorna una lista de jugadores, sean con estado activo o
     * inactivo la lista me mostrara a todos los jugadores.
     */
    public List<Jugador> listaJugadores() {
        try {
            conectar();
            return (List<Jugador>) em.createQuery("Select f From Jugador f").getResultList();
        } catch (Exception e) {
            System.out.println("Error en el metodo listaJugadores() de la clase JugadorDAO: " + e);
        } finally {
            desconectar();
        }
        return null;
    }
}
