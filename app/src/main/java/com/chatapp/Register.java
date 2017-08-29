package com.chatapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText username,email,password,confpassword;
    TextView registerButton;
    String user, pass,confpass,umail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confpassword = (EditText) findViewById(R.id.confpassword);
        registerButton = (TextView) findViewById(R.id.register);
        username=(EditText)findViewById(R.id.username);

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                ckeck();
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                pass = password.getText().toString();
                if (pass.length() < 6) {
                    password.setError("at least 6 characters  long");
                } else if (pass.equals("")) {
                    password.setError("can't be blank");
                }

            }
        });
        confpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                confpass = confpassword.getText().toString();
                pass = password.getText().toString();
                if (!pass.equals(confpass)){
                    confpassword.setError( "doesn't matched !!");
                }

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                createuser();


            }

        });
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                user=username.getText().toString();

                if (user.equals(null)){
                    username.setError( "can't be null");
                }
                else if (user.length()<6){
                    username.setError( "can't be less than 6 char");

                }

            }
        });
    }


        public void createuser () {
            String umail = email.getText().toString();
            String pass = password.getText().toString();
            final ProgressDialog pd = new ProgressDialog(Register.this);
            pd.setMessage("Loading...");
            pd.show();


                mAuth.createUserWithEmailAndPassword(umail,pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    createuserdata();
                                    Toast.makeText(Register.this, " done!!!!!", Toast.LENGTH_SHORT).show();
                                  UserDetails.username=username.getText().toString();
                                    pd.dismiss();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }


                            }
                        });


        }

    private boolean ckeck() {
         pass = password.getText().toString();
        umail=email.getText().toString();
        if (umail.equals("")) {
            email.setError("can't be blank");
            return false;
        } else if (!isEmailValid()) {
            email.setError("invalid email!!!");
            return false;
        } else
            return true;


    }

    public boolean isEmailValid() {
        String memail = email.getText().toString();

        if (memail.equals(null)) {
            return false;
        } else {
            Pattern pattern;
            Matcher matcher;
            final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(memail);
            return matcher.matches();
        }
    }


    public void createuserdata(){
        user=username.getText().toString();
        umail=email.getText().toString();
        User auser=new User(user,umail);
        String userid= FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference("users").child(userid).setValue(auser);


    }
}
