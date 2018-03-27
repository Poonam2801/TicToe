package com.example.poonamgupta2801.tic_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    boolean gameIsActive =true;
    //green=0, red=1

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //2 means unplayed


    int[][] winningPositions = {{0, 1, 2}, {1, 4, 5}, {2, 5, 8}, {0, 3, 6,}, {1, 5, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
    }


    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt ( counter.getTag ().toString () );

        if (gameState[tappedCounter] == 2&&gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY ( -1200f );

            if (activePlayer == 0) {
                counter.setImageResource ( R.drawable.green );
                activePlayer = 1;
            } else {
                counter.setImageResource ( R.drawable.red );
                activePlayer = 0;
            }
            counter.animate ().translationYBy ( 1200f ).rotation ( 420 ).setDuration ( 300 );

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    gameIsActive=false;

                    String winner="Red";
                    if(gameState[winningPosition[0]]==0) winner="Green";

                    TextView winnerMessage= (TextView)findViewById ( R.id.winnerMessage ) ;
                    winnerMessage.setText (winner+ " has won." );
                    //Someone won the game.

                    LinearLayout layout=(LinearLayout)findViewById ( R.id.playAgainLayout );
                    layout.setVisibility ( View.VISIBLE );

                } else{
                    boolean gameIsOver=true;
                    for(int counterState:gameState){
                        if(counterState==2) gameIsOver=false;
                    }
                    if(gameIsOver){
                        TextView winnerMessage= (TextView)findViewById ( R.id.winnerMessage ) ;
                        winnerMessage.setText ( "It is a draw" );
                        //Someone won the game.
                        LinearLayout layout=(LinearLayout)findViewById ( R.id.playAgainLayout );
                        layout.setVisibility ( View.VISIBLE );

                    }
                }

            }
        }
    }

    public void playAgain(View view) {
        gameIsActive=true;
        LinearLayout layout=(LinearLayout)findViewById ( R.id.playAgainLayout );
        layout.setVisibility ( View.INVISIBLE );
         activePlayer = 0;
         for(int i=0;i<gameState.length;i++){
             gameState[i]=2;
         }
        GridLayout gridLayout=(GridLayout)findViewById ( R.id.gridLayout );
        for(int i=0;i<gridLayout.getChildCount ();i++){
            ((ImageView)gridLayout.getChildAt (i)).setImageResource ( 0 );

        }


    }
}
