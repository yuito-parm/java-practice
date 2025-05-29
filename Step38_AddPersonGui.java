import java.awt.*;
import java.util.*;
import java.io.*;

import javax.swing.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step38_AddPersonGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("新規追加できるGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 450);

        String[] columns = {"名前", "点数"};
        JTable table = new JTable(new String[0][2], columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JTextField nameField = new JTextField(10);
        JTextField scoreField = new JTextField(5);
        JButton addButton = new JButton("追加");

        JPanel panel = new JPanel();
        panel.add(new JLabel("名前"));
        panel.add(nameField);
        panel.add(new JLabel("点数"));
        panel.add(scoreField);
        panel.add(addButton);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);

        final java.util.List<Person> people = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("people.json"));
            java.util.List<Person> loaded = new Gson().fromJson(reader, new TypeToken<java.util.List<Person>>(){}.getType());
            reader.close();
            people.addAll(loaded);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "読み込みエラー" + e.getMessage());
        }

        Runnable refreshTable = () -> {
            String[][] data = new String[people.size()][2];
            for (int i = 0; i < people.size(); i++) {
                data[i][0] = people.get(i).getName();
                data[i][1] = String.valueOf(people.get(i).getScore());
            }
            table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
        };

        refreshTable.run();
        
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String scoreText = scoreField.getText().trim();
            
            if (name.isEmpty() || scoreText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "名前と点数を合わせて入力してください。");
                return;
            }
            
            try {
                int score = Integer.parseInt(scoreText);
                Person newPerson = new Person(name, score);
                people.add(newPerson);
                
                BufferedWriter writer = new BufferedWriter(new FileWriter("people.json"));
                String json = new GsonBuilder().setPrettyPrinting().create().toJson(people);
                writer.write(json);
                writer.close();
                nameField.setText("");
                scoreField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "点数は数字で入力してください。");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "保存エラー" + ex.getMessage());
            }
            refreshTable.run();
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

    public String getName() { return name; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}