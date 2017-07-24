package com.packetpub.mathgamechapter2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameActivity extends AppCompatActivity{

    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView textObjectPartC;
    TextView textObjectPartD;
    TextView textObjectScore;
    TextView textObjectLevel;

    int currentScore = 0;
    int currentLevel = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /*Here we get a working object based on either the button
        or TextView class and base as well as link our new objects
        directly to the appropriate UI elements that we created previously.
         */

        textObjectPartA =
                (TextView)findViewById(R.id.textPartA);
        textObjectPartB =
                (TextView)findViewById(R.id.textPartB);
        textObjectPartC =
                (TextView) findViewById(R.id.textPartC);
        textObjectPartD =
                (TextView) findViewById(R.id.textPartD);


        textObjectScore =
                (TextView)findViewById(R.id.textScore);
        textObjectLevel =
                (TextView)findViewById(R.id.textLevel);

        setQuestion();

    }


    void setQuestion(){
        TwentyFourComputation compute = new TwentyFourComputation();
        double[][] numberArray = compute.generateNumbers();
        int numberRange = 3185;
        Random randInt = new Random();
        int index1 = randInt.nextInt(numberRange);


        int partA = (int)numberArray[index1][0];
        int partB = (int)numberArray[index1][1];
        int partC = (int)numberArray[index1][2];
        int partD = (int)numberArray[index1][3];


        textObjectPartA.setText("" + partA);
        textObjectPartB.setText("" + partB);
        textObjectPartC.setText("" + partC);
        textObjectPartD.setText("" + partD);



    }

    void updateScoreandLevel(int answerGiven){

        textObjectScore.setText("Score: " + currentScore);
        textObjectLevel.setText("Level: " + currentLevel);
    }

}
