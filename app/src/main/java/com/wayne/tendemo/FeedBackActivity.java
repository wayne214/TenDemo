package com.wayne.tendemo;

import android.app.ActionBar;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FeedBackActivity extends AppCompatActivity {
    private TextView text_weixin,text_qq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        text_weixin= (TextView) findViewById(R.id.weixinpublic);
        text_qq= (TextView) findViewById(R.id.qqpublic);
        text_weixin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        text_qq.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }
    public void btnClick(View view) {
        int id=view.getId();
        switch (id) {
            case R.id.weixinpublic:
                Toast.makeText(this, "已为您复制"+"\""+text_weixin.getText().toString()+"\""+"到粘贴板", Toast.LENGTH_SHORT).show();
                break;
            case R.id.qqpublic:
                Toast.makeText(this, "已为您复制"+"\""+text_qq.getText().toString()+"\""+"到粘贴板", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void btnBackClick(View view) {
        finish();
    }
}
