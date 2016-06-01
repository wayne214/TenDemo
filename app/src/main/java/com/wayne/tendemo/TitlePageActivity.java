package com.wayne.tendemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TitlePageActivity extends AppCompatActivity implements Animation.AnimationListener {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_page);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        mImageView = (ImageView) findViewById(R.id.welcomo_img);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.donghua);
        mImageView.startAnimation(animation);
        animation.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
