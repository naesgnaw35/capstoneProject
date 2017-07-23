package com.example.android.simplearrayexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] ourArray;
        ourArray = new int[3];
        ourArray[0] = 100000;
        ourArray[1] = 123;
        ourArray[2] = 145;
        Log.i("info", "[0] = " +ourArray[0]);
        Log.i("info", "[1] = " +ourArray[1]);
        Log.i("info", "[2] = " +ourArray[2]);

        int answer = ourArray[0] +
                ourArray[1] +
                ourArray[2];
        Log.i("info", "Answer: " + answer);

    }
}

