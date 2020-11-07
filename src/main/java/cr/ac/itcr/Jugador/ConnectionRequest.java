package cr.ac.itcr.Jugador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cr.ac.itcr.Cartas.Carta;
import cr.ac.itcr.Cartas.HechizosCartas;
import cr.ac.itcr.Cartas.Json;
import cr.ac.itcr.UI.DatosPartida;
import cr.ac.itcr.UI.gameWindow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Clase que establece la conexion con el socket server para iniciar la partida de juego
 */
public class ConnectionRequest {
    private DataOutputStream out;
    private DataInputStream in;
    boolean flag = true;
    private DatosPartida datosPartida;
    private gameWindow gw;

    private Carta carta;

    /**
     * Constructor de la clase que recibe la direccion y el puerto a donde se establece la conexion, además recibe
     * los datos de la partida iniciada
     * @param ip direccion IP del serverSocket
     * @param port puerto
     * @param datosPartida datos de la partida iniciada con las cartas del jugador
     * @throws IOException
     */
    public ConnectionRequest(String ip, int port, DatosPartida datosPartida) throws IOException {

        Socket request = new Socket(ip, port );
        in = new DataInputStream(request.getInputStream());
        out = new DataOutputStream(request.getOutputStream());
        this.datosPartida = datosPartida;

        Thread thread = new Thread(() -> {
            while(flag) {
                try {
                    String message = in.readUTF();
                    processMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    /**
     * Metodo para procesar las acciones que se deben realizar para determinado mensaje
     * @param message mensaje recibido
     * @throws IOException
     */
    public void processMessage(String message) throws IOException {

        if (message.contains("%")) {
            datosPartida.cartasOponente(message);
        }
        else {
            System.out.println("Processing message: " + message );
            JsonNode node = Json.parse(message);
            writingJson("src/jugadas.json", node);
            this.carta = Json.fromJson(node, Carta.class);
            this.gw.showReceivedCard(this.carta, true);
            System.out.println(carta.getName());
        }
    }

    /**
     * Enviar el String mensaje por el socket
     * @param message mensaje a enviar
     */
    public void sendMessage(String message) {
        try{
            out.writeUTF(message);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Metodo que escribe en el Json de las jugadas, las cartas que se tiran en cada turno del juego
     * @param fileName nombre del archivo donde se esta escribiendo
     * @param cartaNode JsonNode que se usa para escribir dentro del archivo Json
     * @throws IOException
     */
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
    public DataOutputStream getOut() {
        return out;
    }

    public void setGw(gameWindow gw) {
        this.gw = gw;
    }
}
