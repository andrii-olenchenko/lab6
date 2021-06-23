package edu.knu.olenchenko;
/*
ЗАДАНИЕ: Розробити віконний додаток, в якому буде відображатися інформація з бази даних. Крім відображення інформації з БД, він повинен дозволяти додавати та видаляти записи. 
Дані представити у вигляді таблиць (використовувати компонент JTable). 
Складність БД - необмежена. Мінімум - вона повинна містити хоча б одну таблицю. Поштове відділення
*/

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Lab9 extends JFrame {
    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;
    private UserData userData;

    public Lab9() {
        initConnection();
        buildUI();
        loadDataFromDB();
        this.userData.fireTableDataChanged();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
        setVisible(true);
        setLayout(null);
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Max/Documents/Olenchenko.accdb");
            statement = connection.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildUI() {
        setTitle("    ");
        Button button1 = new Button("Видалити запис");
        button1.setBounds(200, 160, 120, 30);
        add(button1);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { DeleteC(); }
        });

        Button button2 = new Button("Додати запис");
        button2.setBounds(50, 160, 120, 30);
        add(button2);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { InPutC(); }
        });

        setBounds(100,100,400,255);
        this.userData = new UserData();
        JTable userTable = new JTable(userData);
        userTable.setSize(400, 150);

        getContentPane().add(userTable);
        userTable.add(button1);
        userTable.add(button2);
        userTable.setBackground(new Color(215,228,189));
    }

    private void loadDataFromDB() {
        this.userData.alData.clear();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Olenchenko");
            while (resultSet.next()) {
                String ind = resultSet.getString(1);
                String Nomer = resultSet.getString(2);
                String Misto = resultSet.getString(3);
                String Adresa = resultSet.getString(4);
                RowAddress r = new RowAddress(ind, Nomer, Misto, Adresa);
                this.userData.alData.add(r);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class RowAddress {
        String ind;
        String Nomer;
        String Misto;
        String Adresa;

        public RowAddress(String ind, String Nomer, String Misto, String Adresa) {
            this.ind = ind;
            this.Nomer = Nomer;
            this.Misto = Misto;
            this.Adresa = Adresa;
        }
    }

    class UserData extends AbstractTableModel {
        String columns[] = {"Номер", "Місто", "Адреса"};
        public List alData = new ArrayList();
        public int getColumnCount() {
            return columns.length;
        }

        public Object getValueAt(int nRow, int nCol) {
            if (nRow < 0 || nRow > this.alData.size()) {
                return null;
            }
            RowAddress rowAddress = (RowAddress) this.alData.get(nRow);
            switch (nCol) {
                case 0:
                    return rowAddress.Adresa;

                case 1:
                    return rowAddress.Nomer;

                case 2:
                    return rowAddress.Misto;
            }
            return "";
        }

        public int getRowCount() {
            return alData == null ? 0 : alData.size();
        }
        public String getColumnName(int column) {
            return columns[column];
        }
        public boolean isCellEditable(int nRow, int nCol) {
            return false;
        }
    }

    public void DeleteC() {
        try {
            statement.executeUpdate("delete from Olenchenko where Nomer = '1'");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.userData.fireTableDataChanged();
    }

    public void InPutC() {
        try {
            String Nomer = "67";
            String Misto = "Poltava";
            String Adresa = "Kinceva 13";

            String query1 = "INSERT INTO Olenchenko(Nomer, Misto, Adresa)"
                    + "VALUES ('"+Nomer+"','"+Misto+"','"+Adresa+"')";
            statement = connection.createStatement();
            statement.executeUpdate(query1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public static void main(String[] args) {
		new Lab9();
	}
}
