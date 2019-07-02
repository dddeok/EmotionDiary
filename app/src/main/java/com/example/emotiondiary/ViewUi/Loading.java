package com.example.emotiondiary.ViewUi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.emotiondiary.MainActivity;
import com.example.emotiondiary.R;

public class Loading extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        String[] loadingSent = {
                "_How's it going?",
                "_What's going on?",
                "_How do you do?",
        };
        int randomNum = (int)(Math.random()*loadingSent.length);
        TextView textView = (TextView) findViewById(R.id.loadingSent);
        textView.setText(loadingSent[randomNum]);

                Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);

    }
}
