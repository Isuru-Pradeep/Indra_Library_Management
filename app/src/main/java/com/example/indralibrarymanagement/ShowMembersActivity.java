package com.example.indralibrarymanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowMembersActivity extends AppCompatActivity{

    ArrayList List;
    SimpleAdapter sa;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_show_members);


    Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
    // Get all members from the database
    java.util.List<Member> members = db.getAllMembers();


    List = new ArrayList();

    for (com.example.indralibrarymanagement.Member member : members) {
        HashMap<String, String> item = new HashMap<>();

        long cardNo = member.cardNo;
        String memberName = member.name;
        String memberAddress = member.address;
        String memberPhone = member.phone;
        long unpaidDue = member.unpaidDue;


        item.put("Line1", "Member's name :- "+memberName);
        item.put("Line2", "Member's address :-"+memberAddress);
        item.put("Line3", "Member's Phone :-\n"+ memberPhone);
        item.put("Line4", "Card NO :-"+cardNo);
        item.put("Line5", "Unpaid Due :-"+unpaidDue);
        List.add(item);
    }

    sa = new SimpleAdapter(this,List,
            R.layout.text_lines,
            new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},

            new int[]{R.id.Line_a,R.id.Line_b,R.id.Line_c,R.id.Line_d,R.id.Line_e}
    );
    ListView listView = findViewById(R.id.textViewMembers);
    listView.setAdapter(sa);




        // Create a layout to display the members
//        LinearLayout memberListLayout = new LinearLayout(this);
//        memberListLayout.setOrientation(LinearLayout.VERTICAL);

//        Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
//
//        // Get all members from the database
//        List<com.example.indralibrarymanagement.Member> members = db.getAllMembers();
//
//    // Create a TextView for the header row
//    TextView headerTextView = new TextView(this);
//    headerTextView.setText(String.format("%-7s %-20s %-30s %-13s", "Card No", "Name", "Address", "Phone"));
//
//    // Create a horizontal line
//    View divider = new View(this);
//    divider.setBackgroundColor(Color.BLACK);
//    divider.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
//
//// Add the horizontal line to the layout
//    memberListLayout.addView(divider);
//// Add the header row to the layout
//    memberListLayout.addView(headerTextView);
//        // Create a TextView for each member and add it to the layout
//        for (com.example.indralibrarymanagement.Member member : members) {
//            TextView memberTextView = new TextView(this);
//            memberTextView.setText(member.toString());
//            memberListLayout.addView(memberTextView);
//
////            TextView footerTextView = new TextView(this);
////            memberTextView.setText(String.format("%-5s %-20s %-30s %-13s", member.toString(), member.toString(), member.toString(), member.toString()));
//
//
//        }
//
//    // Create a TextView for the footer row
//    TextView footerTextView = new TextView(this);
//    footerTextView.setText(String.format("%-10s %-20s %-30s %-15s","", "Total Members", ":-",  members.size()));
//
//    // Add the footer row to the layout
//    memberListLayout.addView(footerTextView);
//        // Add the layout to the activity
//        setContentView(memberListLayout);
    }

//    private List<Member> getAllMembers() {
//        List<Member> members = new ArrayList<>();
//
//        // Get a readable database instance
//        SQLiteDatabase db = getReadableDatabase();
//
//        // Create a cursor to select all members
//        Cursor cursor = db.rawQuery("SELECT * FROM Member", null);
//
//        // Loop through the cursor and create Member objects
//        while (cursor.moveToNext()) {
//            long cardNo = cursor.getLong(0);
//            String name = cursor.getString(1);
//            String address = cursor.getString(2);
//            String phone = cursor.getString(3);
//
//            members.add(new Member(cardNo, name, address, phone));
//        }
//
//        // Close the cursor and database
//        cursor.close();
//        db.close();
//
//        return members;
//    }


}