import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Step28_SaveAsJson {
    public static void main(String[] args) throws IOException {
        List<Person> people = new ArrayList<>();

        people.add(new Person("たかし", 72));
        people.add(new Person("さくら", 85));
        people.add(new Person("ゆうた", 90));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(people);

        BufferedWriter writer = new BufferedWriter(new FileWriter("people.json"));
        writer.write(json);
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