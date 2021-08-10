package com.example.aptitudeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aptitudeapp.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public class General_Activity extends AppCompatActivity {

    private TextView tvQuestion,tvScore,tvQuestionNo,tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4;
    private Button btnNext;

    int totalQuestions;
    int qCounter=0;
    int score;

    ColorStateList dfRbColor;
    boolean answered;

    CountDownTimer countDownTimer;

    private com.example.aptitudeapp.QuestionModel currentQuestion;

    private List<com.example.aptitudeapp.QuestionModel> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        questionsList=new ArrayList<>();
        tvQuestion=findViewById(R.id.textQuestion);
        tvScore=findViewById(R.id.textScore);
        tvQuestionNo=findViewById(R.id.textQuestionNO);
        tvTimer=findViewById(R.id.textTimer);

        radioGroup=findViewById(R.id.radioGroup);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rb4=findViewById(R.id.rb4);
        btnNext=findViewById(R.id.btnNext);

        dfRbColor=rb1.getTextColors();

        addQuestions();
        totalQuestions=questionsList.size();
        showNextQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answered==false){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer();
                        countDownTimer.cancel();

                    }else{
                        Toast.makeText(General_Activity.this, "Please Select an option", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    if(btnNext.getText().toString().equals("Finish")){
                        Bundle bundle = new Bundle();
                        bundle.putInt("Score",score);
                        startActivity(new Intent(General_Activity.this,SignOutActivity.class).putExtras(bundle));
                    }else{
                        showNextQuestion();
                    }

                }
            }
        });

    }

    private void checkAnswer() {
        answered=true;
        RadioButton rbSelected=findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo=radioGroup.indexOfChild(rbSelected)+1;
        if(answerNo==currentQuestion.getCorrectAnsNo()){
            score++;
            tvScore.setText("Score :"+score);
        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        switch (currentQuestion.getCorrectAnsNo()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
        }
        if(qCounter<totalQuestions){
            btnNext.setText("Next");
        }else {
            btnNext.setText("Finish");
        }
    }

    private void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        rb4.setTextColor(dfRbColor);

        if (qCounter<totalQuestions){
            timer();
            currentQuestion=questionsList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            qCounter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question: "+qCounter+"/"+totalQuestions);
            answered=false;
        }
        else {
            finish();
        }
    }

    private void timer() {
        countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("00:" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                showNextQuestion();
            }
        }.start();
    }


    private void addQuestions() {
        questionsList.add(new QuestionModel("\t\n" +
                "A train running at the speed of 60 km/hr crosses a pole in 9 seconds. What is the length of the train?","120 meters","55 meters", "180 meters", "150 meters",4));
        questionsList.add(new QuestionModel("\t\n" +
                "The angle of elevation of a ladder leaning against a wall is 60° and the foot of the ladder is 4.6 m away from the wall. The length of the ladder is:","9.2m","3.1m", "6.1m", "4.2m",1));
        questionsList.add(new QuestionModel("\t\n" +
                "It was Sunday on Jan 1, 2006. What was the day of the week Jan 1, 2010?","Tuesday","Friday", "Monday", "Sunday",2));
        questionsList.add(new QuestionModel("The average of 20 numbers is zero. Of them, at the most, how many may be greater than zero?","18","7", "19", "4",3));
        questionsList.add(new QuestionModel("The sum of ages of 5 children born at the intervals of 3 years each is 50 years. What is the age of the youngest child?","4 years","10 years", "6 years", "All Of The Above",1));
        questionsList.add(new QuestionModel("\t\n" +
                "If a - b = 3 and a2 + b2 = 29, find the value of ab.","10","8", "5", "None Of The Above",1));
        questionsList.add(new QuestionModel("\t\n" + "Two numbers are respectively 20% and 50% more than a third number. The ratio of the two numbers is:","4:8","8:4", "2:5", "4:5",4));
        questionsList.add(new QuestionModel("\t\n" +
                "The H.C.F. of two numbers is 23 and the other two factors of their L.C.M. are 13 and 14. The larger of the two numbers is:","571","146", "322", "248",3));
        questionsList.add(new QuestionModel("\t\n" +
                "\t\n" +
                "Three times the first of three consecutive odd integers is 3 more than twice the third. The third integer is:","10","28", "15", "21",3));
        questionsList.add(new QuestionModel("\t\n" +
                "A bag contains 2 red, 3 green and 2 blue balls. Two balls are drawn at random. What is the probability that none of the balls drawn is blue?","10/21","8/7", "5/6", "15/7",1));
    }
}