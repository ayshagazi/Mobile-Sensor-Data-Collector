package com.example.sensorgpsservice.ui.tictactoe;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.sensorgpsservice.R;
import com.example.sensorgpsservice.TictactoeActivity;

public class TictactoeFragment extends Fragment {

    private TictactoeViewModel mViewModel;

    public static TictactoeFragment newInstance() {
        return new TictactoeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = inflater.inflate(R.layout.tictactoe_fragment, container, false);

        Button singleplayer =(Button) view.findViewById(R.id.button1);
        Button multiplayer =(Button) view.findViewById(R.id.button2);

        singleplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent singleplayerIntent = new Intent(getContext(), TictactoeActivity.class);
                singleplayerIntent.putExtra(getString(R.string.intent_extra_key_solo_game_mode), true);
                startActivity(singleplayerIntent);
            }
        });
        multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent multiplayerIntent = new Intent(getContext(), TictactoeActivity.class);
                multiplayerIntent.putExtra(getString(R.string.intent_extra_key_solo_game_mode), false);
                startActivity(multiplayerIntent);
            }
        });
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TictactoeViewModel.class);
        // TODO: Use the ViewModel
    }

}