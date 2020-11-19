package com.Android.Gajju;


public class Product {
    private String title;

    private String coverImage;

    private String expName;
    private String expImg;

    private boolean expended;

    public boolean isExpended() {
        return expended;

    }

    public void setExpended(boolean expended) {
        this.expended = expended;
    }

    public Product() {
    }

    public Product(String title, String coverImage, String expName, String expImg, boolean expended) {
        this.title = title;
        this.coverImage = coverImage;
        this.expName = expName;
        this.expImg = expImg;
        this.expended = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public String getExpImg() {
        return expImg;
    }

    public void setExpImg(String expImg) {
        this.expImg = expImg;
    }
}