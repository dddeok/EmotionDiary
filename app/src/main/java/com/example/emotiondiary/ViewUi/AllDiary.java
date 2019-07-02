package com.example.emotiondiary.ViewUi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.emotiondiary.Adapter.ListViewAdapter;
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

        for(int i=0;i<3;i++) //여기를 적은 사이즈만큼만 받아오도록 설정하고싶은데 일단 임의의 숫자로 해놨소
        {
            list.add(addBoard(R.drawable.picpic,"insta1", "190513"));
            }
        ListViewAdapter adapter = new ListViewAdapter(list);
        listView.setAdapter(adapter);
        return view;
    }
    ListViewModel addBoard(int contentImg, String contentText, String contentDate){
        return new ListViewModel(contentImg, contentText, contentDate,"게시글이 너무 좋네요","190513");
    }

}
