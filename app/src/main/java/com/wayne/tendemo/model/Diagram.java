package com.wayne.tendemo.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Project:TenDemo
 * Author:wayne
 * Date:2016/5/31
 */
public class Diagram {
    private String title;
    private String author;
    private String authorbrief;
    private String text1;
    private String image1;
    private String text2;
    private String times;
    private String publishtime;
    public void parseObject(JSONObject object)
    {
        try {
            publishtime=object.getString("publishtime");
            title=object.getString("title");
            author=object.getString("author");
            authorbrief=object.getString("authorbrief");
            text1=object.getString("text1");
            image1=object.getString("image1");
            text2=object.getString("text2");
            times=object.getString("times");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorbrief() {
        return authorbrief;
    }

    public String getText1() {
        return text1;
    }

    public String getImage1() {
        return image1;
    }

    public String getText2() {
        return text2;
    }

    public String getTimes() {
        return times;
    }

    public String getPublishtime() {
        return publishtime;
    }
}
