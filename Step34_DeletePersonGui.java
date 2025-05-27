import java.awt.*;
import java.util.*;
import java.io.*;

import javax.swing.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step34_DeletePersonGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("削除アプリ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        String[] columns = {"名前", "点数"};
        JTable table = new JTable(new String[0][2], columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton deleteButton = new JButton("削除");

        JPanel bottomJPanel = new JPanel();
        bottomJPanel.add(deleteButton);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(bottomJPanel, BorderLayout.SOUTH);
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

        String[][] data = new String[people.size()][2];
        for (int i = 0; i < people.size(); i++) {
            data[i][0] = people.get(i).getName();
            data[i][1] = String.valueOf(people.get(i).getScore());
        }
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "削除する人を選択してください!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(frame, "本当に削除しますか？", "確認", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            people.remove(selectedRow);

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("people.json"));
                String json = new GsonBuilder().setPrettyPrinting().create().toJson(people);
                writer.write(json);
                writer.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "保存エラー" + ex.getMessage());
            }

            String[][] newData = new String[people.size()][2];
            for (int i = 0; i < people.size(); i++) {
                newData[i][0] = people.get(i).getName();
                newData[i][1] = String.valueOf(people.get(i).getScore());
            }
            table.setModel(new javax.swing.table.DefaultTableModel(newData, columns));
        });
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

    public void setScore(int score) {
        this.score = score;
    }
}