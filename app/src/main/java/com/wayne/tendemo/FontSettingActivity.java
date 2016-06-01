package com.wayne.tendemo;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FontSettingActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mRadioGroup;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_setting);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mRadioGroup= (RadioGroup) findViewById(R.id.fontseting_rg);
        mTextView= (TextView) findViewById(R.id.example_txt);
        mRadioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.fontseting_s:
                mTextView.setTextSize(8);
                break;
            case R.id.fontseting_m:
                mTextView.setTextSize(10);
                break;
            case R.id.fontseting_l:
                mTextView.setTextSize(15);
                break;
        }
    }

    public void btnClick(View view) {
        finish();
    }
}
