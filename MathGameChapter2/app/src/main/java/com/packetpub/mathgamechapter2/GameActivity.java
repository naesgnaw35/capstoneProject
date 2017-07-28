package com.packetpub.mathgamechapter2;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;


public class GameActivity extends AppCompatActivity{

    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView textObjectPartC;
    TextView textObjectPartD;
    TextView textBoxedOne;
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
                (TextView)findViewById(R.id.firstNumber);
        textObjectPartB =
                (TextView)findViewById(R.id.thirdNumber);
        textObjectPartC =
                (TextView) findViewById(R.id.fourthNumber);
        textObjectPartD =
                (TextView) findViewById(R.id.secondNumber);
        textBoxedOne =
                (TextView) findViewById(R.id.textBoxedOne);



        textObjectScore =
                (TextView)findViewById(R.id.textScore);
        textObjectLevel =
                (TextView)findViewById(R.id.textLevel);

        setQuestion();

        textObjectPartA.setOnLongClickListener(longClickListener);
        textObjectPartB.setOnLongClickListener(longClickListener);
        textObjectPartC.setOnLongClickListener(longClickListener);
        textObjectPartD.setOnLongClickListener(longClickListener);
        textBoxedOne.setOnDragListener(dragListener);


    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View v){
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(data, myShadowBuilder, v, 0);
            }
            else {
                v.startDrag(data, myShadowBuilder, v, 0);
            }


            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener(){

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();

            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();
                    if (view.getId() == R.id.firstNumber){
                        textBoxedOne.setText(textObjectPartA.getText());
                    }
                    if (view.getId() == R.id.secondNumber){
                        textBoxedOne.setText(textObjectPartD.getText());
                    }


                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }
            return true;
        }
    };


    void setQuestion(){
        TwentyFourComputation compute = new TwentyFourComputation();
        double[][] numberArray = compute.generateNumbers();
        int numberRange = 11419;
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
