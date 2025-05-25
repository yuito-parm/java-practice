import java.util.*;
import java.io.*;

public class Step25_EditScoreByName {
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
        System.out.print("検索したい人の名前を入れてください: ");
        String targetName = scanner.nextLine();

        boolean found = false;
        for (Person p : people) {
            if (p.getName().equalsIgnoreCase(targetName)) {
                System.out.print("新しい点数を入力してください: ");
                int newScore = scanner.nextInt();
                p.setScore(newScore);
                System.out.println(p.getName() + "さんの点数を" + p.getScore() + "点に更新しました！");
                found = true;
            }
        }
        if (!found) {
            System.out.println("その名前の人は見つかりませんでした。新しく作成します。");
            System.out.print(targetName + "さんの点数を入力してください: ");
            int newPersonScore = scanner.nextInt();
            people.add(new Person(targetName, newPersonScore));
            System.out.println("新しく" + targetName + "さんを追加しました！");

            BufferedWriter writer = new BufferedWriter(new FileWriter("people.txt", true));
            writer.write(targetName + "," + newPersonScore);
            writer.newLine();
            writer.close();
        }

        System.out.println("⬇︎ 更新後のデータ");
        for (Person p : people) {
            System.out.println(p.getName() + "さんの点数: " + p.getScore() + "点");
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

    public void setScore(int score) {
        this.score = score;
    }

}
