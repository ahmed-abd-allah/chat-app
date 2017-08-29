package com.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    TextView registerUser;
    EditText username, password;
    Button loginButton;
    private String user, pass;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerUser = (TextView)findViewById(R.id.register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.loginButton);
        mAuth = FirebaseAuth.getInstance();


        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser.setTextColor(Color.WHITE);
                startActivity(new Intent(LoginActivity.this, Register.class));
            }
        });
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                ckeck();

            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                user = username.getText().toString();
                pass = password.getText().toString();
                if (pass.length() < 6) {
                    password.setError("at least 6 characters  long");
                } else if (pass.equals("")) {
                    password.setError("can't be blank");
                }

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                singnin();
                loginButton.setTextColor(Color.WHITE);

            }
        });


    }
    public void singnin (){
       user =username.getText().toString();

        pass=password.getText().toString();
        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Loading...");
        pd.show();
        mAuth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(LoginActivity.this, "signInWithEmail:success.",
                                    Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            startActivity(new Intent(LoginActivity.this, Users.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }

                        // ...
                    }
                });



    }

    private boolean ckeck() {
        user = username.getText().toString();
        pass = password.getText().toString();
        if (user.equals("")) {
            username.setError("can't be blank");
            return false;
        } else if (!isEmailValid()) {
            username.setError("invalid email!!!");
            return false;
        } else
            return true;


    }

    public boolean isEmailValid() {
        String email = username.getText().toString();

        if (email.equals(null)) {
            return false;
        } else {
            Pattern pattern;
            Matcher matcher;
            final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }






}

