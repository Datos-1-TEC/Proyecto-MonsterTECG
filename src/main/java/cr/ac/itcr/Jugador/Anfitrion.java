package cr.ac.itcr.Jugador;

import java.net.InetAddress;

public class Anfitrion extends Jugador {
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    private int port;
    private String IP;
    public Anfitrion() {

        super();
    }

}
