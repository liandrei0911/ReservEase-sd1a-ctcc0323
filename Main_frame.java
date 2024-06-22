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
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main_frame extends JFrame {

    private List<Hotel> hotels;
    private JPanel centerPanel;
    private JComboBox<String> sortComboBox;
    private JTextField searchField;

    public Main_frame() {
        super("Booking.com Style Interface");
        
        initializeHotels();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.GRAY);

        // Header Panel for Search and Sort
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> performSearch());
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        headerPanel.add(searchPanel, BorderLayout.WEST);

        // Sort Panel
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        String[] sortOptions = {"Default", "Price (Low to High)", "Price (High to Low)"};
        sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.addActionListener(e -> performSorting());
        sortPanel.add(new JLabel("Sort by:"));
        sortPanel.add(sortComboBox);
        headerPanel.add(sortPanel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center Panel for Hotel Listings
        centerPanel = new JPanel(new GridLayout(0, 1, 20, 20));
        populateHotelListings();
        mainPanel.add(new JScrollPane(centerPanel), BorderLayout.CENTER);
        centerPanel.setBackground(Color.LIGHT_GRAY);

        add(mainPanel);

        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void populateHotelListings() {
        centerPanel.removeAll();

        for (Hotel hotel : hotels) {
            JPanel hotelPanel = createHotelPanel(hotel);
            centerPanel.add(hotelPanel);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

     private JPanel createHotelPanel(Hotel hotel) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

    JPanel leftPanel = new JPanel(new BorderLayout());
    leftPanel.setBorder(new EmptyBorder(20, 10, 10, 10));

    JLabel nameLabel = new JLabel(hotel.getName());
    nameLabel.setFont(new Font("Serif", Font.BOLD, 25));
    leftPanel.add(nameLabel, BorderLayout.NORTH);
    
     JLabel priceLabel = new JLabel("    Starting Price: $" + hotel.getPrice() + " per night");
        priceLabel.setFont(new Font("Serif", Font.BOLD, 20));
        leftPanel.add(priceLabel, BorderLayout.CENTER);

        panel.add(leftPanel, BorderLayout.CENTER);

    // Add image label
    JLabel imageLabel = new JLabel();
    ImageIcon imageIcon = new ImageIcon(hotel.getImagePath());
    imageLabel.setIcon(imageIcon);
    leftPanel.add(imageLabel, BorderLayout.WEST);

    panel.add(leftPanel, BorderLayout.CENTER);

    JButton bookButton = new JButton("Book Now");
    bookButton.setBackground(Color.ORANGE);
    bookButton.setForeground(Color.WHITE);
    bookButton.setFont(new Font("Serif", Font.BOLD, 12));
    bookButton.addActionListener(e -> openBookingFrame(hotel));
    panel.add(bookButton, BorderLayout.SOUTH);

    return panel;
}

    private void openBookingFrame(Hotel hotel) {
        switch (hotel.getName()) {
            case "The Plaza Hotel":
                new PlazaBookingFrame(hotel);
                break;
            case "Burj Al Arab":
                new BurjAlArabBookingFrame(hotel);
                break;
            case "Ritz Paris":
                new RitzParisBookingFrame(hotel);
                break;
            case "Marina Bay Sands":
                new MarinaBaySandsBookingFrame(hotel);
                break;
            default:
                JFrame bookingFrame = new JFrame("Booking for " + hotel.getName());
                bookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel mainPanel = new JPanel(new BorderLayout());
                mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                JLabel titleLabel = new JLabel("Booking for " + hotel.getName());
                titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
                mainPanel.add(titleLabel, BorderLayout.NORTH);

                // Add booking UI components (room type selection, date/time selection, confirm button, etc.)

                bookingFrame.add(mainPanel);
                bookingFrame.pack();
                bookingFrame.setLocationRelativeTo(null);
                bookingFrame.setVisible(true);
                break;
        }
    }

    private void performSearch() {
        String searchTerm = searchField.getText().trim().toLowerCase();

        if (searchTerm.isEmpty()) {
            populateHotelListings();
            return;
        }

        List<Hotel> filteredHotels = hotels.stream()
                .filter(hotel -> hotel.getName().toLowerCase().contains(searchTerm)
                        || hotel.getLocation().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());

        displayFilteredHotels(filteredHotels);
    }

    private void performSorting() {
        String selectedOption = (String) sortComboBox.getSelectedItem();

        switch (selectedOption) {
            case "Price (Low to High)":
                hotels.sort(Comparator.comparingInt(Hotel::getPrice));
                break;
            case "Price (High to Low)":
                hotels.sort((h1, h2) -> Integer.compare(h2.getPrice(), h1.getPrice()));
                break;
            default:
                hotels.sort(Comparator.comparing(Hotel::getName));
                break;
        }

        populateHotelListings();
    }

    private void displayFilteredHotels(List<Hotel> filteredHotels) {
        centerPanel.removeAll();

        for (Hotel hotel : filteredHotels) {
            JPanel hotelPanel = createHotelPanel(hotel);
            centerPanel.add(hotelPanel);
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void initializeHotels() {
    hotels = new ArrayList<>();
    hotels.add(new Hotel("The Plaza Hotel", "Luxury hotel in NYC", "New York", 500, "C:\\Users\\Liana\\Pictures\\Saved Pictures\\ThePlazaHotel.jpg"));
    hotels.add(new Hotel("Burj Al Arab", "Luxury hotel in Dubai", "Dubai", 1500, "C:\\Users\\Liana\\Pictures\\Saved Pictures\\BurjAlArab.jpg"));
    hotels.add(new Hotel("Ritz Paris", "Luxury hotel in Paris", "Paris", 800, "C:\\Users\\Liana\\Pictures\\Saved Pictures\\RitzParis.jpg"));
    hotels.add(new Hotel("Marina Bay Sands", "Luxury hotel in Singapore", "Singapore", 1000, "C:\\Users\\Liana\\Pictures\\Saved Pictures\\MarinaBaySands.jpg"));
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main_frame::new);
    }
}