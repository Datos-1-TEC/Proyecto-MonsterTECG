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

/**
 * Ventana de juego donde se cargan las cartas en la mano del jugador, el estado del jugador y le permite tirar cartas
 * y ver las que le van tirando
 */
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
    JButton cartaRecibida = new JButton("carta Recibida");
    JTextArea estadoJugador = new JTextArea("Info Jugador");
    ConnectionReceiver receiver ;
    ConnectionRequest request;
    JButton agregarCarta = new JButton("Add Card");
    ListaDoble<JButton> listButtonCards = new ListaDoble<>();
    Carta receivedCard = new Carta();

    /**
     *Metodo constructor donde se agregan los contenidos de la ventana
     * @param jugador quien se encuentra en la partida
     * @param receiver socketServer que escucha la conexion con el oponente
     * @param request  conexion con el anfitrion mediante el puerto ingresado
     * @throws HeadlessException
     */
    public gameWindow(Jugador jugador, ConnectionReceiver receiver, ConnectionRequest request) throws HeadlessException {
        this.jugador = jugador;
        this.receiver = receiver;
        this.request = request;
        cartasMano();
        addCardsToButtons();
        addCardFromDeck();
        addInfoJugador("Mi estado: " +"\n" +
                "Vida: " + jugador.getVida() + "\n" +
                "Maná: " + jugador.getMana() +"\n" +
                "En Deck: " + jugador.getMiDeck().getSize());

        setTitle("MonsterTEC");
        centerFrame(this, 950, 700);
        //setBounds(100,100,950,700);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        setBackground(Color.darkGray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Metodo que agrega los botones donde estan las cartas y sus respectivos action listener
     */
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


        cartaTirada.setBounds(420,310, 100, 130);
        cartaTirada.setBackground(Color.yellow);

        cartaRecibida.setBounds(300,310, 100, 130);
        cartaRecibida.setBackground(Color.yellow);

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
        add(cartaTirada);
        add(cartaRecibida);

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

    /**
     * Metodo para escalar la imagen en el respectivo boton
     * @param imagePath direccion de la imagen de carta que se desea escalar
     * @param button objeto donde se muestra la imagen escalada
     */
    private void imageInButton(String imagePath, JButton button) {

        ImageIcon imageIcon;
        imageIcon = new ImageIcon(imagePath);
        Image newImage = imageIcon.getImage();
        Image scaled =  newImage.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon paraBoton = new ImageIcon(scaled);
        button.setIcon(paraBoton);
        revalidate();
    }

    /**
     * Método que agrega la carta al botón respectivo segun su posición en la lista de la mano del jugador
     * @param position posicion de la carta en la lista circular doblemente enlazada
     * @param button oobjeto donde se muestra la imagen de la carta encontrada
     */
    private void myPathImage(int position, JButton button) {
        String file = this.jugador.getManoCartas().getCartaListaCircular().getElementAt(position).getImagePath();
        imageInButton(file,button);
    }

    /**
     * Metodo que agrega los botones de la mano de cartas a una lista para actualizar
     * la imagen en cada boton
     */
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

    /**
     * metodo para sobrescribir el estado del jugador cada vez que hay cambios
     * @param info datos actualizados
     */
    public void addInfoJugador(String info){
        estadoJugador.setText(info);
    }

    /**
     * Metodo para centrar la ventana en pantalla
     * @param frame
     * @param width
     * @param height
     */
    public void centerFrame(JFrame frame,int width, int height){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowHeight = screenSize.height;
        int windowWidth = screenSize.width;
        frame.setBounds(windowWidth/2, windowHeight/2, width, height );
        frame.setLocationRelativeTo(null);
    }

    /**
     * Metodo que espera eventos en cada boton para tirar la carta que selecciona
     * Y se manda esa carta al oponente para realizar los respectivos ataques
     * @param e actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().contains("cartaManoI")){
            int cardIndex = getIndex((JButton) e.getSource());
            Carta cartaEnMano = this.jugador.getManoCartas().getCartaListaCircular().getElementAt(cardIndex);
            imageInButton(cartaEnMano.getImagePath(), cartaTirada);
            try {
                String drawedCard = this.jugador.drawCard(cardIndex);
                int CosteMana = cartaEnMano.getCosteMana();
                this.jugador.setMana(this.jugador.getMana() - CosteMana); //Aqui se actualiza el mana del jugador luego de tirar una carta
                addInfoJugador("Mi estado: " +"\n" +
                        "Vida: " + jugador.getVida() + "\n" +
                        "Maná: " + jugador.getMana() +"\n" +
                        "En Deck: " + jugador.getMiDeck().getSize());
                repaint();
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

    /**
     * Metodo para obtener el indice donde se encuentra el boton de interes
     * @param button
     * @return
     */
    public int getIndex(JButton button){
        String textoBtn = button.getText();
        int index = Integer.parseInt(textoBtn.split("I")[1]);
        return index-1;
    }

    /**
     * Metodo para actualizar las cartas en el panel de la mano segun se vayan tirando
     * @param index indice asociado a la posicion de la carta en el boton y la posicion de esa carta en la lista circular doblemente enlazada
     */
    public void cardSHL(int index){
        Carta cartaBorrar = this.jugador.getManoCartas().getCartaListaCircular().getElementAt(index);
        this.jugador.getManoCartas().getCartaListaCircular().deleteNode(cartaBorrar);
        addCardsToButtons();
    }

    /**
     *metodo para mostrar la carta que el oponente tiró
     * @param receivedCard carta que el oponente tiró durante su turno
     */
    public void showReceivedCard(Carta receivedCard){
        this.receivedCard = receivedCard;
        imageInButton(this.receivedCard.getImagePath(), this.cartaRecibida);
        cardsActions();
    }

    /**
     * Metodo para agregar cartas desde el deck a la mano del jugador
     */
    public void addCardFromDeck(){
        this.agregarCarta.setBounds(850-20, 580, 90,30);
        this.agregarCarta.setBackground(Color.yellow);
        add(this.agregarCarta);

        this.agregarCarta.addActionListener(e -> {
            if(jugador.getMiDeck().getSize()>0){
                Carta agregar = ((Carta)jugador.getMiDeck().pop());
                jugador.getManoCartas().getCartaListaCircular().pushFront(agregar);
                addCardsToButtons();
                addInfoJugador("Mi estado: " +"\n" +
                        "Vida: " + jugador.getVida() + "\n" +
                        "Maná: " + jugador.getMana() +"\n" +
                        "En Deck: " + jugador.getMiDeck().getSize());
                repaint();
            }
            else {
                JOptionPane.showMessageDialog(this, "Ya no posee más cartas en el deck");}
        });
    }

    public void cardsActions(){
        if (this.receivedCard.getType().equals("EsbirrosCartas")){
            int vidaActual = jugador.getVida();
            jugador.setVida(vidaActual - 100);
            addInfoJugador("Mi estado: " +"\n" +
                    "Vida: " + jugador.getVida() + "\n" +
                    "Maná: " + jugador.getMana() +"\n" +
                    "En Deck: " + jugador.getMiDeck().getSize());
            repaint();
        }


    }
}

