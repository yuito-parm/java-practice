import java.util.*;

import javax.swing.*;

public class Step49_ShowHistoryWindow {
    public static void open(java.util.List<Map<String, String>> history) {
        JFrame frame = new JFrame("レビュー履歴");
        frame.setSize(400, 300);

        
        String[] columns = {"時間", "レビュー"};
        String[][] data = new String[history.size()][2];

        for (int i = 0; i < history.size(); i++) {
            data[i][0] = history.get(i).get("time");
            data[i][1] = history.get(i).get("status");
        }

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane);
        frame.setVisible(true);


    }
}
