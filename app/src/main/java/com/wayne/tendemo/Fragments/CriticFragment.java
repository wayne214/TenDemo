package com.wayne.tendemo.Fragments;


import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.wayne.mylibrary.CommonFragmentAdapter;
import com.wayne.mylibrary.NetworkTask;
import com.wayne.mylibrary.NetworkTaskCallback;
import com.wayne.tendemo.Fragments.essayfragment.CriticContentFragment;
import com.wayne.tendemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriticFragment extends Fragment implements ViewPager.OnPageChangeListener, NetworkTaskCallback {

    private List<Fragment> mFragments;
    private CommonFragmentAdapter mAdapter;
    private String mId;
    private ViewPager mCriticViewpager;
    private Drawable mDrawable;

    public CriticFragment() {
        // Required empty public constructor
    }

    private ImageView date_week, date_month, date_day1, date_day2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mFragments=new ArrayList<>();
//        mAdapter=new CommonFragmentAdapter(getChildFragmentManager(),mFragments);
        NetworkTask task = new NetworkTask(getContext(), this);
        task.execute("http://api.shigeten.net/api/Critic/GetCriticList");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        initView(view);
        mCriticViewpager = (ViewPager) view.findViewById(R.id.critic_viewpager);
        mCriticViewpager.addOnPageChangeListener(this);
        NetworkTask task = new NetworkTask(getContext(), this);
        task.execute("http://api.shigeten.net/api/Critic/GetCriticList");

        return view;
    }

    private void initView(View v) {
        date_day1 = (ImageView) v.findViewById(R.id.date_day1);
        date_day2 = (ImageView) v.findViewById(R.id.date_day2);
        date_month = (ImageView) v.findViewById(R.id.date_month);
        date_week = (ImageView) v.findViewById(R.id.date_week);
    }

    private int week,month, day;
    private void switchDate(int index) {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, -index);
        week = c.get(Calendar.DAY_OF_WEEK);
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        switch (week) {
            case 1:
                date_week.setImageResource(R.mipmap.week_7);
                break;
            case 2:
                date_week.setImageResource(R.mipmap.week_1);
                break;
            case 3:
                date_week.setImageResource(R.mipmap.week_2);
                break;
            case 4:
                date_week.setImageResource(R.mipmap.week_3);
                break;
            case 5:
                date_week.setImageResource(R.mipmap.week_4);
                break;
            case 6:
                date_week.setImageResource(R.mipmap.week_5);
                break;
            case 7:
                date_week.setImageResource(R.mipmap.week_6);
                break;
        }
        switch (month) {
            case 0:
                date_month.setImageResource(R.mipmap.month_1);
                break;
            case 1:
                date_month.setImageResource(R.mipmap.month_2);
                break;
            case 2:
                date_month.setImageResource(R.mipmap.month_3);
                break;
            case 3:
                date_month.setImageResource(R.mipmap.month_4);
                break;
            case 4:
                date_month.setImageResource(R.mipmap.month_5);
                break;
            case 5:
                date_month.setImageResource(R.mipmap.month_6);
                break;
            case 6:
                date_month.setImageResource(R.mipmap.month_7);
                break;
            case 7:
                date_month.setImageResource(R.mipmap.month_8);
                break;
            case 8:
                date_month.setImageResource(R.mipmap.month_9);
                break;
            case 9:
                date_month.setImageResource(R.mipmap.month_10);
                break;
            case 10:
                date_month.setImageResource(R.mipmap.month_11);
                break;
            case 11:
                date_month.setImageResource(R.mipmap.month_12);
                break;
        }
        switch (day / 10) {
            case 0:
                date_day1.setImageResource(R.mipmap.date_0);
                break;
            case 1:
                date_day1.setImageResource(R.mipmap.date_1);
                break;
            case 2:
                date_day1.setImageResource(R.mipmap.date_2);
                break;
            case 3:
                date_day1.setImageResource(R.mipmap.date_3);
                break;
        }
        switch (day % 10) {
            case 0:
                date_day2.setImageResource(R.mipmap.date_0);
                break;
            case 1:
                date_day2.setImageResource(R.mipmap.date_1);
                break;
            case 2:
                date_day2.setImageResource(R.mipmap.date_2);
                break;
            case 3:
                date_day2.setImageResource(R.mipmap.date_3);
                break;
            case 4:
                date_day2.setImageResource(R.mipmap.date_4);
                break;
            case 5:
                date_day2.setImageResource(R.mipmap.date_5);
                break;
            case 6:
                date_day2.setImageResource(R.mipmap.date_6);
                break;
            case 7:
                date_day2.setImageResource(R.mipmap.date_7);
                break;
            case 8:
                date_day2.setImageResource(R.mipmap.date_8);
                break;
            case 9:
                date_day2.setImageResource(R.mipmap.date_9);
                break;
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switchDate(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTaskFinished(byte[] data) {
        if (data != null) {
            try {
                String json = new String(data, "UTF-8");
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray resultArray = jsonObject.getJSONArray("result");
                    List<String> mlist = new ArrayList<>();
                    List<String> dataList = new ArrayList<>();
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject jsondata = resultArray.getJSONObject(i);
                        mId = jsondata.getString("id");
                        String time = jsondata.getString("publishtime");
                        dataList.add(time);
                        mlist.add(mId);
                    }
                    mFragments = new ArrayList<>();
                    for (int i = 0; i < mlist.size(); i++) {
                        CriticContentFragment contentFragment = new CriticContentFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", mlist.get(i));
                        bundle.putString("date", dataList.get(i));
                        contentFragment.setArguments(bundle);
                        mFragments.add(contentFragment);
                    }
                    mAdapter = new CommonFragmentAdapter(getChildFragmentManager(), mFragments);
                    mCriticViewpager.setAdapter(mAdapter);
                    switchDate(mCriticViewpager.getCurrentItem());
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
        }
    }
}
