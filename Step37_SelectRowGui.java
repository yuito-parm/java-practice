import java.awt.*;
import java.util.*;
import java.io.*;

import javax.swing.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step37_SelectRowGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("選択表示アプリ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        String[] columns = {"名前", "点数"};
        JTable table = new JTable(new String[0][2], columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JLabel selectJLabel = new JLabel("誰も選ばれていません。");
        selectJLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(selectJLabel, BorderLayout.SOUTH);
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

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0 && row < people.size()) {
                String name = people.get(row).getName();
                selectJLabel.setText(name + "さんが選択されました。");
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
