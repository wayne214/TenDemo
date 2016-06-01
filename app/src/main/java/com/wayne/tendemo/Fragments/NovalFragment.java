package com.wayne.tendemo.Fragments;


import android.app.ActionBar;
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
import com.wayne.tendemo.Fragments.essayfragment.NovalContentFragment;
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
public class NovalFragment extends Fragment implements NetworkTaskCallback, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;

    public NovalFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkTask task=new NetworkTask(getContext(),this);
        task.execute("http://api.shigeten.net/api/Novel/GetNovelList");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_essay, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.noval_viewPager);
        mViewPager.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onTaskFinished(byte[] data) {
        if (data != null) {
            try {
                String json=new String(data,"UTF-8");
                try {
                    JSONObject jsonData=new JSONObject(json);
                    JSONArray array = jsonData.getJSONArray("result");
                    List<String>mlist=new ArrayList<>();
                    for (int i = 0; i <array.length() ; i++) {
                        JSONObject data1=array.getJSONObject(i);
                        String id=data1.getString("id");
                        mlist.add(id);
                    }
                    List<Fragment>fragments=new ArrayList<>();
                    for (int i = 0; i <mlist.size(); i++) {
                        NovalContentFragment novalFrament=new NovalContentFragment();
                        Bundle bundle=new Bundle();
                        bundle.putString("id",mlist.get(i));
                        novalFrament.setArguments(bundle);
                        fragments.add(novalFrament);
                    }
                    CommonFragmentAdapter adapter=new CommonFragmentAdapter(getChildFragmentManager(),fragments);
                    mViewPager.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
        }
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
}
