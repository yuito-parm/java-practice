import java.io.*;
import java.util.*;

public class Step27_SortPersonList {
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

        people.sort((a, b) -> {
            if (a.getScore() != b.getScore()) {
                return b.getScore() - a.getScore();
            } else {
                return a.getName().compareTo(b.getName());
            }
        });
        System.out.println("⬇︎ 点数の高い順ランキング(同点なら名前の昇順)");
        for (Person p : people) {
            System.out.println(p.getName() + "さん: " + p.getScore() + "点");
        }
        
        people.sort((a, b) -> a.getName().compareTo(b.getName()));
        System.out.println("\n ⬇︎ 名前の昇順");
        for (Person p : people) {
            System.out.println(p.getName() + "さん: " + p.getScore() + "点");
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("ranking.txt"));
        for (Person p : people) {
            writer.write(p.getName() + "," + p.getScore());
            writer.newLine();
        }
        writer.close();

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
