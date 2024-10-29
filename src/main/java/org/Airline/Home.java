package org.Airline;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Home extends JFrame implements ActionListener {

    JButton bookFlight;

    //default constructor called by anonymous obj
    public Home() {

        setLayout(null);
        ImageIcon i1 = new ImageIcon("C:\\Users\\HP\\IdeaProjects\\Airline_Management_System\\src\\main\\java\\org\\Airline\\icons-20241026T112942Z-001\\icons\\Airo.jpg");
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1600, 800);
        add(image);

        JLabel heading2 = new JLabel("Welcome to Air India ");
        heading2.setBounds(250, 120, 1200, 90);
        heading2.setForeground(Color.white);
        heading2.setFont(new Font("Tahoma", Font.BOLD, 50));
        image.add(heading2);


        JLabel heading3 = new JLabel("Hi, Where would you like to go");
        heading3.setBounds(280, 230, 600, 60);
        heading3.setForeground(new Color(21, 47, 103));
        heading3.setFont(new Font("Tahoma", Font.BOLD, 25));
        image.add(heading3);


        bookFlight = new JButton("Book Flight");
        bookFlight.setBackground(new Color(23, 47, 108));
        bookFlight.setFont(new Font("Tahoma", Font.BOLD, 15));
        bookFlight.setForeground(Color.white);
        bookFlight.addActionListener(this);
        bookFlight.setBounds(340, 420, 200, 35);
        add(bookFlight);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu details = new JMenu("Services");
        details.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuBar.add(details);

        JMenuItem flightDetails = new JMenuItem("Flight Detail");
        flightDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
        flightDetails.addActionListener(this);
        details.add(flightDetails);

        JMenuItem customerDetails = new JMenuItem("Add Customer Detail");
        customerDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
        customerDetails.addActionListener(this);
        details.add(customerDetails);

        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.setFont(new Font("Tahoma", Font.BOLD, 12));
        bookFlight.addActionListener(this);
        details.add(bookFlight);

        JMenuItem journeyDetails = new JMenuItem("Journey Detail");
        journeyDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);


        JMenu ticket = new JMenu("Ticket");
        ticket.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuBar.add(ticket);

        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.setFont(new Font("Tahoma", Font.BOLD, 12));
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);

        JMenuItem ticketCancellation = new JMenuItem("Ticket Cancellation");
        ticketCancellation.setFont(new Font("Tahoma", Font.BOLD, 12));
        ticketCancellation.addActionListener(this);
        ticket.add(ticketCancellation);


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        String text = ae.getActionCommand();

        if (text.equals("Add Customer Detail")) {
            new AddCustomer();
        } else if (text.equals("Flight Detail")) {
            new FlightDetails();
        } else if (text.equals("Book Flight")) {
            new BookFlight();
        } else if (text.equals("Journey Detail")) {
            new JourneyDetails();
        } else if (text.equals("Ticket Cancellation")) {
            new CancellationTicket();
        } else if (text.equals("Boarding Pass")) {
            new BoardingPass();
        }


    }


    public static void main(String[] args) {

        //Anonyous object
        new Home();

    }
}