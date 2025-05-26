import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step30_SimpleGuiApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("名前と点数を登録するアプリ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JTextField nameField = new JTextField(15);
        JTextField scoreField = new JTextField(5);
        JButton saveButton = new JButton("登録");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("名前: "));
        panel.add(nameField);
        panel.add(new JLabel("点数: "));
        panel.add(scoreField);
        panel.add(saveButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int score = Integer.parseInt(scoreField.getText());
                try {
                    File file = new File("people.json");
                    List<Person> people;
                    if (file.exists()) {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        people = new Gson().fromJson(reader, new TypeToken<List<Person>>(){}.getType());
                    } else {
                        people = new ArrayList<>();
                    }
                    people.add(new Person(name, score));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    String json = new GsonBuilder().setPrettyPrinting().create().toJson(people);
                    writer.write(json);
                    writer.close();
                    JOptionPane.showMessageDialog(frame, "登録しました!");
                    nameField.setText("");
                    scoreField.setText("");
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(frame, "エラー: " + ex.getMessage());
                }
            }
        });
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