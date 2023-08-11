package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class PlayGameMulti extends AppCompatActivity {

    TextView txtScore, txtScoreNum, txtLife, txtLifeNum, txtTime, txtTimeNum, txtQuestion;
    EditText edAnswer;
    Button btnOk, btnNext;

    Random random = new Random();
    int num1, num2;

    int userAnswer, realAnswer, userScore = 0, userLife = 3;

    CountDownTimer timer;

    private static final long START_TIMER_IN_MILS = 10000;
    Boolean timer_running;
    long left_time_in_mils = START_TIMER_IN_MILS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game_multi);

        txtScore = findViewById(R.id.txtScore);
        txtScoreNum = findViewById(R.id.txtScoreNum);
        txtLife = findViewById(R.id.txtLife);
        txtLifeNum = findViewById(R.id.txtLifeNum);
        txtTime = findViewById(R.id.txtTime);
        txtTimeNum = findViewById(R.id.txtTimeNum);
        txtQuestion = findViewById(R.id.txtQuestion);

        edAnswer = findViewById(R.id.edAnswer);

        btnOk = findViewById(R.id.btnOk);
        btnNext = findViewById(R.id.btnNext);


        gameContinue();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userAnswer = Integer.parseInt(edAnswer.getText().toString());

                pauseTimer();

                if (userAnswer == realAnswer){

                    userScore += 10;
                    txtScoreNum.setText("" + userScore);
                    txtQuestion.setText("Congratulations your answer is true!");

                } else  {

                    userLife -= 1;
                    txtLifeNum.setText("" + userLife);
                    txtQuestion.setText("Wrong answer!");


                }


            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edAnswer.setText("");

                resetTimer();

                if (userLife <= 0){
                    Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PlayGameMulti.this, Result.class);
                    intent.putExtra("score",userScore);
                    startActivity(intent);
                    finish();
                }
                else {
                    gameContinue();
                }
            }
        });
    }

    public void gameContinue (){

        num1 = random.nextInt(20);
        num2 = random.nextInt(20);

        realAnswer = num1 * num2;

        txtQuestion.setText(num1 + " * " + num2);

        startTimer();
    }

    public void startTimer(){
        timer = new CountDownTimer(left_time_in_mils,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                left_time_in_mils = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

                timer_running = false;
                pauseTimer();
                resetTimer();
                updateTimer();

                userLife -= 1;
                txtLifeNum.setText("" + userLife);
                txtQuestion.setText("Sorry, time is up!");
            }
        }.start();

        timer_running = true;
    }

    public void updateTimer(){

        int sec = (int) (left_time_in_mils / 1000 ) % 60;
        String time_left = String.format(Locale.getDefault() ,"%02d",sec);
        txtTimeNum.setText(time_left);

    }

    public void pauseTimer(){

        timer.cancel();
        timer_running = false;
    }
    public void resetTimer (){

        left_time_in_mils = START_TIMER_IN_MILS;
        updateTimer();
    }

}