package es.iessaladillo.pedrojoya.pr04.data.local;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.data.local.model.Avatar;

// DO NOT TOUCH

public class Database {

    private static Database instance;

    private final ArrayList<Avatar> avatars = new ArrayList<>();
    private final Random random = new Random(1);
    private long count;

    private Database() {
        insertAvatar(new Avatar(R.drawable.cat1, "Tom"));
        insertAvatar(new Avatar(R.drawable.cat2, "Luna"));
        insertAvatar(new Avatar(R.drawable.cat3, "Simba"));
        insertAvatar(new Avatar(R.drawable.cat4, "Kitty"));
        insertAvatar(new Avatar(R.drawable.cat5, "Felix"));
        insertAvatar(new Avatar(R.drawable.cat6, "Nina"));
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    private void insertAvatar(Avatar avatar) {
        long id = ++count;
        avatar.setId(id);
        avatars.add(avatar);
    }

    public Avatar getRandomAvatar() {
        return avatars.get(random.nextInt(avatars.size()));
    }

    public Avatar getDefaultAvatar() {
        return avatars.get(0);
    }

    public List<Avatar> queryAvatars() {
        return new ArrayList<>(avatars);
    }

    public Avatar queryAvatar(long id) {
        for (Avatar avatar: avatars) {
            if (avatar.getId() == id) {
                return avatar;
            }
        }
        return null;
    }

}
