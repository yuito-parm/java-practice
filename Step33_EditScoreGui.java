import java.awt.BorderLayout;
import java.io.*;
import java.util.*;
import javax.swing.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step33_EditScoreGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("点数編集アプリ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        String[] columns = {"名前", "点数"};
        JTable table = new JTable(new String[0][2], columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JTextField scoreField = new JTextField(5);
        JButton saveButton = new JButton("点数を更新");

        JPanel editPanel = new JPanel();
        editPanel.add(new JLabel("新しい点数"));
        editPanel.add(scoreField);
        editPanel.add(saveButton);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(editPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        final java.util.List<Person> people = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("people.json"));
            List<Person> loaded = new Gson().fromJson(reader, new TypeToken<java.util.List<Person>>(){}.getType());
            reader.close();
            people.addAll(loaded);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "読み込みエラー" + e);
        }

        String[][] data = new String[people.size()][2];
        for (int i = 0; i < people.size(); i++) {
            data[i][0] = people.get(i).getName();
            data[i][1] = String.valueOf(people.get(i).getScore());
        }
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
        saveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "編集する人を表から選んでください!");
                return;
            }
            try {
                int newScore = Integer.parseInt(scoreField.getText());
                people.get(selectedRow).setScore(newScore);

                BufferedWriter writer = new BufferedWriter(new FileWriter("people.json"));
                String json = new GsonBuilder().setPrettyPrinting().create().toJson(people);
                writer.write(json);
                writer.close();

                table.setValueAt(String.valueOf(newScore), selectedRow, 1);
                JOptionPane.showMessageDialog(frame, "点数を更新しました!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "点数は数字で入力してください!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "保存エラー" + ex.getMessage());
            }
        });
    }
    
}

class Person {
    private String name;
    private int score;

    public String getName() { return name; }
    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }
}
