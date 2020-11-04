package cr.ac.itcr.UI;

import cr.ac.itcr.Jugador.Anfitrion;
import cr.ac.itcr.Jugador.Invitado;
import cr.ac.itcr.Jugador.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameWindow extends JFrame {
    private Jugador jugador;
    private JButton button1;


    public gameWindow(Jugador jugador) throws HeadlessException {
        this.jugador = jugador;

    }

}
