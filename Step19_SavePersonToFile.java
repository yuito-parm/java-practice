import java.io.*;
import java.util.*;

public class Step19_SavePersonToFile {
    public static void main(String[] args) throws IOException {
        List<Person> people = new ArrayList<>();
        people.add(new Person("たかし", 72));
        people.add(new Person("さくら", 85));
        people.add(new Person("ゆうた", 90));

        BufferedWriter writer = new BufferedWriter(new FileWriter("people.txt"));
        for (Person person : people) {
            writer.write(person.getName() + "," + person.getScore());
            writer.newLine();
        }
        writer.close();
        System.out.println("people.txtに保存しました!");
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