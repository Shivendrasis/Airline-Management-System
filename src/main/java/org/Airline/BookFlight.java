package org.Airline;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;


public class BookFlight extends JFrame implements ActionListener {
    JTextField tfAadhar;
    JLabel tfName, tfAddress, tfNationality, labelGender, labelFname, labelFcode;
    JButton bookFlight, fetchButton, flight;
    Choice source, destination;
    JDateChooser date;


    public BookFlight() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        JLabel lblAadhar = new JLabel("Aadhar Number");
        lblAadhar.setBounds(60, 80, 150, 25);
        lblAadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblAadhar);

        tfAadhar = new JTextField();
        tfAadhar.setBounds(220, 80, 150, 25);
        add(tfAadhar);

        fetchButton = new JButton("Fetch User");
        fetchButton.setBackground(Color.black);
        fetchButton.setForeground(Color.white);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(60, 130, 150, 25);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblName);

        tfName = new JLabel();
        tfName.setBounds(220, 130, 150, 25);
        add(tfName);


        JLabel lblNationality = new JLabel("Nationality");
        lblNationality.setBounds(60, 180, 150, 25);
        lblNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblNationality);

        tfNationality = new JLabel();
        tfNationality.setBounds(220, 180, 150, 25);
        add(tfNationality);


        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(60, 230, 150, 25);
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblAddress);

        tfAddress = new JLabel();
        tfAddress.setBounds(220, 230, 150, 25);
        add(tfAddress);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        labelGender = new JLabel("Gender");
        labelGender.setBounds(220, 280, 150, 25);
        add(labelGender);

        JLabel lblSource = new JLabel("Source");
        lblSource.setBounds(60, 330, 150, 25);
        lblSource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblSource);

        source = new Choice();
        source.setBounds(220, 330, 120, 25);
        add(source);

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setBounds(60, 380, 150, 25);
        lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblDestination);

        destination = new Choice();
        destination.setBounds(220, 380, 150, 25);
        add(destination);

        try {
            JdbcConnection jdbc = new JdbcConnection();
            String query = "select * from flight";
            ResultSet rs = jdbc.s.executeQuery(query);

            while (rs.next()) {
                source.add(rs.getString("Source"));
                destination.add(rs.getString("Destination"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        flight = new JButton("Fetch Flights");
        flight.setBackground(Color.black);
        flight.setForeground(Color.white);
        flight.addActionListener(this);
        flight.setBounds(380, 380, 120, 25);
        add(flight);

        JLabel labelFlightname = new JLabel("Flight Name");
        labelFlightname.setBounds(60, 430, 150, 25);
        labelFlightname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelFlightname);

        labelFname = new JLabel();
        labelFname.setBounds(220, 430, 150, 25);
        add(labelFname);

        JLabel labelFlightcode = new JLabel("Flight Code");
        labelFlightcode.setBounds(60, 480, 150, 25);
        labelFlightcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelFlightcode);

        labelFcode = new JLabel();
        labelFcode.setBounds(220, 480, 150, 25);
        add(labelFcode);


        JLabel labelDate = new JLabel("Date Of Travel");
        labelDate.setBounds(60, 530, 150, 25);
        labelDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelDate);

        date = new JDateChooser();
        date.setBounds(220, 530, 150, 25);
        add(date);



        ImageIcon i1 = new ImageIcon("C:\\Users\\HP\\IdeaProjects\\Airline_Management_System\\src\\main\\java\\org\\Airline\\icons-20241026T112942Z-001\\icons\\details.jpg");
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblImage = new JLabel(image);
        lblImage.setBounds(550, 80, 500, 410);
        add(lblImage);

        bookFlight = new JButton("Book Flight");
        bookFlight.setBackground(Color.black);
        bookFlight.setForeground(Color.white);
        bookFlight.addActionListener(this);
        bookFlight.setBounds(220, 580, 150, 25);
        add(bookFlight);


        setSize(1100, 700);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == fetchButton) {

            String aadhar = tfAadhar.getText();


            try {
                JdbcConnection jdbc = new JdbcConnection();

                String query = "select * from passenger where aadhar = '" + aadhar + "'";
                ResultSet rs = jdbc.s.executeQuery(query);

                if (rs.next()) {

                    tfName.setText(rs.getString("name"));
                    tfAddress.setText(rs.getString("address"));
                    tfNationality.setText(rs.getString("nationality"));
                    labelGender.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct aadhar number");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();

            try {
                JdbcConnection jdbc = new JdbcConnection();

                String query = "select * from flight where Source = '" + src + "' and Destination = '" + dest + "'";
                ResultSet rs = jdbc.s.executeQuery(query);

                if (rs.next()) {

                    labelFname.setText(rs.getString("Flight_Name"));
                    labelFcode.setText(rs.getString("Flight_Code"));

                } else {
                    JOptionPane.showMessageDialog(null, "There is no flight found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Random random = new Random();
            String pnrNumber = "PNR-" + random.nextInt(1000000);
            String aadhar = tfAadhar.getText();
            String name = tfName.getText();
            String nationality = tfNationality.getText();
            String flightname = labelFname.getText();
            String flightcode = labelFcode.getText();
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            String ddate = ((JTextField) date.getDateEditor().getUiComponent()).getText();

            try {
                JdbcConnection jdbc = new JdbcConnection();

                String query = "insert into reservation values('" + pnrNumber + "' , 'TIC-" + random.nextInt(10000) + "' , '" + aadhar + "' , '" + name + "' , '" + nationality + "' , '" + flightname + "' ,'" + flightcode + "' ,'" + src + "' ,'" + dest + "' , '" + ddate + "')";
                jdbc.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Ticket booked successfully. Your PNR number is " + pnrNumber);
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
