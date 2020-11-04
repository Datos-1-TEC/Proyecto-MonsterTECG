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

public class Jugador {

    private String Rol;
    private int mana = 200;
    private int vida = 1000;
    private boolean onGame = false;
    private Deck<Carta> miDeck = new Deck<>();
    private ManoCartas manoCartas = new ManoCartas();
    public ManoCartas getManoCartas() {
        return manoCartas;
    }

    public void setManoCartas(ManoCartas manoCartas) {
        this.manoCartas = manoCartas;
    }


    public String getRol() {return Rol; }

    public void setRol(String rol) { Rol = rol; }

    public int getMana() { return mana; }

    public void setMana(int mana) { this.mana = mana; }

    public int getVida() { return vida; }

    public void setVida(int vida) { this.vida = vida; }

    public Deck getMiDeck() { return miDeck; }

    public void setMiDeck(Deck miDeck) {this.miDeck = miDeck;}

    public String drawCard(int position) throws IOException {
        Carta drawed = getManoCartas().getCartaListaCircular().getElementAt(position);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode cartaNode = Json.toJson(drawed);
        objectMapper.writeValue(new File("src/carta.json"),drawed);
        String card = Json.generateString(cartaNode, true);
        return card;
    }

}

