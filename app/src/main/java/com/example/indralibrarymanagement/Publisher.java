package com.example.indralibrarymanagement;

public class Publisher {
    public String name;
    public String address;
    public String phone;


    public Publisher(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {


        return String.format("%-20s %-30s %-13s", name, address, phone);

    }
}
