package cr.ac.itcr.Jugador;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

public class ConnectionReceiver {
    Anfitrion anfitrion;
    String hostIP;

    public ConnectionReceiver(Anfitrion anfitrion) {
        this.anfitrion = anfitrion;
        anfitrion.setIP(hostIPAddress());
        try {
            ServerSocket incoming = new ServerSocket(anfitrion.getPort());
            System.out.println("listening connections...");
            Socket socket = incoming.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String message = in.readUTF();
            System.out.println("Invitado ha ingresado: "+ message);
            incoming.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String hostIPAddress() {
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url_name.openStream()));
            hostIP = reader.readLine().trim();
            if (!(hostIP.length() > 0))
            {
                try
                {
                    InetAddress localhost = InetAddress.getLocalHost();
                    System.out.println((localhost.getHostAddress()).trim());
                    hostIP = (localhost.getHostAddress()).trim();
                }
                catch(Exception e1)
                {
                    hostIP = "ip no encontrada";
                }
            }
        }
        catch (Exception e2)
        {
            hostIP = "ip no encontrada";
        }
        return hostIP;
    }
}