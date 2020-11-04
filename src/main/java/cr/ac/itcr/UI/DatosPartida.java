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
    private Anfitrion anfitrion;
    private Invitado invitado;


    public DatosPartida(Anfitrion anfitrion, Invitado invitado) {
        this.anfitrion = anfitrion;
        this.invitado = invitado;
    }
    public String cartasInvitado() throws IOException {
        return cartasJugador(this.invitado, "");
    }

    public void cartasAnfitrion(String cartasNombre) throws IOException {
        cartasJugador(this.anfitrion, cartasNombre);
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
