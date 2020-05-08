package com.example.redeye;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class splashScreenActivity extends AppCompatActivity {
    public static boolean started=false;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      mAuth=FirebaseAuth.getInstance();
      if(mAuth.getCurrentUser()!=null)
      {
          Intent intent=new Intent(getApplication(),MainActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(intent);
          finish();
          return ;


      }else
      {
          Intent intent=new Intent(getApplication(),loginActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(intent);
          finish();
          return ;
      }

    }
}
