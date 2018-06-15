package com.skills.interapt.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);

        setListener();

    }

    private void setListener() {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startGame();

                }
            });

        }
    //Method to handle the intent to go from this activity to the GameActivity
    private void startGame() {
        Intent playGame = new Intent(this, GameActivity.class);
        startActivity(playGame);
    }
}

