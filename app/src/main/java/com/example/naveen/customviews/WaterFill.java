package com.example.naveen.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.naveen.waterfill.R;

/**
 * Created by naveen on 4/4/15.
 */
public class WaterFill extends View {

    int waterColor;
    Paint mSurfaceLinePaint = new Paint();
    Paint mWaterPaint = new Paint();

    public WaterFill(Context context, AttributeSet attrs) {
        super(context, attrs);

        parseAttributes(context.obtainStyledAttributes(attrs, R.styleable.WaterFill));
        setupPaints();
    }

    public int getColor() {
        return waterColor;
    }

    public void setColor(int waterColor) {
        this.waterColor = waterColor;
        invalidate();
        requestLayout();
    }

    private void parseAttributes(TypedArray a) {
        try {
            waterColor = a.getColor(R.styleable.WaterFill_waterColor, Color.BLUE);
        } finally {
            a.recycle();
        }
    }

    private void setupPaints() {
        mSurfaceLinePaint.setColor(waterColor);
        mSurfaceLinePaint.setAntiAlias(true);
        mSurfaceLinePaint.setStyle(Paint.Style.STROKE);
        mSurfaceLinePaint.setStrokeWidth(2);

        mWaterPaint.setColor(waterColor);
        mWaterPaint.setAntiAlias(true);
        mWaterPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, 0, 50, 50, mSurfaceLinePaint);
    }
}
