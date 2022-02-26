package com.example.sensorgpsservice.ui.label_bg;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sensorgpsservice.R;
import com.example.sensorgpsservice.labeling;
import com.example.sensorgpsservice.ui.home.HomeFragment;
import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class activity_label extends Fragment {

    

    private Slider sBar,sBar2,sBar3,sBar4,sBar5,sBar6,sBar7,sBar8;
    private Button btn,clr;
    private LinearLayout l;
    private CardView c;

    double p=0, p2=0, p3=0, p4=0, p5=0,p6=0,p7=0,p8=0;
    String date,CurrentDate,CurrentTime;
    //int i=1;

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
        sBar6= (Slider)root.findViewById(R.id.seekBar6);
        sBar7= (Slider)root.findViewById(R.id.seekBar7);
        sBar8= (Slider)root.findViewById(R.id.seekBar8);


        btn= (Button) root.findViewById(R.id.btnOK);

        l=(LinearLayout)root.findViewById(R.id.layout11);
        l.setVisibility(View.VISIBLE);

        //c= (CardView) root.findViewById(R.id.card1);

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("MM dd, yyyy");
        CurrentDate= currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH: mm: ss a");
        CurrentTime= currentTime.format(calendar.getTime());

        date= CurrentDate+CurrentTime;


       // mToastRunnable.run();

        sBar.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p= (double) value;
            }
        });

        sBar2.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p2= (double) value;
            }
        });
        sBar3.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p3= (double) value;
            }
        });
        sBar4.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p4= (double) value;
            }
        });
        sBar5.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p5= (double) value;
            }
        });
        sBar6.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p6= (double) value;
            }
        });
        sBar7.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p7= (double) value;
            }
        });
        sBar8.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                p8= (double) value;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingLabeling();

                new SweetAlertDialog(
                        getContext(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Thanks for your opinion! Please return to Home")
                        .setContentText("")
                        .show();
                setVisibility();



            }
        });



return root;


    }

    private void setVisibility() {

        int commit = getFragmentManager().beginTransaction().replace(R.id.layout11, new background()).addToBackStack(null).commit();
        l.setVisibility(View.INVISIBLE);

        /*
        FragmentTransaction fr= getFragmentManager().beginTransaction();
        fr.replace(R.id.layout11, new background());
        fr.commit();
*/

    }

    private void addingLabeling() {

        String v1=String.valueOf(p);
        String v2=String.valueOf(p2);
        String v3=String.valueOf(p3);
        String v4=String.valueOf(p4);
        String v5=String.valueOf(p5);
        String v6=String.valueOf(p6);
        String v7=String.valueOf(p7);
        String v8=String.valueOf(p8);

        HashMap data =new HashMap();
        data.put("Time",date);
        data.put("Walking",v1+"%");
        data.put("Sitting",v2+"%");
        data.put("Running",v3+"%");
        data.put("Sleeping",v4+"%");
        data.put("Climbing",v5+"%");
        data.put("Working",v6+"%");
        data.put("Social Activity",v7+"%");
        data.put("Mental Health",v8+"%");



        databaseReference2.child(date).setValue(data);

        sBar.setValue(0);
        sBar2.setValue(0);
        sBar3.setValue(0);
        sBar4.setValue(0);
        sBar5.setValue(0);
        sBar6.setValue(0);
        sBar7.setValue(0);
        sBar8.setValue(0);



    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
//            l.setVisibility(View.VISIBLE);
            mHandler.postDelayed(this, 100000);
            //Toast.makeText(getActivity(), "This is a delayed toast", Toast.LENGTH_SHORT).show();


        }
    };
}