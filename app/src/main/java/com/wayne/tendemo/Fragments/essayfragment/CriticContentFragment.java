package com.wayne.tendemo.Fragments.essayfragment;


import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wayne.mylibrary.NetworkTask;
import com.wayne.mylibrary.NetworkTaskCallback;
import com.wayne.tendemo.R;
import com.wayne.tendemo.model.Critic;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriticContentFragment extends Fragment implements NetworkTaskCallback {
    private  Critic mCritic;
    public static final String PICTURE_DISPLAY_URL = "http://api.shigeten.net/";
    public static final String MOVIE_DETATLS_URL = "http://api.shigeten.net/api/Critic/GetCriticContent?id=";
    private ImageView imageforplay,img1,img2,img3,img4,loadImg;
    private TextView title,summary,autor,text2,text3,text4,text5,realtitle,times,authorbrief,autor_bottom;
    private Drawable mDrawable;

    public CriticContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_essay_content, container, false);
        initView(view);
        Bundle bundle=getArguments();
        String id=bundle.getString("id");
        NetworkTask task=new NetworkTask(getContext(),this);
        task.execute(MOVIE_DETATLS_URL + id);
        mDrawable = loadImg.getDrawable();
        if (mDrawable != null) {
            if(mDrawable instanceof AnimationDrawable)
            {
                AnimationDrawable drawable1= (AnimationDrawable) mDrawable;
                drawable1.start();
            }
        }

        return view;
    }
    private void init()
    {
        title.setText(mCritic.getTitle());
        summary.setText(mCritic.getText1());
        autor.setText("作者:"+mCritic.getAuthor());
        realtitle.setText(mCritic.getRealtitle());
        String content=mCritic.getText2().substring(4);
        text2.setText(content);
        text3.setText(mCritic.getText3());
        text4.setText(mCritic.getText4());
        text5.setText(mCritic.getText5());
        times.setText(" | 阅读量:"+mCritic.getTimes());
        authorbrief.setText(mCritic.getAuthorbrief());
        autor_bottom.setText(mCritic.getAuthor());
        Picasso.with(getContext())
                .load(PICTURE_DISPLAY_URL+mCritic.getImage1())
                .into(img1);
        Picasso.with(getContext())
                .load(PICTURE_DISPLAY_URL + mCritic.getImageforplay())
                .into(imageforplay);
        Picasso.with(getContext())
                .load(PICTURE_DISPLAY_URL + mCritic.getImage2())
                .into(img2);
        Picasso.with(getContext())
                .load(PICTURE_DISPLAY_URL + mCritic.getImage3())
                .into(img3);
        Picasso.with(getContext())
                .load(PICTURE_DISPLAY_URL + mCritic.getImage4())
                .into(img4);
    }
    private  void initView(View view)
    {
        loadImg= (ImageView) view.findViewById(R.id.load_img);
        imageforplay= (ImageView) view.findViewById(R.id.critic_imageforplay);
        img1= (ImageView) view.findViewById(R.id.critic_image1);
        img2= (ImageView) view.findViewById(R.id.critic_image2);
        img3= (ImageView) view.findViewById(R.id.critic_image3);
        img4= (ImageView) view.findViewById(R.id.critic_image4);
        title= (TextView) view.findViewById(R.id.critic_title);
        summary= (TextView) view.findViewById(R.id.critic_summary);
        autor= (TextView) view.findViewById(R.id.critic_author);
        realtitle= (TextView) view.findViewById(R.id.critic_realtitle);
        text2= (TextView) view.findViewById(R.id.critic_text2);
        text3= (TextView) view.findViewById(R.id.critic_text3);
        text4= (TextView) view.findViewById(R.id.critic_text4);
        text5= (TextView) view.findViewById(R.id.critic_text5);
        times= (TextView) view.findViewById(R.id.critic_times);
        authorbrief= (TextView) view.findViewById(R.id.authorbrief);
        autor_bottom= (TextView) view.findViewById(R.id.critic_author_bottom);
    }
    @Override
    public void onTaskFinished(byte[] data) {
        if (data != null) {
            try {
                String json=new String(data,"UTF-8");
                try {
                    JSONObject jsonData=new JSONObject(json);
                    mCritic=new Critic();
                    mCritic.parsDetail(jsonData);
                    init();
                    loadImg.setVisibility(View.INVISIBLE);
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
}
