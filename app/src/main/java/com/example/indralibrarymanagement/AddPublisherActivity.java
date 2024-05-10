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

public class AddPublisherActivity extends AppCompatActivity {

    EditText publisherName, publisherAddress, publisherPhone;
    Button addPublisherBtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_publisher);

        publisherName = findViewById(R.id.editTextPublisherName);
        publisherAddress = findViewById(R.id.editTextPublisherAddress);
        publisherPhone = findViewById(R.id.editTextPublisherPhone);
        addPublisherBtton = findViewById(R.id.buttonAddPublisher);

        addPublisherBtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PublisherName = publisherName.getText().toString();
                String PublisherAddress = publisherAddress.getText().toString();
                String PublisherPhone = publisherPhone.getText().toString();

                Database db=new Database(getApplicationContext(), "indraLibrary",null,1);
                if (PublisherName.length() == 0 || PublisherAddress.length() == 0 || PublisherPhone.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(getApplicationContext(), "UP", Toast.LENGTH_SHORT).show();

                    if (db.addPublisher(PublisherName,PublisherAddress,PublisherPhone) ==0){

                        Toast.makeText(getApplicationContext(), "Publisher name already exist", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "New Publisher details added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}