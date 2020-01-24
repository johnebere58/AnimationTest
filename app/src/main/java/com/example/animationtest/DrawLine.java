package com.example.animationtest;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class DrawLine extends View
{
    Path path;
    Paint paint;
    float length;
    boolean boldLine = false;
    boolean drawCheckmark = false;

    public void setDrawCheckmark(boolean drawCheckmark) {
        this.drawCheckmark = drawCheckmark;
    }

    public void setBoldLine(boolean boldLine) {
        this.boldLine = boldLine;
    }

    public DrawLine(Context context)
    {
        super(context);
    }

    public DrawLine(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public DrawLine(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void init(int direction)
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(boldLine?4:2);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
        int boxWidth = convertDpToInteger(20);
        int offset = 2;
        if(drawCheckmark) {
            if (direction == 0) {

                int startX = boxWidth/4;
                int startY = (boxWidth/2);
                int endX = (boxWidth/8)*3;
                int endY = (boxWidth/8)*5;
                path.moveTo(startX, startY);
                path.lineTo(endX, endY);
            } else if (direction == 1) {
                int startX = (boxWidth/8)*3;
                int startY = (boxWidth/8)*5;
                int endX = (boxWidth/4)*3;
                int endY = (boxWidth/4);
                path.moveTo(startX, startY);
                path.lineTo(endX, endY);
            }
        }else{
            if (direction == 0) {

            int startX = boxWidth/4;
            int startY = boxWidth/4;
            int endX = boxWidth-startX;
            int endY = boxWidth-startY;

            path.moveTo(startX, startY);
            path.lineTo(endX, endY);

            } else if (direction == 1) {

            int startX = boxWidth - (boxWidth/4);
            int startY = boxWidth/4;
            int endX = boxWidth/4;
            int endY = boxWidth - (boxWidth/4);
            path.moveTo(startX, startY + offset);
            path.lineTo(endX, endY+offset);

            }
        }
        // Measure the path
        PathMeasure measure = new PathMeasure(path, false);
        length = measure.getLength();

        float[] intervals = new float[]{length, length};

        ObjectAnimator animator = ObjectAnimator.ofFloat(DrawLine.this, "phase", 1.0f, 0.0f);
        animator.setDuration(300);
        animator.start();
    }

    int convertDpToInteger(float dp){
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics()));
    }

    //is called by animtor object
    public void setPhase(float phase)
    {
        Log.d("pathview","setPhase called with:" + String.valueOf(phase));
        paint.setPathEffect(createPathEffect(length, phase, 0.0f));
        invalidate();//will calll onDraw
    }

    private static PathEffect createPathEffect(float pathLength, float phase, float offset)
    {
        return new DashPathEffect(new float[] { pathLength, pathLength },
            Math.max(phase * pathLength, offset));
    }

    @Override
    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        try {
            c.drawPath(path, paint);
        }catch (Exception e){}
    }
}