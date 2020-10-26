package cr.ac.itcr.UI.Jugador;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    JButton getIPButton = new JButton("my IP address");
    JTextArea areaIP = new JTextArea();
    JTextField textoChat = new JTextField();
    JTextField nombre = new JTextField("Nombre:");
    JTextField miNombre = new JTextField();
    JButton Login = new JButton("Login");

    public Window() throws HeadlessException {
        getIPButton.setLocation(10,10);
        add(getIPButton);
        setTitle("Anfitrion info");
        setBounds(100,100, 390, 400);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
