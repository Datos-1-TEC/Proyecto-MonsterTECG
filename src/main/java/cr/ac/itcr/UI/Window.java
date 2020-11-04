package cr.ac.itcr.UI;

import cr.ac.itcr.Jugador.Anfitrion;
import cr.ac.itcr.Jugador.ConnectionReceiver;
import cr.ac.itcr.Jugador.ConnectionRequest;
import cr.ac.itcr.Jugador.Invitado;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Window extends JFrame {
    JButton getIPButton = new JButton("getIP");
    JTextArea areaIP = new JTextArea();
    JTextField rol = new JTextField("Elija su rol:");
    JButton invitado = new JButton("Invitado");
    JButton anfitrion = new JButton("Anfitrion");
    JTextField addIP = new JTextField();
    JTextField addPort = new JTextField();
    JButton loginAnfitrion = new JButton("Registrar");
    Anfitrion anfitrionPartida = new Anfitrion();
    Invitado invitadoPartida;

    public Anfitrion getAnfitrionPartida() {
        return anfitrionPartida;
    }

    public Invitado getInvitadoPartida() {
        return invitadoPartida;
    }



    public Window() throws HeadlessException {
        settingRole();
        setTitle("Anfitrion info");
        setBounds(100,100, 500, 500);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void settingRole() {
        rol.setBounds(100, 20,75, 30);
        rol.setBackground(Color.WHITE);
        rol.setEditable(false);

        invitado.setBounds(30, 100, 90, 30);
        invitado.setBackground(Color.ORANGE);

        anfitrion.setBounds(170, 100, 90, 30);
        anfitrion.setBackground(Color.ORANGE);

        add(rol);
        add(anfitrion);
        add(invitado);
        anfitrion.addActionListener(e -> widgetAnfitrion());
        invitado.addActionListener(e -> {
            this.repaint();
            setAnfitrion();
            this.repaint();
            setLoginInvitado();
        });
    }
    public void widgetAnfitrion(){
        this.repaint();
        getIPButton.setBounds(100, 180, 100, 30);
        getIPButton.setBackground(Color.orange);
        areaIP.setBounds(100, 210, 100, 30);
        areaIP.setBackground(Color.red);
        add(getIPButton);
        add(areaIP);

        getIPButton.addActionListener(e -> {
            InetAddress ip = null;
            try {
                ip = InetAddress.getLocalHost();
            } catch (UnknownHostException unknownHostException) {
                unknownHostException.printStackTrace();
            }
            areaIP.setText(ip.getHostAddress());

        });
        setAnfitrion();
        setLoginAnfitrion();
    }


    public void setAnfitrion(){
        JLabel infoIP = new JLabel("Ingrese IP:");
        infoIP.setBounds(50,250, 70, 20 );
        JLabel infoPort = new JLabel("Ingrese Puerto:");
        infoPort.setBounds(190,250, 90, 20 );

        addIP.setBounds(50, 270,90, 30);
        addPort.setBounds(190, 270, 70, 30);
        add(addIP);
        add(addPort);
        add(infoIP);
        add(infoPort);
    }
    public void setLoginAnfitrion(){
        loginAnfitrion.setBounds(100, 300, 90, 30);
        loginAnfitrion.setBackground(Color.orange);

        loginAnfitrion.addActionListener(e -> {

            try {
                this.anfitrionPartida.setPort(Integer.parseInt(addPort.getText()));
                InetAddress hostIP = InetAddress.getByName(addIP.getText());
                this.anfitrionPartida.setIP(hostIP);
                setVisible(false);

                ConnectionReceiver receiver = new ConnectionReceiver(anfitrionPartida);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("IP de partida: "+ addIP.getText() + " Puerto: " + Integer.parseInt(addPort.getText()));
        });
        add(loginAnfitrion);
    }

    public void setLoginInvitado() {
        loginAnfitrion.setBounds(100, 300, 90, 30);
        loginAnfitrion.setBackground(Color.orange);
        add(loginAnfitrion);
        loginAnfitrion.addActionListener(e -> {
            String serverIP = addIP.getText();
            int serverPort = Integer.parseInt(addPort.getText());
            try {
                this.invitadoPartida = new Invitado(serverIP, serverPort);
                //setVisible(false)
                DatosPartida datosPartida = new DatosPartida(this.anfitrionPartida, this.invitadoPartida);
                String cartasNombre = datosPartida.cartasInvitado();
                ConnectionRequest request = new ConnectionRequest(this.invitadoPartida.getServerIP(), this.invitadoPartida.getServerPort(), datosPartida);
                request.getOut().writeUTF(cartasNombre);
                request.getOut().flush();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("IP de partida: " + addIP.getText() + " Puerto: " + Integer.parseInt(addPort.getText()));
        });


    }

    public static void main (String[] args){
        Window window = new Window();
    }
}
