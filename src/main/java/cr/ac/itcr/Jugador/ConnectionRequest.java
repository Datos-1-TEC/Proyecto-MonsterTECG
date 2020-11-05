package cr.ac.itcr.Jugador;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    DatosPartida datosPartida;


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

        String[] components = message.split("%");
        //Se agrega un nuevo usuario en caso de que no se encuentre en lista
        if (components[0].equals("Anfitrion")) {
            String[] cartas = message.split(";");

            for (String carta: cartas){
                System.out.println(carta);
            }
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

    public DataOutputStream getOut() {
        return out;
    }
    /*    public String drawCard() throws IOException {
        //Object instantiation
        ObjectMapper objectMapper = new ObjectMapper();

        HechizosCartas nuevaCarta = new HechizosCartas();
        nuevaCarta.setName("Congelar");
        nuevaCarta.setCosteMana(90);
        nuevaCarta.setAction("Pasa 3 turnos");
        JsonNode cartaNode = Json.toJson(nuevaCarta);
        objectMapper.writeValue(new File("src/carta.json"),nuevaCarta);

        String card = Json.generateString(cartaNode, true);
        return card;
    }*/
}
