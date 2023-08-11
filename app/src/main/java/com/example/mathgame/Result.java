package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView txtResult, txtResulScore;

    Button btnPlayAgain, btnExit;

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtResult = findViewById(R.id.txtResult);
        txtResulScore = findViewById(R.id.txtResultScore);

        btnExit = findViewById(R.id.btnExit);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        String userScore = String.valueOf(score);
        txtResulScore.setText("Your score is " + userScore);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}