package com.example.indralibrarymanagement;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowBranchActivity extends AppCompatActivity {
    ArrayList List;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_branch);

        Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
        // Get all members from the database
        java.util.List<Branch> branch = db.getAllBranches();

        List = new ArrayList();

        for (Branch branches : branch) {
            HashMap<String, String> item = new HashMap<>();
            String branchName = branches.name;
            String branchAddress = branches.address;
            String branch_ID = branches.branchID;

            item.put("Line1", "Branch name :- "+branchName);
            item.put("Line2", "Branch :-"+branchAddress);
            item.put("Line3", "Branch ID :-"+ branch_ID);

            List.add(item);
        }

        sa = new SimpleAdapter(this,List,
                R.layout.three_text_lines,
                new String[]{"Line1", "Line2", "Line3"},

                new int[]{R.id.Line_a,R.id.Line_b,R.id.Line_c}
        );
        ListView listView = findViewById(R.id.textViewBranches);
        listView.setAdapter(sa);
    }
}