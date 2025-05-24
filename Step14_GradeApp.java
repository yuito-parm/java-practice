import java.io.*;
import java.util.*;

public class Step14_GradeApp {
    public static void main(String[] args) throws IOException {
        // データ作成
        List<Map<String, Object>> people = new ArrayList<>();

        people.add(makePerson("たかし", 72));
        people.add(makePerson("さくら", 85));
        people.add(makePerson("ゆうた", 72));

        // 書き出し
        BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"));
        for (Map<String,Object> person : people) {
            writer.write(person.get("name") + "," + person.get("score"));
            writer.newLine();
        }
        writer.close();
        System.out.println("データの保存に成功しました！");

        // 読み込み
        List<Map<String, Object>> loaded = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            int score = Integer.parseInt(parts[1]);
            Map<String, Object> person = makePerson(name, score);
            loaded.add(person);
        }
        reader.close();

        System.out.println("読み込んだデータ");
        for (Map<String,Object> person : loaded) {
            System.out.println(person.get("name") + "さんの点数" + person.get("score") + "点");
        }
    }
    public static Map<String, Object> makePerson(String name, int score) {
        Map<String, Object> person = new HashMap<>();
        person.put("name", name);
        person.put("score", score);
        return person;
    }
}
