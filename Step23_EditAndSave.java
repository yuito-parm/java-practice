import java.util.*;
import java.io.*;

public class Step23_EditAndSave {
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

        for (Person p : people) {
            if (p.getName().equals("さくら")) {
                p.setScore(95);
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("people.txt"));
        for (Person p : people) {
            writer.write(p.getName() + "," + p.getScore());
            writer.newLine();
        }
        writer.close();

        System.out.println("★編集後の内容（保存済み）");
        for (Person p : people) {
            System.out.println(p.getName() + "さん: " + p.getScore() + "点");
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
