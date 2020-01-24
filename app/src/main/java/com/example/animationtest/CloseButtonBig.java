package com.example.animationtest;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class CloseButtonBig extends FrameLayout
{

    Context context;
    public CloseButtonBig(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public CloseButtonBig(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CloseButtonBig(Context context, AttributeSet attrs, int defStyleAttr)
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

        line1.setBoldLine(true);
        line2.setBoldLine(true);

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