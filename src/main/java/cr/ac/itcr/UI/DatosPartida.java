package cr.ac.itcr.UI;

import cr.ac.itcr.Cartas.AgregarDeck;

import cr.ac.itcr.Cartas.Stack.ManoCartas;

import cr.ac.itcr.Jugador.Jugador;

import java.io.IOException;

/**
 * Clase que inicializa la mano y el deck para cada jugador de manera aleatoria
 */
public class DatosPartida {
    private int ronda = 0;
    private Jugador jugador = new Jugador();
    private Jugador oponente = new Jugador();

    /**
     * Metodo constructor que inicializa los participantes de la partida
     * @param jugador
     * @param oponente
     */
    public DatosPartida(Jugador jugador, Jugador oponente) {
        this.jugador = jugador;
        this.oponente = oponente;
    }

    /**
     * Metodo que carga las cartas del jugador
     * @return string con los nombres de cada carta
     * @throws IOException
     */
    public String cartasPropias() throws IOException {
        return cartasJugador(this.jugador, "");
    }

    /**
     * carga cartas del oponente
     * @param cartasNombre Nombre de las cartas cargadas
     * @throws IOException
     */
    public void cartasOponente(String cartasNombre) throws IOException {
        cartasJugador(this.oponente, cartasNombre);
    }

    /**
     * Metodo que asigna la mano y el deck al jugador
     * @param jugador quien recibe un nuevo deck y mano de cartas
     * @param cartasNombre string con el nombre de las cartas
     * @return
     * @throws IOException
     */
    public String cartasJugador(Jugador jugador, String cartasNombre) throws IOException {
        AgregarDeck nuevoDeck =  new AgregarDeck();
        if(cartasNombre == ""){
            ManoCartas nuevaMano = new ManoCartas();
            nuevaMano.agregarCartas();
            jugador.setManoCartas(nuevaMano);
            jugador.setMiDeck(nuevoDeck.generateDeck());
            return nuevoDeck.cartasNombre;
        }
        else {
            jugador.setMiDeck(nuevoDeck.generateDeck(cartasNombre));
            return "";
        }

    }

}
