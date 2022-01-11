package com.example.sensorgpsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UserinfoActivity extends AppCompatActivity {

    private Button submit;
    //variables for radio button
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private RadioGroup radioGroup2;
    private RadioButton radioButton2;

    private RadioGroup radioGroup3;
    private RadioButton radioButton3;

    private  String selecteduni;
    private  String selectedgender;
    private  String selectedage;

    DatabaseReference databaseReference;
   FirebaseDatabase refDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
//        mDatabase = FirebaseDatabase.getInstance();
//        mDatabase.setPersistenceEnabled(true);
        refDatabase= MyDatabaseUtil.getDatabase();
        databaseReference= refDatabase.getReference("SENSORDATA/USERS/" +uid+ "/USERINFO");
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitdata();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(UserinfoActivity.this, LoginActivity.class));
        finish();


    }

    //fetching the userinfo and submitting it to firebase
    public void  submitdata(){
        //code for fetching selected values
    int flag1=0,flag2=0,flag3=0;
        radioGroup = (RadioGroup) findViewById(R.id.uni_radio_btn);
        //code for gender selection value fetch
        radioGroup2 = (RadioGroup) findViewById(R.id.gender_radio_btn);

        //code for age selection value fetch
        radioGroup3 = (RadioGroup) findViewById(R.id.age_range_radio);

        // get selected radio button from radioGroup
        //check if an option is selected

        if ((radioGroup.getCheckedRadioButtonId() == -1) || (radioGroup2.getCheckedRadioButtonId() == -1) || (radioGroup3.getCheckedRadioButtonId() == -1) )
        {
            //Toast.makeText(getApplicationContext(), "Please select Uni", Toast.LENGTH_SHORT).show();
            //Log.d("Please", "select uni");
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Please fill up the form!")
                    .show();
        }
     else
         {
            int  selectedId = radioGroup.getCheckedRadioButtonId();
             // find the radiobutton by returned id
             radioButton = (RadioButton) findViewById(selectedId);
             selecteduni=radioButton.getText().toString();
             //Log.d("SHOWUNI", selecteduni);
             int selectedId2 = radioGroup2.getCheckedRadioButtonId();
             radioButton2 = (RadioButton) findViewById(selectedId2);
             selectedgender = radioButton2.getText().toString();
             //Log.d("SHOWgender", selectedgender);
             int selectedId3 = radioGroup3.getCheckedRadioButtonId();
             radioButton3 = (RadioButton) findViewById(selectedId3);
             selectedage = radioButton3.getText().toString();
             //Log.d("SHOWage", selectedage);
             flag1++ ;
        }







       /*
        if (radioGroup2.getCheckedRadioButtonId() == -1)
        {
            //Toast.makeText(getApplicationContext(), "Please select Uni", Toast.LENGTH_SHORT).show();
            //Log.d("Please", "select uni");
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Something went wrong!")
                    .show();
        }
        else {
            int selectedId2 = radioGroup2.getCheckedRadioButtonId();
            radioButton2 = (RadioButton) findViewById(selectedId2);
            selectedgender = radioButton2.getText().toString();
            //Log.d("SHOWgender", selectedgender);
            flag1++ ;

        }


        if (radioGroup3.getCheckedRadioButtonId() == -1)
        {
            //Toast.makeText(getApplicationContext(), "Please select Uni", Toast.LENGTH_SHORT).show();
           // Log.d("Please", "select uni");
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Please Fill up the form!")
                    .show();
        }
        else {
            int selectedId3 = radioGroup3.getCheckedRadioButtonId();
            radioButton3 = (RadioButton) findViewById(selectedId3);
            selectedage = radioButton3.getText().toString();
            Log.d("SHOWage", selectedage);
            flag1++ ;
        }
  */

        String key =databaseReference.push().getKey();
        if(flag1 == 1) {
            Users users = new Users(selecteduni, selectedgender, selectedage);

            // databaseReference.child(key).setValue(users);
            databaseReference.setValue(users);


            Intent intent = new Intent(UserinfoActivity.this, MainActivity2.class);
            startActivity(intent);
            finish();
         }


    }

}

