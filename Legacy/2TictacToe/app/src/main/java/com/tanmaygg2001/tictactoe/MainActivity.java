/*Made by Tanmay Garg*/

package com.tanmaygg2001.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean isGameActive = true;
    int currentPlayer = 0;//0-> X , 1-> O
    int[] gameStatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};//implies status of each cell //Initially all cells are empty ,when the game starts
    // 0 - X
    // 1 - O
    // 2 - blank space no picture(no image view) OR null

    int[][] winPossibleCombinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {0, 4, 8}};


    public void playerTap(View view) {//view parameter is passed here.(we can also pass ImageView directly but due to so many types of views we use type casting
        ImageView img = (ImageView) view;//Typecast view to imageView.
        int tappedImageNumber = Integer.parseInt(img.getTag().toString());//gets the tag of the image that was allotted to it.
//        if (!isGameActive) {
////            gameReset(view);
//        }
        if (gameStatus[tappedImageNumber] == 2 && isGameActive) {
            gameStatus[tappedImageNumber] = currentPlayer;
            img.setTranslationY(-1000f); //to make fancier

            if (currentPlayer == 0) {
                img.setImageResource(R.drawable.cross);
                currentPlayer = 1;
            } else {
                img.setImageResource(R.drawable.zero);
                currentPlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(250); //to make fancier
        }
        for (int[] winPosition : winPossibleCombinations) { //for winPosition in array winPossibleCombinations do this :-
            if (gameStatus[winPosition[0]] == gameStatus[winPosition[1]] && gameStatus[winPosition[1]] == gameStatus[winPosition[2]] && gameStatus[winPosition[0]] != 2) {
                String outputMessage;
                isGameActive = false;
                if (gameStatus[winPosition[0]] == 0) {
                    outputMessage = "Player X has won the match!!";
                } else if (gameStatus[winPosition[0]] == 1) {
                    outputMessage = "Player O has won the match!!";
                } else {
                    outputMessage = "--DRAW--";
                }

                Toast.makeText(this, outputMessage, Toast.LENGTH_SHORT).show();
            }
        }

    }

//    public void gameReset(View view) {
//        isGameActive = true;
//        currentPlayer = 0;
//        for (int i = 0; i < 9; i++) {
//            gameStatus[i] = 2;
//        }
//        ImageView imageView = (ImageView) view;
//        imageView.setImageResource(R.drawable.blank);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {//OnCreate method runs the setContentView function that renders the xml file on launching of the application.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}