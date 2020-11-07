package cr.ac.itcr.Jugador;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cr.ac.itcr.Cartas.AgregarDeck;
import cr.ac.itcr.Cartas.Carta;
import cr.ac.itcr.Cartas.Json;
import cr.ac.itcr.Cartas.Stack.Deck;
import cr.ac.itcr.Cartas.Stack.ManoCartas;

import java.io.File;
import java.io.IOException;

/**
 * Clase que contiene las caracteristicas de un jugador en la partida
 */
public class Jugador {

    private int mana = 200;
    private int vida = 1000;
    public boolean onGame = false;
    private Deck<Carta> miDeck = new Deck<>();
    private ManoCartas manoCartas = new ManoCartas();
    public ManoCartas getManoCartas() {
        return manoCartas;
    }

    public void setManoCartas(ManoCartas manoCartas) {
        this.manoCartas = manoCartas;
    }


    public int getMana() { return mana; }

    public void setMana(int mana) { this.mana = mana; }

    public int getVida() { return vida; }

    public void setVida(int vida) { this.vida = vida; }

    public Deck getMiDeck() { return miDeck; }

    public void setMiDeck(Deck miDeck) {this.miDeck = miDeck;}

    /**
     * metodo para indicar la carta que se está tirando y que forma parte de la lista circular doblemente enlazada
     * @param position
     * @return
     * @throws IOException
     */
    public String drawCard(int position) throws IOException {
        Carta drawed = getManoCartas().getCartaListaCircular().getElementAt(position);
        JsonNode cartaNode = Json.toJson(drawed);
        ObjectMapper objectMapper = new ObjectMapper();
        String card = Json.generateString(cartaNode, true);
        return card;
    }

}

