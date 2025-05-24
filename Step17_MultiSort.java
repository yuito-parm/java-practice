import java.util.*;

public class Step17_MultiSort {
    public static void main(String[] args) {
        List<Map<String, Object>> people = new ArrayList<>();

        people.add(makePerson("たかし", 85));
        people.add(makePerson("さくら", 90));
        people.add(makePerson("ゆうた", 85));
        people.add(makePerson("けんじ", 60));
        people.add(makePerson("まなみ", 85));

        people.sort((a, b) -> {
            int scoreA = (int) a.get("score");
            int scoreB = (int) b.get("score");
            if (scoreA != scoreB) {
                return scoreB - scoreA;
            } else {
                String nameA = (String) a.get("name");
                String nameB = (String) b.get("name");
                return nameA.compareTo(nameB);
            }
        });


        System.out.println("★複数条件でソート（点数 → 名前）★");
        for (Map<String,Object> person : people) {
            String name = (String) person.get("name");
            int score = (int) person.get("score");
            System.out.println(name + "さん: " + score + "点");
        }
    }

    public static Map<String, Object> makePerson(String name, int score) {
        Map<String, Object> person = new HashMap<>();
        person.put("name", name);
        person.put("score", score);
        return person;
    }
}
