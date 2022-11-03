package com.example.mfl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void reg(View View){
        Intent intent = new Intent(this, reg.class);
        startActivity(intent);
    }
    public void log(View View){
        Intent intent = new Intent(this, log.class);
        startActivity(intent);
    }
}