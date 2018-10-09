package com.example.ashutoshshrivastava.librarymanagementsystem;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IssuedBooks extends AppCompatActivity {
    DatabaseReference databaseBooks;
    RecyclerView recyclerView;
    ArrayList<Books> list;
    BooksViewAdapter adapter;
    FloatingActionButton fab ;

    public String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issued_books);

        fab = findViewById(R.id.fab2);
        recyclerView = findViewById(R.id.listofissuedbooks);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseBooks = FirebaseDatabase.getInstance().getReference().child("books");
        list=new ArrayList<Books>();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();-----for future refrence
                Intent intent=new Intent(IssuedBooks.this,IssuedSection.class);
                startActivity(intent);
            }
        });

                String searchNameText="Issued";
                list.clear();



                Query query=FirebaseDatabase.getInstance().getReference("books").orderByChild("statusText").equalTo(searchNameText);
                query.addListenerForSingleValueEvent(new ValueEventListener() {



                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {


                            for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                            {

                                key = dataSnapshot1.getKey();

                                Books books=dataSnapshot1.getValue(Books.class);
                                list.add(books);

                            }
                        }
                        adapter=new BooksViewAdapter(IssuedBooks.this, list);
                        recyclerView.setAdapter(adapter);



                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(IssuedBooks.this,"Error",Toast.LENGTH_SHORT).show();;

                    }
                });



            }



}
