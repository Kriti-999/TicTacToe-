package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningState={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean active=true;
    public void drop(View view){

        ImageView counter= (ImageView) view;
        int state=Integer.parseInt(counter.getTag().toString());
        if(gameState[state]==2 && active==true) {
            counter.setTranslationY(-1500);
            Log.i("info",counter.getTag().toString());

            gameState[state] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(500);
            for (int[] winningPosition : winningState) {
                if (gameState[winningPosition[0]] != 2 && gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]) {
                    String winner;
                    active=false;
                    if (activePlayer == 0) {
                        winner = "RED";
                    } else {
                        winner = "YELLOW";
                    }

                    Button playAgainButton=(Button) findViewById(R.id.button);
                    TextView winnerTextView=findViewById((R.id.winngText));
                    winnerTextView.setText(winner+" !");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public  void playAgain(View view){
        Button playAgainButton=(Button) findViewById(R.id.button);
        TextView winnerTextView=findViewById((R.id.winngText));

        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter=(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }
        for(int i=0;i<9;i++){
            gameState[i]=2;
        }
        activePlayer=0;


        active=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}