package com.example.indralibrarymanagement;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DeleteMemberActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

     Spinner spinnerDeleteMember;

    Member memberDelete =new Member(0,"empty","empty","empty",0);
    Button deleteMemberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_member);

        Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
        // Get all members from the database
        java.util.List<Member> member = db.getAllMembers();

        ArrayList<String> myList1 = new ArrayList<>();

        for (Member members : member) {
            String memberName = members.name;
            myList1.add(memberName);
        }
        String[] myArray1 = myList1.toArray(new String[0]);

        spinnerDeleteMember = findViewById(R.id.spinnerDeleteMember);
        spinnerDeleteMember.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, myArray1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDeleteMember.setAdapter(adapter);


        deleteMemberButton = findViewById(R.id.buttonDeleteMember);
        deleteMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.deleteMember(memberDelete.name) == 1){
                    Toast.makeText(getApplicationContext(), "Member Delete Successfully", Toast.LENGTH_SHORT).show();

                }



            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        if (adapterView.getId() == R.id.spinnerDeleteMember){

            String valueFromSpinnerMember = adapterView.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), valueFromSpinnerMember, Toast.LENGTH_SHORT).show();
            memberDelete.name = valueFromSpinnerMember;
            Log.d("MyTag", " memberDelete.name :-" +  memberDelete.name); // Info level

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}