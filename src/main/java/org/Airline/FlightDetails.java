package org.Airline;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;


public class FlightDetails extends JFrame {

    public FlightDetails() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        // Create a JTable to display the flight data
        JTable table = new JTable();


        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);

        // Try to fetch data from the database and set it to the JTable
        try {
            JdbcConnection jdbc = new JdbcConnection();
            ResultSet rs = jdbc.s.executeQuery("select * from flight");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 750, 400);
        add(scrollPane);

        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightDetails();
    }
}
