package com.packetpub.mathgamechapter2;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameActivity extends Activity {

    TextView textObjectPartA, textObjectPartB, textObjectPartC, textObjectPartD;
    TextView textBoxedOne, textBoxedTwo, textBoxedThree, textBoxedFour;
    TextView textObjectScore;

    Spinner spinner1, spinner2, spinner3;
    Button buttonSubmit;

    String[]emptyBoxes = {"true", "true", "true", "true"};
    String[] userOperations = new String[3];
    int[] userNumbers = new int[4];

    double userAnswer = 0;
    int currentScore = 0;

    int numberBoxFull;

    String number1, number2, number3, number4, operation1, operation2, operation3;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        textObjectPartA =
                findViewById(R.id.firstNumber);
        textObjectPartB =
                findViewById(R.id.secondNumber);
        textObjectPartC =
                findViewById(R.id.thirdNumber);
        textObjectPartD =
                findViewById(R.id.fourthNumber);
        textBoxedOne =
                findViewById(R.id.textBoxedOne);
        textBoxedTwo =
                findViewById(R.id.textBoxedTwo);
        textBoxedThree =
                findViewById(R.id.textBoxedThree);
        textBoxedFour =
                findViewById(R.id.textBoxedFour);

        spinner1 =
                findViewById(R.id.spinner1);
        spinner2 =
                findViewById(R.id.spinner2);
        spinner3 =
                findViewById(R.id.spinner3);

        textObjectPartA.setOnTouchListener(new MyTouchListener());
        textObjectPartB.setOnTouchListener(new MyTouchListener());
        textObjectPartC.setOnTouchListener(new MyTouchListener());
        textObjectPartD.setOnTouchListener(new MyTouchListener());

        textBoxedOne.setOnDragListener(new BoxOneListener());
        textBoxedTwo.setOnDragListener(new BoxTwoListener());
        textBoxedThree.setOnDragListener(new BoxThreeListener());
        textBoxedFour.setOnDragListener(new BoxFourListener());

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(
                this, R.array.operation_array, R.layout.spinner_layout);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);
        spinner3.setAdapter(dataAdapter);


        textObjectScore =
                findViewById(R.id.textScore);

        setQuestion();
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        spinner1 =  findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                numberBoxFull=0;
                userOperations[0] = String.valueOf(spinner1.getSelectedItem());
                userOperations[1] = String.valueOf(spinner2.getSelectedItem());
                userOperations[2] = String.valueOf(spinner3.getSelectedItem());

                switch (userOperations[0]) {
                    case "\u00D7":
                        userAnswer = userNumbers[0]*userNumbers[1];
                        break;
                    case "\u00F7":
                        userAnswer = userNumbers[0]/userNumbers[1];
                        break;
                    case "+":
                        userAnswer = userNumbers[0]+userNumbers[1];
                        break;
                    case "\u2212":
                        userAnswer = userNumbers[0]-userNumbers[1];
                        break;
                }
                switch (userOperations[1]) {
                    case "\u00D7":
                        userAnswer = userAnswer*userNumbers[2];
                        break;
                    case "\u00F7":
                        userAnswer = userAnswer/userNumbers[2];
                        break;
                    case "+":
                        userAnswer = userAnswer+userNumbers[2];
                        break;
                    case "\u2212":
                        userAnswer = userAnswer-userNumbers[2];
                        break;
                }
                switch (userOperations[2]) {
                    case "\u00D7":
                        userAnswer = userAnswer*userNumbers[3];
                        break;
                    case "\u00F7":
                        userAnswer = userAnswer/userNumbers[3];
                        break;
                    case "+":
                        userAnswer = userAnswer+userNumbers[3];
                        break;
                    case "\u2212":
                        userAnswer = userAnswer-userNumbers[3];
                        break;
                }
                for (String i: emptyBoxes){
                    if (i!="true"){
                        numberBoxFull+=1;
                    }
                }
                if (numberBoxFull!=4){
                    Toast.makeText(GameActivity.this, "Fill in all boxes"+String.valueOf(numberBoxFull),Toast.LENGTH_LONG).show();
                }
                else if (numberBoxFull==4){
                    if (userAnswer!=24.0){
                        Toast.makeText(GameActivity.this, "Incorrect, possible answer is " + number1 + operation1 + number2
                                + operation2 + number3 + operation3 + number4,Toast.LENGTH_LONG).show();
                    }
                    else if (userAnswer==24.0){
                        Toast.makeText(GameActivity.this, "Correct",Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
    }





    private final class MyTouchListener implements View.OnTouchListener{
        public boolean onTouch(View view, MotionEvent motionEvent){
            if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, shadowBuilder, view, 0);
                }
                else {
                    view.startDrag(data, shadowBuilder, view, 0);
                }
                return true;
            } else {
                return false;
            }
        }
    }
    class BoxOneListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            View view = (View) event.getLocalState();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    view.animate()
                            .x(300)
                            .y(300)
                            .setDuration(1)
                            .start();
                    userNumbers[0] = 0;
                    if (emptyBoxes[0]=="firstNumber"){
                        if (view.getId() == R.id.firstNumber){
                            emptyBoxes[0]="true";
                            textObjectPartA.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                    }
                    else if (emptyBoxes[0] == "secondNumber"){
                        if (view.getId() == R.id.secondNumber){
                            emptyBoxes[0]="true";
                            textObjectPartB.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    else if (emptyBoxes[0] == "thirdNumber"){
                        if (view.getId() == R.id.thirdNumber){
                            emptyBoxes[0]="true";
                            textObjectPartC.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    else if (emptyBoxes[0] == "fourthNumber"){
                        if (view.getId() == R.id.fourthNumber){
                            emptyBoxes[0]="true";
                            textObjectPartD.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    break;
                case DragEvent.ACTION_DROP:
                    if (emptyBoxes[0]=="true"){
                        view.animate()
                                .x(textBoxedOne.getX()-30)
                                .y(textBoxedOne.getY()-30)
                                .setDuration(1)
                                .start();
                    }
                    if (view.getId() == R.id.firstNumber){
                        if (emptyBoxes[0]=="true") {
                            textObjectPartA.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[0] = "firstNumber";
                            userNumbers[0] = Integer.parseInt(textObjectPartA.getText().toString());
                        }
                    }
                    else if (view.getId() == R.id.secondNumber){
                        if (emptyBoxes[0]=="true") {
                            textObjectPartB.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[0] = "secondNumber";
                            userNumbers[0] = Integer.parseInt(textObjectPartB.getText().toString());
                        }
                    }
                    else if (view.getId() == R.id.thirdNumber){
                        if (emptyBoxes[0]=="true") {
                            textObjectPartC.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[0] = "thirdNumber";
                            userNumbers[0] = Integer.parseInt(textObjectPartC.getText().toString());

                        }
                    }
                    else if (view.getId() == R.id.fourthNumber){
                        if (emptyBoxes[0]=="true") {
                            textObjectPartD.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[0] = "fourthNumber";
                            userNumbers[0] = Integer.parseInt(textObjectPartD.getText().toString());
                        }
                    }
                    break;
            }
            return true;
        }
    }
    class BoxTwoListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            View view = (View) event.getLocalState();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    view.animate()
                            .x(300)
                            .y(300)
                            .setDuration(1)
                            .start();
                    userNumbers[1] = 0;
                    if (emptyBoxes[1]=="firstNumber"){
                        if (view.getId() == R.id.firstNumber){
                            emptyBoxes[1]="true";
                            textObjectPartA.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                    }
                    else if (emptyBoxes[1] == "secondNumber"){
                        if (view.getId() == R.id.secondNumber){
                            emptyBoxes[1]="true";
                            textObjectPartB.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    else if (emptyBoxes[1] == "thirdNumber"){
                        if (view.getId() == R.id.thirdNumber){
                            emptyBoxes[1]="true";
                            textObjectPartC.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    else if (emptyBoxes[1] == "fourthNumber"){
                        if (view.getId() == R.id.fourthNumber){
                            emptyBoxes[1]="true";
                            textObjectPartD.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    break;
                case DragEvent.ACTION_DROP:
                    if (emptyBoxes[1]=="true"){
                        view.animate()
                                .x(textBoxedTwo.getX()-30)
                                .y(textBoxedTwo.getY()-30)
                                .setDuration(1)
                                .start();
                    }
                    if (view.getId() == R.id.firstNumber){
                        if (emptyBoxes[1]=="true") {
                            textObjectPartA.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[1] = "firstNumber";
                            userNumbers[1] = Integer.parseInt(textObjectPartA.getText().toString());
                        }
                    }
                    else if (view.getId() == R.id.secondNumber){
                        if (emptyBoxes[1]=="true") {
                            textObjectPartB.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[1] = "secondNumber";
                            userNumbers[1] = Integer.parseInt(textObjectPartB.getText().toString());

                        }
                    }
                    else if (view.getId() == R.id.thirdNumber){
                        if (emptyBoxes[1]=="true") {
                            textObjectPartC.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[1] = "thirdNumber";
                            userNumbers[1] = Integer.parseInt(textObjectPartC.getText().toString());


                        }
                    }
                    else if (view.getId() == R.id.fourthNumber){
                        if (emptyBoxes[1]=="true") {
                            textObjectPartD.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[1] = "fourthNumber";
                            userNumbers[1] = Integer.parseInt(textObjectPartD.getText().toString());

                        }
                    }
                    break;
            }
            return true;
        }
    }
    class BoxThreeListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            View view = (View) event.getLocalState();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    view.animate()
                            .x(300)
                            .y(300)
                            .setDuration(1)
                            .start();
                    userNumbers[2] = 0;
                    if (emptyBoxes[2]=="firstNumber"){
                        if (view.getId() == R.id.firstNumber){
                            emptyBoxes[2]="true";
                            textObjectPartA.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                    }
                    else if (emptyBoxes[2] == "secondNumber"){
                        if (view.getId() == R.id.secondNumber){
                            emptyBoxes[2]="true";
                            textObjectPartB.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    else if (emptyBoxes[2] == "thirdNumber"){
                        if (view.getId() == R.id.thirdNumber){
                            emptyBoxes[2]="true";
                            textObjectPartC.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    else if (emptyBoxes[2] == "fourthNumber"){
                        if (view.getId() == R.id.fourthNumber){
                            emptyBoxes[2]="true";
                            textObjectPartD.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    break;
                case DragEvent.ACTION_DROP:
                    if (emptyBoxes[2]=="true"){
                        view.animate()
                                .x(textBoxedThree.getX()-30)
                                .y(textBoxedThree.getY()-30)
                                .setDuration(1)
                                .start();
                    }
                    if (view.getId() == R.id.firstNumber){
                        if (emptyBoxes[2]=="true") {
                            textObjectPartA.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[2] = "firstNumber";
                            userNumbers[2] = Integer.parseInt(textObjectPartA.getText().toString());

                        }
                    }
                    else if (view.getId() == R.id.secondNumber){
                        if (emptyBoxes[2]=="true") {
                            textObjectPartB.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[2] = "secondNumber";
                            userNumbers[2] = Integer.parseInt(textObjectPartB.getText().toString());

                        }
                    }
                    else if (view.getId() == R.id.thirdNumber){
                        if (emptyBoxes[2]=="true") {
                            textObjectPartC.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[2] = "thirdNumber";
                            userNumbers[2] = Integer.parseInt(textObjectPartC.getText().toString());


                        }
                    }
                    else if (view.getId() == R.id.fourthNumber){
                        if (emptyBoxes[2]=="true") {
                            textObjectPartD.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[2] = "fourthNumber";
                            userNumbers[2] = Integer.parseInt(textObjectPartD.getText().toString());

                        }
                    }
                    break;
            }
            return true;
        }
    }
    class BoxFourListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            View view = (View) event.getLocalState();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    view.animate()
                            .x(300)
                            .y(300)
                            .setDuration(1)
                            .start();
                    userNumbers[3] = 0;
                    if (emptyBoxes[3]=="firstNumber"){
                        if (view.getId() == R.id.firstNumber){
                            emptyBoxes[3]="true";
                            textObjectPartA.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                    }
                    else if (emptyBoxes[3] == "secondNumber"){
                        if (view.getId() == R.id.secondNumber){
                            emptyBoxes[3]="true";
                            textObjectPartB.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    else if (emptyBoxes[3] == "thirdNumber"){
                        if (view.getId() == R.id.thirdNumber){
                            emptyBoxes[3]="true";
                            textObjectPartC.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    else if (emptyBoxes[3] == "fourthNumber"){
                        if (view.getId() == R.id.fourthNumber){
                            emptyBoxes[3]="true";
                            textObjectPartD.setTextColor(Color.parseColor("#FFFFFF"));

                        }
                    }
                    break;
                case DragEvent.ACTION_DROP:
                    if (emptyBoxes[3]=="true"){
                        view.animate()
                                .x(textBoxedFour.getX()-30)
                                .y(textBoxedFour.getY()-30)
                                .setDuration(1)
                                .start();
                    }
                    if (view.getId() == R.id.firstNumber){
                        if (emptyBoxes[3]=="true") {
                            textObjectPartA.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[3] = "firstNumber";
                            userNumbers[3] = Integer.parseInt(textObjectPartA.getText().toString());

                        }
                    }
                    else if (view.getId() == R.id.secondNumber){
                        if (emptyBoxes[3]=="true") {
                            textObjectPartB.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[3] = "secondNumber";
                            userNumbers[3] = Integer.parseInt(textObjectPartB.getText().toString());

                        }
                    }
                    else if (view.getId() == R.id.thirdNumber){
                        if (emptyBoxes[3]=="true") {
                            textObjectPartC.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[3] = "thirdNumber";
                            userNumbers[3] = Integer.parseInt(textObjectPartC.getText().toString());


                        }
                    }
                    else if (view.getId() == R.id.fourthNumber){
                        if (emptyBoxes[3]=="true") {
                            textObjectPartD.setTextColor(Color.parseColor("#000000"));
                            emptyBoxes[3] = "fourthNumber";
                            userNumbers[3] = Integer.parseInt(textObjectPartD.getText().toString());

                        }
                    }
                    break;
            }
            return true;
        }
    }



    public void setQuestion(){
        TwentyFourComputation compute = new TwentyFourComputation();
        String[][] numberArray = compute.returnNumbersOperations();
        int numberRange = 11419;
        Random randInt = new Random();
        int index1 = randInt.nextInt(numberRange);
        number1 = numberArray[index1][0];
        number2 = numberArray[index1][2];
        number3 = numberArray[index1][4];
        number4 = numberArray[index1][6];
        operation1 = numberArray[index1][1];
        operation2 = numberArray[index1][3];
        operation3 = numberArray[index1][5];

        textObjectPartA.setText("" + number1);
        textObjectPartB.setText("" + number2);
        textObjectPartC.setText("" + number3);
        textObjectPartD.setText("" + number4);


    }

    void updateScoreandLevel(int answerGiven){

        textObjectScore.setText("Score: " + currentScore);
    }

}
