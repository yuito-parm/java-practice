import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step35_SortTableGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("並び替えアプリ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);

        String[] columns = {"名前", "点数"};
        JTable table = new JTable(new String[0][2], columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton sortByName = new JButton("名前で並び替え");
        JButton sortByScore = new JButton("点数で並び替え");

        JPanel topPanel = new JPanel();
        topPanel.add(sortByName);
        topPanel.add(sortByScore);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
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

        sortByName.addActionListener(e -> {
            people.sort((a, b) -> a.getName().compareTo(b.getName()));
            refreshTable.run();
        });

        sortByScore.addActionListener(e -> {
            people.sort((a, b) -> 
                b.getScore() - a.getScore()
            );
            refreshTable.run();
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