package com.wayne.tendemo.Fragments.essayfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wayne.mylibrary.NetworkTask;
import com.wayne.mylibrary.NetworkTaskCallback;
import com.wayne.tendemo.R;
import com.wayne.tendemo.model.Noval;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * A simple {@link Fragment} subclass.
 */
public class NovalContentFragment extends Fragment implements NetworkTaskCallback {
    public static final String ARTICLE_DETATLS_URL = "http://api.shigeten.net/api/Novel/GetNovelContent?id=";
    private Noval mNoval;
    private TextView title,author,times,summary,text,authorbrief,authorbottom;

    public NovalContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        String id = bundle.getString("id");
//        NetworkTask task=new NetworkTask(getContext(),this);
//        task.execute(ARTICLE_DETATLS_URL+id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noval_content, container, false);
        initView(view);
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        NetworkTask task=new NetworkTask(getContext(),this);
        task.execute(ARTICLE_DETATLS_URL + id);
        return view;
    }
    private  void initView(View view)
    {
        title= (TextView) view.findViewById(R.id.noval_tital);
        author= (TextView) view.findViewById(R.id.noval_author);
        times= (TextView) view.findViewById(R.id.noval_times);
        summary= (TextView) view.findViewById(R.id.noval_summary);
        text= (TextView) view.findViewById(R.id.noval_text);
        authorbrief= (TextView) view.findViewById(R.id.noval_authorbrief);
        authorbottom= (TextView) view.findViewById(R.id.noval_author_bottom);
    }
    private void init()
    {
        title.setText(mNoval.getTitle());
        author.setText("作者:"+mNoval.getAuthor());
        times.setText(" | 阅读量:"+mNoval.getTimes());
        summary.setText(mNoval.getSummary());
        text.setText(mNoval.getText());
        authorbrief.setText(mNoval.getAuthorbrief());
        authorbottom.setText(mNoval.getAuthor());
    }

    @Override
    public void onTaskFinished(byte[] data) {
        if (data != null) {
            try {
                String json=new String(data,"UTF-8");
                try {
                    JSONObject jsonData=new JSONObject(json);
                    mNoval = new Noval();
                    mNoval.parsObject(jsonData);
                    init();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
