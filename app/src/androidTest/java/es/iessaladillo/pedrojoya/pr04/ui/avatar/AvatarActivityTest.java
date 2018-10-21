package es.iessaladillo.pedrojoya.pr04.ui.avatar;

import android.content.Intent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.data.local.Database;
import es.iessaladillo.pedrojoya.pr04.utils.ResourcesUtils;

import static android.app.Activity.RESULT_OK;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withAlpha;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;

@RunWith(androidx.test.runner.AndroidJUnit4.class)
@LargeTest
public class AvatarActivityTest {

    @Rule
    public final IntentsTestRule<AvatarActivity> testRule = new IntentsTestRule<AvatarActivity>(
            AvatarActivity.class, true, false);

    @Before
    public void setup() {
        testRule.launchActivity(
                new Intent().putExtra("EXTRA_AVATAR", Database.getInstance().getDefaultAvatar()));
    }

    @Test
    public void shouldSelectReceivedAvatar() {
        onView(withId(R.id.imgAvatar1)).check(matches(withAlpha(
                ResourcesUtils.getFloat(testRule.getActivity(), R.dimen.selected_image_alpha))));
    }

    @Test
    public void shouldReturnAvatar1WhenImageView1Clicked() {
        onView(withId(R.id.imgAvatar1)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(1)));
    }

    @Test
    public void shouldReturnAvatar2WhenImageView2Clicked() {
        onView(withId(R.id.imgAvatar2)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(2)));
    }

    @Test
    public void shouldReturnAvatar3WhenImageView3Clicked() {
        onView(withId(R.id.imgAvatar3)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(3)));
    }

    @Test
    public void shouldReturnAvatar4WhenImageView4Clicked() {
        onView(withId(R.id.imgAvatar4)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(4)));
    }

    @Test
    public void shouldReturnAvatar5WhenImageView5Clicked() {
        onView(withId(R.id.imgAvatar5)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(5)));
    }

    @Test
    public void shouldReturnAvatar6WhenImageView6Clicked() {
        onView(withId(R.id.imgAvatar6)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(6)));
    }

    @Test
    public void shouldReturnAvatar1WhenTextView1Clicked() {
        onView(withId(R.id.lblAvatar1)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(1)));
    }

    @Test
    public void shouldReturnAvatar2WhenTextView2Clicked() {
        onView(withId(R.id.lblAvatar2)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(2)));
    }

    @Test
    public void shouldReturnAvatar3WhenTextView3Clicked() {
        onView(withId(R.id.lblAvatar3)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(3)));
    }

    @Test
    public void shouldReturnAvatar4WhenTextView4Clicked() {
        onView(withId(R.id.lblAvatar4)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(4)));
    }

    @Test
    public void shouldReturnAvatar5WhenTextView5Clicked() {
        onView(withId(R.id.lblAvatar5)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(5)));
    }

    @Test
    public void shouldReturnAvatar6WhenTextView6Clicked() {
        onView(withId(R.id.lblAvatar6)).perform(click());
        int resultCode = testRule.getActivityResult().getResultCode();
        Intent intent = testRule.getActivityResult().getResultData();
        assertThat(resultCode, is(RESULT_OK));
        assertThat(intent, hasExtra("EXTRA_AVATAR", Database.getInstance().queryAvatar(6)));
    }

}
