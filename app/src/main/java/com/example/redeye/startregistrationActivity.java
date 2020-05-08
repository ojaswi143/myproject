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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

public class startregistrationActivity extends AppCompatActivity {
    private EditText memail,mpassword,mname;
    private Button mregister;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseauthstatelistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startregistration);
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
        Toast.makeText(getApplicationContext(),"register !",Toast.LENGTH_LONG).show();
        mAuth=FirebaseAuth.getInstance();

        mregister=findViewById(R.id.register);
        mname=findViewById(R.id.name);
        memail=findViewById(R.id.email);
        mpassword=findViewById(R.id.password);
        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=mname.getText().toString();
                final String email=memail.getText().toString();
                final String password=mpassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(startregistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(getApplication(),"sign in ERROR",Toast.LENGTH_SHORT).show();

                        }else
                        {   Toast.makeText(getApplication(),"sign in done",Toast.LENGTH_SHORT).show();
                            String userId= Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                            DatabaseReference currentUserDb= FirebaseDatabase.getInstance().getReference().child("users").child("userId");
                            Map userInfo=new HashMap<>();
                            userInfo.put("email",email);
                            userInfo.put("name",name);
                            userInfo.put("profileImageUrl","default");
                            currentUserDb.updateChildren(userInfo);

                        }
                        Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
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
