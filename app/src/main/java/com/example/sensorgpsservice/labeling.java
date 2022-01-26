package com.example.sensorgpsservice;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;

public class labeling extends AppCompatActivity {

    private Slider sBar,sBar2,sBar3,sBar4,sBar5;
    private Button btn,clr;
    double p=0;
    double p2=0, p3=0, p4=0, p5=0;
    String date,CurrentDate,CurrentTime;
    int i=1;


    private Handler mHandler = new Handler();

    String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("SENSORDATA/USERS/" +uid+ "/Labeling");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labeling);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Labeling");



        //  postDataRef = FirebaseDatabase.getInstance().getReference().child("Rent_posts");


        sBar =findViewById(R.id.seekBar1);
        sBar2 =findViewById(R.id.seekBar2);
        sBar3 =findViewById(R.id.seekBar3);
        sBar4 =findViewById(R.id.seekBar4);
        sBar5 = findViewById(R.id.seekBar5);
        btn=(Button)findViewById(R.id.btnOK);

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("MM dd, yyyy");
        CurrentDate= currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH: mm: ss a");
        CurrentTime= currentTime.format(calendar.getTime());

        date= CurrentDate+CurrentTime;

        //nevigation
        /*
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homePage:
                        return true;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), search.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.postAHome:
                        startActivity(new Intent(getApplicationContext(), PostAHome.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
*/
        //nevigation ends






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


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mToastRunnable.run();


                String v1=String.valueOf(p);
                String v2=String.valueOf(p2);
                String v3=String.valueOf(p3);
                String v4=String.valueOf(p4);
                String v5=String.valueOf(p5);

                HashMap data =new HashMap();
                data.put("Time",date);
                data.put("Walking",v1+"%");
                data.put("Stting",v2+"%");
                data.put("Running",v3+"%");
                data.put("Sleeping",v4+"%");
                data.put("Staring",v5+"%");

                //myRef.child(String.valueOf(i)).setValue(data);
                //  myRef.child(date).setValue(data);
                databaseReference2.child(date).setValue(data);


                Toast.makeText(labeling.this, "Stored value succesfully", Toast.LENGTH_SHORT).show();
                i++;
                sBar.setValue(0);
                sBar2.setValue(0);
                sBar3.setValue(0);
                sBar4.setValue(0);
                sBar5.setValue(0);


                Toast.makeText(labeling.this, "delay text", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
         //   Toast.makeText(MainActivity.this, "This is a delayed toast", Toast.LENGTH_SHORT).show();

            mHandler.postDelayed(this, 10000);


        }
    };
}




