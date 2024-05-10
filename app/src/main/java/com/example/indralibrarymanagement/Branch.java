package com.example.indralibrarymanagement;

public class Branch {
    public String name;
    public String address;
    public String branchID;


    public Branch(String name, String address, String branchID) {
        this.name = name;
        this.address = address;
        this.branchID = branchID;
    }

    @Override
    public String toString() {


        return String.format("%-20s %-30s %-13s", name, address, branchID);

    }
}
