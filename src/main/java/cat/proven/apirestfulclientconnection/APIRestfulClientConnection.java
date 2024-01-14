package cat.proven.apirestfulclientconnection;

import cat.proven.apirestfulclientconnection.Menu;
import cat.proven.apirestfulclientconnection.Option;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.net.*;
import java.io.*;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mon
 */
public class APIRestfulClientConnection {

    String mostrarEquipos = "https://www.balldontlie.io/api/v1/teams";
    String mostrarEquiposLocVisScore = "https://jsonplaceholder.typicode.com/users/";
    String mostrarEquiposLocVisScoreDate = "https://jsonplaceholder.typicode.com/users?username=Bret";
    
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
                    System.out.println("NOT DONE YET");
                    break;
                case "listteamsscoredata":  //add a new category
                    System.out.println("NOT DONE YET");
                    break;
                default:
                    System.out.println("Acción inválida");
                    break;
            }
        } while (!exit);
    }

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

    private void displayTeamList(ArrayList<APIRestulClient> data) {
        for (APIRestulClient us : data) {
            System.out.println(us.toString());
        }
    }

    private APIRestulClient convertirJsonAPersona(String json) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, APIRestulClient.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<APIRestulClient> convertirListaJsonAPersona(String json) {
        try {
            Gson gson = new Gson();
            Type personaListType = new TypeToken<ArrayList<APIRestulClient>>() {
                
            }.getType();
            
            return gson.fromJson(json, personaListType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private void listarUnaPersona() throws MalformedURLException {
        URL url = new URL(mostrarEquipos);
        String mipeticion = realizarSolicitudGet(url.toString());
        System.out.println("Recibido: " + mipeticion);

        APIRestulClient teams = convertirJsonAPersona(mipeticion);
        System.out.println("-" + teams.toString());
    }

    private void listarUnGrupo() throws MalformedURLException {
        URL url = new URL(mostrarEquiposLocVisScore);
        String mipeticion = realizarSolicitudGet(url.toString());
        System.out.println("Recibido: " + mipeticion);

        ArrayList<APIRestulClient> users = convertirListaJsonAPersona(mipeticion);
        displayTeamList(users);
    }
    
    /**
     * Solicita una lista de todos los equipos de la NBA y los muestra
     * en pantalla. Primero, creo la url a partir de un string, después guardo
     * la petición en un string a través de un método que sirve para realizar la
     * propia petición, indicandola la url en cuestión. Por último, imprimo en
     * pantalla el resultado de la lista de equipos que he pedido.
     */
    private void handleMostrarEquipos() throws MalformedURLException {
        URL url = new URL(mostrarEquipos);
        String mipeticion = realizarSolicitudGet(url.toString());
        System.out.println("Recibido: " + mipeticion);
        // Convierto la petición en una lista de equipos
        ArrayList<APIRestulClient> teams = convertirListaJsonAPersona(mipeticion);
        displayTeamList(teams);
    }
}
