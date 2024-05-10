package com.example.indralibrarymanagement;

public class LoanBook {

    public long cardNo;
    public long book_id;
    public String memberName;
    public String branchName;
    public String dueDae;


    public LoanBook() {
        this.memberName = "memberName";
        this.branchName = "branchName";
        this.dueDae = "dueDae";
    }
}
