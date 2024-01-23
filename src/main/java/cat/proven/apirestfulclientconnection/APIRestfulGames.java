package cat.proven.apirestfulclientconnection;

/**
 *
 * @author ergr0428
 */
public class APIRestfulGames {
    
    APIRestfulTeams home_team;
    int home_team_score;
    APIRestfulTeams visitor_team;
    int visitor_team_score;
    String date;

    public APIRestfulGames() {
    }

    public APIRestfulGames(APIRestfulTeams home_team, int home_team_score, APIRestfulTeams visitor_team, int visitor_team_score, String date) {
        this.home_team = home_team;
        this.home_team_score = home_team_score;
        this.visitor_team = visitor_team;
        this.visitor_team_score = visitor_team_score;
        this.date = date;
    }

    public APIRestfulTeams getHome_team() {
        return home_team;
    }

    public void setHome_team(APIRestfulTeams home_team) {
        this.home_team = home_team;
    }

    public int getHome_team_score() {
        return home_team_score;
    }

    public void setHome_team_score(int home_team_score) {
        this.home_team_score = home_team_score;
    }

    public APIRestfulTeams getVisitor_team() {
        return visitor_team;
    }

    public void setVisitor_team(APIRestfulTeams visitor_team) {
        this.visitor_team = visitor_team;
    }

    public int getVisitor_team_score() {
        return visitor_team_score;
    }

    public void setVisitor_team_score(int visitor_team_score) {
        this.visitor_team_score = visitor_team_score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("APIRestfulGames{");
        sb.append("home_team=").append(home_team);
        sb.append(", home_team_score=").append(home_team_score);
        sb.append(", visitor_team=").append(visitor_team);
        sb.append(", visitor_team_score=").append(visitor_team_score);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }


    
    
}

