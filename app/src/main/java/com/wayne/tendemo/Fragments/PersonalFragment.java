package com.wayne.tendemo.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wayne.tendemo.AboutActivity;
import com.wayne.tendemo.FavoriteActivity;
import com.wayne.tendemo.FeedBackActivity;
import com.wayne.tendemo.FontSettingActivity;
import com.wayne.tendemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener {
    private TextView btn_favorite,btn_font,btn_about,btn_feedback;

    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        btn_favorite= (TextView) view.findViewById(R.id.btn_favorite);
        btn_font= (TextView) view.findViewById(R.id.btn_font);
        btn_about= (TextView) view.findViewById(R.id.btn_about);
        btn_feedback= (TextView) view.findViewById(R.id.btn_feedback);
        btn_about.setOnClickListener(this);
        btn_favorite.setOnClickListener(this);
        btn_font.setOnClickListener(this);
        btn_feedback.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getContext(), FavoriteActivity.class);
        int id = v.getId();
        switch (id) {
            case R.id.btn_favorite:
                startActivity(intent);
                break;
            case R.id.btn_font:
                intent=new Intent(getContext(), FontSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_about:
                intent=new Intent(getContext(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_feedback:
                intent=new Intent(getContext(), FeedBackActivity.class);
                startActivity(intent);
                break;
        }
    }
}
