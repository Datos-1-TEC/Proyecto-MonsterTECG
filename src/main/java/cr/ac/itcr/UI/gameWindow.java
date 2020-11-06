package cr.ac.itcr.UI;

import cr.ac.itcr.Cartas.Carta;
import cr.ac.itcr.Cartas.Stack.ListaDoble;
import cr.ac.itcr.Jugador.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;

public class gameWindow extends JFrame implements ActionListener {
    private Jugador jugador;
    JButton cartaManoI1 = new JButton("cartaManoI1");
    JButton cartaManoI2 = new JButton("cartaManoI2");
    JButton cartaManoI3 = new JButton("cartaManoI3");
    JButton cartaManoI4 = new JButton("cartaManoI4");
    JButton cartaManoI5 = new JButton("cartaManoI5");
    JButton cartaManoI6 = new JButton("cartaManoI6");
    JButton cartaManoI7 = new JButton("cartaManoI7");
    JButton cartaManoI8 = new JButton("cartaManoI8");
    JButton cartaManoI9 = new JButton("cartaManoI9");
    JButton cartaManoI10 = new JButton("cartaManoI10");
    JButton cartaTirada = new JButton("carta tirada");
    JTextArea estadoJugador = new JTextArea("Info Jugador");
    ConnectionReceiver receiver ;
    ConnectionRequest request;



    JButton agregarCarta = new JButton("Add Card");
    ListaDoble<JButton> listButtonCards = new ListaDoble<>();

    public gameWindow(Jugador jugador, ConnectionReceiver receiver, ConnectionRequest request) throws HeadlessException {
        this.jugador = jugador;
        this.receiver = receiver;
        this.request = request;
        cartasMano();
        addCardsToButtons();
        addInfoJugador("Mi estado" +"\n" + "Vida: " + jugador.getVida() + "\n" + "Maná: " + jugador.getMana() );
        setTitle("MonsterTEC");
        centerFrame(this, 950, 700);
        //setBounds(100,100,950,700);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        setBackground(Color.darkGray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void cartasMano() {


        cartaManoI1.setBounds(30, 530, 70,90);
        cartaManoI1.setBackground(Color.yellow);

        cartaManoI2.setBounds(110, 530, 70,90);
        cartaManoI2.setBackground(Color.yellow);

        cartaManoI3.setBounds(210-20, 530, 70,90);
        cartaManoI3.setBackground(Color.yellow);

        cartaManoI4.setBounds(290-20, 530, 70,90);
        cartaManoI4.setBackground(Color.yellow);

        cartaManoI5.setBounds(370-20, 530, 70,90);
        cartaManoI5.setBackground(Color.yellow);

        cartaManoI6.setBounds(450-20, 530, 70,90);
        cartaManoI6.setBackground(Color.yellow);

        cartaManoI7.setBounds(530-20, 530, 70,90);
        cartaManoI7.setBackground(Color.yellow);

        cartaManoI8.setBounds(610-20, 530, 70,90);
        cartaManoI8.setBackground(Color.yellow);

        cartaManoI9.setBounds(690-20, 530, 70,90);
        cartaManoI9.setBackground(Color.yellow);

        cartaManoI10.setBounds(770-20, 530, 70,90);
        cartaManoI10.setBackground(Color.yellow);

        agregarCarta.setBounds(850-20, 580, 90,30);
        agregarCarta.setBackground(Color.yellow);

        cartaTirada.setBounds(420,310, 100, 130);
        cartaTirada.setBackground(Color.yellow);

        estadoJugador.setBounds(20, 450, 100, 70);
        estadoJugador.setBackground(Color.yellow);
        estadoJugador.setEditable(false);

        add(estadoJugador);
        add(cartaManoI1);
        add(cartaManoI2);
        add(cartaManoI3);
        add(cartaManoI4);
        add(cartaManoI5);
        add(cartaManoI6);
        add(cartaManoI7);
        add(cartaManoI8);
        add(cartaManoI9);
        add(cartaManoI10);
        add(agregarCarta);
        add(cartaTirada);

        cartaManoI1.addActionListener(this::actionPerformed);
        cartaManoI2.addActionListener(this::actionPerformed);
        cartaManoI3.addActionListener(this::actionPerformed);
        cartaManoI4.addActionListener(this::actionPerformed);
        cartaManoI5.addActionListener(this::actionPerformed);
        cartaManoI6.addActionListener(this::actionPerformed);
        cartaManoI7.addActionListener(this::actionPerformed);
        cartaManoI8.addActionListener(this::actionPerformed);
        cartaManoI9.addActionListener(this::actionPerformed);
        cartaManoI10.addActionListener(this::actionPerformed);

    }

    private void imageInButton(String imagePath, JButton button) {

        ImageIcon imageIcon;
        imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage();
        Image scaled =  newImage.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon paraBoton = new ImageIcon(scaled);
        button.setIcon(paraBoton);
        revalidate();
    }

    private void myPathImage(int position, JButton button) {
        String file = this.jugador.getManoCartas().getCartaListaCircular().getElementAt(position).getImagePath();
        imageInButton(file,button);
    }

    public void addCardsToButtons(){
        int cont = 0;
        int lengthLista = this.jugador.getManoCartas().getCartaListaCircular().getLength();
        listButtonCards.ingresarNodo(cartaManoI1);
        listButtonCards.ingresarNodo(cartaManoI2);
        listButtonCards.ingresarNodo(cartaManoI3);
        listButtonCards.ingresarNodo(cartaManoI4);
        listButtonCards.ingresarNodo(cartaManoI5);
        listButtonCards.ingresarNodo(cartaManoI6);
        listButtonCards.ingresarNodo(cartaManoI7);
        listButtonCards.ingresarNodo(cartaManoI8);
        listButtonCards.ingresarNodo(cartaManoI9);
        listButtonCards.ingresarNodo(cartaManoI10);

        while ( 10 > cont){
            JButton button = listButtonCards.getElementAt(cont);
            if (lengthLista > cont){
                myPathImage(cont, button);
            }
            else{
                button.setIcon(null);
            }

            cont ++;
        }
    }

    public void addInfoJugador(String info){
        estadoJugador.setText(info);
    }
    // center the jframe on screen
    public void centerFrame(JFrame frame,int width, int height){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowHeight = screenSize.height;
        int windowWidth = screenSize.width;
        frame.setBounds(windowWidth/2, windowHeight/2, width, height );
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().contains("cartaManoI")){
            int cardIndex = getIndex((JButton) e.getSource());
            Carta cartaEnMano = this.jugador.getManoCartas().getCartaListaCircular().getElementAt(cardIndex);
            imageInButton(cartaEnMano.getImagePath(), cartaTirada);
            try {
                String drawedCard = this.jugador.drawCard(cardIndex);
                if (this.jugador instanceof Invitado){
                    request.sendMessage(drawedCard);
                }
                else{
                    receiver.getHandler().sendMessage(drawedCard);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            cardSHL(cardIndex);
        }
    }

    public int getIndex(JButton button){
        String textoBtn = button.getText();
        int index = Integer.parseInt(textoBtn.split("I")[1]);
        return index-1;
    }

    public void cardSHL(int index){
        Carta cartaBorrar = this.jugador.getManoCartas().getCartaListaCircular().getElementAt(index);
        this.jugador.getManoCartas().getCartaListaCircular().deleteNode(cartaBorrar);
        addCardsToButtons();

    }
}

