package com.example.emotiondiary.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ListViewModel {
    byte[] contentImg;
    String contentDate;
    String contents;
    String contentsEmotion;

    public ListViewModel(byte[] contentImg, String contentDate, String contents, String emotion) {
        this.contentImg = contentImg;
        this.contentDate = contentDate;
        this.contents = contents;
        this.contentsEmotion = emotion;
    }

    public Bitmap getContentImg() {
        Bitmap bitmap = BitmapFactory.decodeByteArray(contentImg,0,contentImg.length);
        return bitmap;
    }

    public String getContentDate() { return contentDate; }

    public String getContents(){
        return contents;
    }

    public String getContentsEmotion(){
        return contentsEmotion;
    }
}
