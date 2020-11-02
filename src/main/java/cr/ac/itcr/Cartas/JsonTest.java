package cr.ac.itcr.Cartas;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cr.ac.itcr.Cartas.Stack.Deck;
import cr.ac.itcr.Cartas.Stack.ManoCartas;
import cr.ac.itcr.Jugador.Invitado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class JsonTest {

    public static Deck miDeck = new Deck();

    public static void main(String args[]) {
        try{
            String json = new String();
            Json cardsReader = new Json();
            //PARSE JSON TO STRING
            JsonNode node = Json.parse(cardsReader.jsonReader(json));
            //System.out.println(node.get("cartas").get(0));
            //System.out.println(node.get("cartas").get("Action").asText());
/*            //Object instantiation
            ObjectMapper objectMapper = new ObjectMapper();
            HechizosCartas nuevaCarta = new HechizosCartas();
            nuevaCarta.setName("Congelar");
            nuevaCarta.setCosteMana(50);
            nuevaCarta.setAction("Pasa 3 turnos");


            //Object to Json
            JsonNode cartaNode = Json.toJson(nuevaCarta);
            System.out.println(Json.generateString(cartaNode, true));
            objectMapper.writeValue(new File("src/carta.json"),nuevaCarta);
*/
            Invitado invitado = new Invitado("192.168.8.100", 9090);
            AgregarDeck nuevoDeck =  new AgregarDeck();
            ManoCartas nuevaMano = new ManoCartas();
            nuevaMano.agregarCartas();
            invitado.setMiDeck(nuevoDeck.generateDeck());
            invitado.setManoCartas(nuevaMano);


        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
