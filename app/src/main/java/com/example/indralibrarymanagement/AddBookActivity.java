package com.example.indralibrarymanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class AddBookActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinnerPublishers;

    TextView bookTitle,authorName;
    Button addBookButton;

    Publisher bookPublisher = new Publisher("empty","empty","empty");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_book);




        Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
        // Get all members from the database
        java.util.List<Publisher> publisher = db.getAllPublishers();

        ArrayList<String> myList = new ArrayList<>();

        for (Publisher publishers : publisher) {
            String publisherName = publishers.name;
            myList.add(publisherName);
        }
////        String[] myArray = (String[]) List.toArray(new String[0]);
        String[] myArray = myList.toArray(new String[0]);


        spinnerPublishers = findViewById(R.id.spinnerPublisherName);

        spinnerPublishers.setOnItemSelectedListener(this);
////        String[] textSizes = getResources().getStringArray(R.array.font_sizes);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, myArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPublishers.setAdapter(adapter);

        bookTitle = findViewById(R.id.editTextBookName);
        authorName = findViewById(R.id.editAuthorName);
        addBookButton = findViewById(R.id.buttonAddBook);

        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String BookTitle = bookTitle.getText().toString();
                String AuthorName = authorName.getText().toString();

                if (BookTitle.length() == 0 || AuthorName.length() == 0 ) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (db.addBookAndAuthor(BookTitle,bookPublisher.name, AuthorName) ==0){

                        Toast.makeText(getApplicationContext(), "Book Title already exist", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "New Book details added", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if (parent.getId() == R.id.spinnerPublisherName){
            String valueFromSpinner = parent.getItemAtPosition(position).toString();

            Toast.makeText(getApplicationContext(), valueFromSpinner, Toast.LENGTH_SHORT).show();
            bookPublisher.name = valueFromSpinner;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}