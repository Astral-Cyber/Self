package com.jxufe.self.ui.company;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CompanyViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CompanyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is company fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}