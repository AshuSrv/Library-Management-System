package com.example.ashutoshshrivastava.librarymanagementsystem;

import android.content.Intent;
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
                String emailText = regEmail.getText().toString();
                String passwordText = regPass.getText().toString();
                String retypedPassText = confPass.getText().toString();

                if (passwordText.equals(retypedPassText)) {
                    performSignup(emailText, passwordText);
                } else {
                    showError("Please enter same password");
                }
            }
        });
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