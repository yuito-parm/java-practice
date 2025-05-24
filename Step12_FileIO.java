import java.io.*;
import java.util.*;

public class Step12_FileIO {
    public static void main(String[] args) throws IOException {
        List<Map<String, Object>> people = new ArrayList<>();

        Map<String, Object> p1 = new HashMap<>();
        p1.put("name", "たかし");
        p1.put("score", 72);
        people.add(p1);

        Map<String, Object> p2 = new HashMap<>();
        p2.put("name", "さくら");
        p2.put("score", 85);
        people.add(p2);
        
        Map<String, Object> p3 = new HashMap<>();
        p3.put("name", "ゆうた");
        p3.put("score", 90);
        people.add(p3);

        BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"));

        for (Map<String,Object> person : people) {
            String name = (String) person.get("name");
            int score = (int) person.get("score");
            writer.write(name + "," + score);
            writer.newLine();
        }

        writer.close();
        System.out.println("ファイルに保存しました！");
    }
}
