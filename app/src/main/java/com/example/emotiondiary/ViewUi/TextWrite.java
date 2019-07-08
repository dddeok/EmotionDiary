package com.example.emotiondiary.ViewUi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.emotiondiary.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TextWrite extends Activity {

    TextView current;
    ImageButton img_btn;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_write);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = format.format(date);

        current = (TextView)findViewById(R.id.current);
        current.setText(getTime);

        img_btn = (ImageButton)findViewById(R.id.check_btn);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClick(v);
            }
        });

    }

    public void mOnClick(View v) {finish();}
}
