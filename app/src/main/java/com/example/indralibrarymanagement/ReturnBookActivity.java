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

public class ReturnBookActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{


    Book bookReturnBook = new Book(0,"empty","empty","empty");
    Branch branchReturnBook =new Branch("empty","empty","empty");
    EditText dueChargesEditText;
    Spinner spinnerBooks,spinnerBranches;
    Button returnBookButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_return_book);

        Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
        // Get all members from the database
        java.util.List<Book> book = db.getAllLoanedBooks();
        java.util.List<Branch> branch = db.getAllLoanBranches();

        ArrayList<String> myList2 = new ArrayList<>();
        ArrayList<String> myList3 = new ArrayList<>();

        for (Book books : book) {
            String bookName = books.bookName;
            myList2.add(bookName);
        }
        String[] myArray2 = myList2.toArray(new String[0]);


        spinnerBooks = findViewById(R.id.spinnerBookNameReturnBook);
        spinnerBooks.setOnItemSelectedListener(this);
        ArrayAdapter adapter2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, myArray2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBooks.setAdapter(adapter2);



        for (Branch branches : branch) {
            String branchName = branches.name;
            myList3.add(branchName);
        }
        String[] myArray3 = myList3.toArray(new String[0]);


        spinnerBranches = findViewById(R.id.spinnerBranchNameReturnBook);
        spinnerBranches.setOnItemSelectedListener(this);
        ArrayAdapter adapter3 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, myArray3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranches.setAdapter(adapter3);



        dueChargesEditText = findViewById(R.id.editOverDueChargePerDay);
        returnBookButton = findViewById(R.id.buttonReturnBook);

        returnBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String charges = dueChargesEditText.getText().toString();
                String bookTitle = bookReturnBook.bookName;
                String branchName = branchReturnBook.name;

                if (charges.length() == 0 || bookTitle.length() == 0 || branchName.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else {
                   int status;
                    status = db.returnBook(bookReturnBook.bookName,branchReturnBook.name,charges);
                    if(status == 0){
                        Toast.makeText(getApplicationContext(), "Don't have recode", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Return Successfully", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        if (adapterView.getId() == R.id.spinnerBookNameReturnBook){
            String valueFromSpinnerBook = adapterView.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), valueFromSpinnerBook, Toast.LENGTH_SHORT).show();
            bookReturnBook.bookName = valueFromSpinnerBook;

        }
        if (adapterView.getId() == R.id.spinnerBranchNameReturnBook){
            String valueFromSpinnerBranch = adapterView.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), valueFromSpinnerBranch, Toast.LENGTH_SHORT).show();
            branchReturnBook.name = valueFromSpinnerBranch;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}