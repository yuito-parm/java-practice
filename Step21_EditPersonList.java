import java.util.*;

public class Step21_EditPersonList {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("たかし", 72));
        people.add(new Person("Sakura", 85));
        people.add(new Person("ゆうた", 90));

        System.out.println("⬇︎変更前のデータ");
        for (Person p : people) {
            String name = p.getName();
            int score = p.getscore();
            System.out.println(name + "さん: " + score + "点");
        }

        for (Person p : people) {
            if (p.getName().equalsIgnoreCase("sakura")) {
                p.setScore(95);
                p.setName("たけし");
            }
            if (80 >= p.getscore()) {
                int sum = p.getscore() + 10;
                p.setScore(sum);
            }
        }

        System.out.println("⬇︎変更後のデータ");
        for (Person p : people) {
            String name = p.getName();
            int score = p.getscore();
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
    public int getscore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
}