package com.wayne.tendemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.wayne.tendemo.Fragments.NovalFragment;
import com.wayne.tendemo.Fragments.CriticFragment;
import com.wayne.tendemo.Fragments.PersonalFragment;
import com.wayne.tendemo.Fragments.DiagramFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private List<Fragment> mFragments;
    private android.support.v7.app.ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();
        actionBar.hide();
        FragmentManager manager = getSupportFragmentManager();
        mFragments=new ArrayList<>();
        if (savedInstanceState == null) {
            Fragment fragment = new CriticFragment();
            mFragments.add(fragment);
            fragment = new NovalFragment();
            mFragments.add(fragment);
            fragment = new DiagramFragment();
            mFragments.add(fragment);
            fragment = new PersonalFragment();
            mFragments.add(fragment);
            FragmentTransaction transaction = manager.beginTransaction();
            int index = 0;
            for (Fragment f : mFragments) {
                transaction.add(R.id.fragment_container, f, "tag" + index);
                transaction.hide(f);
                index++;
            }
            transaction.show(mFragments.get(0));
            transaction.commit();
        } else {
            for (int i = 0; i < mFragments.size(); i++) {
                String tag = "tag" + i;
                //根据add时，设置的tag，来查找Fragment
                Fragment f = manager.findFragmentByTag(tag);
                if (f != null) {
                    mFragments.add(f);
                }
            }
        }
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.item_tab_bar);
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = 0;
        switch (checkedId) {
            case R.id.item_tab_film:
                index = 0;
                break;
            case R.id.item_tab_essay:
                index = 1;
                break;
            case R.id.item_tab_picture:
                index = 2;
                break;
            case R.id.item_tab_personal:
                index = 3;
                break;
        }
        switchFrament(index);
    }
    private void switchFrament(int index)
    {
        int size=mFragments.size();
        if(index>=0 && index<size)
        {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            for (int i = 0; i <size ; i++) {
                Fragment f=mFragments.get(i);
                if (index==i)
                {
                    transaction.show(f);
                }else
                {
                    transaction.hide(f);
                }
            }
            transaction.commit();
        }
    }
}
