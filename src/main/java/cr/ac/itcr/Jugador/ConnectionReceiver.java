package cr.ac.itcr.Jugador;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Set;

public class ConnectionReceiver {
    private DataOutputStream out;
    private DataInputStream in;
    Anfitrion anfitrion;
    Boolean flag = true;
    private HashMap<String, ConnectionHandler> usuarios = new HashMap<>();

    public ConnectionReceiver(Anfitrion anfitrion) throws IOException {
        this.anfitrion = anfitrion;

        try {
            ServerSocket incoming = new ServerSocket(anfitrion.getPort(), 1, anfitrion.getIP());
            System.out.println("listening connections on: " + anfitrion.getIP() + "," + anfitrion.getPort());
            while (flag){
                Socket socket = incoming.accept();
                processConnection(socket);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processConnection(Socket socket) throws IOException {
        in = new DataInputStream(socket.getInputStream());

        String incomingCard = in.readUTF();
        System.out.println("Invitado ha ingresado: "+ incomingCard);
        ConnectionHandler handler = new ConnectionHandler(socket, incomingCard, this);
        usuarios.put(incomingCard, handler);
        notifyClients();
    }
    public void processMessage (String name, String message) throws IOException {

        System.out.println("Processing message: " + message );
        String[] components = message.split("%");
        ConnectionHandler handler = usuarios.get(components[0]);
        if (handler != null){
            handler.sendMessage(name + "%" + components[1]);
        } else throw new ArrayIndexOutOfBoundsException();

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
    public DataOutputStream getOut() {
        return out;
    }
}