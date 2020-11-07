package cr.ac.itcr.Jugador;


import java.io.IOException;

/**
 * Clase invitado que hereda de jugador
 */
public class Invitado extends Jugador {
    private String serverIP;
    private int serverPort;

    public String getServerIP() {
        return serverIP;
    }
    public int getServerPort() {
        return serverPort;
    }

    /**
     * Constructor del jugador que ingresa los datos de partida
     * @param serverIP direccion en donde está el SocketServer
     * @param serverPort puerto donde está escuchando el server
     * @throws IOException
     */
    public Invitado(String serverIP, int serverPort) throws IOException {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

}