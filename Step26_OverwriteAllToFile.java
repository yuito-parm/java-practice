import java.util.*;
import java.io.*;

public class Step26_OverwriteAllToFile {
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
        System.out.print("点数を変更したい人の名前を入力してください。");
        String targetName = scanner.nextLine();

        boolean found = false;
        for (Person p : people) {
            if (p.getName().equalsIgnoreCase(targetName)) {
                System.out.print("新しい点数を入力してください: ");
                int newScore = scanner.nextInt();
                p.setScore(newScore);
                System.out.println(p.getName() + "さんの点数を" + p.getScore() + "に更新しました!");
                found = true;
            }
        }
        
        String answer = "";
        if (!found) {
            System.out.print("入力された名前の人はいませんでした。新しく作成しますか？。(yes/no)");
            answer = scanner.nextLine();
        }
        if (answer.equalsIgnoreCase("yes")) {
            System.out.print(targetName + "さんの点数を入力してください: ");
            int newPersonScore = scanner.nextInt();
            people.add(new Person(targetName, newPersonScore));
            System.out.println(targetName + "さん: " + newPersonScore + "点 を新しく追加しました!");
        } else {
            System.out.println("終了します。");
        }

        scanner.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter("people.txt"));
        for (Person p : people) {
            writer.write(p.getName() + "," + p.getScore());
            writer.newLine();
        }
        writer.close();

        System.out.println("⬇︎ 更新後のデータ");
        for (Person p : people) {
            System.out.println(p.getName() + "さんの点数: " + p.getScore() + "点");
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