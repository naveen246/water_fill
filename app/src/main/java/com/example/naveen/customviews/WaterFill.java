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
    Paint paint = new Paint();
    Path path;
    int y0, y1, y2;
    int xOffset;
    int yOffset = 25;
    int amplitude = 50;
    int height, width;
    int currentRun = 0;

    public WaterFill(Context context, AttributeSet attrs) {
        super(context, attrs);

        parseAttributes(context.obtainStyledAttributes(attrs, R.styleable.WaterFill));
    }

    private void parseAttributes(TypedArray a) {
        try {
            waterColor = a.getColor(R.styleable.WaterFill_waterColor, Color.BLUE);
        } finally {
            a.recycle();
        }
    }

    private void setupPaints() {
        paint.setColor(waterColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    private void setupBounds() {
        width = getWidth();
        height = getHeight();
        xOffset = width / 6;
        y0 = height - amplitude;
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

        path = new Path();
        path.moveTo(0, y0);

        if(currentRun % 2 == 0) {
            y1 = y0 - amplitude; y2 = y0 + amplitude;
        } else {
            y1 = y0 + amplitude; y2 = y0 - amplitude;
        }

        path.cubicTo(xOffset, y1, (xOffset * 2), y2, (xOffset * 3), y0);
        path.moveTo((xOffset * 3), y0);
        path.cubicTo((xOffset * 4), y1, (xOffset * 5), y2, (xOffset * 6), y0);
        canvas.drawPath(path, paint);
        canvas.drawRect(0, y0, width, height, paint);
        currentRun++;
        if(y0 - amplitude > 0) {
            y0 -= yOffset;
            postInvalidateDelayed(500);
        }
    }
}
