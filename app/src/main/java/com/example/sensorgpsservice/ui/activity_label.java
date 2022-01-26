package com.example.sensorgpsservice.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sensorgpsservice.R;
import com.example.sensorgpsservice.labeling;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class activity_label extends Fragment {


    private Slider sBar,sBar2,sBar3,sBar4,sBar5;
    private Button btn,clr;
    double p=0;
    private LinearLayout l;
    int p2=0; int p3=0; int p4=0; int p5=0;
    String date,CurrentDate,CurrentTime;
    int i=1;

    private Handler mHandler = new Handler();

    String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("SENSORDATA/USERS/" +uid+ "/Labeling");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     //  inflater.inflate(R.layout.fragment_home, container, false);
        View root =  inflater.inflate(R.layout.fragment_activity_label, container, false);


        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Labeling");


        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("SENSORDATA/USERS/" +uid+ "/Labeling");


        //  postDataRef = FirebaseDatabase.getInstance().getReference().child("Rent_posts");
/*

        sBar =findViewById(R.id.seekBar1);

        sBar2 =findViewById(R.id.seekBar2);
        sBar3 =findViewById(R.id.seekBar3);
        sBar4 =findViewById(R.id.seekBar4);
        sBar5 = findViewById(R.id.seekBar5);
        btn=(Button)findViewById(R.id.btnOK);

*/
        sBar= (Slider)root.findViewById(R.id.seekBar1);
        sBar2= (Slider)root.findViewById(R.id.seekBar2);

        sBar3= (Slider)root.findViewById(R.id.seekBar3);

        sBar4= (Slider)root.findViewById(R.id.seekBar4);

        sBar5= (Slider)root.findViewById(R.id.seekBar5);
        btn= (Button) root.findViewById(R.id.btnOK);

        l=(LinearLayout)root.findViewById(R.id.layout);
        l.setVisibility(View.VISIBLE);


        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("MM dd, yyyy");
        CurrentDate= currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH: mm: ss a");
        CurrentTime= currentTime.format(calendar.getTime());

        date= CurrentDate+CurrentTime;


        mToastRunnable.run();

        sBar.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p= (double) value;
            }
        });

        sBar2.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p2= (int) value;
            }
        });
        sBar3.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p3= (int) value;
            }
        });
        sBar4.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p4= (int) value;
            }
        });
        sBar5.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p5= (int) value;
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                l.setVisibility(View.INVISIBLE);
                addingLabeling();

            //    mToastRunnable.run();
            }
        });







return root;


    }

    private void addingLabeling() {

        String v1=String.valueOf(p);
        String v2=String.valueOf(p2);
        String v3=String.valueOf(p3);
        String v4=String.valueOf(p4);
        String v5=String.valueOf(p5);

        HashMap data =new HashMap();
        data.put("pid",date);
        data.put("Walking : ",v1+"%");
        data.put("Stting : ",v2+"%");
        data.put("Running : ",v3+"%");
        data.put("Sleeping: ",v4+"%");
        data.put("Staring : ",v5+"%");

        //myRef.child(String.valueOf(i)).setValue(data);
        databaseReference2.child(date).setValue(data);
        Toast.makeText(getActivity(),"succesfully text" ,Toast.LENGTH_SHORT).show();
        //  Toast.makeText(labeling.this, "Stored value succesfully", Toast.LENGTH_SHORT).show();
        i++;


        sBar.setValue(0);
        sBar2.setValue(0);
        sBar3.setValue(0);
        sBar4.setValue(0);
        sBar5.setValue(0);

    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
            l.setVisibility(View.VISIBLE);

            mHandler.postDelayed(this, 10000);
            Toast.makeText(getActivity(), "This is a delayed toast", Toast.LENGTH_SHORT).show();





        }
    };
}