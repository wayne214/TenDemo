package com.wayne.tendemo.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriticFragment extends Fragment implements ViewPager.OnPageChangeListener, NetworkTaskCallback {

    private List<Fragment> mFragments;
    private CommonFragmentAdapter mAdapter;
    private String mId;
    private ViewPager mCriticViewpager;

    public CriticFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mFragments=new ArrayList<>();
//        mAdapter=new CommonFragmentAdapter(getChildFragmentManager(),mFragments);
        NetworkTask task=new NetworkTask(getContext(),this);
        task.execute("http://api.shigeten.net/api/Critic/GetCriticList");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        mCriticViewpager = (ViewPager) view.findViewById(R.id.critic_viewpager);
//        mCriticViewpager.setAdapter(mAdapter);
        mCriticViewpager.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTaskFinished(byte[] data) {
        if (data != null) {
            try {
                String json=new String(data,"UTF-8");
                try {
                    JSONObject jsonObject=new JSONObject(json);
                    JSONArray resultArray = jsonObject.getJSONArray("result");
                    List<String> mlist=new ArrayList<>();
                    for (int i = 0; i <resultArray.length() ; i++) {
                        JSONObject jsondata=resultArray.getJSONObject(i);
                        mId = jsondata.getString("id");
                        mlist.add(mId);
                    }
                    mFragments=new ArrayList<>();
                    for (int i = 0; i <mlist.size() ; i++) {
                        CriticContentFragment contentFragment=new CriticContentFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("id",mlist.get(i));
                        contentFragment.setArguments(bundle);
                        mFragments.add(contentFragment);
                    }
                    mAdapter=new CommonFragmentAdapter(getChildFragmentManager(),mFragments);
                    mCriticViewpager.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
        }
    }
}
