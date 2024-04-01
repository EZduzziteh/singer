package com.example.whoisthesinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends AppCompatActivity implements View.OnClickListener {

    Button Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("Who is the Singer ?");

        Back = findViewById(R.id.back_button);

        Back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(About.this, MainActivity.class);
        startActivity(intent);
    }
}