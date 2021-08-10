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

public class Logical_Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_verbal);

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
                        Toast.makeText(Logical_Activity.this, "Please Select an option", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    if(btnNext.getText().toString().equals("Finish")){
                        Bundle bundle = new Bundle();
                        bundle.putInt("Score",score);
                        startActivity(new Intent(Logical_Activity.this,SignOutActivity.class).putExtras(bundle));
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
                System.out.println(millisUntilFinished);
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
                "Look at this series: 7, 10, 8, 11, 9, 12, ... What number should come next?","7","10", "12", "13",2));
        questionsList.add(new QuestionModel("Which word does NOT belong with the others?","Tyre","Steering Wheel", "Engine", "Car",4));
        questionsList.add(new QuestionModel("\t\n" +
                "Odometer is to mileage as compass is to","Direction","Speed", "Needle", "Ship",1));
        questionsList.add(new QuestionModel("\t\n" +
                "SCD, TEF, UGH, __, WKL","CMN","UJI", "VIJ", "IDT",3));
        questionsList.add(new QuestionModel("Marathon is to race as hibernation is to","Winter","Bear", "Dream", "Sleep",4));
        questionsList.add(new QuestionModel("Look at this series: 14, 28, 20, 40, 32, 64, ... What number should come next?","52","56", "96", "128",2));
        questionsList.add(new QuestionModel("choose the word that is a necessary part of the word\"Dessert\"" ,"Cactus","Arid", "Oasis", "Camel",2));
        questionsList.add(new QuestionModel("Look at this series: 544, 509, 474, 439, ... What number should come next?","404","420", "428", "414",1));
        questionsList.add(new QuestionModel("\t\n" +
                "CMM, EOO, GQQ, ___, KUU","GRR","GSS", "ISS", "ITT",3));
        questionsList.add(new QuestionModel("\t\n" +
                "Look at this series: 53, 53, 40, 40, 27, 27, ... What number should come next?","12","14", "27", "16",2));
    }
}