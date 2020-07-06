package com.example.admin2.liontiger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    enum Player{
       ONE,TWO,NO
    }
    Player currentPlayer=Player.ONE;
    Player[] playerChoices=new Player[9];
    int[][] winnerRowsColumns={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{1,4,7},{2,5,8},{0,3,6}};
    private boolean gameOver=false;
    private Button btnReset;
    private GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*playerChoices[0]=Player.NO;
        playerChoices[1]=Player.NO;
        playerChoices[2]=Player.NO;
        playerChoices[3]=Player.NO;
        playerChoices[4]=Player.NO;
        playerChoices[5]=Player.NO;
        playerChoices[6]=Player.NO;
        playerChoices[7]=Player.NO;
        playerChoices[8]=Player.NO;*/
        for(int index=0;index<playerChoices.length;index++){
            playerChoices[index]=Player.NO;
        }
        btnReset=findViewById(R.id.btnReset);
        gridLayout=findViewById(R.id.gridLayout);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              resetTheGame();
            }
        });

    }
    public void imageViewIsTapped(View imageView){
       ImageView tappedImageView=(ImageView)imageView;
        int tiTag=Integer.parseInt(tappedImageView.getTag().toString());
        if(playerChoices[tiTag]==Player.NO&& gameOver==false) {
            tappedImageView.setTranslationX(-2000);

            playerChoices[tiTag] = currentPlayer;
            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);
            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();
            String winnerOfGame = "";
            for (int[] winnerColums : winnerRowsColumns) {
                if (playerChoices[winnerColums[0]] == playerChoices[winnerColums[1]] && playerChoices[winnerColums[1]] == playerChoices[winnerColums[2]] && playerChoices[winnerColums[0]] != Player.NO) {
                    btnReset.setVisibility(View.VISIBLE);
                    gameOver=true;

                    if (currentPlayer == Player.ONE) {
                        winnerOfGame = "Player Two";
                    } else if (currentPlayer == Player.TWO) {
                        winnerOfGame = "Player One";
                    }

                    Toast.makeText(this, winnerOfGame + " is the winner", Toast.LENGTH_LONG).show();

                }


            }
            btnReset.setVisibility(View.VISIBLE);
        }
    }
    //Reset Game
    private void resetTheGame(){
        for (int index=0;index<gridLayout.getChildCount();index++){
            ImageView imageView= (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);

        }
       currentPlayer=Player.ONE;
       /* playerChoices[0]=Player.NO;
        playerChoices[1]=Player.NO;
        playerChoices[2]=Player.NO;
        playerChoices[3]=Player.NO;
        playerChoices[4]=Player.NO;
        playerChoices[5]=Player.NO;
        playerChoices[6]=Player.NO;
        playerChoices[7]=Player.NO;
        playerChoices[8]=Player.NO;
        */
       for(int index=0;index<playerChoices.length;index++){
           playerChoices[index]=Player.NO;
       }
       gameOver=false;
        btnReset.setVisibility(View.INVISIBLE);

    }
}
