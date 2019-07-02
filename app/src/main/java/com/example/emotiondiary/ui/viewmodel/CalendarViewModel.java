package com.example.emotiondiary.ui.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.emotiondiary.data.TSLiveData;

import java.util.Calendar;

public class CalendarViewModel extends ViewModel {
    public TSLiveData<Calendar> mCalendar = new TSLiveData<>();

    public void setCalendar(Calendar calendar) {
        this.mCalendar.setValue(calendar);
    }

}
