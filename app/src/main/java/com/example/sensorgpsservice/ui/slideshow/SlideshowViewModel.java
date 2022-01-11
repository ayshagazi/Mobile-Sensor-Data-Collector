package com.example.sensorgpsservice.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue
                ("\nThis application is a part of the thesis research titled as \"Spatial Analysis based on Smartphone Sensor Data\". \n \n \n" +
                        "The Supervisor of this research is: \n" +
                        "Mr. H M Zabir Haque \n" +
                        "Assistant Professor, \n" + "Department of Computer Science and Engineering, AUST. \n \n" +
                        "The Contributors of this application are: \n" +
                        "[1]  17.01.04.004 \n -  Umme Habiba \n \n" +
                        "[2]  17.01.04.015 \n -  Kazi Taqi Tahmid \n \n" +
                        "[3]  17.01.04.024 \n -  Koushik Mallik \n \n" +
                        "[4]  17.01.04.044 \n -  Khandaker Rezwan Ahmed \n \n"
                        );
    }

    public LiveData<String> getText() {
        return mText;
    }
}