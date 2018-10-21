package es.iessaladillo.pedrojoya.pr04.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

public class EspressoUtils {

    private EspressoUtils() {
    }

    public static void rotateScreen(ActivityTestRule activityTestRule) {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;
        Activity activity = activityTestRule.getActivity();
        activity.setRequestedOrientation((orientation
                == Configuration.ORIENTATION_PORTRAIT) ? ActivityInfo
                                                 .SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo
                                                 .SCREEN_ORIENTATION_PORTRAIT);
    }

}
