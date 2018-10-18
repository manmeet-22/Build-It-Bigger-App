package com.manmeet.jokefromandroidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_android_jokes);

        Intent receivedIntent = getIntent();
        Bundle extras = receivedIntent.getExtras();
        String joke = extras.getString("joke");

        TextView jokeTextView = findViewById(R.id.textview_android_joke);
        jokeTextView.setText(joke);
    }
}
