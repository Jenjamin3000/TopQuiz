package com.example.topquiz.model;

import java.util.Collections;
import java.util.List;

import kotlin.random.Random;

public class QuestionBank {

        private List<Question> mQuestionList;
        private int mNextQuestionIndex;

        public QuestionBank(List<Question> questionList) {
                mQuestionList = questionList;
                Collections.shuffle(mQuestionList);

                mNextQuestionIndex = 0;
        }

        public Question getNextQuestion() {
                return mQuestionList.get(mNextQuestionIndex++);
        }

}
