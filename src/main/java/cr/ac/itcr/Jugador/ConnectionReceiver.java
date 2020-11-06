package cr.ac.itcr.Jugador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cr.ac.itcr.Cartas.Carta;
import cr.ac.itcr.Cartas.Json;
import cr.ac.itcr.Cartas.Stack.ListaDoble;
import cr.ac.itcr.UI.DatosPartida;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Set;

public class ConnectionReceiver {
    Anfitrion anfitrion;
    Boolean flag = true;
    DataInputStream in;
    DataOutputStream out;
    private HashMap<String, ConnectionHandler> usuarios = new HashMap<>();
    DatosPartida datosPartida;
    private ConnectionHandler handler;

    public ConnectionReceiver(Anfitrion anfitrion, String cartasNombre, DatosPartida datosPartida) throws IOException {
        this.anfitrion = anfitrion;
        this.datosPartida = datosPartida;

        Thread thread = new Thread(()-> {
            try {
                ServerSocket incoming = new ServerSocket(anfitrion.getPort(), 1, anfitrion.getIP());
                System.out.println("listening connections on: " + anfitrion.getIP() + "," + anfitrion.getPort());
                while (flag){
                    Socket socket = incoming.accept();
                    processConnection(socket, cartasNombre, datosPartida);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void processConnection(Socket socket, String cartasNombre, DatosPartida datosPartida) throws IOException {
        in = new DataInputStream(socket.getInputStream());
        String incomingCard = in.readUTF();
        datosPartida.cartasOponente(incomingCard);
        System.out.println("Invitado ha ingresado: "+ incomingCard);
        handler = new ConnectionHandler(socket, this);
        handler.sendMessage(cartasNombre);
    }

    public void processMessage (String message) throws IOException {

        System.out.println("Processing message: " + message );
        JsonNode node = Json.parse(message);
        writingJson("src/jugadas.json", node);
        Carta carta = Json.fromJson(node, Carta.class);
    }
    //Lee las jugadas en el archivo de Json jugadas.json

    public ListaDoble<JsonNode> readNodes(String fileName) throws JsonProcessingException {
        ListaDoble<JsonNode> listaNodos = new ListaDoble<>();
        JsonNode nodoJugadas =  Json.parse(fileName);
        int cont = 1;
        while (nodoJugadas.get("Jugadas").get(cont) != null ){
            listaNodos.ingresarNodo(nodoJugadas.get("Jugadas").get(cont));
            cont ++;
        }
        return listaNodos;
    }
    public void writingJson(String fileName, JsonNode cartaNode) throws IOException {
        Json cardsreader = new Json();
        String json = new String();
        JsonNode nodoJugadas =  Json.parse(cardsreader.jsonReader(json, fileName));
        int size = 0;
        try{
            size = nodoJugadas.get("Jugadas").size();
        }catch (Exception e){
            e.printStackTrace();
        }
        ((ObjectNode)nodoJugadas.get("Jugadas")).set(String.valueOf(size+1), cartaNode);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(fileName),nodoJugadas);
    }

    public void notifyClients() throws IOException {

        Set<String> keys = usuarios.keySet();
        String message = "Anfitrion" + "%" + unifyKeys(keys);
        for (String key: keys){
            usuarios.get(key).sendMessage(message);
        }
    }
    public String unifyKeys(Set<String> keys){
        StringBuilder set = new StringBuilder();
        for (String key: keys){
            set.append(key).append(";");
        }
        set = new StringBuilder(set.substring(0, set.length() - 1));
        return set.toString();
    }
    public ConnectionHandler getHandler() {
        return handler;
    }
}