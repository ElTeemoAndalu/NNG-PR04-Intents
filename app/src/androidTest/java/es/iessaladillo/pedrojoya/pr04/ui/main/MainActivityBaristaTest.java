package es.iessaladillo.pedrojoya.pr04.ui.main;


import android.graphics.Typeface;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.data.local.Database;
import es.iessaladillo.pedrojoya.pr04.data.local.model.Avatar;

import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertDisabled;
import static com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertEnabled;
import static com.schibsted.spain.barista.assertion.BaristaErrorAssertions.assertError;
import static com.schibsted.spain.barista.assertion.BaristaFocusedAssertions.assertFocused;
import static com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertContains;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotContains;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo;
import static com.schibsted.spain.barista.interaction.BaristaKeyboardInteractions.closeKeyboard;
import static com.schibsted.spain.barista.interaction.BaristaKeyboardInteractions.pressImeActionButton;
import static com.schibsted.spain.barista.interaction.BaristaMenuClickInteractions.clickMenu;
import static com.schibsted.spain.barista.internal.AssertAnyKt.assertAny;
import static com.schibsted.spain.barista.internal.matcher.DrawableMatcher.withDrawable;
import static es.iessaladillo.pedrojoya.pr04.utils.Matchers.withBoldTypeface;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityBaristaTest {

    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    // Initial state.

    @Test
    public void shouldNameHasFocusInitially() {
        assertFocused(R.id.txtName);
    }

    @Test
    public void shouldLblNameBeBoldInitially() {
        assertAny(withId(R.id.lblName), withBoldTypeface(Typeface.DEFAULT_BOLD.isBold()));
    }

    @Test
    public void shouldAvatarHasDefaultOneInitially() {
        Avatar avatar = Database.getInstance().getDefaultAvatar();
        assertHasDrawable(R.id.imgAvatar, avatar.getImageResId());
        assertContains(R.id.lblAvatar, avatar.getName());

    }

    // TextView gets bold when editText gets focus.

    // We need to make the view visible, so we click first.

    @Test
    public void shouldNameBeBoldTypefaceWhenFocus() {
        clickOn(R.id.txtName);
        closeKeyboard();
        assertAny(withId(R.id.lblName), withBoldTypeface(Typeface.DEFAULT_BOLD.isBold()));
    }

    @Test
    public void shouldEmailBeBoldTypefaceWhenFocus() {
        clickOn(R.id.txtEmail);
        closeKeyboard();
        assertAny(withId(R.id.lblEmail), withBoldTypeface(Typeface.DEFAULT_BOLD.isBold()));
    }

    @Test
    public void shouldPhonenumberBeBoldTypefaceWhenFocus() {
        clickOn(R.id.txtPhonenumber);
        closeKeyboard();
        assertAny(withId(R.id.lblPhonenumber), withBoldTypeface(Typeface.DEFAULT_BOLD.isBold()));
    }

    @Test
    public void shouldAddressBeBoldTypefaceWhenFocus() {
        clickOn(R.id.txtAddress);
        closeKeyboard();
        assertAny(withId(R.id.lblAddress), withBoldTypeface(Typeface.DEFAULT_BOLD.isBold()));
    }

    @Test
    public void shouldWebBeBoldTypefaceWhenFocus() {
        clickOn(R.id.txtWeb);
        closeKeyboard();
        assertAny(withId(R.id.lblWeb), withBoldTypeface(Typeface.DEFAULT_BOLD.isBold()));
    }

    // TextView is not bold when editText loses focus.

    @Test
    public void shouldNameLabelBeDefaultTypefaceWhenNoFocus() {
        clickOn(R.id.txtName);
        closeKeyboard();
        clickOn(R.id.txtEmail);
        closeKeyboard();
        assertAny(withId(R.id.lblWeb), withBoldTypeface(Typeface.DEFAULT.isBold()));
    }

    @Test
    public void shouldEmailLabelBeDefaultTypefaceWhenNoFocus() {
        clickOn(R.id.txtEmail);
        closeKeyboard();
        clickOn(R.id.txtName);
        closeKeyboard();
        assertAny(withId(R.id.lblEmail), withBoldTypeface(Typeface.DEFAULT.isBold()));
    }

    @Test
    public void shouldPhonenumberLabelBeDefaultTypefaceWhenNoFocus() {
        clickOn(R.id.txtPhonenumber);
        closeKeyboard();
        clickOn(R.id.txtEmail);
        closeKeyboard();
        assertAny(withId(R.id.lblPhonenumber), withBoldTypeface(Typeface.DEFAULT.isBold()));
    }

    @Test
    public void shouldAddressLabelBeDefaultTypefaceWhenNoFocus() {
        clickOn(R.id.txtAddress);
        closeKeyboard();
        clickOn(R.id.txtEmail);
        closeKeyboard();
        assertAny(withId(R.id.lblAddress), withBoldTypeface(Typeface.DEFAULT.isBold()));
    }

    @Test
    public void shouldWebLabelBeDefaultTypefaceWhenNoFocus() {
        clickOn(R.id.txtWeb);
        closeKeyboard();
        clickOn(R.id.txtEmail);
        closeKeyboard();
        assertAny(withId(R.id.lblWeb), withBoldTypeface(Typeface.DEFAULT.isBold()));
    }

    // ImageView should get disabled when EditText content is invalid.

    @Test
    public void shouldEmailIconBeDisabledWhenInvalidData() {
        writeTo(R.id.txtEmail, "test");
        assertDisabled(R.id.imgEmail);
    }

    @Test
    public void shouldPhonenumberIconBeDisabledWhenInvalidData() {
        writeTo(R.id.txtPhonenumber, "1");
        assertDisabled(R.id.imgPhonenumber);
    }

    @Test
    public void shouldAddressIconBeDisabledWhenInvalidData() {
        writeTo(R.id.txtAddress, "");
        assertDisabled(R.id.imgAddress);
    }

    @Test
    public void shouldWebIconBeDisabledWhenInvalidData() {
        writeTo(R.id.txtWeb, "test");
        assertDisabled(R.id.imgWeb);
    }

    // ImageView should get enabled when EditText content is valid.

    @Test
    public void shouldEmailIconBeEnabledWhenValidData() {
        writeTo(R.id.txtEmail, "test@test.com");
        assertEnabled(R.id.imgEmail);
    }

    @Test
    public void shouldPhonenumberIconBeEnabledWhenValidData() {
        writeTo(R.id.txtPhonenumber, "666666666");
        assertEnabled(R.id.imgPhonenumber);
    }

    @Test
    public void shouldAddressIconBeEnabledWhenValidData() {
        writeTo(R.id.txtAddress, "test");
        assertEnabled(R.id.imgAddress);
    }

    @Test
    public void shouldWebIconBeEnabledWhenValidData() {
        writeTo(R.id.txtWeb, "http://www.test.com");
        assertEnabled(R.id.imgWeb);
    }

    // EditText should show error when content is invalid.

    @Test
    public void shouldNameEditTextShowErrorWhenInvalidData() {
        writeTo(R.id.txtName, "");
        assertError(R.id.txtName, R.string.main_invalid_data);
    }

    @Test
    public void shouldEmailEditTextShowErrorWhenInvalidData() {
        writeTo(R.id.txtEmail, "test");
        assertError(R.id.txtEmail, R.string.main_invalid_data);
    }

    @Test
    public void shouldPhonenumberEditTextShowErrorWhenInvalidData() {
        writeTo(R.id.txtPhonenumber, "1");
        assertError(R.id.txtPhonenumber, R.string.main_invalid_data);
    }

    @Test
    public void shouldAddressEditTextShowErrorWhenInvalidData() {
        writeTo(R.id.txtAddress, "");
        assertError(R.id.txtAddress, R.string.main_invalid_data);

    }

    @Test
    public void shouldWebEditTextShowErrorWhenInvalidData() {
        writeTo(R.id.txtWeb, "test");
        assertError(R.id.txtWeb, R.string.main_invalid_data);
    }

    // EditText should show no error when content is valid.

    @Test
    public void shouldNameEditTextShowNoErrorWhenValidData() {
        writeTo(R.id.txtName, "test");
        assertAny(withId(R.id.txtName), hasErrorText(isEmptyOrNullString()));
    }

    @Test
    public void shouldEmailEditTextShowNoErrorWhenValidData() {
        writeTo(R.id.txtEmail, "test@test.com");
        assertAny(withId(R.id.txtEmail), hasErrorText(isEmptyOrNullString()));
    }

    @Test
    public void shouldPhonenumberEditTextShowNoErrorWhenValidData() {
        writeTo(R.id.txtPhonenumber, "666666666");
        assertAny(withId(R.id.txtPhonenumber), hasErrorText(isEmptyOrNullString()));
    }

    @Test
    public void shouldAddressEditTextShowNoErrorWhenValidData() {
        writeTo(R.id.txtAddress, "test");
        assertAny(withId(R.id.txtAddress), hasErrorText(isEmptyOrNullString()));
    }

    @Test
    public void shouldWebEditTextShowNoErrorWhenValidData() {
        writeTo(R.id.txtWeb, "http://www.test.com");
        assertAny(withId(R.id.txtWeb), hasErrorText(isEmptyOrNullString()));
    }

    // ImageView should be enabled initially.

    @Test
    public void shouldEmailIconBeEnabledInitially() {
        assertEnabled(R.id.imgEmail);
    }

    @Test
    public void shouldPhonenumberIconBeEnabledInitially() {
        assertEnabled(R.id.imgPhonenumber);
    }

    @Test
    public void shouldAddressIconBeEnabledInitially() {
        assertEnabled(R.id.imgAddress);
    }

    @Test
    public void shouldWebIconBeEnabledInitially() {
        assertEnabled(R.id.imgWeb);
    }

    // Should change avatar.

    @Test
    public void shouldChangeAvatarWhenAvatarImageViewClicked() {
        Avatar defaultAvatar = Database.getInstance().getDefaultAvatar();
        clickOn(R.id.imgAvatar);
        assertAny(withId(R.id.imgAvatar), not(withDrawable(defaultAvatar.getImageResId())));
        assertNotContains(R.id.lblAvatar, defaultAvatar.getName());
    }

    @Test
    public void shouldChangeAvatarWhenAvatarTextViewClicked() {
        Avatar defaultAvatar = Database.getInstance().getDefaultAvatar();
        clickOn(R.id.lblAvatar);
        assertAny(withId(R.id.imgAvatar), not(withDrawable(defaultAvatar.getImageResId())));
        assertNotContains(R.id.lblAvatar, defaultAvatar.getName());
    }

    // Snackbar

    @Test
    public void shouldShowErrorSnackbarWhenSaveMenuItemClickedOnInvalidForm() {
        clickOn(R.id.mnuSave);
        assertDisabled(R.id.imgEmail);
        assertDisabled(R.id.imgPhonenumber);
        assertDisabled(R.id.imgAddress);
        assertDisabled(R.id.imgWeb);
        assertError(R.id.txtName, R.string.main_invalid_data);
        assertError(R.id.txtEmail, R.string.main_invalid_data);
        assertError(R.id.txtPhonenumber, R.string.main_invalid_data);
        assertError(R.id.txtAddress, R.string.main_invalid_data);
        assertError(R.id.txtWeb, R.string.main_invalid_data);
        assertDisplayed(R.string.main_error_saving);
    }

    @Test
    public void shouldShowSuccessSnackbarWhenSaveMenuItemClickedOnValidForm() {
        writeTo(R.id.txtName, "test");
        writeTo(R.id.txtEmail, "test@test.com");
        writeTo(R.id.txtPhonenumber, "666666666");
        writeTo(R.id.txtAddress, "test");
        writeTo(R.id.txtWeb, "http://www.test.com");
        clickMenu(R.id.mnuSave);
        assertDisplayed(R.string.main_saved_succesfully);
    }

    // ImeOptions.

    @Test
    public void shouldNameEditTextGoForwardWhenImeOptionsClicked() {
        clickOn(R.id.txtName);
        pressImeActionButton();
        assertFocused(R.id.txtEmail);
    }

    @Test
    public void shouldEmailEditTextGoForwardWhenImeOptionsClicked() {
        clickOn(R.id.txtEmail);
        pressImeActionButton();
        assertFocused(R.id.txtPhonenumber);
    }

    @Test
    public void shouldPhonenumberEditTextGoForwardWhenImeOptionsClicked() {
        clickOn(R.id.txtPhonenumber);
        pressImeActionButton();
        assertFocused(R.id.txtAddress);
    }

    @Test
    public void shouldAddressEditTextGoForwardWhenImeOptionsClicked() {
        clickOn(R.id.txtAddress);
        pressImeActionButton();
        assertFocused(R.id.txtWeb);
    }

    @Test
    public void shouldWebEditTextSaveWhenImeOptionsClicked() {
        clickOn(R.id.txtWeb);
        pressImeActionButton();
        assertDisplayed(R.string.main_error_saving);
    }

}
