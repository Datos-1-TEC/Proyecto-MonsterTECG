package cr.ac.itcr.Jugador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Esta clase se utiliza como intermediario que es capaz de comunicar al receiver y al request para recibir y enviar mensajes
 */
public class ConnectionHandler {
    Socket socket;
    boolean flag = true;
    DataOutputStream out;
    DataInputStream in;
    ConnectionReceiver receiver;

    /**
     * Constructor que inicia el hilo de ejecución para el correcto flujo de mensajes
     * @param socket
     * @param receiver
     * @throws IOException
     */
    public ConnectionHandler(Socket socket, ConnectionReceiver receiver) throws IOException {
        this.socket = socket;
        this.out = new DataOutputStream(socket.getOutputStream());
        this.in = new DataInputStream(socket.getInputStream());
        this.receiver = receiver;

        Thread thread = new Thread(() -> {
            while(flag) {
                try {
                    String message = in.readUTF();
                    receiver.processMessage(message);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    public void sendMessage(String message)  {

        try{
            out.writeUTF(message);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

