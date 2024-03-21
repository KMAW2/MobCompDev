package ru.mirea.ivanovea.activitylifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "This is OnCreate!");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "This is OnStart!");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "This is onSaveInstanceState!");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "This is OnRestoreInstanceState!");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, "This is OnPostCreate!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "This is onResume!");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "This is onPostResume!");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "This is onAttachedToWindow!");
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
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "This is onDetachedFromWindow!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "This is onRestart!");
    }
}