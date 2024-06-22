/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reserveasegui;

/**
 *
 * @author Liana
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Custom panel class to draw the background image
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String fileName) {
        backgroundImage = new ImageIcon(fileName).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class Sign_up_frame extends JFrame {

    public Sign_up_frame() {
        // Set the title of the JFrame
        super("ReservEase");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the content pane to the background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("C:\\Users\\Liana\\Pictures\\Camera Roll\\SignUp.jpg");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null); // Use null layout for absolute positioning

        // Create a panel for the sign-up form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null); // Use null layout for absolute positioning
        formPanel.setOpaque(false); // Make the panel transparent
        formPanel.setBounds(100, 100, 800, 600); // Set bounds for the form panel
        backgroundPanel.add(formPanel);

        // Define the font for labels
        Font labelFont = new Font("Serif", Font.BOLD, 25);

        // Add form elements
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(200, 150, 200, 30);
        usernameLabel.setFont(labelFont);
        formPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(420, 150, 200, 30);
        formPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 200, 200, 30);
        passwordLabel.setFont(labelFont);
        formPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(420, 200, 200, 30);
        formPanel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(200, 250, 400, 30);
        confirmPasswordLabel.setFont(labelFont);
        formPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(420, 250, 200, 30);
        formPanel.add(confirmPasswordField);

        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(450, 300, 200, 30);
        showPasswordCheckBox.setOpaque(false); // Make the checkbox background transparent
        formPanel.add(showPasswordCheckBox);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 350, 200, 30);
        emailLabel.setFont(labelFont);
        formPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(420, 350, 200, 30);
        formPanel.add(emailField);

        JLabel phoneLabel = new JLabel("Contact no:");
        phoneLabel.setBounds(200, 400, 200, 30);
        phoneLabel.setFont(labelFont);
        formPanel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(420, 400, 200, 30);
        formPanel.add(phoneField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(250, 460, 130, 35);
        signUpButton.setFont(new Font("Serif", Font.BOLD, 25));
        formPanel.add(signUpButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(650, 460, 130, 35);
        exitButton.setFont(new Font("Serif", Font.BOLD, 25));
        formPanel.add(exitButton);
        
        JButton clrButton = new JButton("Clear");
        clrButton.setBounds(450, 460, 130, 35);
        clrButton.setFont(new Font("Serif", Font.BOLD, 25));
        formPanel.add(clrButton);

        // Add ActionListener to the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String email = emailField.getText();
                String phone = phoneField.getText();

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(Sign_up_frame.this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!email.endsWith("@gmail.com")) {
                    JOptionPane.showMessageDialog(Sign_up_frame.this, "Email must be a Gmail address (ending with @gmail.com)!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!phone.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(Sign_up_frame.this, "Phone number must be exactly 11 digits!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Sign-Up Successful
                    JOptionPane.showMessageDialog(Sign_up_frame.this, "Sign-Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Open Main_frame and close Sign_up_frame
                    new Main_frame();
                    dispose();
                }
            }
        });

        // Add ActionListener to the show password checkbox
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                    confirmPasswordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('\u2022'); // default echo character
                    confirmPasswordField.setEchoChar('\u2022'); // default echo character
                }
            }
        });

        // Add ActionListener to the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Add ActionListener to the clear button
        clrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText(null);
                passwordField.setText(null);
                confirmPasswordField.setText(null);
                emailField.setText(null);
                phoneField.setText(null);
                 
            }
        });

        // Set the size of the frame
        setSize(1000, 800); // Adjust these values as needed
        // Center the frame on the screen
        setLocationRelativeTo(null);
        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create and display the sign-up frame
        new Sign_up_frame();
    }
}