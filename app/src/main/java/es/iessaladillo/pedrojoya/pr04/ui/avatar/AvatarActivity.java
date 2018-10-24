package es.iessaladillo.pedrojoya.pr04.ui.avatar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.data.local.Database;
import es.iessaladillo.pedrojoya.pr04.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr04.utils.ResourcesUtils;

public class AvatarActivity extends AppCompatActivity {

    public static final String EXTRA_AVATAR = "EXTRA_AVATAR";
    private Avatar prvlySelectedAvatar, selectedAvatar;
    private Database database;
    private ArrayList<ImageView> imagesList;
    private ArrayList<TextView> namesList;
    private final int LBL_IMGVIEWS_PER_PICTURE = 2;
    private int avatarCount;
    private List<Avatar> avatarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        getIntentData(getIntent());
        initViews();
    }

    private void getIntentData(Intent intent) {
        if (intent != null && intent.hasExtra(EXTRA_AVATAR)) {
            prvlySelectedAvatar = intent.getParcelableExtra(EXTRA_AVATAR);
        }
    }

    private void initViews() {
        ConstraintLayout constLayout = ActivityCompat.requireViewById(this, R.id.constraintLayout);

        imagesList = new ArrayList<>();
        namesList = new ArrayList<>();

        imagesList.add(ActivityCompat.requireViewById(this, R.id.imgAvatar1));
        imagesList.add(ActivityCompat.requireViewById(this, R.id.imgAvatar2));
        imagesList.add(ActivityCompat.requireViewById(this, R.id.imgAvatar3));
        imagesList.add(ActivityCompat.requireViewById(this, R.id.imgAvatar4));
        imagesList.add(ActivityCompat.requireViewById(this, R.id.imgAvatar5));
        imagesList.add(ActivityCompat.requireViewById(this, R.id.imgAvatar6));

        namesList.add(ActivityCompat.requireViewById(this, R.id.lblAvatar1));
        namesList.add(ActivityCompat.requireViewById(this, R.id.lblAvatar2));
        namesList.add(ActivityCompat.requireViewById(this, R.id.lblAvatar3));
        namesList.add(ActivityCompat.requireViewById(this, R.id.lblAvatar4));
        namesList.add(ActivityCompat.requireViewById(this, R.id.lblAvatar5));
        namesList.add(ActivityCompat.requireViewById(this, R.id.lblAvatar6));

        database = Database.getInstance();

        avatarCount = constLayout.getChildCount() / LBL_IMGVIEWS_PER_PICTURE;

        setAvatars();


        selectPreviousAvatar();

        for (int i = 0; i < avatarCount; i++) {
            imagesList.get(i).setOnClickListener(this::getSelectedAvatar);
            namesList.get(i).setOnClickListener(this::getSelectedAvatar);
        }

    }

    private void selectPreviousAvatar() {
        for (int i = 0; i < avatarCount; i++) {
            if (prvlySelectedAvatar.getImageResId() == database.queryAvatar((long) i + 1).getImageResId()) {
                selectImageView(imagesList.get(i));
            }
        }

    }

    private void getSelectedAvatar(View v) {
        long selectedAvatarID;
        for (int i = 0; i < avatarCount; i++) {
            if (v.getId() == namesList.get(i).getId() || v.getId() == imagesList.get(i).getId()) {
                selectedAvatarID = avatarList.get(i).getId();
                selectedAvatar = database.queryAvatar(selectedAvatarID);
            }
        }
        finish();
    }

    private void setAvatars() {
        String name;
        int imgID;

        avatarList = database.queryAvatars();

        for (int i = 0; i < avatarCount; i++) {
            name = avatarList.get(i).getName();
            imgID = avatarList.get(i).getImageResId();
            imagesList.get(i).setImageResource(imgID);
            namesList.get(i).setText(name);
        }

    }

    // DO NO TOUCH
    private void selectImageView(ImageView imageView) {
        imageView.setAlpha(ResourcesUtils.getFloat(this, R.dimen.selected_image_alpha));
    }

    // DO NOT TOUCH
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void startForResult(Activity actividad, int requestCode, Avatar avatar) {
        Intent intent = new Intent(actividad, AvatarActivity.class);
        intent.putExtra(EXTRA_AVATAR, avatar);
        actividad.startActivityForResult(intent, requestCode);
    }

    @Override
    public void finish() {
        sendAvatarBack();
        super.finish();
    }

    private void sendAvatarBack() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_AVATAR, selectedAvatar);
        this.setResult(RESULT_OK, intent);
    }
}
