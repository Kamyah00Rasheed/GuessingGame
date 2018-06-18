package com.skills.interapt.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.skills.interapt.guessinggame.GameActivity.winningNumber;

public class ResultsActivity extends AppCompatActivity {

    private Button playAgainButton;
    private TextView correctNumberTextview;
    private TextView resultsTextview;
    private ImageView resultImageView;
    private Intent intent;


    //TODO fix restarting game
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        correctNumberTextview = findViewById(R.id.number_textview);
        playAgainButton = findViewById(R.id.restart_button);
        resultsTextview = findViewById(R.id.results_textview);
        resultImageView = findViewById(R.id.winning_imageview);
        //Get winning number from Intent if user has lost
        setListener();
        intent = getIntent();
        if(intent.hasExtra(winningNumber)) {
            setLosingData();
        }
    }


    private void setLosingData() {
        int winningNumber = intent.getIntExtra(GameActivity.winningNumber, 0);
        resultsTextview.setText(R.string.you_lost);
        correctNumberTextview.setText(getString(R.string.winning_number, winningNumber));
        correctNumberTextview.setVisibility(View.VISIBLE);
        resultImageView.setImageResource(R.drawable.losingsadface);
    }

    //Method to handle setting the listener for the playAgainButton
    private void setListener() {
       playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });

    }
}
