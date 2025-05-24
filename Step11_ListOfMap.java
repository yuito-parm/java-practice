import java.util.*;

public class Step11_ListOfMap {
    public static void main(String[] args) {
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

        for (Map<String,Object> person : people) {
            String name = (String) person.get("name");
            int score = (int) person.get("score");

            System.out.print(name + "さんの点数: " + score + "点");
            
            if (score >= 80) {
                System.out.print("よくできました！");
            }
            System.out.println();
        }
    }
    
}
