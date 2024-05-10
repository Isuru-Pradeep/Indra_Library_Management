package com.example.indralibrarymanagement;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.text.ParseException;




public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users (username text, email text, password text)";
        String qry2 = "CREATE TABLE Book (BOOK_ID VARCHAR(13),TITLE VARCHAR(30),PUBLISHER_NAME VARCHAR(20),PRIMARY KEY (BOOK_ID))";
        String qry3 = "CREATE TABLE Publisher (NAME VARCHAR(20),ADDRESS VARCHAR(30),PHONE VARCHAR(10),PRIMARY KEY (NAME))";
        String qry4 = "CREATE TABLE Branch (BRANCH_ID VARCHAR(5), BRANCH_NAME VARCHAR(20),ADDRESS VARCHAR(30),PRIMARY KEY (BRANCH_ID))";
        String qry5 = "CREATE TABLE Member (CARD_NO VARCHAR(10), NAME VARCHAR(20), ADDRESS VARCHAR(30), PHONE VARCHAR(10), UNPAID_DUES DECIMAL(5,2), PRIMARY KEY (CARD_NO))";
        String qry6 = "CREATE TABLE Book_Author (BOOK_ID VARCHAR(13), AUTHOR_NAME VARCHAR(20), PRIMARY KEY (BOOK_ID, AUTHOR_NAME), FOREIGN KEY (BOOK_ID) REFERENCES Book(BOOK_ID))";
        String qry7 = "CREATE TABLE Book_Copy (BOOK_ID VARCHAR(13), BRANCH_ID VARCHAR(5), ACCESS_NO VARCHAR(5), PRIMARY KEY (ACCESS_NO, BRANCH_ID), FOREIGN KEY (BOOK_ID) REFERENCES Book(BOOK_ID), FOREIGN KEY (BRANCH_ID) REFERENCES Branch(BRANCH_ID))";
        String qry8 = "CREATE TABLE Book_Loan (ACCESS_NO VARCHAR(5), BRANCH_ID VARCHAR(5), CARD_NO VARCHAR(10), DATE_OUT DATE, DATE_DUE DATE, DATE_RETURNED DATE, PRIMARY KEY (ACCESS_NO, BRANCH_ID, CARD_NO, DATE_OUT), FOREIGN KEY (ACCESS_NO, BRANCH_ID) REFERENCES Book_Copy(ACCESS_NO, BRANCH_ID),FOREIGN KEY (CARD_NO) REFERENCES Member(CARD_NO), FOREIGN KEY (BRANCH_ID) REFERENCES Branch(BRANCH_ID))";

        sqLiteDatabase.execSQL(qry1);
        sqLiteDatabase.execSQL(qry2);
        sqLiteDatabase.execSQL(qry3);
        sqLiteDatabase.execSQL(qry4);
        sqLiteDatabase.execSQL(qry5);
        sqLiteDatabase.execSQL(qry6);
        sqLiteDatabase.execSQL(qry7);
        sqLiteDatabase.execSQL(qry8);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1){

    }
    public void register(String username,String email, String password){
        ContentValues cv=new ContentValues();
        cv.put( "username",username);
        cv.put( "email",email);
        cv.put( "password",password);
        SQLiteDatabase db=getWritableDatabase();
        db.insert( "users", null ,cv);
        db.close();
    }
    public int login(String username,String password){
        int result=0;
        String str[] = new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery( "select * from users where username=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }

    public int addMember(String memberName,String memberAddress,String memberPhone){
        String str[] = new String[1];
        str[0] = memberName;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery( "select * from Member where NAME=?",str);
        if(c.moveToFirst()){
            return  0;
        }
        else {




            Cursor cursor = db.rawQuery("SELECT MAX(CARD_NO) FROM Member", null);
            long maxCardNo = -1;
            String CardNo;
            if (cursor.moveToFirst()) {
                maxCardNo = cursor.getInt(0);
            }

            // If there are no members in the database, assign CARD_NO as 1
            if (maxCardNo == -1) {
                maxCardNo = 1;
            } else {
                // Otherwise, assign CARD_NO as the next number after the maximum CARD_NO
                maxCardNo = maxCardNo +1;
            }
            CardNo = Long.toString(maxCardNo);

            ContentValues cv=new ContentValues();
            cv.put( "CARD_NO",CardNo);
            cv.put( "NAME",memberName);
            cv.put( "ADDRESS",memberAddress);
            cv.put( "PHONE",memberPhone);
            SQLiteDatabase dbw=getWritableDatabase();
            dbw.insert( "Member", null ,cv);
            dbw.close();
            db.close();

        }


    return 1;
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();

        // Get a readable database instance
        SQLiteDatabase db = getReadableDatabase();

        // Create a cursor to select all members
        Cursor cursor = db.rawQuery("SELECT * FROM Member", null);

        // Loop through the cursor and create Member objects
        while (cursor.moveToNext() ) {
            long cardNo = cursor.getLong(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            String phone = cursor.getString(3);
            long unpaidDue = cursor.getLong(4);


            members.add(new Member(cardNo, name, address, phone ,unpaidDue));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return members;
    }

    public int addPublisher(String publisherName,String publisherAddress,String publisherPhone){
        String str[] = new String[1];
        str[0] = publisherName;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery( "select * from Publisher where NAME=?",str);
        if(c.moveToFirst()){
            return  0;
        }
        else {
            ContentValues cv=new ContentValues();
            cv.put( "NAME",publisherName);
            cv.put( "ADDRESS",publisherAddress);
            cv.put( "PHONE",publisherPhone);
            SQLiteDatabase dbw=getWritableDatabase();
            dbw.insert( "Publisher", null ,cv);
            dbw.close();

        }


        return 1;
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = new ArrayList<>();

        // Get a readable database instance
        SQLiteDatabase db = getReadableDatabase();

        // Create a cursor to select all members
        Cursor cursor = db.rawQuery("SELECT * FROM Publisher", null);

        // Loop through the cursor and create Member objects
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String address = cursor.getString(1);
            String phone = cursor.getString(2);


            publishers.add(new Publisher( name, address, phone ));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return publishers;
    }


    public int addBranch(String branchName,String branchAddress){
        String str[] = new String[1];
        str[0] = branchName;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery( "select * from Branch where BRANCH_NAME=?",str);
        if(c.moveToFirst()){
            return  0;
        }
        else {

            Cursor cursor = db.rawQuery("SELECT MAX(BRANCH_ID) FROM Branch", null);
            long branchId = -1;
            String CardNo;
            if (cursor.moveToFirst()) {
                branchId = cursor.getInt(0);
            }

            // If there are no members in the database, assign CARD_NO as 1
            if (branchId == -1) {
                branchId = 1;
            } else {
                // Otherwise, assign CARD_NO as the next number after the maximum CARD_NO
                branchId = branchId +1;
            }
            String branch_Id = Long.toString(branchId);

            ContentValues cv=new ContentValues();
            cv.put( "BRANCH_ID",branch_Id);
            cv.put( "BRANCH_NAME",branchName);
            cv.put( "ADDRESS",branchAddress);
            SQLiteDatabase dbw=getWritableDatabase();
            dbw.insert( "Branch", null ,cv);
            dbw.close();
            db.close();

        }


        return 1;
    }

    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();

        // Get a readable database instance
        SQLiteDatabase db = getReadableDatabase();

        // Create a cursor to select all members
        Cursor cursor = db.rawQuery("SELECT * FROM Branch", null);

        // Loop through the cursor and create Member objects
        while (cursor.moveToNext()) {
            String branchID = cursor.getString(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);


            branches.add(new Branch( name, address, branchID ));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return branches;
    }

    public int addBookAndAuthor(String bookName, String publisherName ,String authorName){
        String str[] = new String[1];
        str[0] = bookName;
        SQLiteDatabase db=getReadableDatabase();

        Cursor c=db.rawQuery( "select * from Book where TITLE=?",str);


        if(c.moveToFirst()){
            return  0;
        }
        else {

            Cursor cursor = db.rawQuery("SELECT MAX(BOOK_ID) FROM Book", null);
            long bookId = -1;

            if (cursor.moveToFirst()) {
                bookId = cursor.getInt(0);
            }

            // If there are no members in the database, assign CARD_NO as 1
            if (bookId == -1) {
                bookId = 1;
            } else {
                // Otherwise, assign CARD_NO as the next number after the maximum CARD_NO
                bookId = bookId +1;
            }
            String book_Id = Long.toString(bookId);
            db.close();

            ContentValues cv=new ContentValues();
            cv.put( "BOOK_ID",book_Id);
            cv.put( "TITLE",bookName);
            cv.put( "PUBLISHER_NAME",publisherName);
            SQLiteDatabase dbw=getWritableDatabase();
            dbw.insert( "Book", null ,cv);


            ContentValues cv2=new ContentValues();
            cv2.put( "BOOK_ID",book_Id);
            cv2.put( "AUTHOR_NAME",authorName);
            dbw.insert( "Book_Author", null ,cv2);
//            dbw.close();

//            SQLiteDatabase dbw1=getWritableDatabase();

            dbw.close();


        }


        return 1;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();


        SQLiteDatabase db = getReadableDatabase();

// Create a cursor to select all books and their authors
        Cursor cursor = db.rawQuery(
                "SELECT Book.BOOK_ID, Book.TITLE, Book.PUBLISHER_NAME, Book_Author.AUTHOR_NAME FROM Book INNER JOIN Book_Author ON Book.BOOK_ID = Book_Author.BOOK_ID",
                null
        );



// Loop through the cursor and create Book objects
        while (cursor.moveToNext()) {
            long bookNo = cursor.getLong(0);
            String bookTitle = cursor.getString(1);
            String publisherName = cursor.getString(2);
            String authorName = cursor.getString(3);

            books.add(new Book(bookNo, bookTitle, publisherName, authorName));
        }

// Close the cursor and database
        cursor.close();
        db.close();

        return books;
    }

    public String getBookID(String bookTitle){
        String bookID = "";
        Log.e("MyTag", "bookTitle :-" +bookTitle); // Info level

        String str[] = new String[1];
        str[0] = bookTitle;
        SQLiteDatabase db=getReadableDatabase();

        Cursor c=db.rawQuery( "select BOOK_ID from Book where TITLE=?",str);
        if (c.moveToFirst()) {
            bookID = c.getString(0);
        }

        return bookID;
    }

    public String getBranchID(String branchName){
        String branchID = "";

        String str[] = new String[1];
        str[0] = branchName;
        SQLiteDatabase db=getReadableDatabase();

        Cursor c=db.rawQuery( "select BRANCH_ID from Branch where BRANCH_NAME=?",str);
        if (c.moveToFirst()) {
            branchID = c.getString(0);
        }

        return branchID;
    }

    public String getCardID(String memberName){
        String cardID = "";

        String str[] = new String[1];
        str[0] = memberName;
        SQLiteDatabase db=getReadableDatabase();

        Cursor c=db.rawQuery( "select CARD_NO from Member where NAME=?",str);
        if (c.moveToFirst()) {
            cardID = c.getString(0);
        }

        return cardID;
    }

    public int loanBook(String bookName, String BranchName ,String memberName, String dueDate){
        String bookId = getBookID(bookName);
        String branchID = getBranchID(BranchName);
        String cardID = getCardID(memberName);

//        String bookId = "sample";
//        String branchID = "sample2";
//        String cardID = "sample3";

        SQLiteDatabase db=getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT MAX(ACCESS_NO) FROM Book_Copy", null);
        long accessId = -1;

        if (cursor.moveToFirst()) {
            accessId = cursor.getInt(0);
        }

        // If there are no members in the database, assign CARD_NO as 1
        if (accessId == -1) {
            accessId = 1;
        } else {
            // Otherwise, assign CARD_NO as the next number after the maximum CARD_NO
            accessId = accessId +1;
        }
        String access_Id = Long.toString(accessId);
        db.close();



        // Get the current date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Create a SimpleDateFormat object to parse the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Format the date as "yyyy-MM-dd"
        String formattedDate = dateFormat.format(currentDate);

        ContentValues cv=new ContentValues();
        cv.put( "BOOK_ID",bookId);
        cv.put( "BRANCH_ID",branchID);
        cv.put( "ACCESS_NO",access_Id);
        SQLiteDatabase dbw=getWritableDatabase();
        dbw.insert( "Book_Copy", null ,cv);

        ContentValues cv2=new ContentValues();
        cv2.put( "ACCESS_NO",access_Id);
        cv2.put( "BRANCH_ID",branchID);
        cv2.put( "CARD_NO",cardID);
        cv2.put( "DATE_OUT",formattedDate);
        cv2.put( "DATE_DUE",dueDate);
        dbw.insert( "Book_Loan", null ,cv2);

        dbw.close();


        return 1;
    }

    public String getAccessNo(String bookTitle,String branch){
        String bookId = getBookID(bookTitle);
        String branchId = getBranchID(branch);

        String accessNo = "";


        String str[] = new String[2];
        str[0] = bookId;
        str[1] = branchId;
        SQLiteDatabase db=getReadableDatabase();

        Cursor c=db.rawQuery( "select ACCESS_NO from Book_Copy where BOOK_ID=? and BRANCH_ID=? ",str);
        if (c.moveToFirst()) {
            accessNo = c.getString(0);
        }

        return accessNo;
    }

    public String getCardIDByAccessNo(String AccessNo){
        String cardID = "";

        String str[] = new String[1];
        str[0] = AccessNo;
        SQLiteDatabase db=getReadableDatabase();

        Cursor c=db.rawQuery( "select CARD_NO from Book_Loan where ACCESS_NO=?",str);
        if (c.moveToFirst()) {
            cardID = c.getString(0);
        }

        return cardID;
    }

    public String getUpaidDue(String cardNO){
        String UpaidDue  = "";

        String str[] = new String[1];
        str[0] = cardNO;
        SQLiteDatabase db=getReadableDatabase();

        Cursor c=db.rawQuery( "select UNPAID_DUES from Member where CARD_NO=?",str);
        if (c.moveToFirst()) {
            UpaidDue = c.getString(0);
        }

        return UpaidDue;
    }
    public int returnBook(String bookTitle,String branch ,String dueCharPerDay){
        String accessNo = getAccessNo(bookTitle,branch);
        String cardNo = getCardIDByAccessNo(accessNo);
        Log.i("MyTag", "accessNo :-"+accessNo); // Info level
        Log.i("MyTag", "cardNo :-"+cardNo); // Info level

        if (accessNo.length() == 0 || cardNo.length() == 0 ) {
            Log.w("MyTag", "Warning message"); // Warning level
            return 0;

        }
        else {
            String getUpaidDue = getUpaidDue(cardNo);
            Log.i("MyTag", "getUpaidDue :-" + getUpaidDue); // Info level


            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();

            // Create a SimpleDateFormat object to parse the date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Format the date as "yyyy-MM-dd"
            String formattedDate = dateFormat.format(currentDate);
//        String formattedDate = "2024-05-20";
            Log.i("MyTag", "formattedDate :-" + formattedDate); // Info level


            String str[] = new String[1];
            str[0] = accessNo;
//
        ContentValues cv = new ContentValues();
        cv.put("DATE_RETURNED", formattedDate);
        SQLiteDatabase dbw = getWritableDatabase();
        int rowsUpdated = dbw.update("Book_Loan", cv, "ACCESS_NO = ?", str);

        Log.i("MyTag", "Updated Book Loan rows :-" + rowsUpdated); // Info level

        SQLiteDatabase db=getReadableDatabase();
        String str2[] = new String[1];
        str2[0] = accessNo;

        Cursor cursor=db.rawQuery( "select DATE_DUE from Book_Loan where ACCESS_NO=?",str2);

        String duedate ="";
        if (cursor.moveToFirst()) {
            duedate = cursor.getString(0);
        }

        Log.i("MyTag", "loanDate :-" + duedate); // Info level

        // Get the two dates in string format
        String date1Str = duedate;
        String date2Str = formattedDate;

        // Create a SimpleDateFormat object to parse the dates
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Parse the dates
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = dateFormat.parse(date1Str);
            date2 = dateFormat.parse(date2Str);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // Calculate the difference between the two dates in milliseconds
        long differenceInMilliseconds = date2.getTime() - date1.getTime();

        // Convert the difference in milliseconds to days
        long differenceInDays = differenceInMilliseconds / (1000 * 60 * 60 * 24);

        Log.i("MyTag", "differenceInDays :-" + differenceInDays); // Info level

        long dueCharges = differenceInDays * Long.parseLong(dueCharPerDay);;
        Log.i("MyTag", "dueCharges :-" + dueCharges); // Info level

        long GetUpaidDue =0;
        if (getUpaidDue == null){

        }
        else {
            try {
                GetUpaidDue = Long.parseLong(getUpaidDue);
                Log.d("MyTag", "GetUpaidDue :-" + GetUpaidDue); // Info level
            } catch (NumberFormatException e) {
                // Handle the case when the string cannot be parsed as a long
                Log.d("MyTag", "e.getMessage() :-" + e.getMessage()); // Info level
            }
        }

        long peviousCharges = GetUpaidDue;
        Log.e("MyTag", "peviousCharges :-" + peviousCharges); // Info level


        long totalDue = dueCharges+peviousCharges;
        Log.d("MyTag", "totalDue :-" + totalDue); // Info level

//        long totalDue = 20;
        Log.e("MyTag", "totalDue :-" + totalDue); // Info level

        String str3[] = new String[1];
        str3[0] = cardNo;
        ContentValues cv3 = new ContentValues();
        cv3.put("UNPAID_DUES", totalDue);
        int rowsUpdated2 = dbw.update("Member", cv3, "CARD_NO = ?", str3);

        Log.e("MyTag", "Updated2 Book Loan rows :-" + rowsUpdated2); // Info level

        dbw.close();

            return 1;
        }
    }

    public int deleteMember(String name) {
        Log.i("MyTag", "Member name :-" + name); // Info level

        SQLiteDatabase dbw = getWritableDatabase();
        String str[] = new String[1];
        str[0] = name;

        int rowsDeleted = dbw.delete("Member", "NAME = ?", str);

        Log.i("MyTag", "Delete Member rows :-" + rowsDeleted); // Info level

        dbw.close();
        return rowsDeleted;
    }

    public List<Book> getAllLoanedBooks() {
        List<Book> books = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

// Create a cursor to select all books and their authors
        Cursor cursor = db.rawQuery(
                "SELECT Book.TITLE FROM Book INNER JOIN Book_Copy ON Book.BOOK_ID = Book_Copy.BOOK_ID",
                null
        );



// Loop through the cursor and create Book objects
        while (cursor.moveToNext()) {
            long bookNo =0;
            String bookTitle = cursor.getString(0);

            books.add(new Book(bookNo, bookTitle, "publisherName", "authorName"));
        }

// Close the cursor and database
        cursor.close();
        db.close();

        return books;
    }

    public List<Branch> getAllLoanBranches() {
        List<Branch> branches = new ArrayList<>();

        // Get a readable database instance
        SQLiteDatabase db = getReadableDatabase();

        // Create a cursor to select all branches on loan

        Cursor cursor = db.rawQuery(
                "SELECT Branch.BRANCH_NAME FROM Branch INNER JOIN Book_Copy ON Branch.BRANCH_ID = Book_Copy.BRANCH_ID",
                null
        );
//        Cursor cursor = db.rawQuery("SELECT * FROM Branch", null);

        // Loop through the cursor and create Member objects
        while (cursor.moveToNext()) {
//            String branchID = cursor.getString(0);
            String name = cursor.getString(0);
//            String address = cursor.getString(2);


            branches.add(new Branch( name, "address", "branchID" ));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return branches;
    }


}

