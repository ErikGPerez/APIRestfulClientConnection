package cat.proven.apirestfulclientconnection;

import java.util.Date;
import java.util.Objects;

/**
 * Clase constructora de la api. El contenido a tener en cuenta es lo siguiente:
 * Los equipos -> Id, nombre
 * Los paritdos -> id_equipoLocal, id_equipoVisitante
 * @author ergr0428
 */
public class APIRestfulTeams {
    private Integer id;
    private String full_name;

    public APIRestfulTeams(Integer id, String first_name) {
        this.id = id;
        this.full_name = first_name;
    }

    public APIRestfulTeams() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return full_name;
    }

    public void setFirst_name(String first_name) {
        this.full_name = first_name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("APIRestfulClient{");
        sb.append("id=").append(id);
        sb.append(", first_name=").append(full_name);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final APIRestfulTeams other = (APIRestfulTeams) obj;
        return Objects.equals(this.id, other.id);
    }
    
}

    

