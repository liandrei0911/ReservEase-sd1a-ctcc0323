/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reserveasegui;

/**
 *
 * @author Liana
 */
public class Hotel {
    private String name;
    private String description;
    private String location;
    private int price;
    private String imagePath;

    public Hotel(String name, String description, String location, int price, String imagePath) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }
    public int getPrice() {
        return price;
        }
    
    public String getImagePath() {
        return imagePath;
    }
}