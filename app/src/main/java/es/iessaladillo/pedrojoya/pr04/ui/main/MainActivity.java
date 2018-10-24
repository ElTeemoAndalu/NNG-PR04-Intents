package es.iessaladillo.pedrojoya.pr04.ui.main;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.data.local.Database;
import es.iessaladillo.pedrojoya.pr04.data.local.business.FieldEnabler;
import es.iessaladillo.pedrojoya.pr04.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr04.ui.avatar.AvatarActivity;
import es.iessaladillo.pedrojoya.pr04.utils.SnackBarUtils;
import es.iessaladillo.pedrojoya.pr04.utils.ValidationUtils;

@SuppressWarnings("WeakerAccess")
public class MainActivity extends AppCompatActivity {

    private ImageView imgAvatar;
    private TextView lblAvatar;
    private final int EDITTEXT_QUANTITY = 5;
    private final int NAME = 0;
    private final int EMAIL = 1;
    private final int PHONE = 2;
    private final int ADDRESS = 3;
    private final int WEB = 4;
    private final int RC_AVATAR = 3000;
    private final EditText[] txtFields = new EditText[EDITTEXT_QUANTITY];
    private final TextView[] lblFields = new TextView[EDITTEXT_QUANTITY];
    private String errorMsg;
    private ImageView imgEmail, imgPhone, imgAddress, imgWeb;
    private Avatar profileAvatar;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        //Initializations
        errorMsg = getString(R.string.main_invalid_data);

        imgAvatar = ActivityCompat.requireViewById(this, R.id.imgAvatar);
        lblAvatar = ActivityCompat.requireViewById(this, R.id.lblAvatar);

        txtFields[NAME] = ActivityCompat.requireViewById(this, R.id.txtName);
        txtFields[EMAIL] = ActivityCompat.requireViewById(this, R.id.txtEmail);
        txtFields[PHONE] = ActivityCompat.requireViewById(this, R.id.txtPhonenumber);
        txtFields[ADDRESS] = ActivityCompat.requireViewById(this, R.id.txtAddress);
        txtFields[WEB] = ActivityCompat.requireViewById(this, R.id.txtWeb);

        lblFields[NAME] = ActivityCompat.requireViewById(this, R.id.lblName);
        lblFields[EMAIL] = ActivityCompat.requireViewById(this, R.id.lblEmail);
        lblFields[PHONE] = ActivityCompat.requireViewById(this, R.id.lblPhonenumber);
        lblFields[ADDRESS] = ActivityCompat.requireViewById(this, R.id.lblAddress);
        lblFields[WEB] = ActivityCompat.requireViewById(this, R.id.lblWeb);

        imgEmail = ActivityCompat.requireViewById(this, R.id.imgEmail);
        imgPhone = ActivityCompat.requireViewById(this, R.id.imgPhonenumber);
        imgAddress = ActivityCompat.requireViewById(this, R.id.imgAddress);
        imgWeb = ActivityCompat.requireViewById(this, R.id.imgWeb);

        database = Database.getInstance();
        profileAvatar = database.getDefaultAvatar();
        setProfileAvatar();

        //Listeners
        imgAvatar.setOnClickListener(v -> selectAvatarImg());

        txtFields[WEB].setOnEditorActionListener((v, actionId, event) -> {
            save();
            return false;
        });


        for (EditText txt : txtFields) {
            txt.setOnFocusChangeListener(this::changeLblStyle);
        }

        imgEmail.setOnClickListener(v -> sendEmail());
        imgPhone.setOnClickListener(v -> callPhone());
        imgAddress.setOnClickListener(v -> showAddress());
        imgWeb.setOnClickListener(v -> searchURL());

        //These listeners are split due to them not working on a separate class
        txtFields[NAME].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FieldEnabler.enableOrDisableFieldState(TextUtils.isEmpty(s), txtFields[NAME], null, lblFields[NAME], errorMsg);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtFields[EMAIL].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FieldEnabler.enableOrDisableFieldState(ValidationUtils.isValidEmail(s.toString()), txtFields[EMAIL], imgEmail, lblFields[EMAIL], errorMsg);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtFields[PHONE].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FieldEnabler.enableOrDisableFieldState(ValidationUtils.isValidPhone(s.toString()), txtFields[PHONE], imgPhone, lblFields[PHONE], errorMsg);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtFields[ADDRESS].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FieldEnabler.enableOrDisableFieldState(TextUtils.isEmpty(s), txtFields[ADDRESS], imgAddress, lblFields[ADDRESS], errorMsg);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtFields[WEB].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FieldEnabler.enableOrDisableFieldState(ValidationUtils.isValidUrl(s.toString()), txtFields[WEB], imgWeb, lblFields[WEB], errorMsg);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    private void setProfileAvatar() {
        imgAvatar.setTag(profileAvatar.getImageResId());
        imgAvatar.setImageResource(profileAvatar.getImageResId());
        lblAvatar.setText(profileAvatar.getName());
    }

