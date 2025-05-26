import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step31_ShowListGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("登録された人たちの一覧");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        List<Person> people = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("people.json"));
            people = new Gson().fromJson(reader, new TypeToken<List<Person>>(){}.getType());
            reader.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "読み込みエラー" + e.getMessage());
        }

        String[] columns = {"名前", "点数"};
        String[][] data = new String[people.size()][2];

        for (int i = 0; i < people.size(); i++) {
            data[i][0] = people.get(i).getName();
            data[i][1] = String.valueOf(people.get(i).getScore());
        }

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().add(scrollPane);
        frame.setVisible(true);
    }
}

class Person {
    private String name;
    private int score;

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
}
