import java.util.*;
import java.io.*;

public class Step24_SearchPerson {
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

        Scanner scanner = new Scanner(System.in);
        System.out.print("検索したい名前を入力してください: ");
        String targetName = scanner.nextLine();

        boolean found = false;
        for (Person p : people) {
            if (p.getName().equalsIgnoreCase(targetName)) {
                System.out.println(p.getName() + "さんの点数" + p.getScore() + "点です!");
                found = true;
            }
        }

        if (!found) {
            System.out.println("その名前の人は見つかりませんでした。");
        }
        scanner.close();
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