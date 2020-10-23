package com.myapplication.data.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class User
{
    private String uname,uphone,uemail,upassword,ugender,ustatus,uid,user_img_path;
    private Bitmap bitmap_img;

    public String getUser_img_path() {
        return user_img_path;
    }

    public void setUser_img_path(String user_img_path) {
        this.user_img_path = user_img_path;
    }

    public User(String uid, String uname, String uphone, String uemail, String ugender, String ustatus, Bitmap bitmap_img)
    {
        this.uid = uid;
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.ugender = ugender;
        this.ustatus = ustatus;
        this.bitmap_img = bitmap_img;
    }
    public User(String uid, String uname, String uphone, String uemail, String ugender, String ustatus, String user_img_path)
    {
        this.uid = uid;
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.ugender = ugender;
        this.ustatus = ustatus;
        this.user_img_path = user_img_path;
    }

    public Bitmap getBitmap_img() {
        return bitmap_img;
    }

    public void setBitmap_img(Bitmap bitmap_img) {
        this.bitmap_img = bitmap_img;
    }

    public User()
    {
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User(String uname, String uphone, String uemail, String upassword, String ugender, String ustatus, String uid, Bitmap bitmap_img) {
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.upassword = upassword;
        this.ugender = ugender;
        this.ustatus = ustatus;
        this.uid = uid;
        this.bitmap_img = bitmap_img;
    }

    public User(String uname, String uphone, String uemail, String upassword, String ugender) {
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.upassword = upassword;
        this.ugender = ugender;
    }

    public User(String ustatus, String uid)
    {
        this.ustatus = ustatus;
        this.uid = uid;
    }


    public User(String uname, String uphone, String uemail, String ugender)
    {
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.ugender = ugender;
    }

    public User(String user_id, String uname, String uphone, String uemail, String ugender, String ustatus)
    {
        this.uname = uname;
        this.uphone = uphone;
        this.uemail = uemail;
        this.ugender = ugender;
        this.uid = user_id;
        this.ustatus = ustatus;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUgender() {
        return ugender;
    }

    public void setUgender(String ugender) {
        this.ugender = ugender;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public String getUid() {
        return uid;
    }

}
