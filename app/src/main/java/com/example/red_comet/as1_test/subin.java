package com.example.red_comet.as1_test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Red_Comet on 2018/2/5.
 */

public class subin {
    private String name;
    private Date date;
    private String comment;
    private int value;

    public subin(String name, int value) {
        this.name = name;
        this.value = value;
        this.date = new Date();
    }

    public subin(String name, String comment, int value) {
        this.name = name;
        this.comment = comment;
        this.value = value;

        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public Date getDate() {
        return date;
    }

    /**
     * set date to current date, called whenever the value of a counter is changed
     */
    public void setDate() {
        this.date = new Date();
    }

    public void delete(){
        this.value = 0;
        this.comment = "";
        this.name = "";
    }
    @Override
    public String toString() {
        // reference https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android
        String d = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        return "Name:"+ name + ", Monthly charge:" + value + ", date:" + d + ", " + comment;

    }
}