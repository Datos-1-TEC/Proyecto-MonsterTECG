package cr.ac.itcr.UI;

import cr.ac.itcr.Cartas.AgregarDeck;
import cr.ac.itcr.Cartas.Carta;
import cr.ac.itcr.Cartas.Stack.ManoCartas;
import cr.ac.itcr.Jugador.Anfitrion;
import cr.ac.itcr.Jugador.Invitado;
import cr.ac.itcr.Jugador.Jugador;

import java.io.IOException;

public class DatosPartida {
    private int ronda = 0;
    private Jugador jugador = new Jugador();
    private Jugador oponente = new Jugador();

    public DatosPartida(Jugador jugador, Jugador oponente) {
        this.jugador = jugador;
        this.oponente = oponente;
    }
    public String cartasPropias() throws IOException {
        return cartasJugador(this.jugador, "");
    }

    public void cartasOponente(String cartasNombre) throws IOException {
        cartasJugador(this.oponente, cartasNombre);
    }
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
