import java.awt.*;
import java.util.*;
import java.io.*;

import javax.swing.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step47_StatusHistory {
    public static void main(String[] args) {
        JFrame frame = new JFrame("全削除できるアプリ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 530);

        String[] columns = {"名前", "価格", "在庫", "状態"};
        JTable table = new JTable(new String[0][4], columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JTextField nameField = new JTextField(20);
        JTextField valueField = new JTextField(6);
        JTextField stockField = new JTextField(5);
        JTextField statusField = new JTextField(20);
        JButton deleteButton = new JButton("全削除");
        JButton addButton = new JButton("追加");
        JButton detailButton = new JButton("詳細を見る");
        JButton statusButton = new JButton("状態履歴");
        JButton reloadButton = new JButton("リセット");
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("検索");

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,140));
        JPanel textPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        textPanel.add(new JLabel("名前"));
        textPanel.add(nameField);
        textPanel.add(new JLabel("価格"));
        textPanel.add(valueField);
        textPanel.add(new JLabel("在庫"));
        textPanel.add(stockField);
        textPanel.add(new JLabel("状態"));
        textPanel.add(statusField);
        buttonPanel.add(deleteButton, BorderLayout.WEST);
        buttonPanel.add(addButton, BorderLayout.NORTH);
        buttonPanel.add(detailButton, BorderLayout.CENTER);
        buttonPanel.add(statusButton, BorderLayout.EAST);
        buttonPanel.add(reloadButton, BorderLayout.SOUTH);
        searchPanel.add(new JLabel("キーワード: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);


        frame.getContentPane().add(searchPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);

        final java.util.List<Product> products = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.json"));
            java.util.List<Product> loaded = new Gson().fromJson(reader, new TypeToken<java.util.List<Product>>(){}.getType());
            reader.close();
            products.addAll(loaded);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "読み込みエラー" + e.getMessage());
        }

        Runnable refreshTable = ()-> {
            String[][] data = new String[products.size()][4];
            for (int i = 0; i < products.size(); i++) {
                data[i][0] = products.get(i).getName();
                data[i][1] = String.valueOf(products.get(i).getValue());
                data[i][2] = String.valueOf(products.get(i).getStock());
                data[i][3] = products.get(i).getStatus();
            }
            table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
        };

        refreshTable.run();

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String valueText = valueField.getText().trim();
            String stockText = stockField.getText().trim();
            String status = statusField.getText().trim();

            if (name.isEmpty() || valueText.isEmpty() || stockText.isEmpty() || status.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "すべての項目を入力してください。");
                return;
            }

            try {
                int value = Integer.parseInt(valueText);
                int stock = Integer.parseInt(stockText);
                Product newProduct = new Product(name, value, stock, status, status);
                products.add(newProduct);

                BufferedWriter writer = new BufferedWriter(new FileWriter("products.json"));
                String json = new GsonBuilder().setPrettyPrinting().create().toJson(products);
                writer.write(json);
                writer.close();
                nameField.setText("");
                valueField.setText("");
                stockField.setText("");
                statusField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "価格・在庫は数字で入力してください。");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "保存エラー" + ex.getMessage());
            }
            refreshTable.run();
        });

        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "本当にすべて削除しますか？", "確認", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                products.clear();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("products.json"));
                    String json = new GsonBuilder().setPrettyPrinting().create().toJson(products);
                    writer.write(json);
                    writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "保存エラー" + ex.getMessage());
                }
            }
            refreshTable.run();
        });

        detailButton.addActionListener(ex -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                Product selected = products.get(row);
                String message = selected.getName() + "\n価格: " + selected.getValue() + "\n在庫数: " +selected.getStock() + "\n状態: ";
                String newStatus = JOptionPane.showInputDialog(frame, message, selected.getStatus());
                if (!newStatus.isEmpty()) {
                    selected.setStatus(newStatus);
                    selected.addStatus(newStatus);
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("products.json"));
                        String json = new GsonBuilder().setPrettyPrinting().create().toJson(products);
                        writer.write(json);
                        writer.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(frame, "保存エラー" + e.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "表示する商品を選択してください。");
            }

            refreshTable.run();
        });

        
        reloadButton.addActionListener(e -> {
            products.clear();
            try {
                BufferedReader reader = new BufferedReader(new FileReader("products.json"));
                java.util.List<Product> loaded = new Gson().fromJson(reader, new TypeToken<java.util.List<Product>>(){}.getType());
                reader.close();
                products.addAll(loaded);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "読み込みエラー" + ex.getMessage());
            }
            refreshTable.run();
        });
        
        Runnable search = () -> {
            String keyword = searchField.getText().trim();
            java.util.List<Product> filterd = new ArrayList<>();
            for (Product p : products) {
                if (p.getName().contains(keyword) || String.valueOf(p.getValue()).contains(keyword) || String.valueOf(p.getStock()).contains(keyword) || p.getStatus().contains(keyword))  {
                    filterd.add(p);
                }
            }
            if (filterd.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "そのキーワードを含む商品はありません。");
                return;
            }
            
            String[][] data = new String[filterd.size()][4];
            for (int i = 0; i < filterd.size(); i++) {
                data[i][0] = filterd.get(i).getName();
                data[i][1] = String.valueOf(filterd.get(i).getValue());
                data[i][2] = String.valueOf(filterd.get(i).getStock());
                data[i][3] = filterd.get(i).getStatus();
            }
            
            searchField.setText("");
            table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
        };
        
        searchButton.addActionListener(e -> search.run());
        searchField.addActionListener(e -> search.run());

        
        statusButton.addActionListener(ex -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                Product selected = products.get(row);
                java.util.List<String> history = selected.getAllStatus();
                StringBuilder message = new StringBuilder();
                for (String s : history) {
                    message.append("- ").append(s).append("\n");
                }
                String newStatus = JOptionPane.showInputDialog(frame, message.toString(), "状態履歴", JOptionPane.QUESTION_MESSAGE);
                if (!newStatus.isEmpty()) {
                    selected.setStatus(newStatus);
                    selected.addStatus(newStatus);
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("products.json"));
                        String json = new GsonBuilder().setPrettyPrinting().create().toJson(products);
                        writer.write(json);
                        writer.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(frame, "保存エラー" + e.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "履歴を表示する商品を選択してください。");
            }
        
            refreshTable.run();
        });
    }
}


class Product {
    private String name;
    private int value;
    private int stock;
    private String status;
    private java.util.List<String> statusHistory = new ArrayList<>();

    public Product(String name, int value, int stock, String status, String newStatus) {
        this.name = name;
        this.value = value;
        this.stock = stock;
        this.status = status;
        this.statusHistory.add(newStatus);
    }

    public String  getName() {
        return name;
    }
    public int  getValue() {
        return value;
    }
    public int  getStock() {
        return stock;
    }
    public String  getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addStatus(String newStatus) {
        statusHistory.add(newStatus);
    }
    public String getLatestStatus() {
        if (statusHistory.isEmpty()) return "";
        return statusHistory.get(statusHistory.size() - 1);
    }
    public java.util.List<String> getAllStatus() {
        return statusHistory;
    }
}