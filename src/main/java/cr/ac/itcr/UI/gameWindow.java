package cr.ac.itcr.UI;

import cr.ac.itcr.Jugador.Anfitrion;
import cr.ac.itcr.Jugador.Invitado;
import cr.ac.itcr.Jugador.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class gameWindow extends JFrame {
    private Jugador jugador;
    JButton cartaManoI1 = new JButton("Carta1");
    JButton cartaManoI2 = new JButton("2");
    JButton cartaManoI3 = new JButton("3");
    JButton cartaManoI4 = new JButton("4");
    JButton agregarCarta = new JButton("Add Card");



    public gameWindow(Jugador jugador) throws HeadlessException {
        this.jugador = jugador;
        cartasMano();
        setTitle("MonsterTEC");
        setBounds(100,100,950,700);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setBackground(Color.darkGray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void cartasMano() {
        cartaManoI1.setBounds(50, 530, 70,90);
        cartaManoI1.setBackground(Color.yellow);

        cartaManoI2.setBounds(130, 530, 70,90);
        cartaManoI2.setBackground(Color.yellow);

        cartaManoI3.setBounds(210, 530, 70,90);
        cartaManoI3.setBackground(Color.yellow);

        cartaManoI4.setBounds(290, 530, 70,90);
        cartaManoI4.setBackground(Color.yellow);

        agregarCarta.setBounds(800, 580, 120,30);
        agregarCarta.setBackground(Color.yellow);


        add(cartaManoI1);
        add(cartaManoI2);
        add(cartaManoI3);
        add(cartaManoI4);
        add(agregarCarta);

        myPathImage();


    }

    private void imageInButton(String imagePath, JButton button) {

        ImageIcon imageIcon;
        imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage();
        Image scaled =  newImage.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon paraBoton = new ImageIcon(scaled);
        button.setIcon(paraBoton);

        validate();
    }

    private void myPathImage() {
        String file = this.jugador.getManoCartas().getCartaListaCircular().getElementAt(1).getImagePath();
        imageInButton(file,cartaManoI1);
    }


    public static void main (String[] args) {
        gameWindow game = new gameWindow(new Jugador());
    }

}
