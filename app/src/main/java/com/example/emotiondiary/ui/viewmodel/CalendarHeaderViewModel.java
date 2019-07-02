package com.example.emotiondiary.ui.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.emotiondiary.data.TSLiveData;

public class CalendarHeaderViewModel extends ViewModel {
    public TSLiveData<Long> mHeaderDate = new TSLiveData<>();
    public void setHeaderDate(long headerDate) {
        this.mHeaderDate.setValue(headerDate);
    }
}
