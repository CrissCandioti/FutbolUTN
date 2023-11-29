package Entidades;

import Entidades.Jugador;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-11-29T18:38:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Equipo.class)
public class Equipo_ { 

    public static volatile SingularAttribute<Equipo, Integer> id;
    public static volatile ListAttribute<Equipo, Jugador> jugadores;
    public static volatile SingularAttribute<Equipo, Integer> puntos;
    public static volatile SingularAttribute<Equipo, Integer> partidosJugados;
    public static volatile SingularAttribute<Equipo, String> nombre;
    public static volatile SingularAttribute<Equipo, String> directorTecnico;

}