package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    Button goButton;
    Random rand;
    TextView sumFind;
    TextView scoreTextView;
    TextView result;
    TextView timerText;
    ConstraintLayout sub;

    Button option1;
    Button option2;
    Button option3;
    Button option4;
    Button playAgain;
    int correctB;
    int score;
    int totalScore;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    int maxSec=30;



    public void startGame(View view){
        goButton.setVisibility(View.INVISIBLE);
        playA(findViewById(R.id.timerText));
        sub.setVisibility(View.VISIBLE);
    }
    public void chooseAns(View view){
        if (Integer.toString(correctB+1).equals(view.getTag().toString())) {
            result.setText("Correct!");
            score++;
        }
        else{
            result.setText("Wrong :(");
        }
        totalScore++;
        scoreTextView.setText(""+score+"/"+totalScore);

        newQues();

    }
    public void newQues(){
        int a=rand.nextInt(100)-50;
        int b=rand.nextInt(100)-51;

        sumFind.setText(""+a+"+"+b+"=");

        correctB=rand.nextInt(4);
        answers.clear();

        for(int i=0;i<4;i++){
            if(i==correctB) answers.add(a+b);
            else {
                int wrong=rand.nextInt(200)-100;
                while(wrong==a+b)
                    wrong=rand.nextInt(200)-100;
                answers.add(wrong);
            }

        }
        option1.setText(""+answers.get(0));
        option2.setText(""+answers.get(1));
        option3.setText(""+answers.get(2));
        option4.setText(""+answers.get(3));
    }
    public void playA(View view){
        playAgain.setVisibility(View.INVISIBLE);
        score=0;
        totalScore=0;
        timerText.setText("30s");
        scoreTextView.setText(""+score+"/"+totalScore);
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
        result.setText("");
        new CountDownTimer(maxSec*1000+100,1000){
            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                result.setText("DONE!");
                playAgain.setVisibility(View.VISIBLE);
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
            }
        }.start();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.goButton);
        rand  =new Random();
        sumFind=findViewById(R.id.questionText);
        option1=findViewById(R.id.button1);
        option2=findViewById(R.id.button2);
        option3=findViewById(R.id.button3);
        option4=findViewById(R.id.button4);
        scoreTextView=findViewById(R.id.scoreText);
        result=findViewById(R.id.textView4);
        timerText=findViewById(R.id.timerText);
        playAgain=findViewById(R.id.button7);
        sub=findViewById(R.id.sub);
        goButton.setVisibility(View.VISIBLE);
        sub.setVisibility(View.INVISIBLE);

        newQues();



    }
}