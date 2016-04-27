package com.example.rahall.jokedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        Intent intent = getIntent();
        String joke = "";
        joke = intent.getStringExtra("joke");
        TextView displayJoke = (TextView) this.findViewById(R.id.display_joke);
        displayJoke.setText(joke);
    }
}
