package com.example.emotiondiary.ViewUi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.emotiondiary.Adapter.ListViewAdapter;
import com.example.emotiondiary.DBHelper;
import com.example.emotiondiary.Model.ListViewModel;
import com.example.emotiondiary.R;

import java.util.ArrayList;

public class AllDiary extends Fragment  {

    ListView listView;
    ArrayList<ListViewModel> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_diary, container, false);
        listView = view.findViewById(R.id.listView);

        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from diary_table", null);

        while(cursor.moveToNext()) {
            String date = cursor.getString(0);
            byte[] img = cursor.getBlob(1);
            String contents = cursor.getString(2);
            String emotion = cursor.getString(3);
            System.out.println("Check The date" + date);
            Log.d("test",img.toString());
            list.add(addBoard(img,  date, contents, emotion));

            ListViewAdapter adapter = new ListViewAdapter(list);
            listView.setAdapter(adapter);
        }
        return view;
    }

    ListViewModel addBoard(byte[] contentImg, String contentDate, String contents, String emotion){
        return new ListViewModel(contentImg, contentDate, contents, emotion);
    }

}
