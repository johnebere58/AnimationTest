package com.example.animationtest;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class OkButton extends FrameLayout
{

    Context context;
    public OkButton(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public OkButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

    public OkButton(Context context, AttributeSet attrs, int defStyleAttr)
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

        line1.setDrawCheckmark(true);
        line2.setDrawCheckmark(true);

        addView(line1);
        addView(line2);

        drawLine();
    }

    public void drawLine(){
        line1.init(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                line2.init(1);
            }
        },150);

    }

}