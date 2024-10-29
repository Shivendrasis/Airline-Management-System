package org.Airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton submit, reset, close;
    JTextField TFusername;
    JPasswordField TFpassword;

    //default constructor called by anonymous obj
    public Login() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        // set text on frame
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(20, 20, 100, 20);
        add(lblusername);

        // set input text box
        TFusername = new JTextField();
        TFusername.setBounds(130, 20, 200, 20);
        add(TFusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(20, 60, 100, 20);
        add(lblpassword);

        TFpassword = new JPasswordField();
        TFpassword.setBounds(130, 60, 200, 20);
        add(TFpassword);

        // Buttons
        reset = new JButton("Reset");
        reset.setBounds(40, 120, 120, 20);
        reset.addActionListener(this);
        add(reset);

        submit = new JButton("Submit");
        submit.setBounds(190, 120, 120, 20);
        submit.addActionListener(this);
        add(submit);

        close = new JButton("Close");
        close.setBounds(120, 160, 120, 20);
        close.addActionListener(this);
        add(close);


        setSize(400, 250);
        setLocation(600, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == submit) {
            String username = TFusername.getText();
            String password = TFpassword.getText();

            try {
                JdbcConnection jdbc = new JdbcConnection();
                String query = "select * from login where username = '" + username + "' and password = '" + password + "' ";
                ResultSet rs = jdbc.s.executeQuery(query);

                if (rs.next()) {
                    new Home();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    setVisible(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == close) {
            setVisible(false);
        } else if (ae.getSource() == reset) {
            TFusername.setText("");
            TFpassword.setText("");
        }
    }

    public static void main(String[] args) {

        //Anonyous object
        new Login();

    }
}