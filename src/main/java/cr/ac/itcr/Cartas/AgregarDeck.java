package cr.ac.itcr.Cartas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import cr.ac.itcr.Cartas.Stack.Deck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ThreadLocalRandom;

public class AgregarDeck {

    public  CartasFactory cf = new CartasFactory();
    public  Deck miDeck = new Deck();
    public ThreadLocalRandom random = ThreadLocalRandom.current();

    public AgregarDeck() {

        try {
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
            AddtoDeck(6, 17,"EsbirrosCartas", node);
            AddtoDeck(5, 13,"HechizosCartas", node);
            AddtoDeck(5, 10,"EsbirrosCartas", node);


        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void AddtoDeck(int max, int indiceCarta, String tipoCarta, JsonNode node) throws JsonProcessingException {
        int contador = 0;

        while (contador < max) {
            int indice = random.nextInt(1, indiceCarta+1);
            String contadorString = String.valueOf(indice);
            String nombre = tipoCarta + contadorString;
            Carta carta = Json.fromJson(node.get("cartas").get(nombre), Carta.class);
            miDeck.push(carta);
            Carta nuevaCarta = (Carta) miDeck.peek();
            System.out.println(nuevaCarta.getName());
            System.out.println(nuevaCarta.getCosteMana());
            contador++;
        }
    }

}
