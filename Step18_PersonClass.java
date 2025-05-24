import java.util.*;

public class Step18_PersonClass {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(new Person("たかし", 72));
        people.add(new Person("さくら", 85));
        people.add(new Person("ゆうた", 90));
        people.add(new Person("けんじ", 60));
        people.add(new Person("まなみ", 77));

        people.sort((a, b) -> b.getScore() - a.getScore());

        System.out.println("クラスで管理するランキング");
        for (Person p : people) {
            String name = p.getName();
            int score = p.getScore();
            System.out.println(name + "さん: " + score + "点");
        }
    }
}



class Person {
    private String name;
    private int score;

    public Person(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}