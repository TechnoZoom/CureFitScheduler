package schedule.curefit.com.curefitscheduleapp.utils;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;

public class DrawableUtils {

    public static GradientDrawable getGradientDrawable(int startColor, int endColor) {

        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {startColor,endColor});
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(0f);

        return gradientDrawable;
    }


    public static int getInterpolatedColor(float fraction, int startColor, int endColor) {

        return  (Integer) new ArgbEvaluator().evaluate(fraction, startColor, endColor);
    }

}
