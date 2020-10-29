package cr.ac.itcr.Cartas;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.FileReader;

public class JsonTest {
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

            HechizosCartas cartahechizo = Json.fromJson(node.get("cartas").get("HechizosCartas2"), HechizosCartas.class);

            System.out.println(cartahechizo.getName());
            System.out.println(cartahechizo.getAction());
            System.out.println(cartahechizo.getCosteMana());
            cartahechizo.EjecutarAccion();


        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
