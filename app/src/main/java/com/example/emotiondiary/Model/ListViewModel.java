package com.example.emotiondiary.Model;

public class ListViewModel {
    int contentImg;
    String contentText;
    String contentDate;

    public ListViewModel(int contentImg, String text, String date, String contentText, String contentDate) {
        this.contentImg = contentImg;
        this.contentText = contentText;
        this.contentDate = contentDate;
    }

    public int getContentImg() {
        return contentImg;
    }

    public void setContentImg(int contentImg) {
        this.contentImg = contentImg;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentDate() { return contentDate; }

    public void setContentDate() { this.contentDate = contentDate;}
}
