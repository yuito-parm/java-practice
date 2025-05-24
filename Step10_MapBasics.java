import java.util.HashMap;
import java.util.Map;

public class Step10_MapBasics {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("たかし", 72);
        scores.put("さくら", 85);
        scores.put("ゆうた", 90);
        scores.put("まなみ", 68);
        scores.put("けんじ", 77);

        int total = 0;

        for (String name : scores.keySet()) {
            int score = scores.get(name);

            if (name.contains("さくら")) {
                System.out.print(name + "さんの点数: " + score + "点");
    
                if (score >= 80) {
                    System.out.print("(よくできました！)");
                }
                System.out.println();
            }

            total += score;
        }

        double average = (double) total / scores.size();
        System.out.println("平均点: " + average + "点");
    }
    
}
