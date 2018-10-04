package com.example.ashutoshshrivastava.librarymanagementsystem;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBookToDatabase extends AppCompatActivity {

    EditText bookName, authorName,rating,genre,url;
    Button addBook;

    DatabaseReference databaseBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_to_database);

        bookName=findViewById(R.id.book_name);
        addBook=findViewById(R.id.addBook);
        authorName=findViewById(R.id.author_name);
        rating=findViewById(R.id.rating_value);
        genre=findViewById(R.id.genre_input);
        url=findViewById(R.id.url_input);

        databaseBooks=FirebaseDatabase.getInstance().getReference("books");
        databaseBooks.keepSynced(true);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String booknameText=bookName.getText().toString().trim();
                String authorText=authorName.getText().toString().trim();
                String issuerText="Null";
                String ratingText=rating.getText().toString().trim();
                String coverPicTextURL=url.getText().toString().trim();
                String statusText="Available";
                String genreText=genre.getText().toString().trim();


                if
                        (booknameText.compareTo("")==0||authorText.compareTo("")==0||ratingText.compareTo("")==0||genreText.compareTo("")==0||coverPicTextURL.compareTo("")==0)
                {
                    Toast.makeText(AddBookToDatabase.this,"Enter all the fields",Toast.LENGTH_SHORT).show();
                }
else
                {
                    String id=databaseBooks.push().getKey();
                    BookList bookList=new
                            BookList(booknameText,authorText,ratingText,genreText,coverPicTextURL,issuerText,statusText);
                    databaseBooks.child(id).setValue(bookList);
                    Toast.makeText(AddBookToDatabase.this,"Book added",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(AddBookToDatabase.this,HomePage.class);
                    startActivity(intent);
                }
            }
        } );
            }

    powerConnection receiver = new powerConnection();
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Intent.ACTION_POWER_CONNECTED);
        ifilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(receiver, ifilter);

    }

}
