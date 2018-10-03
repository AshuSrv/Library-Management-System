package com.example.ashutoshshrivastava.librarymanagementsystem;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReissueSection extends AppCompatActivity {


    EditText searchNamereissue,reissuerNme;
    Button updatereissue,searchreissue;

    DatabaseReference databaseBooks;


    RecyclerView recyclerView;
    ArrayList<Books> list;
    BooksViewAdapter adapter;
    public String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reissue_section);

        searchNamereissue=findViewById(R.id.reissuebookname);
        updatereissue=findViewById(R.id.updatetoissued);
        searchreissue=findViewById(R.id.reissue_searchbuttom);
         reissuerNme=findViewById(R.id.issuerName);
        recyclerView = findViewById(R.id.recycler_view_reissue);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseBooks = FirebaseDatabase.getInstance().getReference().child("books");

        list=new ArrayList<Books>();


        String id;


        searchreissue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchNameText=searchNamereissue.getText().toString();
                list.clear();



                Query query=FirebaseDatabase.getInstance().getReference("books").orderByChild("booknameText").equalTo(searchNameText);
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
                        adapter=new BooksViewAdapter(ReissueSection.this, list);
                        recyclerView.setAdapter(adapter);



                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ReissueSection.this,"Error",Toast.LENGTH_SHORT).show();;

                    }
                });



            }
        });


        updatereissue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=reissuerNme.getText().toString();
                Toast.makeText(ReissueSection.this,"Values Updated",Toast.LENGTH_LONG).show();
                databaseBooks.child(key).child("statusText").setValue("Issued");
                databaseBooks.child(key).child("issuerText").setValue(name);



            }
        });


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
