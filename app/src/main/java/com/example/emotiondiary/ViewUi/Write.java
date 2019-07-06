package com.example.emotiondiary.ViewUi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.emotiondiary.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Write extends Fragment {
    Button button;
    View view;
    TextView currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.write, container, false);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = format.format(date);

        currentDate = view.findViewById(R.id.current_date);
        currentDate.setText(getTime);

        button = (Button)view.findViewById(R.id.addTextBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TextWrite.class);
                startActivity(intent);
            }
        });

        return view;
    }



}

