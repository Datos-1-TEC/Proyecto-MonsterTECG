package cr.ac.itcr.Cartas.Stack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import cr.ac.itcr.Cartas.Carta;
import cr.ac.itcr.Cartas.Json;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase donde se definen las primeras 4 cartas del jugador en una lista
 * circular doblemente enlazada para hacer uso en cada turno
 */
public class ManoCartas {
    private int min = 4;
    private int maxi = 10;
    public ThreadLocalRandom random = ThreadLocalRandom.current();
    public ListaCircular<Carta> getCartaListaCircular() {
        return cartaListaCircular;
    }
    private ListaCircular<Carta> cartaListaCircular = new ListaCircular<>();

    public ManoCartas() {

    }

    /**
     * Metodo que agrega las cartas desde el archivo json a una lista
     * @return la lista de cartas generada
     * @throws IOException
     */
    public ListaCircular<Carta> agregarCartas() throws IOException {
        String json = new String();
        Json cardsReader = new Json();
        JsonNode node = Json.parse(cardsReader.jsonReader(json, "cartas.json"));
        selectFromJson(2, 17, "EsbirrosCartas", node);
        selectFromJson(1, 10, "SecretosCartas", node);
        selectFromJson(1, 13, "HechizosCartas", node);

        return cartaListaCircular;
    }

    /**
     *Metodo para selecionar cartas desde el archivo de JSON
     * @param cantidad cuantas cartas del tipo se agregan
     * @param indiceCarta indice de la carta a agregar
     * @param tipoCarta tipo de carta a agregar
     * @param node nodo que da acceso al archivo JSON
     * @throws JsonProcessingException
     */
    public void selectFromJson(int cantidad, int indiceCarta, String tipoCarta, JsonNode node) throws JsonProcessingException {
        int contador = 0;
        while (contador < cantidad) {
            int indice = random.nextInt(1, indiceCarta+1);
            String contadorString = String.valueOf(indice);
            String nombre = tipoCarta + contadorString;
            Carta carta = Json.fromJson(node.get("cartas").get(nombre), Carta.class);
            cartaListaCircular.pushFront(carta);
            contador++;
        }


    }
}
