package com.example.naveen.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.example.naveen.waterfill.R;

/**
 * Created by naveen on 4/4/15.
 */
public class WaterFill extends View {

    int mColor;

    public WaterFill(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WaterFill, 0, 0);

        try {
            mColor = a.getColor(R.styleable.WaterFill_waterColor, Color.BLUE);
        } finally {
            a.recycle();
        }
    }

    

}
