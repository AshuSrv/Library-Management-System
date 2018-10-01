package com.example.ashutoshshrivastava.librarymanagementsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    FloatingActionButton fab ;
    FirebaseAuth firebaseAuth;
TextView textView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
       ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
       actionBar.setHomeAsUpIndicator(R.drawable.menu);
       textView=findViewById(R.id.textmm);
        drawerLayout = findViewById(R.id.my_drwawer);
        navigationView = findViewById(R.id.nav);
        fab = findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();-----for future refrence
               Intent intent=new Intent(HomePage.this,AddBookToDatabase.class);
                startActivity(intent);
            }
        });




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.issued:
                        Intent intent=new Intent(HomePage.this,IssuedSection.class);
                        startActivity(intent);
                        break;

                    case R.id.returned:
                         intent=new Intent(HomePage.this,ReturnSection.class);
                        startActivity(intent);
                        break;

                    case R.id.fine:
                        intent=new Intent(HomePage.this,FineSection.class);
                        startActivity(intent);
                        break;
                    case R.id.reg_people:
                        intent=new Intent(HomePage.this,RegisterdPeopleSection.class);
                        startActivity(intent);
                        break;
                    case R.id.logout:
                      firebaseAuth.getInstance().signOut();
                      intent=new Intent(HomePage.this,LoginPage.class);
                      startActivity(intent);
                }
                item.setChecked(true);

                drawerLayout.closeDrawers();
                return true;


            }
        });


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                break;

            case R.id.account:
                Intent intent=new Intent(HomePage.this,AccountActivity.class);
                startActivity(intent);
                break;

            case R.id.invite:

                String data = "Hii buddy,you should try this amazing library management app";

                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, data);
                intent.setType("text/plain");

                startActivity(intent);
                break;
            case R.id.help:
                intent=new Intent(HomePage.this,HelpActicity.class);
                startActivity(intent);
                break;



        }
        super.onOptionsItemSelected(item);
        return true;
    }

}
