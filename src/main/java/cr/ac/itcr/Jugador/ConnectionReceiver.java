package cr.ac.itcr.Jugador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cr.ac.itcr.Cartas.Carta;
import cr.ac.itcr.Cartas.Json;
import cr.ac.itcr.Cartas.Stack.ListaDoble;
import cr.ac.itcr.UI.DatosPartida;
import cr.ac.itcr.UI.gameWindow;
import java.io.*;
import java.net.*;

/**
 *
 */
public class ConnectionReceiver {
    Anfitrion anfitrion;
    Boolean flag = true;
    DataInputStream in;
    DataOutputStream out;
    DatosPartida datosPartida;
    private ConnectionHandler handler;
    gameWindow gw;

    private Carta carta;

    /**
     * Constructor que lleva el hilo de ejecucion durante la partida que se inicializó en el socketServer
     * @param anfitrion quien abrió la partida
     * @param cartasNombre nombre de cartas creadas
     * @param datosPartida detos de la partida del jugador
     * @throws IOException
     */
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

    /**
     * Metodo que procesa la informacion recibida en una partida mediante sockets
     * @param socket socket en que se da la conexion
     * @param cartasNombre nombre de las cartas que se comunican
     * @param datosPartida datos de la partida con la info de jugador
     * @throws IOException
     */
    public void processConnection(Socket socket, String cartasNombre, DatosPartida datosPartida) throws IOException {
        in = new DataInputStream(socket.getInputStream());
        String incomingCard = in.readUTF();
        datosPartida.cartasOponente(incomingCard);
        System.out.println("Invitado ha ingresado: "+ incomingCard);
        handler = new ConnectionHandler(socket, this);
        handler.sendMessage(cartasNombre);
    }

    /**
     * Metodo para procesar las acciones que se deben realizar para determinado mensaje
     * @param message mensaje recibido
     * @throws IOException
     */
    public void processMessage (String message) throws IOException {

        System.out.println("Processing message: " + message );
        JsonNode node = Json.parse(message);
        writingJson("src/jugadas.json", node);
        this.carta = Json.fromJson(node, Carta.class);
        this.gw.showReceivedCard(this.carta, true);

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

    public ConnectionHandler getHandler() {
        return handler;
    }

    public void setGw(gameWindow gw) {
        this.gw = gw;
    }
}