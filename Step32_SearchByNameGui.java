import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step32_SearchByNameGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("名前で検索できる一覧");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextField searchField = new JTextField(10);
        JButton searchbButton = new JButton("検索");

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("名前で検索"));
        topPanel.add(searchField);
        topPanel.add(searchbButton);

        String[] columns = {"名前", "点数"};
        JTable table = new JTable(new String[0][2], columns);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

        searchbButton.addActionListener(e -> {
            String keyword = searchField.getText().trim().toLowerCase();
            java.util.List<Person> people = new ArrayList<>();

            try {
                BufferedReader reader = new BufferedReader(new FileReader("people.json"));
                people = new Gson().fromJson(reader, new TypeToken<java.util.List<Person>>(){}.getType());
                reader.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "読み込みエラー" + ex.getMessage());
            }

            java.util.List<Person> filterd = new ArrayList<>();
            for (Person p : people) {
                if (p.getName().contains(keyword)) {
                    filterd.add(p);
                }
            }
            if (filterd.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "その名前の人はいませんでした。");
            }

            String[][] data = new String[filterd.size()][2];
            for (int i = 0; i < filterd.size(); i++) {
                data[i][0] = filterd.get(i).getName();
                data[i][1] = String.valueOf(filterd.get(i).getScore());
            }

            table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
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
}
