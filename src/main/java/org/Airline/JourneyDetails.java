package org.Airline;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JTextField pnr;
    JButton show;

    public JourneyDetails() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setFont(new Font("tahoma", Font.PLAIN, 16));
        lblpnr.setBounds(50, 50, 100, 25);
        add(lblpnr);

        pnr = new JTextField();
        pnr.setBounds(160, 50, 120, 25);
        add(pnr);

        show = new JButton("Show Details");
        show.setBackground(Color.black);
        show.setForeground(Color.white);
        show.setBounds(290, 50, 120, 25);
        show.addActionListener(this);
        add(show);


        // Create a JTable to display the flight data
        table = new JTable();

        // Add the JTable to a JScrollPane
        JScrollPane scrollBar = new JScrollPane(table);
        scrollBar.setBounds(0, 100, 800, 150);
        scrollBar.setBackground(Color.white);
        add(scrollBar);


        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);


        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        // Try to fetch data from the database and set it to the JTable
        try {
            JdbcConnection jdbc = new JdbcConnection();
            ResultSet rs = jdbc.s.executeQuery("select * from reservation where PNR = '" + pnr.getText() + "'");
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}
