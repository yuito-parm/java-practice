import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Step29_LoadFromJson {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("people.json"));
        
        Gson gson = new Gson();
        List<Person> people = gson.fromJson(reader, new TypeToken<List<Person>>(){}.getType());

        reader.close();

        System.out.println("⬇︎ people.jsonから読み込んだデータ");
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