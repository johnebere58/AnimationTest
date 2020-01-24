package com.example.animationtest;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.download_button) DownloadButton download_button;
    @BindView(R.id.play_button) View play_button;
    @BindView(R.id.card1) View card1;
    @BindView(R.id.card2) View card2;
    @BindView(R.id.second_frame) View second_frame;
    @BindView(R.id.third_frame) View third_frame;
    @BindView(R.id.fourth_frame) View fourth_frame;
    @BindView(R.id.black_back) View black_back;
    @BindView(R.id.white_back) View white_back;
    @BindView(R.id.next_button) View next_button;
    @BindView(R.id.next_button_third_frame) View next_button_third_frame;
    @BindView(R.id.next_button_fourth_frame) View next_button_fourth_frame;

    @BindView(R.id.t1) View t1;
    @BindView(R.id.t1b) View t1b;
    @BindView(R.id.item_a1) View item_a1;
    @BindView(R.id.item_a2) View item_a2;
    @BindView(R.id.item_a3) View item_a3;
    @BindView(R.id.item_b1) View item_b1;
    @BindView(R.id.item_b2) View item_b2;
    @BindView(R.id.item_b3) View item_b3;
    @BindView(R.id.photo) View photo;

    @BindView(R.id.text1) View text1;
    @BindView(R.id.text2) View text2;
    @BindView(R.id.text3) View text3;

    @BindView(R.id.text_back1) View text_back1;
    @BindView(R.id.text_back2) View text_back2;
    @BindView(R.id.text_back3) View text_back3;

    @BindView(R.id.wallet_bal) View wallet_bal;

    @BindView(R.id.curve_back1) View curve_back1;
    @BindView(R.id.curve_back2) View curve_back2;
    @BindView(R.id.curve_back3) View curve_back3;

    @BindView(R.id.title1) View title1;
    @BindView(R.id.title2) View title2;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.main_page);
        ButterKnife.bind(this);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSecondFrame();
            }
        });

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showSecondFrame();
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showThirdFrame();
            }
        });
        next_button_third_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFourthFrame();
            }
        });

        download_button.setDownloadListener(new DownloadListener() {
            @Override
            public void onCompleted() {
                new Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                Animation fade_out = AnimationUtils.loadAnimation(context,R.anim.fade_out);
                                fade_out.setAnimationListener(getAnimListener(download_button,true));
                                download_button.startAnimation(fade_out);

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Animation slide_in = AnimationUtils.loadAnimation(context,R.anim.slide_in_from_bottom);
                                        slide_in.setAnimationListener(getAnimListener(play_button,false));
                                        play_button.setVisibility(View.VISIBLE);
                                        play_button.startAnimation(slide_in);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                play_button.startAnimation(glowAnimation());
                                            }
                                        },500);
                                    }
                                },200);
                            }
                        },500
                );
            }
        });

        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.slide_in_from_right);
        anim1.setAnimationListener(getAnimListener(card1,false));
        Animation anim2 = AnimationUtils.loadAnimation(this,R.anim.slide_in_from_right);
        anim2.setAnimationListener(getAnimListener(card2,false));
        card1.setVisibility(View.VISIBLE);
        card1.startAnimation(anim1);
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        card2.setVisibility(View.VISIBLE);
                        card2.startAnimation(anim2);
                    }
                },200
        );

    }

    void showSecondFrame(){
        t1.setVisibility(View.INVISIBLE);
        item_a1.setVisibility(View.INVISIBLE);
        item_a2.setVisibility(View.INVISIBLE);
        item_a3.setVisibility(View.INVISIBLE);
        item_b1.setVisibility(View.INVISIBLE);
        item_b2.setVisibility(View.INVISIBLE);
        item_b3.setVisibility(View.INVISIBLE);
        second_frame.setVisibility(View.VISIBLE);
        Animation slide_in_bottom = AnimationUtils.loadAnimation(context,R.anim.slide_in_from_bottom);
        Animation slide_in_right = AnimationUtils.loadAnimation(context,R.anim.slide_in_from_right);
        Animation slide_in_right_x2 = AnimationUtils.loadAnimation(context,R.anim.slide_in_from_right);
        slide_in_right_x2.setDuration(500);
        Animation zoom_in = AnimationUtils.loadAnimation(context,R.anim.zoom_in);

        black_back.startAnimation(slide_in_bottom);
        white_back.startAnimation(slide_in_bottom);
        next_button.startAnimation(slide_in_bottom);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                item_a1.setVisibility(View.VISIBLE);
                item_a2.setVisibility(View.VISIBLE);
                item_a3.setVisibility(View.VISIBLE);

                item_b1.setVisibility(View.VISIBLE);
                item_b2.setVisibility(View.VISIBLE);
                item_b3.setVisibility(View.VISIBLE);

                item_a1.startAnimation(slide_in_right);
                item_a2.startAnimation(slide_in_right);
                item_a3.startAnimation(slide_in_right);

                item_b1.startAnimation(slide_in_right_x2);
                item_b2.startAnimation(slide_in_right_x2);
                item_b3.startAnimation(slide_in_right_x2);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        t1.setVisibility(View.VISIBLE);
                        t1.startAnimation(zoom_in);
                    }
                },500);


            }
        },200);
    }

    void showThirdFrame(){
        second_frame.setVisibility(View.GONE);
        third_frame.setVisibility(View.VISIBLE);
        t1b.setVisibility(View.INVISIBLE);
        Animation slide_in_bottom = AnimationUtils.loadAnimation(context,R.anim.slide_in_from_bottom_with_alpha);
        Animation slide_in_right = AnimationUtils.loadAnimation(context,R.anim.slide_in_from_right);
        Animation zoom_in = AnimationUtils.loadAnimation(context,R.anim.zoom_in);
        wallet_bal.startAnimation(slide_in_bottom);

        text1.startAnimation(slide_in_right);
        text2.startAnimation(slide_in_right);
        text3.startAnimation(slide_in_right);
        text_back1.startAnimation(slide_in_right);
        text_back2.startAnimation(slide_in_right);
        text_back3.startAnimation(slide_in_right);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                t1b.setVisibility(View.VISIBLE);
                t1b.startAnimation(zoom_in);
            }
        },500);
    }

    void showFourthFrame(){
        third_frame.setVisibility(View.GONE);
        fourth_frame.setVisibility(View.VISIBLE);
        Animation slide_in_bottom = AnimationUtils.loadAnimation(context,R.anim.slide_in_from_bottom_with_alpha);
        Animation slide_in_right = AnimationUtils.loadAnimation(context,R.anim.slide_in_from_right);
        Animation zoom_in = AnimationUtils.loadAnimation(context,R.anim.zoom_in);
        next_button_fourth_frame.startAnimation(slide_in_bottom);

        curve_back1.startAnimation(slide_in_right);
        curve_back2.startAnimation(slide_in_right);
        curve_back3.startAnimation(slide_in_right);

        title1.startAnimation(zoom_in);
        title2.startAnimation(zoom_in);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    Animation.AnimationListener getAnimListener(View v,boolean hide){
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(hide?View.GONE:View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    public AlphaAnimation glowAnimation(){
        AlphaAnimation an = new AlphaAnimation(1f,0.7f);

        an.setDuration(2000);
        an.setInterpolator(new LinearInterpolator());
        an.setRepeatCount(Animation.INFINITE);
        an.setRepeatMode(Animation.REVERSE);
        return an;
    }
}
