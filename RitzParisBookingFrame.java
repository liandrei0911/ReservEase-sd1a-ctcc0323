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
import java.util.Date;
import java.util.List;

public class RitzParisBookingFrame extends JFrame {
    private Hotel hotel;
    private JComboBox<Integer> roomNumberComboBox;
    private JButton regularButton;
    private JButton deluxeButton;
    private JButton vipButton;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JLabel priceLabel;

    private List<Integer> regularRooms;
    private List<Integer> deluxeRooms;
    private List<Integer> vipRooms;
    private double basePrice = 800.0; // Example base price for Ritz Paris

    public RitzParisBookingFrame(Hotel hotel) {
        this.hotel = hotel;

        regularRooms = List.of(1, 2, 3, 4, 5); // Example regular rooms
        deluxeRooms = List.of(6, 7, 8, 9, 10); // Example deluxe rooms
        vipRooms = List.of(11, 12, 13, 14, 15); // Example VIP rooms

        setTitle("Booking for " + hotel.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel titleLabel = new JLabel("Booking for " + hotel.getName());
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(titleLabel);

        JLabel locationLabel = new JLabel("Location: " + hotel.getLocation());
        panel.add(locationLabel);

        priceLabel = new JLabel("Price per night: $" + "800");
        panel.add(priceLabel);

        JLabel availableRoomsLabel = new JLabel("Available Rooms: 15"); // Example static value
        panel.add(availableRoomsLabel);

        // Room type buttons
        JLabel roomTypeLabel = new JLabel("Select Room Type:");
        panel.add(roomTypeLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        regularButton = new JButton("Regular");
        regularButton.addActionListener(e -> filterRoomNumbers("Regular"));
        buttonPanel.add(regularButton);

        deluxeButton = new JButton("Deluxe");
        deluxeButton.addActionListener(e -> filterRoomNumbers("Deluxe"));
        buttonPanel.add(deluxeButton);

        vipButton = new JButton("   VIP   ");
        vipButton.addActionListener(e -> filterRoomNumbers("VIP"));
        buttonPanel.add(vipButton);

        panel.add(buttonPanel);

        // Room number selection
        JLabel roomNumberLabel = new JLabel("Select Room Number:");
        roomNumberComboBox = new JComboBox<>();
        panel.add(roomNumberLabel);
        panel.add(roomNumberComboBox);

        // Date and time selection
        JLabel startDateLabel = new JLabel("Start Date and Time:");
        startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "MM/dd/yyyy hh:mm a");
        startDateSpinner.setEditor(startDateEditor);

        JLabel endDateLabel = new JLabel("End Date and Time:");
        endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "MM/dd/yyyy hh:mm a");
        endDateSpinner.setEditor(endDateEditor);

        panel.add(startDateLabel);
        panel.add(startDateSpinner);
        panel.add(endDateLabel);
        panel.add(endDateSpinner);

        // Confirm Booking button
        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.setBackground(Color.ORANGE);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Serif", Font.BOLD, 12));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmBooking();
            }
        });
        panel.add(confirmButton);

        add(panel);
        setVisible(true);
    }

    private void filterRoomNumbers(String roomType) {
        roomNumberComboBox.removeAllItems();
        List<Integer> roomNumbers;

        double price;

        switch (roomType) {
            case "Regular":
                roomNumbers = regularRooms;
                price = 800;
                break;
            case "Deluxe":
                roomNumbers = deluxeRooms;
                price = 1300;
                break;
            case "VIP":
                roomNumbers = vipRooms;
                price = 2000;
                break;
            default:
                roomNumbers = List.of();
                price = 800;
        }

        for (Integer roomNumber : roomNumbers) {
            roomNumberComboBox.addItem(roomNumber);
        }

        priceLabel.setText("Price per night: $" + price);
    }

    private void confirmBooking() {
        if (roomNumberComboBox.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a room number.");
            return;
        }

        int roomNumber = (int) roomNumberComboBox.getSelectedItem();
        Date startDate = (Date) startDateSpinner.getValue();
        Date endDate = (Date) endDateSpinner.getValue();

        if (startDate.equals(endDate)) {
            JOptionPane.showMessageDialog(this, "End Date/Time must be after Start Date/Time.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Booking confirmed for " + hotel.getName() +
                "\nRoom Number: " + roomNumber +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate);

        this.dispose();
    }

    public static void main(String[] args) {
        // Example usage
        Hotel ritzParis = new Hotel("Ritz Paris", "Luxury hotel in Paris", "Paris", 800, "C:\\Users\\Liana\\Pictures\\Saved Pictures\\RitzParis.jpg");
        SwingUtilities.invokeLater(() -> new RitzParisBookingFrame(ritzParis));
    }
}