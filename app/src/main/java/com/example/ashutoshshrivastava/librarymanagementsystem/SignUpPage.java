package com.example.ashutoshshrivastava.librarymanagementsystem;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPage extends AppCompatActivity {

    EditText regName,regEmail,regPass,confPass;
    Button register;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        regName=findViewById(R.id.regName);
        regEmail=findViewById(R.id.regEmail);
        regPass=findViewById(R.id.regPass);
        confPass=findViewById(R.id.confirm_pass);
        register=findViewById(R.id.register);

        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=regName.getText().toString().trim();
                String CapemailText = regEmail.getText().toString().trim();
                 String  emailText =CapemailText.toLowerCase();
                String passwordText = regPass.getText().toString().trim();
                String retypedPassText = confPass.getText().toString().trim();


                if (passwordText.equals(retypedPassText)) {
                    performSignup(emailText, passwordText);

                    Intent intent=new Intent(SignUpPage.this,LoginPage.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                } else {
                    showError("Please enter same password");
                }
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


    void performSignup(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            Intent intent = new Intent(SignUpPage.this, LoginPage.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            //task.getResult();
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    void showError(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}