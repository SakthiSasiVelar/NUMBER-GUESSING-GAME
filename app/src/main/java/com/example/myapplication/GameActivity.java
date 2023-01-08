package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView textviewLast,textViewRight,textViewHint;
    EditText editText;
    Button buttonConfirm;

     boolean twoDigit,threeDigit,fourDigit;

     Random random = new Random();
     int r;
     int remainingRights = 0;
     int userAttempts = 0;
     ArrayList<Integer> guessesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textviewLast =findViewById(R.id.textViewLast);
        textViewRight = findViewById(R.id.textViewRight);
        textViewHint = findViewById(R.id.textViewHint);
        editText = findViewById(R.id.editTextNumber);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        twoDigit = getIntent().getBooleanExtra("two",false);
        threeDigit =getIntent().getBooleanExtra("three",false);
        fourDigit =getIntent().getBooleanExtra("four",false);

        if(twoDigit)
        {
            r = random.nextInt(90)+10;
            remainingRights =6;

        }
        if(threeDigit){
            r= random.nextInt(900)+100;
            remainingRights = 10;

        }
        if(fourDigit){
            r = random.nextInt(9000)+1000;
            remainingRights = 13;
        }
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = editText.getText().toString();
                if (guess.equals("")) {
                    Toast.makeText(GameActivity.this, "please enter your guess", Toast.LENGTH_LONG).show();
                }
                else {

                    textviewLast.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);


                    userAttempts++;
                    remainingRights--;

                    int userGuess = Integer.parseInt(guess);
                    guessesList.add(userGuess);
                    textviewLast.setText("Your last guess : " + userGuess);
                    textViewRight.setText("Your remaining rights : " +remainingRights);

                    if(r == userGuess)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("NUMBER GUESSING GAME");
                        builder.setCancelable(false);
                        builder.setMessage("Congratulations . My guess was " + r + "\n\n You found" +
                                " my guess in  " + userAttempts + " attempt." + " Your guess list : "
                         + guessesList + "." + "Would you like to try again ? ");


                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });


                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);

                            }
                        });
                        builder.create().show();
                    }
                    if(r > userGuess){
                        textViewHint.setText("INCREASE YOUR GUESS");
                    }
                    if(r < userGuess){
                        textViewHint.setText("DECREASE YOUR GUESS");

                    }
                    if(remainingRights == 0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("NUMBER GUESSING GAME");
                        builder.setCancelable(false);
                        builder.setMessage("Sorry. Your attempts to guess the number was over ."
                                 +" My guess was "+ r + " Your guess list : "
                                + guessesList + "." + "Would you like to try again ? ");


                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });


                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);

                            }
                        });
                        builder.create().show();
                    }

                    editText.setText("");



                }

            }
        });
    }
}