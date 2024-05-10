package com.example.indralibrarymanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.cardview.widget.CardView;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username= sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome "+username, Toast.LENGTH_SHORT).show();



        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent( HomeActivity.this,LoginActivity.class));

            }
        });

        CardView addMembers = findViewById(R.id.cardAddMembers);
        addMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,AddMembersActivity.class));
            }
        });

        CardView addPublisher = findViewById(R.id.cardAddPublisher);
        addPublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "add pulisher", Toast.LENGTH_SHORT).show();

                startActivity(new Intent( HomeActivity.this, AddPublisherActivity.class));
            }
        });

//        CardView labTest = findViewById(R.id.cardLabTest);
//        findDoctor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
////                startActivity(new Intent( HomeActivity.this,LabTestActivity.class));
//            }
//        });

        CardView showMembers = findViewById(R.id.cardShowMembers);
        showMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "show members", Toast.LENGTH_SHORT).show();

                startActivity(new Intent( HomeActivity.this, ShowMembersActivity.class));
            }
        });

        CardView showPublisher = findViewById(R.id.cardShowPublishers);
        showPublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "show publishers", Toast.LENGTH_SHORT).show();

                startActivity(new Intent( HomeActivity.this, ShowPublisherActivity.class));
            }
        });

        CardView addBranch = findViewById(R.id.cardAddBranch);
        addBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                startActivity(new Intent( HomeActivity.this, AddBranchActivity.class));
            }
        });

        CardView showBranch = findViewById(R.id.cardShowBranchs);
        showBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                startActivity(new Intent( HomeActivity.this, ShowBranchActivity.class));
            }
        });

        CardView addBook = findViewById(R.id.cardAddBook);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                startActivity(new Intent( HomeActivity.this, AddBookActivity.class));
            }
        });

        CardView showBooks = findViewById(R.id.cardShowBooks);
        showBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                startActivity(new Intent( HomeActivity.this, ShowBookActivity.class));
            }
        });

        CardView LoanBook = findViewById(R.id.cardLoanBook);
        LoanBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                startActivity(new Intent( HomeActivity.this, LoanBookActivity.class));
            }
        });

        CardView ReturnBook = findViewById(R.id.cardReturnBook);
        ReturnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                startActivity(new Intent( HomeActivity.this, ReturnBookActivity.class));
            }
        });

        CardView DeleteMember = findViewById(R.id.cardDeleteMember);
        DeleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                startActivity(new Intent( HomeActivity.this, DeleteMemberActivity.class));
            }
        });

    }

}
