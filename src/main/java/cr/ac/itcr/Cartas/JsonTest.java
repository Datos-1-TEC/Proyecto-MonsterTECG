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
            JsonNode node = Json.parse(json);
            System.out.println(node.get("cartas").get("type").asText());
            System.out.println(node.get("cartas").get("Action").asText());

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
