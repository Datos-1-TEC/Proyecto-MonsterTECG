package cr.ac.itcr.Cartas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import cr.ac.itcr.Cartas.Stack.Deck;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase que se encarga de generar el deck para un determinado jugador
 */
public class AgregarDeck {

    private Deck<Carta> miDeck = new Deck<>();
    private Deck<Carta> suDeck = new Deck<>();
    public ThreadLocalRandom random = ThreadLocalRandom.current();
    public String cartasNombre = "";

    public AgregarDeck() {
    }

    /**
     * Metodo que retorna un deck generado desde el archivo de cartas
     * @return
     */
    public Deck<Carta> generateDeck() {
        try {
            Json cardsreader = new Json();
            String json = new String();

            //PARSE JSON TO STRING
            JsonNode node = Json.parse(cardsreader.jsonReader(json, "cartas.json"));
            System.out.println("Deck jugador: ");
            AddtoDeck(5, 10,"SecretosCartas", node);
            AddtoDeck(6, 17,"EsbirrosCartas", node);
            AddtoDeck(5, 13,"HechizosCartas", node);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return miDeck;
    }

    /**
     * Metodo sobre cargado que genera deck del oponente y de esta forma accesarlo
     * @param cartasNombre
     * @return deck
     */
    public Deck<Carta> generateDeck(String cartasNombre) {
        try {
            Json cardsreader = new Json();
            String json = new String();
            //PARSE JSON TO STRING
            JsonNode node = Json.parse(cardsreader.jsonReader(json, "cartas.json"));
            AddtoDeck(cartasNombre, node);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return suDeck;
    }

    /**
     * Metodo para agregar cartas al deck segun un maximo de elementos por categoria de carta
     * @param max maxima cantidad de cartas de esa categoria a añadir
     * @param indiceCarta indice generado aleatoriamente para un tipo de carta
     * @param tipoCarta tipo de carta a añadir
     * @param node JsonNode para agregar
     * @throws JsonProcessingException
     */
    public void AddtoDeck(int max, int indiceCarta, String tipoCarta, JsonNode node) throws JsonProcessingException {
        int contador = 0;

        while (contador < max) {
            int indice = random.nextInt(1, indiceCarta+1);
            String contadorString = String.valueOf(indice);
            String nombre = tipoCarta + contadorString;
            cartasNombre += nombre + "%";
            Carta carta = Json.fromJson(node.get("cartas").get(nombre), Carta.class);
            miDeck.push(carta);
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
