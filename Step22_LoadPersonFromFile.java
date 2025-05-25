import java.util.*;
import java.io.*;

public class Step22_LoadPersonFromFile {
    public static void main(String[] args) throws IOException {
        List<Person> people = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("people.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            int score = Integer.parseInt(parts[1]);
            people.add(new Person(name, score));
        }
        reader.close();

        System.out.println("⬇︎ ファイルから読み込んだ人たち");
        for (Person p : people) {
            String name = p.getName();
            int score = p.getScore();
            System.out.println(name + "さん: " + score + "点");;
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

    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
}