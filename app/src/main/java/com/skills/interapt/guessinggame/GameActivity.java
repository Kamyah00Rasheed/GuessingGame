package com.skills.interapt.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private Button guessButton;
    private TextView clue;
    private EditText guess;

    private int generatedNumber;
    private int numberOfGuesses = 0;
    private final int MAX_GUESS_COUNT = 4;
    public static final String winningNumber = "WINNING_NUMBER";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        guessButton = findViewById(R.id.submit_guess_button);
        clue = findViewById(R.id.clue_textview);
        guess = findViewById(R.id.guess_edittext);

        //This generates a random number between 1-100
        generatedNumber = (int) Math.ceil(Math.random() * 100);

        //Toast.makeText(this, Integer.toString(generatedNumber), Toast.LENGTH_SHORT).show();

        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        generatedNumber= (int) Math.ceil(Math.random() * 100);
        numberOfGuesses=0;
        clue.setVisibility(View.INVISIBLE);
        guess.setText("");
    }

    private void setListener() {
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateGuess();
            }
        });

    }

    //Check to make sure the user has put in a valid number
    private void validateGuess() {

        try {
            int userGuess = Integer.parseInt(guess.getText().toString());
            if (userGuess > 100 || userGuess <= 0) {
                clue.setText(R.string.enter_number_1_100);
                clue.setVisibility(View.VISIBLE);
                guess.setText("");
            } else {
                checkGuess(userGuess);
            }
        } catch (NumberFormatException nfe) {
            clue.setText(R.string.enter_number);
            clue.setVisibility(View.VISIBLE);
        }
    }

    //This method will take the input and check it against the generatedNumber. Depending on the outcome it will change the view
    //Accordingly or take us to the ResultsActivity
    private void checkGuess(int userGuess) {
        if (userGuess == generatedNumber) {
            Intent winner = new Intent(this, ResultsActivity.class);
            startActivity(winner);
            // Goes to ResultsActivity. User has guessed correctly.
        } else if (numberOfGuesses == MAX_GUESS_COUNT) {
            Intent loser = new Intent(this, ResultsActivity.class);
            loser.putExtra(winningNumber, generatedNumber );
            startActivity(loser);
            // Goes to ResultsActivity. User has ran out of chances.
        } else if (userGuess < generatedNumber) {
            //TextView to says higher, the visibility is VISIBLE, and guess EditText is ""
            //Incremented numberOfGuesses by 1
            clue.setText(R.string.higher);
            clue.setVisibility(View.VISIBLE);
            guess.setText("");
            numberOfGuesses++;
            Toast.makeText(this, getString(R.string.chances_left, (5- numberOfGuesses)), Toast.LENGTH_SHORT).show();
        } else if (userGuess > generatedNumber) {
            clue.setText(R.string.lower);
            clue.setVisibility(View.VISIBLE);
            guess.setText("");
            numberOfGuesses++;
            Toast.makeText(this, getString(R.string.chances_left, (5- numberOfGuesses)), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        //Removed super.onBackPressed(); to make sure that if the back button is pressed nothing will happen.
    }
}
