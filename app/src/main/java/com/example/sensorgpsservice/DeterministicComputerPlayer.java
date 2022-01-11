package com.example.sensorgpsservice;

import com.example.sensorgpsservice.PlayerActivity;

import android.view.View;
import android.widget.Button;

public class DeterministicComputerPlayer extends PlayerActivity{
    public DeterministicComputerPlayer(Board board, int playerMark) {
        super(board, playerMark);
    }

    @Override
    public void playTurn(View view) {
        // make sure the computer agent never makes a violated move on board.
        if (board.getEmptyCells().size() > 1) {
            // this is where the deterministic logic comes from. It chooses index ) each time.
            int buttonID = board.getEmptyCells().get(0);
            Button buttonSelected = view.getRootView().findViewById(buttonID);
            // disable the button to avoid re-clicking on it.
            buttonSelected.setEnabled(false);
            // display player's mark on the button selected.
            buttonSelected.setBackgroundResource(playerMark);
            // occupy a cell to be chosen by this player.
            addChosenCell(buttonID);
            board.occupyCell(buttonID);
        }
    }

}
