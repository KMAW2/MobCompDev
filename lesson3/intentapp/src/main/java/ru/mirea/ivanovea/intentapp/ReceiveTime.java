package ru.mirea.ivanovea.intentapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiveTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        String message = getIntent().getStringExtra("message");

        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}
