package org.Airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;


public class CancellationTicket extends JFrame implements ActionListener {
    JTextField tfpnr;
    JLabel tfName, tfCode, cancellationno, lblDateofTravel, lblcancelno;
    JButton fetchButton, flight;


    public CancellationTicket() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        Random random = new Random();

        JLabel heading = new JLabel("Cancellation Ticket");
        heading.setBounds(180, 20, 220, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        JLabel lblCancellation = new JLabel("PNR Number");
        lblCancellation.setBounds(60, 80, 150, 25);
        lblCancellation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblCancellation);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Show Details");
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


        JLabel lblcancelno = new JLabel("Cancellation No");
        lblcancelno.setBounds(60, 180, 150, 25);
        lblcancelno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcancelno);

        cancellationno = new JLabel("" + random.nextInt(1000000));
        cancellationno.setBounds(220, 180, 150, 25);
        add(cancellationno);


        JLabel lblCode = new JLabel("Flight Code");
        lblCode.setBounds(60, 230, 150, 25);
        lblCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblCode);

        tfCode = new JLabel();
        tfCode.setBounds(220, 230, 150, 25);
        add(tfCode);

        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(60, 280, 150, 25);
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblDate);

        lblDateofTravel = new JLabel();
        lblDateofTravel.setBounds(220, 280, 150, 25);
        add(lblDateofTravel);


        flight = new JButton("Cancel");
        flight.setBackground(Color.black);
        flight.setForeground(Color.white);
        flight.addActionListener(this);
        flight.setBounds(220, 330, 120, 25);
        add(flight);


        ImageIcon i1 = new ImageIcon("C:\\Users\\HP\\IdeaProjects\\Airline_Management_System\\src\\main\\java\\org\\Airline\\icons-20241026T112942Z-001\\icons\\cancel.jpg");
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblImage = new JLabel(image);
        lblImage.setBounds(470, 120, 250, 250);
        add(lblImage);


        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == fetchButton) {

            String pnr = tfpnr.getText();


            try {
                JdbcConnection jdbc = new JdbcConnection();

                String query = "select * from reservation where PNR = '" + pnr + "'";
                ResultSet rs = jdbc.s.executeQuery(query);

                if (rs.next()) {

                    tfName.setText(rs.getString("Name"));
                    lblDateofTravel.setText(rs.getString("Date"));
                    tfCode.setText(rs.getString("FlightCode"));

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR number");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String name = tfName.getText();
            String pnr = tfpnr.getText();
            String cancel = cancellationno.getText();
            String flightcode = tfCode.getText();
            String date = lblDateofTravel.getText();

            try {
                JdbcConnection jdbc = new JdbcConnection();

                String query = "insert into cancel values('" + pnr + "' ,'" + name + "','" + cancel + "', '" + date + "' ,'" + flightcode + "')";
                jdbc.s.executeUpdate(query);

                jdbc.s.executeUpdate("delete from reservation where PNR = '" + pnr + "'");

                JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                setVisible(false);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new CancellationTicket();
    }
}
