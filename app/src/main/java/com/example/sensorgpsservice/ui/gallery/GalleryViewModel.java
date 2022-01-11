package com.example.sensorgpsservice.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("\nThe main rules and instructions for using this application are: \n \n" +
                "[1]. Firstly, sign up with a google account.\n \n" +
                "[2]. Then, fill up the mandatory form according to your valid info.\n \n" +
                "[3]. Allow the permission asked by the app, such as: GPS and others.\n \n" +
                "[4]. Keep the application running in the background for participating in this survey.\n \n" +
                "[5]. Participate and give your opinion in the daily updated question.\n \n" +
                "[6]. If you want to change the currently given email, then use the exit button and sign up with a new one. Otherwise don't need to use the exit button.\n\n" +
                "       Thank You for your co-operation on this survey. Have a good day! \n");
    }

    public LiveData<String> getText() {
        return mText;
    }
}