package org.Airline;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;


public class BoardingPass extends JFrame implements ActionListener {
    JTextField tfpnr;
    JLabel tfName, tfNationality, lbldate, labelFname, labelFcode, tfSource, tfDestination;
    JButton bookFlight, fetchButton, flight;


    public BoardingPass() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("AIR INDIA");
        heading.setBounds(380, 10, 450, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 35));
        add(heading);

        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(360, 50, 300, 30);
        subheading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(subheading);

        JLabel lblAadhar = new JLabel("PNR DETAILS");
        lblAadhar.setBounds(60, 100, 150, 25);
        lblAadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblAadhar);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 100, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Enter");
        fetchButton.setBackground(Color.black);
        fetchButton.setForeground(Color.white);
        fetchButton.setBounds(380, 100, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(60, 140, 150, 25);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblName);

        tfName = new JLabel();
        tfName.setBounds(220, 140, 150, 25);
        add(tfName);


        JLabel lblNationality = new JLabel("Nationality");
        lblNationality.setBounds(60, 180, 150, 25);
        lblNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblNationality);

        tfNationality = new JLabel();
        tfNationality.setBounds(220, 180, 150, 25);
        add(tfNationality);


        JLabel lblSource = new JLabel("Source");
        lblSource.setBounds(60, 220, 150, 25);
        lblSource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblSource);

        tfSource = new JLabel();
        tfSource.setBounds(220, 220, 150, 25);
        add(tfSource);

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(380, 220, 150, 25);
        lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblDestination);

        tfDestination = new JLabel();
        tfDestination.setBounds(540, 220, 150, 25);
        add(tfDestination);


        JLabel labelFlightname = new JLabel("Flight Name");
        labelFlightname.setBounds(60, 260, 150, 25);
        labelFlightname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelFlightname);

        labelFname = new JLabel();
        labelFname.setBounds(220, 260, 150, 25);
        add(labelFname);

        JLabel labelFlightcode = new JLabel("Flight Code");
        labelFlightcode.setBounds(380, 260, 150, 25);
        labelFlightcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelFlightcode);

        labelFcode = new JLabel();
        labelFcode.setBounds(540, 260, 150, 25);
        add(labelFcode);


        JLabel labelDate = new JLabel("date");
        labelDate.setBounds(60, 300, 150, 25);
        labelDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelDate);

        lbldate = new JLabel();
        lbldate.setBounds(220, 300, 150, 25);
        add(lbldate);


        ImageIcon i1 = new ImageIcon("C:\\Users\\HP\\IdeaProjects\\Airline_Management_System\\src\\main\\java\\org\\Airline\\icons-20241026T112942Z-001\\icons\\airindia.png");
        Image i2 = i1.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblImage = new JLabel(image);
        lblImage.setBounds(600, 0, 300, 300);
        add(lblImage);


        setSize(1000, 450);
        setLocation(300, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        String pnr = tfpnr.getText();


        try {
            JdbcConnection jdbc = new JdbcConnection();

            String query = "select * from reservation where PNR = '" + pnr + "'";
            ResultSet rs = jdbc.s.executeQuery(query);

            if (rs.next()) {

                tfName.setText(rs.getString("Name"));
                tfNationality.setText(rs.getString("Nationality"));
                tfSource.setText(rs.getString("Source"));
                tfDestination.setText(rs.getString("Destination"));
                labelFname.setText(rs.getString("FlightName"));
                labelFcode.setText(rs.getString("FlightCode"));
                lbldate.setText(rs.getString("date"));

            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct pnr number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}
