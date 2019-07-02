package com.example.emotiondiary.ViewUi;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emotiondiary.R;
import com.example.emotiondiary.databinding.CalendarListBinding;
import com.example.emotiondiary.ui.adapter.CalendarAdapter;
import com.example.emotiondiary.ui.viewmodel.CalendarListViewModel;

import java.util.ArrayList;

public class Calendar extends Fragment {
    private CalendarListBinding binding;
    private CalendarListViewModel model;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
                savedInstanceState) {
            View view = inflater.inflate(R.layout.calendar, container, false);
            binding = DataBindingUtil.setContentView(getActivity(), R.layout.calendar);
            model = ViewModelProviders.of(this).get(CalendarListViewModel.class);
            binding.setModel(model);
            binding.setLifecycleOwner(this);

            observe();
            if (model != null) {
                model.initCalendarList();
            }
            return view;
        }

    private void observe() {
        model.mCalendarList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.pagerCalendar;
                CalendarAdapter adapter = (CalendarAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setCalendarList(objects);
                } else {
                    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
                    adapter = new CalendarAdapter(objects);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                    if (model.mCenterPosition >= 0) {
                        view.scrollToPosition(model.mCenterPosition);
                    }
                }
            }
        });
    }
}
