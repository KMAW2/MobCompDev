package ru.mirea.ivanovea.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i(TAG, "This is onCreate!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView out = findViewById(R.id.textView);
        String text = (String) getIntent().getSerializableExtra("key");
        out.setText(text);
        Log.i(TAG, "This is OnStart!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "This is onResume!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "This is onPause!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "This is onStop!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "This is onDestroy!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "This is onRestart!");
    }
}