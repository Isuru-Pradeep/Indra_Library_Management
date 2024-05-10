package com.example.indralibrarymanagement;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class LoanBookActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText dateEditText;
    private Spinner spinnerMembers,spinnerBooks,spinnerBranches;

    Member memberLoanBook =new Member(0,"empty","empty","empty",0);
    Book bookLoanBook = new Book(0,"empty","empty","empty");
    Branch branchLoanBook =new Branch("empty","empty","empty");
    Button loanBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loan_book);


        Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
        // Get all members from the database
        java.util.List<Member> member = db.getAllMembers();
        java.util.List<Book> book = db.getAllBooks();
        java.util.List<Branch> branch = db.getAllBranches();

        ArrayList<String> myList1 = new ArrayList<>();
        ArrayList<String> myList2 = new ArrayList<>();
        ArrayList<String> myList3 = new ArrayList<>();

        for (Member members : member) {
            String memberName = members.name;
            myList1.add(memberName);
        }
        String[] myArray1 = myList1.toArray(new String[0]);

        spinnerMembers = findViewById(R.id.spinnerMemberNameLoanBook);
        spinnerMembers.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, myArray1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMembers.setAdapter(adapter);



        for (Book books : book) {
            String bookName = books.bookName;
            myList2.add(bookName);
        }
        String[] myArray2 = myList2.toArray(new String[0]);


        spinnerBooks = findViewById(R.id.spinnerBookNameLoanBook);
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


        spinnerBranches = findViewById(R.id.spinnerBranchNameLoanBook);
        spinnerBranches.setOnItemSelectedListener(this);
        ArrayAdapter adapter3 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, myArray3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranches.setAdapter(adapter3);



//        memberName = findViewById(R.id.editTextMemberName);
//        memberPhone = findViewById(R.id.editTextMemberphone);
        loanBookButton = findViewById(R.id.buttonLoanBook);
        dateEditText = findViewById(R.id.editTextDueDate);

        // Set a click listener on the EditText to show the DatePickerDialog
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        loanBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String duedate = dateEditText.getText().toString();
                String member = memberLoanBook.name;
                String book = bookLoanBook.bookName;
                String branch = branchLoanBook.name;

//                Toast.makeText(getApplicationContext(), "Due Date - "+loanBook.dueDae, Toast.LENGTH_SHORT).show();
                if(db.loanBook(book, branch ,member, duedate) == 1){
                    Toast.makeText(getApplicationContext(), "Loan Book Successfully", Toast.LENGTH_SHORT).show();

                }



            }
        });

    }

    private void showDatePickerDialog() {
        // Create a new DatePickerDialog instance
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);

        // Set the callback for when the user selects a date
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Update the EditText with the selected date
                dateEditText.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        });

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getId() == R.id.spinnerMemberNameLoanBook){

            String valueFromSpinnerMember = adapterView.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), valueFromSpinnerMember, Toast.LENGTH_SHORT).show();
            memberLoanBook.name = valueFromSpinnerMember;
        }
        if (adapterView.getId() == R.id.spinnerBookNameLoanBook){
            String valueFromSpinnerBook = adapterView.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), valueFromSpinnerBook, Toast.LENGTH_SHORT).show();
            bookLoanBook.bookName = valueFromSpinnerBook;
        }
        if (adapterView.getId() == R.id.spinnerBranchNameLoanBook){
            String valueFromSpinnerBranch = adapterView.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), valueFromSpinnerBranch, Toast.LENGTH_SHORT).show();
            branchLoanBook.name = valueFromSpinnerBranch;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}