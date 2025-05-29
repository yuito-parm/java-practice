import java.awt.*;
import java.io.*;
import java.util.*;

import javax.print.attribute.IntegerSyntax;
import javax.swing.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Step40_AnimalInventoryBasic {
    public static void main(String[] args) {
        JFrame frame = new JFrame("商品情報管理アプリPart1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 530);

        String[] columns = {"名前", "価格", "在庫", "状態"};
        JTable table = new JTable(new String[0][4], columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JTextField nameField = new JTextField(20);
        JTextField valueField = new JTextField(6);
        JTextField stockField = new JTextField(5);
        JTextField statusField = new JTextField(20);
        JButton addButton = new JButton("追加");

        JPanel panel = new JPanel();
        JPanel fieldPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        fieldPanel.add(new JLabel("名前"));
        fieldPanel.add(nameField);
        fieldPanel.add(new JLabel("価格"));
        fieldPanel.add(valueField);
        fieldPanel.add(new JLabel("在庫"));
        fieldPanel.add(stockField);
        fieldPanel.add(new JLabel("状態"));
        fieldPanel.add(statusField);
        buttonPanel.add(addButton);
        panel.add(fieldPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        frame.getContentPane().add(scrollPane, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
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


        Runnable refreshTable = () -> {
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
                Product newProduct = new Product(name, value, stock, status);
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
    }
}


class Product {
    private String name;
    private int value;
    private int stock;
    private String status;

    public Product(String name, int value, int stock, String status) {
        this.name = name;
        this.value = value;
        this.stock = stock;
        this.status = status;
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public int getStock() {
        return stock;
    }
    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}