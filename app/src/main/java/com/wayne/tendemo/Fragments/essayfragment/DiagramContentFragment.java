package com.wayne.tendemo.Fragments.essayfragment;


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
import com.wayne.tendemo.model.Diagram;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiagramContentFragment extends Fragment implements NetworkTaskCallback {
    public static final String PICTURE_DETATLS_URL = "http://api.shigeten.net/api/Diagram/GetDiagramContent?id=";
    private ImageView image1;
    public static final String PICTURE_DISPLAY_URL = "http://api.shigeten.net/";
    private TextView title,authorbrief,text1,text2;
    private Diagram mDiagram;
    public DiagramContentFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diagram, container, false);
        init(view);
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        NetworkTask task=new NetworkTask(getContext(),this);
        task.execute(PICTURE_DETATLS_URL+id);
        return view;
    }
    private void init(View v)
    {
        title= (TextView) v.findViewById(R.id.diagram_title);
        authorbrief= (TextView) v.findViewById(R.id.diagram_authorbrief);
        image1= (ImageView) v.findViewById(R.id.diagram_img);
        text1= (TextView) v.findViewById(R.id.diagram_text1);
        text2= (TextView) v.findViewById(R.id.diagram_text2);
    }
    private void init()
    {
        title.setText(mDiagram.getTitle());
        authorbrief.setText(mDiagram.getAuthorbrief());
        text1.setText(mDiagram.getText1());
        text2.setText(mDiagram.getText2());
        Picasso.with(getContext())
                .load(PICTURE_DISPLAY_URL+mDiagram.getImage1())
                .into(image1);
    }
    @Override
    public void onTaskFinished(byte[] data) {
        if (data != null) {
            try {
                String json=new String(data,"UTF-8");
                try {
                    JSONObject jsonData=new JSONObject(json);
                    mDiagram=new Diagram();
                    mDiagram.parseObject(jsonData);
                    init();
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
