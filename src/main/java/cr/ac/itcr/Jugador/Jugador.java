package cr.ac.itcr.Jugador;

import cr.ac.itcr.Cartas.AgregarDeck;
import cr.ac.itcr.Cartas.Carta;
import cr.ac.itcr.Cartas.Stack.Deck;
import cr.ac.itcr.Cartas.Stack.ManoCartas;

public class Jugador {

    private String Rol;
    public int mana = 200;
    public int vida = 1000;
    public Deck<Carta> miDeck = new Deck<>();

    public ManoCartas getManoCartas() {
        return manoCartas;
    }

    public void setManoCartas(ManoCartas manoCartas) {
        this.manoCartas = manoCartas;
    }

    public ManoCartas manoCartas = new ManoCartas();
    public String getRol() {return Rol; }

    public void setRol(String rol) { Rol = rol; }

    public int getMana() { return mana; }

    public void setMana(int mana) { this.mana = mana; }

    public int getVida() { return vida; }

    public void setVida(int vida) { this.vida = vida; }

    public Deck getMiDeck() { return miDeck; }

    public void setMiDeck(Deck miDeck) {

        this.miDeck = miDeck;}

}

