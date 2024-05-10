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

public class ShowBookActivity extends AppCompatActivity {

    ArrayList List;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_book);

        Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
        // Get all members from the database
        java.util.List<Book> books = db.getAllBooks();


        List = new ArrayList();


        for (com.example.indralibrarymanagement.Book book : books) {
            HashMap<String, String> item = new HashMap<>();

            long BookNo = book.book_id;
            String bookName = book.bookName;
            String bookPublisher = book.publisherName;
            String bookAuthor = book.authorName;

            item.put("Line1", "Book Title :- "+bookName);
            item.put("Line2", "Book Publisher :-"+ bookPublisher);
            item.put("Line3", "Book NO :-"+BookNo);
            item.put("Line4", "Book Author :-"+bookAuthor);
            List.add(item);
        }



        sa = new SimpleAdapter(this,List,
                R.layout.four_text_lines,
                new String[]{"Line1", "Line2", "Line3", "Line4"},

                new int[]{R.id.Line_a,R.id.Line_b,R.id.Line_c,R.id.Line_d}
        );
        ListView listView = findViewById(R.id.textViewBooks);
        listView.setAdapter(sa);

    }
}