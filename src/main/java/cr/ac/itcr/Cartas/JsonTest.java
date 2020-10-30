package cr.ac.itcr.Cartas;

import com.fasterxml.jackson.databind.JsonNode;
import cr.ac.itcr.Cartas.Stack.Deck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class JsonTest {

    public static CartasFactory cf = new CartasFactory();
    public static Deck miDeck = new Deck();

    public static void main(String args[]) {
        try{
            String json;
            // Read the file
            BufferedReader br = new BufferedReader(new FileReader("cartas.json"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                json = sb.toString();
            } finally {
                br.close();
            }
            //PARSE JSON TO STRING
            JsonNode node = Json.parse(json);
            //System.out.println(node.get("cartas").get(0));
            //System.out.println(node.get("cartas").get("Action").asText());

            Carta hechizoscartas = cf.crearCarta("HechizosCartas");

            Random random = new Random();
            int indice = random.nextInt(14);
            int contadorHechizos = 0;

            while (contadorHechizos < 5) {
                HechizosCartas cartahechizo = Json.fromJson(node.get("cartas").get("HechizosCartas" + String.valueOf(indice)), HechizosCartas.class);
                miDeck.push(cartahechizo);
                contadorHechizos ++;
            }
            Carta cartaPeek = (Carta) miDeck.peek();
            System.out.println(cartaPeek.getName());

            miDeck.pop();

            miDeck.pop();
            System.out.println(cartaPeek.getName());




        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
