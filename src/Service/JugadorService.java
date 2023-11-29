/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entidades.Jugador;
import Persistencia.JugadorDAO;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * El paquete Service contiene las clases jugadorSerice,equipoService e
 * campeonatoService. Estas clases estan en constante comunicacion con el
 * paquete "Vista" y con el paquete "Persistencia" Estas clases primero reciben
 * los datos obtenidos de las "Vistas", luego cada uno de los metodos ubicados
 * dentro de las clases Services se encargan de analizar y de cumplir las
 * restricciones programas. Una vez de desempeñar con la tarea asignada cada
 * metodo establece una comunicacion con el paquete "Persistencia", la cual se
 * encarga de enviarle esta informacion.
 */
public class JugadorService {

    public void crearJugador(int documento, String apellido, String nombre, LocalDate fechaNacimiento, String posicion, boolean estado) {
        /**
         * Dentro de un bloque try-catch el metodo procede a analizar estos
         * datos con las restricciones
         */
        try {
            JugadorDAO dao = new JugadorDAO();
            /**
             * Se crean dos variables con los nombre dniReglamentarioMinimo y
             * dniReglamentarioMaximo, estas variables de tipo de dato entero
             * sirven para la restriccion del documento establecido mas adelante
             */
            int dniReglamentarioMinimo = 1234567;
            int dniReglamentarioMaximo = 123456789;
            /**
             * Se crea una variable LocalDate la cual se utiliza para la
             * restriccion de la edad, el programa solo admite alumnos mayores
             * de 18 años de edad
             */
            LocalDate mayorEdad = LocalDate.of(2005, 01, 01);
            /**
             * Se procede a pasar el dato dni y las variables
             * cadenaDniReglamentarioMinimo y cadenaDniReglamentarioMaximo a
             * cadena de texto para proceder con su restriccion
             */
            String cadenaDni = Integer.toString(documento);
            String cadenaDniReglamentarioMinimo = Integer.toString(dniReglamentarioMinimo);
            String cadenaDniReglamentarioMaximo = Integer.toString(dniReglamentarioMaximo);
            boolean limiteEdad = fechaNacimiento.isAfter(mayorEdad);
            /**
             * Estas restricciones se encargan que algunos datos no esten vacios
             */
            if (apellido.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del apellido no puede estar vacia");
                return;
            }
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del nombre no puede estar vacia");
                return;
            }
            /**
             * Las restricciones realizada mas adelante se encargan de
             * desempeñar distintas condiciones. Esta restriccion se encarga de
             * buscar un dni en la base de datos para no registrar un jugador
             * con ese documento
             */
            if (dao.buscarJugadorPorDNI(documento) != null) {
                JOptionPane.showMessageDialog(null, "Tenemos registrado un jugador con el documento ingresado");
                return;
            }
            /**
             * Esta restriccion se encarga de analizar la cantidad de caracteres
             * que se ingresa por el documento.
             */
            if (cadenaDni.length() > cadenaDniReglamentarioMaximo.length()) {
                JOptionPane.showMessageDialog(null, "El documento ingresado es mayor al reglamentario");
                return;
            }
            if (cadenaDni.length() < cadenaDniReglamentarioMinimo.length()) {
                JOptionPane.showMessageDialog(null, "El documento ingresado es menor al reglamentario");
                return;
            }
            /**
             * Esta restriccion es la encarga de analizar la cantidad minima de
             * caracter que aceptan los datos del nombre y apellido
             */
            if (nombre.length() < 3 || apellido.length() < 3) {
                JOptionPane.showMessageDialog(null, "El nombre u apellido no pueden tener menos de 3 caracteres");
                return;
            }
            /**
             * Esta restriccion se encarga de establecer el limite de edad
             * admitida para el registro
             */
            if (limiteEdad == true) {
                JOptionPane.showMessageDialog(null, "Eres menor de edad para proseguir con el registro de inscripcion");
                return;
            }
            char primerCaracterN = nombre.charAt(0);
            char primerCaracterA = apellido.charAt(0);
            if (Character.isDigit(primerCaracterN)) {
                JOptionPane.showMessageDialog(null, "El nombre no puede comenzar con numeros");
                return;
            }
            if (Character.isDigit(primerCaracterA)) {
                JOptionPane.showMessageDialog(null, "El apellido no puede comenzar con numeros");
                return;
            }
            /**
             * Esta restriccion esta creada para que el usuario no ingrese un
             * nombre o apellido con caracteres numericos.
             */
            for (char caracter : nombre.toCharArray()) {
                if (Character.isDigit(caracter)) {
                    JOptionPane.showMessageDialog(null, "El nombre no puede contener caracteres numericos");
                    return;
                }
            }
            for (char caracter : apellido.toCharArray()) {
                if (Character.isDigit(caracter)) {
                    JOptionPane.showMessageDialog(null, "El apellido no puede contener caracteres numericos");
                    return;
                }
            }
            dao.persistirEntidad(new Jugador(documento, apellido, nombre, fechaNacimiento, posicion, estado));
            JOptionPane.showMessageDialog(null, "El jugador se creo con exito");
        } catch (Exception e) {
            System.out.println("Error en el metodo crearJugador() de la calse JugadorService: " + e);
        }
    }

    /*
     * El metodo modificarJugador tiene un desempeño muy similar al crear ,con
     * la diferencia que en la comunicacion con el paquete "Persitencia" el
     * metodo que va a recibir toda esta informacion es modificarJugador y no
     * guardar como en el metodo persistirJugador.
     */
    public void modificarJugador(int id, int documento, String apellido, String nombre, LocalDate fechaNacimiento, String posicion, boolean estado) {
        try {
            JugadorDAO dao = new JugadorDAO();
            int dniReglamentarioMinimo = 1234567;
            int dniReglamentarioMaximo = 123456789;
            LocalDate mayorEdad = LocalDate.of(2005, 01, 01);

            String cadenaDni = Integer.toString(documento);
            String cadenaDniReglamentarioMinimo = Integer.toString(dniReglamentarioMinimo);
            String cadenaDniReglamentarioMaximo = Integer.toString(dniReglamentarioMaximo);
            boolean limiteEdad = fechaNacimiento.isAfter(mayorEdad);

            if (apellido.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del apellido no puede estar vacia");
                return;
            }
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La celda del nombre no puede estar vacia");
                return;
            }
            if (dao.buscarJugadorPorDNI(documento) != null && dao.buscarJugadorPorID(id).getDocumento() != documento) {
                JOptionPane.showMessageDialog(null, "Tenemos registrado un jugador con el documento ingresado");
                JOptionPane.showMessageDialog(null, "Recuerde ingresar correctamente el documento para su modificacion");
                return;
            }
            if (nombre.length() < 3 || apellido.length() < 3) {
                JOptionPane.showMessageDialog(null, "El nombre u apellido no pueden tener menos de 3 caracteres");
                JOptionPane.showMessageDialog(null, "Debe tener presente el respetar los caracteres reglamentarios para su modificacion");
                return;
            }
            if (cadenaDni.length() < cadenaDniReglamentarioMinimo.length()) {
                JOptionPane.showMessageDialog(null, "El documento ingresado es menor al reglamentario");
                return;
            }
            if (cadenaDni.length() > cadenaDniReglamentarioMaximo.length()) {
                JOptionPane.showMessageDialog(null, "El documento ingresado es mayor al reglamentario");
                return;
            }
            if (limiteEdad == true) {
                JOptionPane.showMessageDialog(null, "Eres menor de edad para proseguir con el registro de inscripcion");
                return;
            }
            char primerCaracterN = nombre.charAt(0);
            char primerCaracterA = apellido.charAt(0);
            if (Character.isDigit(primerCaracterN)) {
                JOptionPane.showMessageDialog(null, "El nuevo nombre no puede comenzar con numeros");
                return;
            }
            if (Character.isDigit(primerCaracterA)) {
                JOptionPane.showMessageDialog(null, "El nuevo apellido no puede comenzar con numeros");
                return;
            }

            for (char caracter : nombre.toCharArray()) {
                if (Character.isDigit(caracter)) {
                    JOptionPane.showMessageDialog(null, "El nuevo nombre no puede contener caractere numericos");
                    return;
                }
            }
            for (char caracter : apellido.toCharArray()) {
                if (Character.isDigit(caracter)) {
                    JOptionPane.showMessageDialog(null, "El nuevo apellido no puede contener caractere numericos");
                    return;
                }
            }
            Jugador aux = dao.buscarJugadorPorID(id);
            aux.setDocumento(documento);
            aux.setApellido(apellido);
            aux.setNombre(nombre);
            aux.setFechaNacimiento(fechaNacimiento);
            aux.setPosicion(posicion);
            aux.setEstado(estado);
            dao.actualizarEntidad(aux);
            JOptionPane.showMessageDialog(null, "Se modifico con exito el jugador");
        } catch (Exception e) {
            System.out.println("Error en el metodo modificarJugador() de la clase JugadorService: " + e);
        }
    }

    /*
     * //El metodo buscarJugadorPorID se encarga de buscar al jugador que recibe
     * por parametro el id, y este retornara al jugador si lo encuentra.
     */
    public Jugador buscarJugadorPorID(int ID) {
        try {
            JugadorDAO dao = new JugadorDAO();
            return dao.buscarJugadorPorID(ID);
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarJugadorPorID() de la clase JugadorService: " + e);
        }
        return null;
    }

    /*
     * //El metodo buscarAlumnoPorDNI tiene un desempeño similar al metodo
     * anterior con la peculiar diferencia que busca a este jugador por el
     * documento.
     */
    public Jugador buscarJugadorPorDNI(int dni) {
        try {
            JugadorDAO dao = new JugadorDAO();
            return dao.buscarJugadorPorDNI(dni);
        } catch (Exception e) {
            System.out.println("Error en el metodo buscarJugadorPorDNI() en la clase JugadorService: " + e);
        }
        return null;
    }

    /*
     * El metodo eliminarJugador se encarga de eliminar al jugador por medio de su ID
     */
    public void eliminarJugador(int id) {
        try {
            JugadorDAO dao = new JugadorDAO();
            Jugador aux = dao.buscarJugadorPorID(id);
            dao.borrarEntidad(aux);
            JOptionPane.showMessageDialog(null, "Se elimino con exito el jugador");
        } catch (Exception e) {
            System.out.println("Error en el metodo eliminarJugador() de la clase JugadorService: " + e);
        }
    }

    /*
     * //El metodo listaJugadores se encarga de mostrar a todos los alumno
     * registrados en la base de datos, esten tanto activo como inactivos
     */
    public List<Jugador> listaJugadores() {
        try {
            JugadorDAO dao = new JugadorDAO();
            return dao.listaJugadores();
        } catch (Exception e) {
            System.out.println("Error en el metodo listaJugadores() de la clase JugadorService: " + e);
        }
        return null;
    }
}
