package com.example.animationtest;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DownloadButton extends FrameLayout
{

    Context context;
    View cover_layout;
    ProgressBar background_pb;
    ProgressBar download_pb;
    FrameLayout cancel_but;
    DownloadListener downloadListener;

    public void setDownloadListener(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    public DownloadButton(Context context)
    {
        super(context);
        this.context = context;
        init();
    }

    public DownloadButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init();
    }

    public DownloadButton(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init()
    {
        View rootView = View.inflate(context,R.layout.download_button,null);
        addView(rootView);
        cover_layout = rootView.findViewById(R.id.cover_layout);
        background_pb = rootView.findViewById(R.id.background_pb);
        download_pb = rootView.findViewById(R.id.download_pb);
        cancel_but = rootView.findViewById(R.id.cancel_but);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int mainPbWidth = screenWidth - convertDpToInteger(75);
        widthProgress = ((double)mainPbWidth/(double)screenWidth) * 100;
        cover_layout.setVisibility(VISIBLE);


        cover_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CloseButton closeButton = new CloseButton(context);
                cancel_but.addView(closeButton);
                cover_layout.setVisibility(INVISIBLE);
                reduceBackProgress();
                downloadItem();
            }
        });
    }

    int defProgress = 100;
    double widthProgress = 0;
    void reduceBackProgress(){
        if(defProgress<=widthProgress)return;
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        background_pb.setProgress(defProgress);
                        defProgress--;
                        reduceBackProgress();
                    }
                },10
        );
    }

    int downloadProgress = 20;
    void downloadItem(){
        if(downloadProgress>100){
            cancel_but.addView(new OkButton(context));
            if(downloadListener!=null)downloadListener.onCompleted();
            return;
        }
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        download_pb.setProgress(downloadProgress);
                        downloadProgress++;
                        if(downloadProgress>75)cancel_but.removeAllViews();
                        downloadItem();
                    }
                },(downloadProgress>65 && downloadProgress<75)?50:downloadProgress==75?500:10
        );
    }

    int convertDpToInteger(float dp){
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics()));
    }

}