package com.wayne.tendemo.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Project:TenDemo
 * Author:wayne
 * Date:2016/5/31
 */
public class Critic {
    private String author;
    private String authorbrief;
    private String times;
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private String text5;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    //private String image5;
    private String realtitle;
    private String title;
    private String imageforplay;
    private String publishtime;
    public void parsDetail(JSONObject object)
    {
        try {
            publishtime=object.getString("publishtime");
            imageforplay=object.getString("imageforplay");
            author=object.getString("author");
            authorbrief=object.getString("authorbrief");
            title=object.getString("title");
            times=object.getString("times");
            text1=object.getString("text1");
            text2=object.getString("text2");
            text3=object.getString("text3");
            text4=object.getString("text4");
            text5=object.getString("text5");
            image1=object.getString("image1");
            image2=object.getString("image2");
            image3=object.getString("image3");
            image4=object.getString("image4");
            realtitle=object.getString("realtitle");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getImageforplay() {
        return imageforplay;
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

    public String getTimes() {
        return times;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getText3() {
        return text3;
    }

    public String getText4() {
        return text4;
    }

    public String getText5() {
        return text5;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public String getImage4() {
        return image4;
    }

    public String getRealtitle() {
        return realtitle;
    }
}
