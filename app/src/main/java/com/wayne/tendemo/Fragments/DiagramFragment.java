package com.wayne.tendemo.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wayne.mylibrary.CommonFragmentAdapter;
import com.wayne.mylibrary.NetworkTask;
import com.wayne.mylibrary.NetworkTaskCallback;
import com.wayne.tendemo.Fragments.essayfragment.DiagramContentFragment;
import com.wayne.tendemo.R;
import com.wayne.tendemo.model.Diagram;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiagramFragment extends Fragment implements NetworkTaskCallback {
    public static final String PICTURE_URL = "http://api.shigeten.net/api/Diagram/GetDiagramList";
    private ViewPager mViewPager;
    public DiagramFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        mViewPager= (ViewPager) view.findViewById(R.id.diagram_viewPager);
        NetworkTask task=new NetworkTask(getContext(),this);
        task.execute(PICTURE_URL);
        return view;
    }

    @Override
    public void onTaskFinished(byte[] data) {
        if (data != null) {
            try {
                String json=new String(data,"UTF-8");
                try {
                    JSONObject jsonObject=new JSONObject(json);
                    JSONArray array = jsonObject.getJSONArray("result");
                    List<String>list=new ArrayList<>();
                    for (int i = 0; i <array.length() ; i++) {
                        JSONObject objec=array.getJSONObject(i);
                        String id = objec.getString("id");
                        list.add(id);
                    }
                    List<Fragment>fragments=new ArrayList<>();
                    for (int i = 0; i <list.size() ; i++) {
                        DiagramContentFragment dFragment=new DiagramContentFragment();
                        Bundle bundle=new Bundle();
                        bundle.putString("id",list.get(i));
                        dFragment.setArguments(bundle);
                        fragments.add(dFragment);
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
        }
    }
}
