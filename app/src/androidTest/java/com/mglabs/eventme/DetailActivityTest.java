package com.mglabs.eventme;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class DetailActivityTest {
    @Rule
    public ActivityTestRule<DetailActivity> activityRule = new ActivityTestRule<>(
            DetailActivity.class, true, false);  //true perche' lanciamo il test
    //con un touch   e false perche' non vogliamo il lancio automatico in quanto abbiamo bisogno di
    //passare un intent con l'id dell'evento.


    @Test
    public void eventNotFound() {
        activityRule.launchActivity(null);

        onView(withId(R.id.title_detail))
                .check(matches(withText(R.string.event_not_found)));

    }


}