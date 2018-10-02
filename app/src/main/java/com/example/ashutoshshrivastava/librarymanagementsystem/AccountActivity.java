package com.example.ashutoshshrivastava.librarymanagementsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {
TextView accountactivityemail,accoundactivityPassword;
Button changeemail,changepass,Updateparamter;
EditText DynamicParameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Intent intent=getIntent();
        final String emailvaluetodisplay = intent.getStringExtra("passEmail");
        final String passwordvaluetodisplay = intent.getStringExtra("passPassword");


        accountactivityemail=findViewById(R.id.accountactivityemailid);
        accoundactivityPassword=findViewById(R.id.accountactivityPassword);
        DynamicParameter=findViewById(R.id.DynamicParameter);
        Updateparamter=findViewById(R.id.Updateparameter);
        changepass=findViewById(R.id.changepass);

        accoundactivityPassword.setText(passwordvaluetodisplay);
        accountactivityemail.setText(emailvaluetodisplay);
changepass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        DynamicParameter.setVisibility(View.VISIBLE);
        Updateparamter.setVisibility(View.VISIBLE);
    }
});
        Updateparamter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newPass=DynamicParameter.getText().toString().trim();
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
                AuthCredential credential = EmailAuthProvider
                        .getCredential(emailvaluetodisplay,passwordvaluetodisplay);

// Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(AccountActivity.this,"Password Changed",Toast.LENGTH_SHORT).show();
                                                DynamicParameter.setVisibility(View.INVISIBLE);
                                                Updateparamter.setVisibility(View.INVISIBLE);

                                            } else {
                                                Toast.makeText(AccountActivity.this,"Password failed to update",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(AccountActivity.this,"Authentication failure",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
