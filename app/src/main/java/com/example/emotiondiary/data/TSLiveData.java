package com.example.emotiondiary.data;

import android.arch.lifecycle.MutableLiveData;

public class TSLiveData<T> extends MutableLiveData<T> {
    public TSLiveData() {

    }

    public TSLiveData(T value) {
        setValue(value);
    }
}
