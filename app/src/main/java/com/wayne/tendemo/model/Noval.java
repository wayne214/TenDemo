package com.wayne.tendemo.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Project:TenDemo
 * Author:wayne
 * Date:2016/5/31
 */
public class Noval {
    private String title;
    private String author;
    private String times;
    private String authorbrief;
    private String summary;
    private String text;
    private String publishtime;
    public void parsObject(JSONObject object)
    {
        try {
            title=object.getString("title");
            author=object.getString("author");
            times=object.getString("times");
            authorbrief=object.getString("authorbrief");
            summary=object.getString("summary");
            text=object.getString("text");
            publishtime=object.getString("publishtime");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTimes() {
        return times;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorbrief() {
        return authorbrief;
    }

    public String getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public String getText() {
        return text;
    }

    public String getPublishtime() {
        return publishtime;
    }
}
