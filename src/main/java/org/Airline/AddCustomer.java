package org.Airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomer extends JFrame implements ActionListener
{
    JTextField tfAadhar, tfAddress, tfName,tfNationality , tfNumber;
    JRadioButton rbMale, rbFemale;

    public AddCustomer(){
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(220,20,500,35);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        heading.setForeground(Color.black);
        add(heading);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(60,80,150,25);
        lblName.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblName.setForeground(Color.black);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(220,80,150,25);
        add(tfName);

        JLabel lblNationality = new JLabel("Nationality");
        lblNationality.setBounds(60,130,150,25);
        lblNationality.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblNationality.setForeground(Color.black);
        add(lblNationality);

        tfNationality = new JTextField();
        tfNationality.setBounds(220,130,150,25);
        add(tfNationality);

        JLabel lblAadhar = new JLabel("Aadhar Number");
        lblAadhar.setBounds(60,180,150,25);
        lblAadhar.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblAadhar.setForeground(Color.black);
        add(lblAadhar);

        tfAadhar = new JTextField();
        tfAadhar.setBounds(220,180,150,25);
        add(tfAadhar);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(60,230,150,25);
        lblAddress.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblAddress.setForeground(Color.black);
        add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(220,230,150,25);
        add(tfAddress);


        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(60,280,150,25);
        lblGender.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblGender.setForeground(Color.black);
        add(lblGender);

        ButtonGroup genderGroup = new ButtonGroup();

        rbMale = new JRadioButton("Male");
        rbMale.setBounds(220,280,70,25);
        rbMale.setBackground(Color.white);
        add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(300,280,70,25);
        rbFemale.setBackground(Color.white);
        add(rbFemale);

        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        JLabel lblNumber = new JLabel("Contect Number");
        lblNumber.setBounds(60,330,150,25);
        lblNumber.setFont(new Font("Tahoma",Font.PLAIN,16));
        lblNumber.setForeground(Color.black);
        add(lblNumber);

        tfNumber = new JTextField();
        tfNumber.setBounds(220,330,150,25);
        add(tfNumber);

        ImageIcon image = new ImageIcon("C:\\Users\\HP\\IdeaProjects\\Airline_Management_System\\src\\main\\java\\org\\Airline\\icons-20241026T112942Z-001\\icons\\emp.png");
        JLabel lblImage = new JLabel(image);
        lblImage.setBounds(450,80,280,400);
        add(lblImage);

        JButton save = new JButton("SAVE");
        save.setBackground(Color.black);
        save.setForeground(Color.white);
        save.addActionListener(this);
        save.setBounds(220,380,150,30);
        add(save);



        setSize(900,600);
        setLocation(300,150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        String name = tfName.getText();
        String aadhar = tfAadhar.getText();
        String nationality = tfNationality.getText();
        String number = tfNumber.getText();
        String address = tfAddress.getText();
        String gender = null;

        if(rbMale.isSelected()){
            gender = "Male";
        }else {
            gender = "Female";
        }

        try {
           JdbcConnection jdbc = new JdbcConnection();

           String query = "insert into passenger values('"+name+"', '"+aadhar+"' , '"+nationality+"','"+number+"','"+address+"' ,'"+gender+"')";
           jdbc.s.executeUpdate(query);

           JOptionPane.showMessageDialog(null,"Customer Details Added Successfully");
           setVisible(false);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new AddCustomer();
    }
}
