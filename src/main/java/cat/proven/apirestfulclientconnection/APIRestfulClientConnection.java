package cat.proven.apirestfulclientconnection;

import cat.proven.apirestfulclientconnection.Menu;
import cat.proven.apirestfulclientconnection.Option;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.net.*;
import java.io.*;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mon
 */
public class APIRestfulClientConnection {

    String mostrarEquipos = "https://www.balldontlie.io/api/v1/teams";
    String mostrarEquiposLocVisScore = "https://www.balldontlie.io/api/v1/games";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        APIRestfulClientConnection restful = new APIRestfulClientConnection();
        Boolean exit = false;
        // Menu options
        Menu menu = new Menu("Menú NBA");
        menu.addOption(new Option("Salir", "exit"));
        menu.addOption(new Option("Listar a todos os jugadores", "listall"));
        menu.addOption(new Option("Listar equipos local visitantes y puntuación", "listteamsscore"));
        menu.addOption(new Option("Listar equipos local visitatnes y puntuación con fecha de partido", "listteamsscoredata"));

        //Show options
        do {
            //display menu
            menu.show();
            //get action
            String action = menu.getSelectedOptionActionCommand();
            if (action == null) {
                action = "invalidaction";
            }
            //control
            switch (action) {
                case "exit": //exit
                    exit = true;
                    break;
                case "listall":  // Lista todos los equipos
                    restful.handleMostrarEquipos();
                    break;
                case "listteamsscore":  //Lista los teams
                    restful.handleMostrarPartidosPorTemporadas();
                    break;
                case "listteamsscoredata":  //Lista los teams con la fecha
                    restful.handleMostrarPartidosPorTemporadasFecha();
                    break;
                default:
                    System.out.println("Acción inválida");
                    break;
            }
        } while (!exit);
    }

    /**
     * Realixa la solicitud a partir del string que se le ha pasado
     * @param urlString
     * @return 
     */
    private String realizarSolicitudGet(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud
            connection.setRequestMethod("GET");

            // Obtener la respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.toString();
            } else {
                System.out.println("Error en la solicitud. Código de respuesta: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lista los equipos
     * @param data 
     */
    private void displayTeamList(ArrayList<APIRestfulTeams> data) {
        for (APIRestfulTeams us : data) {
            System.out.println(us.toString());
        }
    }

    /**
     * Lista los partidos
     * @param data 
     */
    private void displayGamesList(ArrayList<APIRestfulGames> data) {
        for (APIRestfulGames us : data) {
            System.out.println(us.toString());
        }
    }

    /**
     * Convierte una lista de Json al objeto team
     * @param json
     * @return Un objeto Team
     */
    private ArrayList<APIRestfulTeams> convertirListaJsonATeam(String json) {
        try {
            Gson gson = new Gson();
            Type teamsListType = new TypeToken<ArrayList<APIRestfulTeams>>() {
            }.getType();
            return gson.fromJson(json, teamsListType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convierte una lista de Json al objeto Game
     * @param json
     * @return Un objeto Game
     */
    private ArrayList<APIRestfulGames> convertirListaJsonAGame(String json) {
        try {
            Gson gson = new Gson();
            Type gameListType = new TypeToken<ArrayList<APIRestfulGames>>() {
            }.getType();
            return gson.fromJson(json, gameListType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Solicita una lista de todos los equipos de la NBA y los muestra en
     * pantalla. Primero, creo la url a partir de un string, después guardo la
     * petición en un string a través de un método que sirve para realizar la
     * propia petición, indicandola la url en cuestión. Por último, imprimo en
     * pantalla el resultado de la lista de equipos que he pedido.
     */
    private void handleMostrarEquipos() throws MalformedURLException {
        URL url = new URL(mostrarEquipos);
        String mipeticion = realizarSolicitudGet(url.toString());
        System.out.println("Recibido: " + mipeticion);

//        mipeticion = mipeticion.replace("{\"data\":", "");
//        mipeticion = mipeticion.replace(",\"meta\":{\"current_page\":1,\"next_page\":2,\"per_page\":30}}", "");
// Obtengo el string que he recibido y lo convierto en una lista de lo que yo quiero a través del element
        JsonObject jObj = JsonParser.parseString(mipeticion).getAsJsonObject();
        JsonElement jEl = jObj.get("data");

        // Convierto la petición en una lista de equipos
        ArrayList<APIRestfulTeams> teams = convertirListaJsonATeam(jEl.toString()); //LE PASO EL JSONELEMENT!!!
        displayTeamList(teams);
    }

    /**
     * Solicita los partidos de una temporada en específico. Primero,
     * preguntamos al usuario que año de temporada quiere ver. Una vez lo hace,
     * construimos la url en base a la petición. Por último, gestionamos el
     * string de la petición y la convertimos en un objeto SeasonMatches y
     * mostramos en pantalla
     */
    private void handleMostrarPartidosPorTemporadas() throws MalformedURLException {
        // Preguntamos al usuario
        System.out.println("Indica el año de temporada: ");
        Scanner scan = new Scanner(System.in);
        String usuarioTemporada = scan.next();
        // Quizás validaciones (?)
        // Construimos el string
        mostrarEquiposLocVisScore += "?seasons[]:" + usuarioTemporada;
        // Convertimos el string a un URL para solicitar una petición
        URL url = new URL(mostrarEquiposLocVisScore);
        String mipeticion = realizarSolicitudGet(url.toString());
        System.out.println("Recibido: " + mipeticion);
        // El usuario

        JsonObject jObj = JsonParser.parseString(mipeticion).getAsJsonObject();
        JsonElement jEl = jObj.get("data");

        ArrayList<APIRestfulGames> games = convertirListaJsonAGame(jEl.toString()); //LE PASO EL JSONELEMENT!!!
        displayGamesList(games);
        mostrarEquiposLocVisScore = "https://www.balldontlie.io/api/v1/games";
    }

    /**
     * Solicita los partidos de una temporada en específico. Primero,
     * preguntamos al usuario que año de temporada quiere ver. Una vez lo hace,
     * construimos la url en base a la petición. Por último, gestionamos el
     * string de la petición y la convertimos en un objeto SeasonMatches y
     * mostramos en pantalla
     */
    private void handleMostrarPartidosPorTemporadasFecha() throws MalformedURLException {
        // Preguntamos al usuario
        System.out.println("Indica el año de temporada: ");
        Scanner scan = new Scanner(System.in);
        String usuarioTemporada = scan.next();
        // La fecha inicial
        System.out.println("Indica la fecha de inicio (dd-mm-yyyy): ");
        String usuarioFechaInicio = scan.next();
        // La fecha final
        System.out.println("Indica la fecha final (dd-mm-yyyy): ");
        String usuarioFechaFinal = scan.next();
        // Quizás validaciones (?)
        // Construimos el string
        mostrarEquiposLocVisScore += "?seasons[]:" + usuarioTemporada + "?start_date=" + usuarioFechaInicio + "?end_date=" + usuarioFechaFinal;
        // Convertimos el string a un URL para solicitar una petición
        URL url = new URL(mostrarEquiposLocVisScore);
        String mipeticion = realizarSolicitudGet(url.toString());
        System.out.println("Recibido: " + mipeticion);
        // El usuario

        JsonObject jObj = JsonParser.parseString(mipeticion).getAsJsonObject();
        JsonElement jEl = jObj.get("data");

        ArrayList<APIRestfulGames> games = convertirListaJsonAGame(jEl.toString()); //LE PASO EL JSONELEMENT!!!
        displayGamesList(games);
        
        mostrarEquiposLocVisScore = "https://www.balldontlie.io/api/v1/games";
    }
}
