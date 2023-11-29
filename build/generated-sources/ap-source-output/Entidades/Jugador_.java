package Entidades;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-11-29T18:38:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Jugador.class)
public class Jugador_ { 

    public static volatile SingularAttribute<Jugador, String> posicion;
    public static volatile SingularAttribute<Jugador, Boolean> estado;
    public static volatile SingularAttribute<Jugador, LocalDate> fechaNacimiento;
    public static volatile SingularAttribute<Jugador, String> apellido;
    public static volatile SingularAttribute<Jugador, Integer> documento;
    public static volatile SingularAttribute<Jugador, Integer> id;
    public static volatile SingularAttribute<Jugador, String> nombre;

}