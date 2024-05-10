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
    }

}