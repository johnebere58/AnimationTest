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
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

public class CloseButton extends FrameLayout
{

    Context context;
    public CloseButton(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public CloseButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CloseButton(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    DrawLine line1;
    DrawLine line2;
    private void init()
    {
        line1 = new DrawLine(context);
        line2 = new DrawLine(context);

        addView(line1);
        addView(line2);

        drawLine();
    }

    public void drawLine(){
        line2.init(1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                line1.init(0);
            }
        },150);

    }

}