    private void searchURL() {
        String url = txtFields[WEB].getText().toString();
        Intent lookForAddress = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startImplicitIntent(lookForAddress);
    }

    private void showAddress() {
        String address = String.format("geo:0,0?q=%s", txtFields[ADDRESS].getText().toString());
        Intent showMap = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
        startImplicitIntent(showMap);
    }

    private void callPhone() {
        String phoneWritten = txtFields[PHONE].getText().toString();
        Intent callThisPhone = new Intent(Intent.ACTION_DIAL, Uri.parse(String.format("tel:%s", phoneWritten)));
        startImplicitIntent(callThisPhone);
    }

    private void sendEmail() {
        String emailWritten = txtFields[EMAIL].getText().toString();
        Intent sendToEmail = new Intent(Intent.ACTION_SENDTO, Uri.parse(String.format("mailto:%s", emailWritten)));
        startImplicitIntent(sendToEmail);
    }

    private void startImplicitIntent(Intent implicitIntent) {
        try {
            startActivity(implicitIntent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    //This method changes the font style of the TextViews associated to the EditTexts, from default to bold and vice versa.
    //It depends on its current state.
    private void changeLblStyle(View v, boolean hasFocus) {
        if (hasFocus) {
            for (int i = 0; i < txtFields.length; i++) {
                if (txtFields[i].getId() == v.getId()) {
                    lblFields[i].setTypeface(Typeface.DEFAULT_BOLD);
                }
            }
        } else {
            for (int i = 0; i < txtFields.length; i++) {
                if (txtFields[i].getId() == v.getId()) {
                    lblFields[i].setTypeface(Typeface.DEFAULT);
                }
            }
        }
    }

    //It changes, including the picture and the name, the current avatar for one chosen by the user in another activity.
    private void selectAvatarImg() {
        AvatarActivity.startForResult(this, RC_AVATAR, profileAvatar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == RC_AVATAR) {
            getSelectedAvatar(data);
        }
    }

    private void getSelectedAvatar(Intent receivedIntent) {
        if (receivedIntent != null && receivedIntent.hasExtra(AvatarActivity.EXTRA_AVATAR)) {
            profileAvatar = receivedIntent.getParcelableExtra(AvatarActivity.EXTRA_AVATAR);
        }

        setProfileAvatar();
    }

    // DO NOT TOUCH
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // DO NOT TOUCH
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if form is valid or not and shows a Snackbar accordingly
     **/

    //Save checks all EditText in the activity, if these fufill the patterns required, the user is shown a snackbar
    //showing a success message, if not, the views that fail the requirements are show an error and a snakbar is also shown
    //with a message of failure
    private void save() {
        if (validateAll()) {
            SnackBarUtils.showSnackBar(lblFields[NAME], getString(R.string.main_saved_succesfully));
        } else {
            SnackBarUtils.showSnackBar(lblFields[NAME], getString(R.string.main_error_saving));
        }


    }

    //It checks if all the edittexts pass the requirements and shows errors if thy do not
    private boolean validateAll() {
        boolean checkName = TextUtils.isEmpty(txtFields[NAME].getText().toString());
        boolean checkEmail = ValidationUtils.isValidEmail(txtFields[EMAIL].getText().toString());
        boolean checkPhone = ValidationUtils.isValidPhone(txtFields[PHONE].getText().toString());
        boolean checkAddress = TextUtils.isEmpty(txtFields[ADDRESS].getText().toString());
        boolean checkeb = ValidationUtils.isValidUrl(txtFields[WEB].getText().toString());

        FieldEnabler.enableOrDisableFieldState(checkName, txtFields[NAME], null, lblFields[NAME], errorMsg);
        FieldEnabler.enableOrDisableFieldState(checkEmail, txtFields[EMAIL], imgEmail, lblFields[EMAIL], errorMsg);
        FieldEnabler.enableOrDisableFieldState(checkPhone, txtFields[PHONE], imgPhone, lblFields[PHONE], errorMsg);
        FieldEnabler.enableOrDisableFieldState(checkAddress, txtFields[ADDRESS], imgAddress, lblFields[ADDRESS], errorMsg);
        FieldEnabler.enableOrDisableFieldState(checkeb, txtFields[WEB], imgWeb, lblFields[WEB], errorMsg);

        for (int i = 0; i < lblFields[0].length(); i++) {
            if (!lblFields[i].isEnabled()) {
                return false;
            }
        }
        return true;
    }

}
