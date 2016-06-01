package com.wayne.tendemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.wayne.tendemo.Fragments.PersonalFragment;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ImageView imageView= (ImageView) findViewById(R.id.about_back);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
                finish();
        }
    }
