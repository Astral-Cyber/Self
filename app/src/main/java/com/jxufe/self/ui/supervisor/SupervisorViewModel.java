package com.jxufe.self.ui.supervisor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SupervisorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SupervisorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is supervisor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}