package cr.ac.itcr.UI;

import cr.ac.itcr.Cartas.AgregarDeck;
import cr.ac.itcr.Cartas.Stack.ManoCartas;
import cr.ac.itcr.Jugador.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Esta clase se encarga de cargar las ventanas para inicializar la partida de los
 * jugadores una vez que ingresan al juego.
 *
 */
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
    ConnectionReceiver receiver;
    ConnectionRequest request;
    Jugador oponente = new Jugador();


    public Window() throws HeadlessException {
        settingRole();
        setTitle("Anfitrion info");
        setBounds(100, 100, 500, 500);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * En este método se selecciona el rol que se desea tener: anfitrion o invitado.
     */
    public void settingRole() {
        rol.setBounds(100, 20, 75, 30);
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

    /**
     * En este método se retorna la IP del anfitrion de la partida para que pueda ingresar.
     */
    public void widgetAnfitrion() {
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

    /**
     * Metodo que despliega los espacios correspondientes para agregar la informacion de la partida.
     */
    public void setAnfitrion() {
        JLabel infoIP = new JLabel("Ingrese IP:");
        infoIP.setBounds(50, 250, 70, 20);
        JLabel infoPort = new JLabel("Ingrese Puerto:");
        infoPort.setBounds(190, 250, 90, 20);

        addIP.setBounds(50, 270, 90, 30);
        addPort.setBounds(190, 270, 70, 30);
        add(addIP);
        add(addPort);
        add(infoIP);
        add(infoPort);
    }

    /**
     * Metodo para hacer valido el log in del anfitrion.
     */
    public void setLoginAnfitrion() {
        loginAnfitrion.setBounds(100, 300, 90, 30);
        loginAnfitrion.setBackground(Color.orange);
        add(loginAnfitrion);
        loginAnfitrion.addActionListener(e -> {

            try {
                DatosPartida datosPartida = new DatosPartida(this.anfitrionPartida, this.oponente);
                String cartasNombre = datosPartida.cartasPropias();
                this.anfitrionPartida.setPort(Integer.parseInt(addPort.getText()));
                InetAddress hostIP = InetAddress.getByName(addIP.getText());
                this.anfitrionPartida.setIP(hostIP);
                this.setVisible(false);
                receiver = new ConnectionReceiver(this.anfitrionPartida, cartasNombre, datosPartida);

                gameWindow gw = new gameWindow(this.anfitrionPartida, receiver, request = null);
                receiver.setGw(gw);


            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("IP de partida: " + addIP.getText() + " Puerto: " + Integer.parseInt(addPort.getText()));
        });

    }

    /**
     * Metodo para hacer valido el log in del invitado.
     */
    public void setLoginInvitado() {
        loginAnfitrion.setBounds(100, 300, 90, 30);
        loginAnfitrion.setBackground(Color.orange);
        add(loginAnfitrion);
        loginAnfitrion.addActionListener(e -> {
            String serverIP = addIP.getText();
            int serverPort = Integer.parseInt(addPort.getText());
            try {
                this.invitadoPartida = new Invitado(serverIP, serverPort);
                setVisible(false);
                DatosPartida datosPartida = new DatosPartida(this.invitadoPartida, this.oponente);
                String cartasNombre = datosPartida.cartasPropias();
                request = new ConnectionRequest(this.invitadoPartida.getServerIP(), this.invitadoPartida.getServerPort(), datosPartida);
                gameWindow gw = new gameWindow(this.invitadoPartida, receiver = null, request);
                request.setGw(gw);

                request.getOut().writeUTF(cartasNombre);
                request.getOut().flush();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("IP de partida: " + addIP.getText() + " Puerto: " + Integer.parseInt(addPort.getText()));
        });

    }
}