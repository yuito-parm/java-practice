import java.util.*;

public class Step16_SortByName {
    public static void main(String[] args) {
        List<Map<String, Object>> people = new ArrayList<>();

        people.add(makePerson("たかし", 72));
        people.add(makePerson("さくら", 85));
        people.add(makePerson("ゆうた", 90));
        people.add(makePerson("けんじ", 60));
        people.add(makePerson("まなみ", 77));

        people.sort((a, b) -> {
            String nameA = (String) a.get("name");
            String nameB = (String) b.get("name");
            return nameA.compareTo(nameB);
        });

        System.out.println("名前順ソート結果");
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
