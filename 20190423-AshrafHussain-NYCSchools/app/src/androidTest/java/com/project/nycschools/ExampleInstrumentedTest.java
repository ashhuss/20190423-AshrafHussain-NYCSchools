package com.project.nycschools;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.project.nycschools.schoolDetail.SatScoreActivity;
import com.project.nycschools.schoolList.view.SchoolListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<SchoolListActivity> activityTestRule =
            new ActivityTestRule<>(SchoolListActivity.class);
    @Rule
    public ActivityTestRule<SatScoreActivity> satScoreActivityActivityTestRule =
            new ActivityTestRule<>(SatScoreActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.project.nycschools", appContext.getPackageName());
    }

    @Test
    public void onSchoolListRecyclerViewClicked() {
        onView(withId(R.id.rv_school_list)).perform(click());
    }

    public static void pressBack() {
        onView(isRoot()).perform(ViewActions.pressBack());
    }

}
