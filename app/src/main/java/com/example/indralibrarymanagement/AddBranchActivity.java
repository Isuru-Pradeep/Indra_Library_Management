package com.example.indralibrarymanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddBranchActivity extends AppCompatActivity {

    EditText branchName, branchAddress;
    Button addBranchBtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_branch);

        branchName = findViewById(R.id.editTextBranchName);
        branchAddress = findViewById(R.id.editTextBranchAddress);
        addBranchBtton = findViewById(R.id.buttonAddBranch);

        addBranchBtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Branchname = branchName.getText().toString();
                String BranchAddress = branchAddress.getText().toString();

                Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
                if (Branchname.length() == 0 || BranchAddress.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (db.addBranch(Branchname,BranchAddress) ==0){

                        Toast.makeText(getApplicationContext(), "Branch name already exist", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "New branch details added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}