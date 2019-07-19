package com.example.emotiondiary.ViewUi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emotiondiary.DBHelper;
import com.example.emotiondiary.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TextWrite extends Activity {

    TextView current;
    ImageButton img_btn;
    EditText editText;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_write);

        DBHelper dbHelper = new DBHelper(getApplicationContext());

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = format.format(date);
        current = (TextView)findViewById(R.id.current);
        current.setText(getTime);

        Intent intent = getIntent();
        String data = intent.getStringExtra("date");
        String emotion = intent.getStringExtra("emotion");
        byte[] bytes = intent.getByteArrayExtra("img");
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        editText = findViewById(R.id.edit_text);


        ImageView imageView = (ImageView)findViewById(R.id.text_img);
        imageView.setImageBitmap(bitmap);

        img_btn = (ImageButton)findViewById(R.id.check_btn);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contents = editText.getText().toString();
                dbHelper.insert(data,  bytes, contents, emotion);
                Log.d("Test", "Success");
                mOnClick(v);
            }
        });

    }

    public void mOnClick(View v) {
        finish();
    }
}
