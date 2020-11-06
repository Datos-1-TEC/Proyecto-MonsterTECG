package cr.ac.itcr.Jugador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cr.ac.itcr.Cartas.Carta;
import cr.ac.itcr.Cartas.HechizosCartas;
import cr.ac.itcr.Cartas.Json;
import cr.ac.itcr.UI.DatosPartida;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionRequest {
    private DataOutputStream out;
    private DataInputStream in;
    boolean flag = true;
    private DatosPartida datosPartida;


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
    public void processMessage(String message) throws IOException {

        if (message.contains("%")) {
            datosPartida.cartasOponente(message);
        }
        else {
            System.out.println("Processing message: " + message );
            JsonNode node = Json.parse(message);
            writingJson("src/jugadas.json", node);
            Carta carta = Json.fromJson(node, Carta.class);
            System.out.println(carta.getName());
        }
    }
    public void sendMessage(String message) {
        try{
            out.writeUTF(message);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
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

    public DataOutputStream getOut() {
        return out;
    }

}
