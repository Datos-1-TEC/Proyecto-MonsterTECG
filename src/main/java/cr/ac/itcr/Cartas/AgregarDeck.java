package cr.ac.itcr.Cartas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import cr.ac.itcr.Cartas.Stack.Deck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ThreadLocalRandom;

public class AgregarDeck {

    private Deck<Carta> miDeck = new Deck<>();
    public ThreadLocalRandom random = ThreadLocalRandom.current();

    public AgregarDeck() {
    }

    public Deck<Carta> generateDeck() {
        try {
            Json cardsreader = new Json();
            String json = new String();

            //PARSE JSON TO STRING
            JsonNode node = Json.parse(cardsreader.jsonReader(json));
            AddtoDeck(6, 17,"EsbirrosCartas", node);
            AddtoDeck(5, 13,"HechizosCartas", node);
            AddtoDeck(5, 10,"SecretosCartas", node);
            System.out.println("Deck jugador: ");


        } catch(Exception e) {
            e.printStackTrace();
        }
        return miDeck;
    }

    public void AddtoDeck(int max, int indiceCarta, String tipoCarta, JsonNode node) throws JsonProcessingException {
        int contador = 0;

        while (contador < max) {
            int indice = random.nextInt(1, indiceCarta+1);
            String contadorString = String.valueOf(indice);
            String nombre = tipoCarta + contadorString;
            Carta carta = Json.fromJson(node.get("cartas").get(nombre), Carta.class);
            miDeck.push(carta);
            System.out.println("agregando carta: " + contador);
            contador++;
        }
    }


}
