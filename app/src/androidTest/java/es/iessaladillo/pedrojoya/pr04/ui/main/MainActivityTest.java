package es.iessaladillo.pedrojoya.pr04.ui.main;


import android.content.Intent;
import android.graphics.Typeface;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;
import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.data.local.Database;
import es.iessaladillo.pedrojoya.pr04.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr04.ui.avatar.AvatarActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static es.iessaladillo.pedrojoya.pr04.utils.Matchers.withBoldTypeface;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public final IntentsTestRule<MainActivity> testRule = new IntentsTestRule<>(MainActivity.class);

    // Initial state.

    @Test
    public void shouldNameHasFocusInitially() {
        onView(withId(R.id.txtName)).check(matches(hasFocus()));
    }

    @Test
    public void shouldLblNameBeBoldInitially() {
        onView(withId(R.id.lblName)).check(
                matches(withBoldTypeface(Typeface.DEFAULT_BOLD.isBold())));
    }

    @Test
    public void shouldAvatarHasDefaultOneInitially() {
        Avatar avatar = Database.getInstance().getDefaultAvatar();
        onView(withId(R.id.imgAvatar)).check(
                matches(withTagValue(equalTo(avatar.getImageResId()))));
        onView(withId(R.id.lblAvatar)).check(matches(withText(avatar.getName())));
    }

    // TextView gets bold when editText gets focus.

    // We need to make the view visible, so we click first.

    @Test
    public void shouldNameBeBoldTypefaceWhenFocus() {
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.lblName)).check(
                matches(withBoldTypeface(Typeface.DEFAULT_BOLD.isBold())));
    }

    @Test
    public void shouldEmailBeBoldTypefaceWhenFocus() {
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.lblEmail)).check(
                matches(withBoldTypeface(Typeface.DEFAULT_BOLD.isBold())));
    }

    @Test
    public void shouldPhonenumberBeBoldTypefaceWhenFocus() {
        onView(withId(R.id.txtPhonenumber)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.lblPhonenumber)).check(
                matches(withBoldTypeface(Typeface.DEFAULT_BOLD.isBold())));
    }

    @Test
    public void shouldAddressBeBoldTypefaceWhenFocus() {
        onView(withId(R.id.txtAddress)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.lblAddress)).check(
                matches(withBoldTypeface(Typeface.DEFAULT_BOLD.isBold())));
    }

    @Test
    public void shouldWebBeBoldTypefaceWhenFocus() {
        onView(withId(R.id.txtWeb)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.lblWeb)).check(matches(withBoldTypeface(true)));
    }

    // TextView is not bold when editText loses focus.

    @Test
    public void shouldNameLabelBeDefaultTypefaceWhenNoFocus() {
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.lblName)).check(matches(withBoldTypeface(Typeface.DEFAULT.isBold())));
    }

    @Test
    public void shouldEmailLabelBeDefaultTypefaceWhenNoFocus() {
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.lblEmail)).check(matches(withBoldTypeface(Typeface.DEFAULT.isBold())));
    }

    @Test
    public void shouldPhonenumberLabelBeDefaultTypefaceWhenNoFocus() {
        onView(withId(R.id.txtPhonenumber)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.lblPhonenumber)).check(
                matches(withBoldTypeface(Typeface.DEFAULT.isBold())));
    }

    @Test
    public void shouldAddressLabelBeDefaultTypefaceWhenNoFocus() {
        onView(withId(R.id.txtAddress)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.lblAddress)).check(matches(withBoldTypeface(Typeface.DEFAULT.isBold())));
    }

    @Test
    public void shouldWebLabelBeDefaultTypefaceWhenNoFocus() {
        onView(withId(R.id.txtWeb)).perform(click(), closeSoftKeyboard());
        // We need to close the keyboard so lblWeb is visible when checking.
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard());
        onView(withId(R.id.lblWeb)).check(matches(withBoldTypeface(Typeface.DEFAULT.isBold())));
    }

    // ImageView should get disabled when EditText content is invalid.

    @Test
    public void shouldEmailIconBeDisabledWhenInvalidData() {
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard(), replaceText("test"));
        onView(withId(R.id.imgEmail)).check(matches(not(isEnabled())));
    }

    @Test
    public void shouldPhonenumberIconBeDisabledWhenInvalidData() {
        onView(withId(R.id.txtPhonenumber)).perform(click(), closeSoftKeyboard(), replaceText("1"));
        onView(withId(R.id.imgPhonenumber)).check(matches(not(isEnabled())));
    }

    @Test
    public void shouldAddressIconBeDisabledWhenInvalidData() {
        onView(withId(R.id.txtAddress)).perform(click(), closeSoftKeyboard(), replaceText(""));
        onView(withId(R.id.imgAddress)).check(matches(not(isEnabled())));
    }

    @Test
    public void shouldWebIconBeDisabledWhenInvalidData() {
        onView(withId(R.id.txtWeb)).perform(click(), closeSoftKeyboard(), replaceText("test"));
        onView(withId(R.id.imgWeb)).check(matches(not(isEnabled())));
    }

    // ImageView should get enabled when EditText content is valid.

    @Test
    public void shouldEmailIconBeEnabledWhenValidData() {
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard(),
                replaceText("test@test.com"));
        onView(withId(R.id.imgEmail)).check(matches(isEnabled()));
    }

    @Test
    public void shouldPhonenumberIconBeEnabledWhenValidData() {
        onView(withId(R.id.txtPhonenumber)).perform(click(), closeSoftKeyboard(),
                replaceText("666666666"));
        onView(withId(R.id.imgPhonenumber)).check(matches(isEnabled()));
    }

    @Test
    public void shouldAddressIconBeEnabledWhenValidData() {
        onView(withId(R.id.txtAddress)).perform(click(), closeSoftKeyboard(), replaceText("test"));
        onView(withId(R.id.imgAddress)).check(matches(isEnabled()));
    }

    @Test
    public void shouldWebIconBeEnabledWhenValidData() {
        onView(withId(R.id.txtWeb)).perform(click(), closeSoftKeyboard(),
                replaceText("http://www.test.com"));
        onView(withId(R.id.imgWeb)).check(matches(isEnabled()));
    }

    // EditText should show error when content is invalid.

    @Test
    public void shouldNameEditTextShowErrorWhenInvalidData() {
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard(), replaceText(""));
        onView(withId(R.id.txtName)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
    }

    @Test
    public void shouldEmailEditTextShowErrorWhenInvalidData() {
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard(), replaceText("test"));
        onView(withId(R.id.txtEmail)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
    }

    @Test
    public void shouldPhonenumberEditTextShowErrorWhenInvalidData() {
        onView(withId(R.id.txtPhonenumber)).perform(click(), closeSoftKeyboard(), replaceText("1"));
        onView(withId(R.id.txtPhonenumber)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
    }

    @Test
    public void shouldAddressEditTextShowErrorWhenInvalidData() {
        onView(withId(R.id.txtAddress)).perform(click(), closeSoftKeyboard(), replaceText(""));
        onView(withId(R.id.txtAddress)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
    }

    @Test
    public void shouldWebEditTextShowErrorWhenInvalidData() {
        onView(withId(R.id.txtWeb)).perform(click(), closeSoftKeyboard(), replaceText("test"));
        onView(withId(R.id.txtWeb)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
    }

    // EditText should show no error when content is valid.

    @Test
    public void shouldNameEditTextShowNoErrorWhenValidData() {
        onView(withId(R.id.txtName)).perform(click(), closeSoftKeyboard(), replaceText("test"));
        onView(withId(R.id.txtName)).check(matches(hasErrorText(isEmptyOrNullString())));
    }

    @Test
    public void shouldEmailEditTextShowNoErrorWhenValidData() {
        onView(withId(R.id.txtEmail)).perform(click(), closeSoftKeyboard(),
                replaceText("test@test.com"));
        onView(withId(R.id.txtEmail)).check(matches(hasErrorText(isEmptyOrNullString())));
    }

    @Test
    public void shouldPhonenumberEditTextShowNoErrorWhenValidData() {
        onView(withId(R.id.txtPhonenumber)).perform(click(), closeSoftKeyboard(),
                replaceText("666666666"));
        onView(withId(R.id.txtPhonenumber)).check(matches(hasErrorText(isEmptyOrNullString())));
    }

    @Test
    public void shouldAddressEditTextShowNoErrorWhenValidData() {
        onView(withId(R.id.txtAddress)).perform(click(), closeSoftKeyboard(), replaceText("test"));
        onView(withId(R.id.txtAddress)).check(matches(hasErrorText(isEmptyOrNullString())));
    }

    @Test
    public void shouldWebEditTextShowNoErrorWhenValidData() {
        onView(withId(R.id.txtWeb)).perform(click(), closeSoftKeyboard(),
                replaceText("http://www.test.com"));
        onView(withId(R.id.txtWeb)).check(matches(hasErrorText(isEmptyOrNullString())));
    }

    // ImageView should be enabled initially.

    @Test
    public void shouldEmailIconBeEnabledInitially() {
        onView(withId(R.id.imgEmail)).check(matches(isEnabled()));
    }

    @Test
    public void shouldPhonenumberIconBeEnabledInitially() {
        onView(withId(R.id.imgPhonenumber)).check(matches(isEnabled()));
    }

    @Test
    public void shouldAddressIconBeEnabledInitially() {
        onView(withId(R.id.imgAddress)).check(matches(isEnabled()));
    }

    @Test
    public void shouldWebIconBeEnabledInitially() {
        onView(withId(R.id.imgWeb)).check(matches(isEnabled()));
    }

    // Snackbar

    @Test
    public void shouldShowErrorSnackbarWhenSaveMenuItemClickedOnInvalidForm() {
        onView(withId(R.id.mnuSave)).perform(click());
        onView(withId(R.id.imgEmail)).check(matches(not(isEnabled())));
        onView(withId(R.id.imgPhonenumber)).check(matches(not(isEnabled())));
        onView(withId(R.id.imgAddress)).check(matches(not(isEnabled())));
        onView(withId(R.id.imgWeb)).check(matches(not(isEnabled())));
        onView(withId(R.id.txtName)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
        onView(withId(R.id.txtEmail)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
        onView(withId(R.id.txtPhonenumber)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
        onView(withId(R.id.txtAddress)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
        onView(withId(R.id.txtWeb)).check(matches(hasErrorText(
                testRule.getActivity().getString(R.string.main_invalid_data))));
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(
                matches(withText(R.string.main_error_saving)));
    }

    @Test
    public void shouldShowSuccessSnackbarWhenSaveMenuItemClickedOnValidForm() {
        onView(withId(R.id.txtName)).perform(click(), replaceText("test"), closeSoftKeyboard());
        onView(withId(R.id.txtEmail)).perform(click(), replaceText("test@test.com"),
                closeSoftKeyboard());
        onView(withId(R.id.txtPhonenumber)).perform(click(), replaceText("666666666"),
                closeSoftKeyboard());
        onView(withId(R.id.txtAddress)).perform(click(), replaceText("test"),
                closeSoftKeyboard());
        onView(withId(R.id.txtWeb)).perform(click(), replaceText("http://www.test.com"),
                closeSoftKeyboard());
        onView(withId(R.id.mnuSave)).perform(click());
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(
                matches(withText(R.string.main_saved_succesfully)));
    }

    // ImeOptions.

    @Test
    public void shouldNameEditTextGoForwardWhenImeOptionsClicked() {
        onView(withId(R.id.txtName)).perform(click(), typeText("test"), pressImeActionButton());
        onView(withId(R.id.txtEmail)).perform(closeSoftKeyboard()).check(matches(hasFocus()));
    }

    @Test
    public void shouldEmailEditTextGoForwardWhenImeOptionsClicked() {
        onView(withId(R.id.txtEmail)).perform(click(), typeText("test"), pressImeActionButton());
        onView(withId(R.id.txtPhonenumber)).perform(closeSoftKeyboard()).check(matches(hasFocus()));
    }

    @Test
    public void shouldPhonenumberEditTextGoForwardWhenImeOptionsClicked() {
        onView(withId(R.id.txtPhonenumber)).perform(click(), typeText("66666666"),
                pressImeActionButton());
        onView(withId(R.id.txtAddress)).check(matches(hasFocus()));
    }

    @Test
    public void shouldAddressEditTextGoForwardWhenImeOptionsClicked() {
        onView(withId(R.id.txtAddress)).perform(click(), typeText("test"), pressImeActionButton());
        onView(withId(R.id.txtWeb)).perform(closeSoftKeyboard()).check(matches(hasFocus()));
    }


    @Test
    public void shouldWebEditTextSaveWhenImeOptionsClicked() {
        onView(withId(R.id.txtWeb)).perform(click(), typeText("test"), pressImeActionButton(), closeSoftKeyboard());
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(
                matches(withText(R.string.main_error_saving)));
    }

    // Implicit intents

    @Test
    public void shouldEmailSendIntent() {
        onView(withId(R.id.txtEmail)).perform(typeText("baldomero@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.imgEmail)).perform(click());
        intended(Matchers.allOf(hasAction(Intent.ACTION_SENDTO), hasData("mailto:baldomero@gmail.com")));
    }

    @Test
    public void shouldPhonenumberSendIntent() {
        onView(withId(R.id.txtPhonenumber)).perform(typeText("666666666"), closeSoftKeyboard());
        onView(withId(R.id.imgPhonenumber)).perform(click());
        intended(Matchers.allOf(hasAction(Intent.ACTION_DIAL), hasData("tel:666666666")));
    }

    @Test
    public void shouldAddressSendIntent() {
        onView(withId(R.id.txtAddress)).perform(typeText("Avda. Duque de Rivas"),
                closeSoftKeyboard());
        onView(withId(R.id.imgAddress)).perform(click());
        intended(Matchers.allOf(hasAction(Intent.ACTION_VIEW), hasData("geo:0,0?q=Avda. Duque de Rivas")));
    }

    @Test
    public void shouldWebSendIntent() {
        ViewInteraction txtWeb = onView(withId(R.id.txtWeb));
        txtWeb.perform(replaceText("http://www.iessaladillo.es"), closeSoftKeyboard());
        onView(withId(R.id.imgWeb)).perform(click());
        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData("http://www.iessaladillo.es")));
    }

    // Explicit Intent

    @Test
    public void shouldAvatarSendIntent() {
        onView(withId(R.id.imgAvatar)).perform(click());
        // Check sending intent
        intended(allOf(hasComponent(AvatarActivity.class.getName()), hasExtra("EXTRA_AVATAR",
                Database.getInstance().queryAvatar(1))));
        // Perform click on Avatar activity to send result and finish.
        onView(withId(R.id.imgAvatar2)).perform(click());
        // Check result set to views.
        onView(withId(R.id.imgAvatar)).check(matches(withTagValue(equalTo(R.drawable.cat2))));
        onView(withId(R.id.lblAvatar)).check(
                matches(withText(testRule.getActivity().getString(R.string.avatar2_name))));
    }

}
