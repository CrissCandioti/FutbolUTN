package Entidades;

import Entidades.Equipo;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-11-29T18:38:48", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Campeonato.class)
public class Campeonato_ { 

    public static volatile SingularAttribute<Campeonato, Integer> id;
    public static volatile SingularAttribute<Campeonato, String> nombre;
    public static volatile ListAttribute<Campeonato, Equipo> equipos;

}