package cat.proven.apirestfulclientconnection;

import java.util.Date;
import java.util.Objects;

/**
 * Clase constructora de la api. El contenido a tener en cuenta es lo siguiente:
 * Los equipos -> Id, nombre
 * Los paritdos -> id_equipoLocal, id_equipoVisitante
 * @author ergr0428
 */
public class APIRestulClient {
    private Integer id_team;
    private String first_name;

    public APIRestulClient(Integer id, String first_name) {
        this.id_team = id;
        this.first_name = first_name;
    }

    public APIRestulClient() {
    }

    public Integer getId() {
        return id_team;
    }

    public void setId(Integer id) {
        this.id_team = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("APIRestulClient{");
        sb.append("id=").append(id_team);
        sb.append(", first_name=").append(first_name);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id_team);
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
        final APIRestulClient other = (APIRestulClient) obj;
        return Objects.equals(this.id_team, other.id_team);
    }
    
}

/**
 * Clase constructora especifica de los partidos jugados.
 * @author erikgp
 */
class Partidos {
    private Integer id_game;
    private String id_homeTeam;
    private String homeTeam_name;
    private String homeTeam_score;
    private String id_visitor;
    private String visitorTeam_name;
    private String visitorTeam_score;
    private Date dateMatch;

    // Constructor, getters, and setters

    public Partidos(Integer id_game, String id_homeTeam, String homeTeam_name, String homeTeam_score, String id_visitor, String visitorTeam_name, String visitorTeam_score, Date dateMatch) {
        this.id_game = id_game;
        this.id_homeTeam = id_homeTeam;
        this.homeTeam_name = homeTeam_name;
        this.homeTeam_score = homeTeam_score;
        this.id_visitor = id_visitor;
        this.visitorTeam_name = visitorTeam_name;
        this.visitorTeam_score = visitorTeam_score;
        this.dateMatch = dateMatch;
    }

    public Partidos() {
    }

    public Integer getId_game() {
        return id_game;
    }

    public void setId_game(Integer id_game) {
        this.id_game = id_game;
    }

    public String getId_homeTeam() {
        return id_homeTeam;
    }

    public void setId_homeTeam(String id_homeTeam) {
        this.id_homeTeam = id_homeTeam;
    }

    public String getHomeTeam_name() {
        return homeTeam_name;
    }

    public void setHomeTeam_name(String homeTeam_name) {
        this.homeTeam_name = homeTeam_name;
    }

    public String getHomeTeam_score() {
        return homeTeam_score;
    }

    public void setHomeTeam_score(String homeTeam_score) {
        this.homeTeam_score = homeTeam_score;
    }

    public String getId_visitor() {
        return id_visitor;
    }

    public void setId_visitor(String id_visitor) {
        this.id_visitor = id_visitor;
    }

    public String getVisitorTeam_name() {
        return visitorTeam_name;
    }

    public void setVisitorTeam_name(String visitorTeam_name) {
        this.visitorTeam_name = visitorTeam_name;
    }

    public String getVisitorTeam_score() {
        return visitorTeam_score;
    }

    public void setVisitorTeam_score(String visitorTeam_score) {
        this.visitorTeam_score = visitorTeam_score;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id_game);
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
        final Partidos other = (Partidos) obj;
        return Objects.equals(this.id_game, other.id_game);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Partidos{");
        sb.append("id_game=").append(id_game);
        sb.append(", id_homeTeam=").append(id_homeTeam);
        sb.append(", homeTeam_name=").append(homeTeam_name);
        sb.append(", homeTeam_score=").append(homeTeam_score);
        sb.append(", id_visitor=").append(id_visitor);
        sb.append(", visitorTeam_name=").append(visitorTeam_name);
        sb.append(", visitorTeam_score=").append(visitorTeam_score);
        sb.append(", dateMatch=").append(dateMatch);
        sb.append('}');
        return sb.toString();
    }
    
    
}
    

