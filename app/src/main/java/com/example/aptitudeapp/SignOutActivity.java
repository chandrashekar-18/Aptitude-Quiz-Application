package com.example.aptitudeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SignOutActivity extends AppCompatActivity {
    private Button signOutBtn;
    private FirebaseAuth mAuth;
    private TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signout);
        signOutBtn=findViewById(R.id.signout_btn);
        mAuth=FirebaseAuth.getInstance();
        score = findViewById(R.id.score);
        score.setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();
        score.setText("Your Score is : "+bundle.getInt("Score"));
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity((new Intent(SignOutActivity.this, LoginActivity.class)));
                finish();
            }
        });
    }
}