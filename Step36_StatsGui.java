import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step36_StatsGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("統計表示アプリ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);

        String[] columns = {"名前", "点数"};
        JTable table = new JTable(new String[0][2], columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JLabel statsJLabel = new JLabel("平均：--点 最高点：--点 人数：--人");
        statsJLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(statsJLabel, BorderLayout.SOUTH);
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

        Runnable refresh = () -> {
            String[][] data = new String[people.size()][2];
            int sum = 0;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < people.size(); i++) {
                Person p = people.get(i);
                data[i][0] = p.getName();
                data[i][1] = String.valueOf(p.getScore());
                sum += p.getScore();
                if (p.getScore() > max) {
                    max = p.getScore();
                }
            }

            int avg = people.size() > 0 ? sum / people.size() : 0;
            table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
            statsJLabel.setText("平均: " + avg + "点 最高点: " + max + "点 人数: " + people.size() + "人");
        };

        refresh.run();
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
