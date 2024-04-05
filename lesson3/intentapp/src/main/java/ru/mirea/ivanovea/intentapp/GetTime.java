package ru.mirea.ivanovea.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime extends AppCompatActivity {
    String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long dateInMillis = System.currentTimeMillis();
        String format = "HH:mm:ss";
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        dateString = sdf.format(new Date(dateInMillis));
    }

    public void OnClick(View view) {
        Intent intent = new Intent(this, ReceiveTime.class);
        intent.putExtra("message", "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА (8) " +
                "ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ 64, а текущее время " + dateString);
        startActivity(intent);
    }
}

