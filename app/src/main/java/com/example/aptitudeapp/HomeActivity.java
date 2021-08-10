package com.example.aptitudeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button btncat1;
    Button btncat2;
    Button btncat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btncat1=findViewById(R.id.cat1);
        btncat2=findViewById(R.id.cat2);
        btncat3=findViewById(R.id.cat3);

        btncat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, General_Activity.class);
                startActivity(intent);
            }
        });

        btncat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, Verbal_Activity.class);
                startActivity(intent);
            }
        });

        btncat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, Logical_Activity.class);
                startActivity(intent);
            }
        });
    }
}