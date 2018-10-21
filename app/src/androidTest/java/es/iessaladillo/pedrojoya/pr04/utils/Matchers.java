package es.iessaladillo.pedrojoya.pr04.utils;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import androidx.test.espresso.matcher.BoundedMatcher;

public class Matchers {

    private Matchers() {
    }

    public static Matcher<View> withBoldTypeface(final boolean isBold) {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public boolean matchesSafely(TextView textView) {
                return isBold == textView.getTypeface().isBold();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with bold typeface: ");
            }
        };
    }

//    // Receives drawable resId to check for ImageView.
//    // Receives <0 if we want to check if ImageView has no drawable.
//    public static Matcher<View> withDrawable(@DrawableRes final int resourceId) {
//        return new TypeSafeMatcher<View>() {
//
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("with drawable from resource id: ");
//                description.appendValue(resourceId);
//            }
//
//            @Override
//            protected boolean matchesSafely(View target) {
//                if (!(target instanceof ImageView)) {
//                    return false;
//                }
//                ImageView imageView = (ImageView) target;
//                if (resourceId < 0) {
//                    return imageView.getDrawable() == null;
//                }
//                Resources resources = target.getContext().getResources();
//                Drawable expectedDrawable = resources.getDrawable(resourceId);
//                if (expectedDrawable == null) {
//                    return false;
//                }
//                Bitmap bitmap = getBitmap(imageView.getDrawable());
//                Bitmap otherBitmap = getBitmap(expectedDrawable);
//                return bitmap.sameAs(otherBitmap);
//            }
//
//            private Bitmap getBitmap(Drawable drawable) {
//                Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
//                        drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bitmap);
//                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//                drawable.draw(canvas);
//                return bitmap;
//            }
//        };
//    }

}