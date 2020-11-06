package cr.ac.itcr.Cartas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import cr.ac.itcr.Cartas.Stack.Deck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ThreadLocalRandom;

public class AgregarDeck {

    private Deck<Carta> miDeck = new Deck<>();
    private Deck<Carta> suDeck = new Deck<>();
    public ThreadLocalRandom random = ThreadLocalRandom.current();
    public String cartasNombre = "";

    public AgregarDeck() {
    }
    public Deck<Carta> generateDeck() {
        try {
            Json cardsreader = new Json();
            String json = new String();

            //PARSE JSON TO STRING
            JsonNode node = Json.parse(cardsreader.jsonReader(json, "cartas.json"));
            System.out.println("Deck jugador: ");
            AddtoDeck(6, 17,"EsbirrosCartas", node);
            AddtoDeck(5, 13,"HechizosCartas", node);
            AddtoDeck(5, 10,"SecretosCartas", node);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return miDeck;
    }
    public Deck<Carta> generateDeck(String cartasNombre) {
        try {
            Json cardsreader = new Json();
            String json = new String();

            //PARSE JSON TO STRING
            JsonNode node = Json.parse(cardsreader.jsonReader(json, "cartas.json"));
            System.out.println("Deck de oponente: ");
            AddtoDeck(cartasNombre, node);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return suDeck;
    }

    public void AddtoDeck(int max, int indiceCarta, String tipoCarta, JsonNode node) throws JsonProcessingException {
        int contador = 0;

        while (contador < max) {
            int indice = random.nextInt(1, indiceCarta+1);
            String contadorString = String.valueOf(indice);
            String nombre = tipoCarta + contadorString;
            cartasNombre += nombre + "%";
            Carta carta = Json.fromJson(node.get("cartas").get(nombre), Carta.class);
            miDeck.push(carta);
            Carta carta1 = (Carta) miDeck.peek();

            System.out.println("agregando carta: " + contador);
            contador++;
        }
    }
    public void AddtoDeck(String cartasNombre, JsonNode node) throws JsonProcessingException {
        String[] types = cartasNombre.split("%");
        for (String type: types){
            Carta carta = Json.fromJson(node.get("cartas").get(type), Carta.class);
            suDeck.push(carta);
        }
    }


}
