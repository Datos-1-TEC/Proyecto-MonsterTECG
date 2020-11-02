package cr.ac.itcr.UI;

import cr.ac.itcr.Cartas.AgregarDeck;
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
    public void cartasInvitado() throws IOException {
        cartasJugador(this.invitado);
    }
    public void cartasAnfitrion() throws IOException {
        cartasJugador(this.anfitrion);
    }
    public void cartasJugador(Jugador jugador) throws IOException {
        AgregarDeck nuevoDeck =  new AgregarDeck();
        ManoCartas nuevaMano = new ManoCartas();
        nuevaMano.agregarCartas();
        jugador.setMiDeck(nuevoDeck.generateDeck());
        jugador.setManoCartas(nuevaMano);
    }

}
