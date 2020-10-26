package cr.ac.itcr.Jugador;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionRequest {

    public ConnectionRequest(String hostAddress, int port) {
        try{
            Socket request = new Socket(hostAddress, port );
            DataOutputStream out = new DataOutputStream(request.getOutputStream());
            out.writeUTF("Connected to host");
            out.flush();
            request.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}