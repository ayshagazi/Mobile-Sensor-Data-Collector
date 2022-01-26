package com.example.sensorgpsservice.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sensorgpsservice.R;
import com.example.sensorgpsservice.labeling;
import com.example.sensorgpsservice.ui.activity_label;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private CardView c;
    private TextView thankyoutext;
    TextView textView;
    private Button btn;
    String currentQuesKey = "";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RadioGroup radioGroup = (RadioGroup) root.findViewById(R.id.radioGroup);

      //  setVisibility();
     /*   btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent intent1 = new Intent(home.this, testing.class);
                Intent intent =new Intent(getContext(),testing.class);
              //  intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

*/



        textView= (TextView)root.findViewById(R.id.textView2);
        c= (CardView) root.findViewById(R.id.card);
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Log.d("uidfrom home:",uid);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDateandTime = sdf.format(new Date());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Questions");
        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("SENSORDATA/USERS/" +uid+ "/SURVEYANSWER");

        // Read from the database

        databaseReference.orderByChild("ShowTime").equalTo(currentDateandTime).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String message = "";

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    currentQuesKey = "Questions/"+postSnapshot.getKey();
                    Map<String, String> map = (Map) postSnapshot.getValue();

                    if (map != null) {
                        message = map.get("question");
                    }
                }

                //Log.d("FirstQuestion:",value.toString());
                textView.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

                Log.w("Failed to read",error.toException());
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.option1:
                        // switch to fragment 1
                    {
                        int  selectedId = group.getCheckedRadioButtonId();
                        // find the radiobutton by returned id
                        RadioButton radioButton = (RadioButton)root.findViewById(selectedId);

                        String answer=radioButton.getText().toString();
                        //Log.d("answer", answer);

                        Map<String, Object> childUpdates = new HashMap<>();

                        childUpdates.put("answer", "Yes");
                        childUpdates.put("question_ref", currentQuesKey);

                        databaseReference2.child(currentQuesKey).setValue(childUpdates);

                        new SweetAlertDialog(
                                getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Thanks for your opinion!")
                                .setContentText("")
                                .show();


                        // setVisibility();
                        Intent intent =new Intent(getContext(),labeling.class);
                        startActivity(intent);
                        break;


                    }

                    case R.id.option2:
                        // Fragment 2
                    {
                        int  selectedId = radioGroup.getCheckedRadioButtonId();
                        // find the radiobutton by returned id
                        RadioButton radioButton = (RadioButton)root.findViewById(selectedId);

                        String answer=radioButton.getText().toString();
                        //Log.d("answer", answer);

                        Map<String, Object> childUpdates = new HashMap<>();

                        childUpdates.put("answer", "No");
                        childUpdates.put("question_ref", currentQuesKey);

                        databaseReference2.child(currentQuesKey).setValue(childUpdates);

                        new SweetAlertDialog(
                                getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Thanks for your opinion!")
                                .setContentText("")
                                .show();

                   setVisibility();
                        //Intent intent =new Intent(getContext(), activity_label.class);
                       // startActivity(intent);
                        break;
                    }


                }
            }
        });


        return root;
    }

    private void setVisibility() {

        activity_label h= new activity_label();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, h, "activity_label")
                .addToBackStack(null)
                .commit();
c.setVisibility(View.INVISIBLE);



       /* activity!!
        .supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, NewFragment.newInstance())
                .commitNow()
                */
    }


}

