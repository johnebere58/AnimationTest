package com.example.animationtest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreActivity extends BaseActivity {

    @BindView(R.id.rootView) View rootView;
    @BindView(R.id.photo) View photo;
    @BindView(R.id.button) View button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_page);
        ButterKnife.bind(this);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreActivity.this,MainActivity.class);
                Pair<View,String> pair1 = new Pair<>(photo,"photo");
                Pair<View,String> pair2 = new Pair<>(button,"button");
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(PreActivity.this,pair1,pair2);
                 startActivity(intent,optionsCompat.toBundle());
            }
        });
    }
}
