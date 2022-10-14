package com.bmszcpatrik.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textViewPlayerChoice, textViewComputerChoice, textViewResultLabel, textViewPlayerScore, textViewComputerScore, textViewDebug;
    private ImageView imgViewPlayerChoice, imgViewComputerChoice;
    private Button btnR, btnP, btnS;
    private int playerChoice;
    /**
     * 0 - Rock, 1 - Paper, 2 - Scissors
     */
    private int computerChoice;
    /**
     * 0 - Rock, 1 - Paper, 2 - Scissors
     */
    private int playerScore = 0;
    private int computerScore = 0;
    private Random rng = new Random();
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerChoice = 0;
                imgViewPlayerChoice.setImageResource(R.drawable.rock);
                roll(playerChoice);
            }
        });

        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerChoice = 1;
                imgViewPlayerChoice.setImageResource(R.drawable.paper);
                roll(playerChoice);
            }
        });

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerChoice = 2;
                imgViewPlayerChoice.setImageResource(R.drawable.scissors);
                roll(playerChoice);
            }
        });

    }
    private void gameOver(boolean winner){
        /**false - player, true - computer*/
        if (winner == false){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Nyertel!")
                    .setMessage("Akarsz ujat jatszani?")
                    .setPositiveButton("Kovetkezo kor", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            playerScore = 0;
                            computerScore = 0;
                            imgViewComputerChoice.setImageResource(R.drawable.rock);
                            imgViewPlayerChoice.setImageResource(R.drawable.rock);
                            textViewComputerScore.setText("Computer: 0");
                            textViewPlayerScore.setText("Ember: 0");

                        }
                    }) //TODO: kovetkezo kor
                    .setNegativeButton("Kilepes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }else{
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Vesztettel!")
                    .setMessage("Akarsz ujat jatszani?")
                    .setPositiveButton("Kovetkezo kor", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            playerScore = 0;
                            computerScore = 0;
                            imgViewComputerChoice.setImageResource(R.drawable.rock);
                            imgViewPlayerChoice.setImageResource(R.drawable.rock);
                            textViewComputerScore.setText("Computer: 0");
                            textViewPlayerScore.setText("Ember: 0");

                        }
                    }) //TODO: kovetkezo kor
                    .setNegativeButton("Kilepes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
    }
    private void roll(int paramPlayerChoice) {

        computerChoice = rng.nextInt((2 - 0) + 1) + 0;
        if (computerChoice == 0) {
            imgViewComputerChoice.setImageResource(R.drawable.rock);
        } else if (computerChoice == 1) {
            imgViewComputerChoice.setImageResource(R.drawable.paper);
        } else {
            imgViewComputerChoice.setImageResource(R.drawable.scissors);
        }


        textViewDebug.setText("Computer choice: " + String.valueOf(computerChoice));
        if (paramPlayerChoice == 0 && computerChoice == 1) {
            computerWin();
        } else if (paramPlayerChoice == 1 && computerChoice == 0) {
            playerWin();
        } else if (paramPlayerChoice == 0 && computerChoice == 2) {
            playerWin();
        } else if (paramPlayerChoice == 2 && computerChoice == 0) {
            computerWin();
        } else if (paramPlayerChoice == 1 && computerChoice == 2) {
            computerWin();
        } else if (paramPlayerChoice == 2 && computerChoice == 1) {
            playerWin();
        } else if (paramPlayerChoice == computerChoice) {
            draw();
        }

    }

    private void draw() {
        Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_SHORT).show();
    }

    private void computerWin() {
        computerScore++;
        if (computerScore == 3) {
            gameOver(true);
        }

        textViewComputerScore.setText(String.format("Computer: %d", computerScore));
        Toast.makeText(MainActivity.this, "Computer nyert", Toast.LENGTH_SHORT).show();
    }


    private void playerWin() {
        playerScore++;
        if(playerScore == 3){
            gameOver(false);
        }
        textViewPlayerScore.setText(String.format("Player: %d", playerScore));
        Toast.makeText(MainActivity.this, "Játékos nyert", Toast.LENGTH_SHORT).show();

    }

    private void init() {
        textViewPlayerChoice = findViewById(R.id.textViewPlayerChoice);
        textViewComputerChoice = findViewById(R.id.textViewComputerChoice);
        textViewPlayerScore = findViewById(R.id.textViewPlayerScore);
        textViewComputerScore = findViewById(R.id.textViewComputerScore);
        textViewResultLabel = findViewById(R.id.textViewResultLabel);
        textViewDebug = findViewById(R.id.textViewDebug);

        imgViewComputerChoice = findViewById(R.id.imgViewComputerChoice);
        imgViewPlayerChoice = findViewById(R.id.imgViewPlayerChoice);

        btnR = findViewById(R.id.btnR);
        btnP = findViewById(R.id.btnP);
        btnS = findViewById(R.id.btnS);



    }
}