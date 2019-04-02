package com.breakout.myapplication.main;

class NavElement {

    public NavElement(int image, String title) {
        this.image = image;
        this.title = title;
    }

    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
}
