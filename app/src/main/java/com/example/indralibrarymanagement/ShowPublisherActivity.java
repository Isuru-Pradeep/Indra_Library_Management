package com.example.indralibrarymanagement;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowPublisherActivity extends AppCompatActivity {
    ArrayList List;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_publisher);

        Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
        // Get all members from the database
        java.util.List<Publisher> publisher = db.getAllPublishers();

        List = new ArrayList();

        for (Publisher publishers : publisher) {
            HashMap<String, String> item = new HashMap<>();
            String publisherName = publishers.name;
            String publisherAddress = publishers.address;
            String publisherPhone = publishers.phone;

            item.put("Line1", "Publisher's name :- "+publisherName);
            item.put("Line2", "Publisher's address :-"+publisherAddress);
            item.put("Line3", "Publisher's Phone :-"+ publisherPhone);

            List.add(item);
        }

        sa = new SimpleAdapter(this,List,
                R.layout.three_text_lines,
                new String[]{"Line1", "Line2", "Line3"},

                new int[]{R.id.Line_a,R.id.Line_b,R.id.Line_c}
        );
        ListView listView = findViewById(R.id.textView1);
        listView.setAdapter(sa);

    }
}