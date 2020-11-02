package cr.ac.itcr.Cartas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Json {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        return defaultObjectMapper;
    }

    public static JsonNode parse(String jsonSource) throws JsonProcessingException {
        return objectMapper.readTree(jsonSource);
    }
    public static JsonNode toJson (Object o){ return objectMapper.valueToTree(o);}
    public static <A> A fromJson( JsonNode node, Class<A> aClass) throws JsonProcessingException {
        return objectMapper.treeToValue(node, aClass);
    }
    public static String generateString (JsonNode node, boolean pretty) throws JsonProcessingException{
        ObjectWriter objectWriter = objectMapper.writer();
        if(pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(node);
    }
    public String jsonReader (String json) throws IOException {
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
        return json;
    }

}
