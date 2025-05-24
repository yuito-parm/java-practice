import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Step13_FileRead {
    public static void main(String[] args) throws IOException {
        List<Map<String, Object>> people = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            int score = Integer.parseInt(parts[1]);

            Map<String, Object> person = new HashMap<>();
            person.put("name", name);
            person.put("score", score);
            people.add(person);
        }
        reader.close();

        for (Map<String,Object> person : people) {
            String name = (String) person.get("name");
            int score = (int) person.get("score");
            System.out.println(name + "さんの点数" + score + "点");
        }
    }
}
