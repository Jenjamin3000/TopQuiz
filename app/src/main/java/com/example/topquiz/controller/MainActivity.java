package com.example.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingTextView;
    private EditText mNameEditText;
    private Button mPlayButton;

    private Intent gameActivityIntent;

    private User mUser;

    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    //SharedPreferences preferences = getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mGreetingTextView = findViewById(R.id.main_textview_greeting);
        mNameEditText = findViewById(R.id.main_edittext_name);
        mPlayButton = findViewById(R.id.main_button_play);

        mUser = new User();

        mPlayButton.setEnabled(false);

        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPlayButton.setEnabled(!editable.toString().isEmpty());
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);

                mUser.setFirstName(mNameEditText.getText().toString());

                getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE)
                        .edit()
                        .putString("SHARED_PREF_USER_INFO_NAME", mUser.getFirstName())
                        .apply();
            }
        });

        /*String firstName = getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString("SHARED_PREF_USER_INFO", null);
        if(!firstName.isEmpty()) {
            mPlayButton.setText(firstName);
        }*/

    }
}