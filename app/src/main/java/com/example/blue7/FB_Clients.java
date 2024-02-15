package com.example.blue7;


import java.io.Serializable;

public class FB_Clients  implements Serializable {
    private int fb_client_id;
    private String fb_client_name;

    public int getFb_client_id() {
        return fb_client_id;
    }

    public void setFb_client_id(int fb_client_id) {
        this.fb_client_id = fb_client_id;
    }

    public String getFb_client_name() {
        return fb_client_name;
    }

    public void setFb_client_name(String fb_client_name) {
        this.fb_client_name = fb_client_name;
    }

    public int getFb_client_age() {
        return fb_client_age;
    }

    public void setFb_client_age(int fb_client_age) {
        this.fb_client_age = fb_client_age;
    }

    public String getFb_client_gender() {
        return fb_client_gender;
    }

    public void setFb_client_gender(String fb_client_gender) {
        this.fb_client_gender = fb_client_gender;
    }

    public float getFb_client_rating() {
        return fb_client_rating;
    }

    public void setFb_client_rating(float fb_client_rating) {
        this.fb_client_rating = fb_client_rating;
    }

    private int fb_client_age;
    private String fb_client_gender;
    private float fb_client_rating;


    public FB_Clients(){}

}
