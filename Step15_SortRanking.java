import java.util.*;

public class Step15_SortRanking {
    public static void main(String[] args) {
        List<Map<String, Object>> people = new ArrayList<>();

        people.add(makePerson("たかし", 72));
        people.add(makePerson("さくら", 85));
        people.add(makePerson("ゆうた", 90));
        people.add(makePerson("けんじ", 60));
        people.add(makePerson("まなみ", 77));

        people.sort((a, b) -> {
            int scoreA = (int) a.get("score");
            int scoreB = (int) b.get("score");
            return scoreB - scoreA;
        });

        System.out.println("成績ランキング");
        int rank = 1;
        for (Map<String,Object> person : people) {
            String name = (String) person.get("name");
            int score = (int) person.get("score");
            System.out.println(rank + "位" + name + "さん(" + score + "点)" );
            rank++;
        }
    }

    public static Map<String, Object> makePerson(String name, int score) {
        Map<String, Object> person = new HashMap<>();
        person.put("name", name);
        person.put("score", score);
        return person;
    }
    
}
