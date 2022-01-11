package com.example.sensorgpsservice;

import android.view.View;

import java.util.ArrayList;
import com.example.sensorgpsservice.PlayerActivity;
import com.example.sensorgpsservice.Board;

public class ControllerActivity {
    private PlayerActivity Player1;
    private PlayerActivity Player2;
    private Board board;

    public ControllerActivity(Board board, PlayerActivity player1, PlayerActivity player2) {
        this.board = board;
        this.Player1 = player1;
        this.Player2 = player2;
    }

    public void playMultiMode(View view) {
        if (Player1.isActive()) {
            Player1.playTurn(view);
            Player2.setActive(true);
            Player1.setActive(false);

        } else if (Player2.isActive()) {
            Player2.playTurn(view);
            Player1.setActive(true);
            Player2.setActive(false);
        }
    }

    public void playSoloMode(View view) {
        if (Player1.isActive()) {
            Player1.playTurn(view);
            Player2.setActive(true);
            Player1.setActive(false);
            // Call the Function again to trigger the AI Player to play his turn.
            playSoloMode(view);

        } else if (Player2.isActive()) {
            Player2.playTurn(view);
            Player2.setActive(false);
            Player1.setActive(true);
        }
    }

    public boolean isTieGame() {
        return Player1.getCellsChosen().size() + Player2.getCellsChosen().size() == Board.BOARD_CELLS_NUMBER;
    }

    public boolean isPlayerWinner(PlayerActivity player) {
        for (ArrayList<Integer> winningArray : board.getWinningCells())
            if (player.getCellsChosen().containsAll(winningArray))
                return true;
        return false;
    }
}

