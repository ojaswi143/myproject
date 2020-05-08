package com.example.redeye;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class startloginActivity extends AppCompatActivity {
    private EditText memail,mpassword;
    private Button mlogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseauthstatelistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startlogin);
        firebaseauthstatelistener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
                    Intent intent=new Intent(getApplication(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return ;
                }
            }
        };
       Toast.makeText(getApplicationContext(),"you are not logged in. Do login !",Toast.LENGTH_LONG).show();
        mAuth=FirebaseAuth.getInstance();

        mlogin=findViewById(R.id.login);
        memail=findViewById(R.id.email);
         mpassword=findViewById(R.id.password);
         mlogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final String email=memail.getText().toString();
                 final String password=mpassword.getText().toString();
                 mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(startloginActivity.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(!task.isSuccessful())
                         {
                             Toast.makeText(startloginActivity.this,"sign in ERROR",Toast.LENGTH_SHORT).show();

                         }
                         else   Toast.makeText(startloginActivity.this,"logged in !",Toast.LENGTH_SHORT).show();
                     }
                 });


             }
         });

    }
    protected void onStart() {

        super.onStart();
        mAuth.addAuthStateListener(firebaseauthstatelistener);
    }
    protected void onStop() {

        super.onStop();
         mAuth.removeAuthStateListener(firebaseauthstatelistener);
    }



}
