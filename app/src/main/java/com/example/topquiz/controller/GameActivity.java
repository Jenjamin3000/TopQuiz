package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionTextview;
    private Button mAnswer1Button;
    private Button mAnswer2Button;
    private Button mAnswer3Button;
    private Button mAnswer4Button;

    private QuestionBank mQuestionBank;

    private int mCorrectAnswerIndex;

    private int mRemainingQuestionCount;

    private int mScore;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionTextview = findViewById(R.id.game_activity_textview_question);
        mAnswer1Button = findViewById(R.id.game_activity_button_1);
        mAnswer2Button = findViewById(R.id.game_activity_button_2);
        mAnswer3Button = findViewById(R.id.game_activity_button_3);
        mAnswer4Button = findViewById(R.id.game_activity_button_4);

        mAnswer1Button.setOnClickListener(this);
        mAnswer2Button.setOnClickListener(this);
        mAnswer3Button.setOnClickListener(this);
        mAnswer4Button.setOnClickListener(this);

        mQuestionBank = generateQuestionBank();

        displayQuestion(mQuestionBank.getNextQuestion());

        mRemainingQuestionCount = 4;

        mScore = 0;


    }

    public QuestionBank generateQuestionBank(){
        List<Question> questionList = new ArrayList<>();

        questionList.add(
                new Question(
                    "Who is the creator of Android?",
                    Arrays.asList(
                            "Andy Rubin",
                            "Steve Wozniak",
                            "Jake Wharton",
                            "Paul Smith"
                    ),
                        0
                )
        );

        questionList.add(
                new Question(
                    "When did the first man land on the moon?",
                    Arrays.asList(
                            "1958",
                            "1962",
                            "1967",
                            "1969"
                    ),
                    3
        ));

        questionList.add(
                new Question(
                    "What is the house number of The Simpsons?",
                    Arrays.asList(
                            "42",
                            "101",
                            "666",
                            "742"
                    ),
                    3
                )
        );

        questionList.add(
                new Question(
                    "What is this?",
                    Arrays.asList(
                            "A house",
                            "A bench",
                            "A question",
                            "An answer"
                    ),
                    2
                )
        );

        questionList.add(
                new Question(
                    "Reading is good but ... is better.",
                    Arrays.asList(
                            "falling",
                            "living",
                            "eating his own arm",
                            "answering this"
                    ),
                    1
                )
        );

        return new QuestionBank(questionList);
    }

    private void displayQuestion(final Question question){
        mQuestionTextview.setText(question.getQuestion());

        List<String> choiceList = question.getChoiceList();
        mAnswer1Button.setText(choiceList.get(0));
        mAnswer2Button.setText(choiceList.get(1));
        mAnswer3Button.setText(choiceList.get(2));
        mAnswer4Button.setText(choiceList.get(3));

        mCorrectAnswerIndex = question.getAnswerIndex();
    }

    @Override
    public void onClick(View view) {
        int index;

        if(view == mAnswer1Button){
            index = 0;
        } else if(view == mAnswer2Button){
            index = 1;
        } else if(view == mAnswer3Button){
            index = 2;
        } else if(view == mAnswer4Button){
            index = 3;
        } else {
            throw new IllegalStateException("Connais pas ce bouton... : "+view);
        }
        if(index == mCorrectAnswerIndex) {
            mScore++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "False!", Toast.LENGTH_SHORT).show();
        }

        mRemainingQuestionCount--;

        if (mRemainingQuestionCount > 0) {
            displayQuestion(mQuestionBank.getNextQuestion());
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Well done!")
                    .setMessage("Your score is " + mScore)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    })
                    .create()
                    .show();        }


    }
}