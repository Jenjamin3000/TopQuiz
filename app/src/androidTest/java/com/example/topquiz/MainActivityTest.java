package com.example.topquiz;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.topquiz.controller.GameActivity;
import com.example.topquiz.controller.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import java.util.regex.Matcher;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static String userName = "Roger Federer";

    @Rule
    public ActivityScenarioRule<MainActivity> testRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void CorrectName(){
        onView(ViewMatchers.withId(R.id.main_edittext_name)).perform(ViewActions.typeText(userName));
        onView(ViewMatchers.withId(R.id.main_edittext_name)).check(matches(withText(userName)));
    }

    @Test
    public void ChangeActivity(){
        Intents.init();
        onView(ViewMatchers.withId(R.id.main_edittext_name)).perform(ViewActions.typeText(userName));
        onView(ViewMatchers.withId(R.id.main_button_play)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(GameActivity.class.getName()));

        Intents.release();


    }
}
