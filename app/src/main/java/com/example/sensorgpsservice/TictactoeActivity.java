package com.example.sensorgpsservice;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.sensorgpsservice.R;
import com.example.sensorgpsservice.ControllerActivity;
import com.example.sensorgpsservice.Board;
import com.example.sensorgpsservice.DeterministicComputerPlayer;
import com.example.sensorgpsservice.HumanPlayer;
import com.example.sensorgpsservice.PlayerActivity;

import java.util.Timer;
import java.util.TimerTask;


public class TictactoeActivity extends AppCompatActivity {
    private PlayerActivity player1;
    private PlayerActivity player2;
    private boolean isSoloGameMode;
    private ControllerActivity controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe3x3);
        isSoloGameMode = getIntent().getBooleanExtra(getString(R.string.intent_extra_key_solo_game_mode), true);
        setupActivity();
    }

    public void buClick(View view) {
        if (isSoloGameMode)
            controller.playSoloMode(view);
        else
            controller.playMultiMode(view);
        // check winning  or tie condition and show appropriate notification accordingly.
        if (controller.isPlayerWinner(player1))
            notifyResultAndEndGame(R.string.x_won);
        else if (controller.isPlayerWinner(player2))
            notifyResultAndEndGame(R.string.o_won);
        else if (controller.isTieGame())
            notifyResultAndEndGame(R.string.tie_game);
    }

    private void setupActivity() {
        Board board = new Board();
        // Player 1 is always Human, always takes X mark and always starts first.
        player1 = new HumanPlayer(board, R.drawable.x);
        player1.setActive(true);
        // Player 2 is based on whether this is a solo-player mode game or multi-player mode game.
        player2 = isSoloGameMode ? new DeterministicComputerPlayer(board, R.drawable.o) : new HumanPlayer(board, R.drawable.o);
        controller = new ControllerActivity(board, player1, player2);
    }

    private void notifyResultAndEndGame(int messageRes) {
        // Show notification and then after a delay of 1.5 seconds finish activity.
        Toast.makeText(this, messageRes, Toast.LENGTH_LONG).show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        }, 1500);
    }
}
