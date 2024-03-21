package ru.mirea.ivanovea.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textViewStudent);
        tv.setText("New text in MIREA");

        Button mybutton = findViewById(R.id.buttonStudent);
        mybutton.setText("MireaButton");

        CheckBox mycheck = findViewById(R.id.checkBoxStudent);
        mycheck.setChecked(true);
    }
}