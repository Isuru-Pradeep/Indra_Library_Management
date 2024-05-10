package com.example.indralibrarymanagement;

public class Member {
    public long cardNo;
    public String name;
    public String address;
    public String phone;

    public long unpaidDue;

    public Member(long cardNo, String name, String address, String phone,long unpaidDue) {
        this.cardNo = cardNo;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.unpaidDue = unpaidDue;
    }

    @Override
    public String toString() {
//      return
//                    " " + cardNo +
//                    " " + name + '\'' +
//                    " " + address + '\'' +
//                    " " + phone + '\'';

            return String.format("%-7s %-20s %-30s %-13s %-13s", cardNo, name, address, phone,unpaidDue);

    }
}