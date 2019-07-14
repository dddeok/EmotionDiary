package com.example.emotiondiary.ViewUi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

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
                AlertDialog.Builder dialog = new AlertDialog.Builder(TextWrite.this);
                dialog .setTitle("Emotion Diary")
                        .setMessage("저장하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String contents = editText.getText().toString();
                                dbHelper.insert(data,  bytes, contents, emotion);
                                Log.d("Test", "Success");
                                mOnClick(v);
                                Toast.makeText(TextWrite.this, "저장을 완료했습니다.",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(TextWrite.this, "취소 버튼을 눌렀습니다.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.create();
                dialog.show();

            }
        });

    }

    public void mOnClick(View v) {
        finish();
    }
}
