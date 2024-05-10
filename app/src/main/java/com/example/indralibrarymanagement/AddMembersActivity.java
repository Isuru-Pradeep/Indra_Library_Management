package com.example.indralibrarymanagement;

import android.content.Intent;
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

public class AddMembersActivity extends AppCompatActivity {

    EditText memberName, memberAddress, memberPhone;
    Button addMemberBtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_members);

        memberName = findViewById(R.id.editTextMemberName);
        memberAddress = findViewById(R.id.editTextMemberAddress);
        memberPhone = findViewById(R.id.editTextMemberphone);
        addMemberBtton = findViewById(R.id.buttonAddMember);

        addMemberBtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Membername = memberName.getText().toString();
                String MemberAddress = memberAddress.getText().toString();
                String MemberPhone = memberPhone.getText().toString();

                Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
                if (Membername.length() == 0 || MemberAddress.length() == 0 || MemberPhone.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(getApplicationContext(), "UP", Toast.LENGTH_SHORT).show()

                    if (db.addMember(Membername,MemberAddress,MemberPhone) ==0){

                    Toast.makeText(getApplicationContext(), "Member name alredy exist", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "New member details added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}