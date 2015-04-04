package com.example.naveen.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    Path mPath;
    int y0 = 100, y1 = 50, y2 = 150;
    int xOffset, yOffset;
    int bezierPointsCount = 6;
    int currentRun = 0;

    public WaterFill(Context context, AttributeSet attrs) {
        super(context, attrs);

        parseAttributes(context.obtainStyledAttributes(attrs, R.styleable.WaterFill));
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

    private void setupBounds() {
        xOffset = getWidth() / bezierPointsCount;
        yOffset = getHeight() / 6;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        setupBounds();
        setupPaints();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath = new Path();
        mPath.moveTo(0, y0);

        if(currentRun % 2 == 0) {
            y1 = 50; y2 = 150;
        } else {
            y1 = 150; y2 = 50;
        }

        mPath.cubicTo(xOffset, y1, (xOffset * 2), y2, (xOffset * 3), y0);
        mPath.moveTo((xOffset * 3), y0);
        mPath.cubicTo((xOffset * 4), y1, (xOffset * 5), y2, (xOffset * 6), y0);
        canvas.drawPath(mPath, mSurfaceLinePaint);
        currentRun++;

        postInvalidateDelayed(500);
    }
}
