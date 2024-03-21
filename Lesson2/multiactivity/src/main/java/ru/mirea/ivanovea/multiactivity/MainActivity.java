package ru.mirea.ivanovea.multiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "This is onCreate!");
    }

    public void OnClickNewActivity(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        EditText edit = findViewById(R.id.editTextText);
        intent.putExtra("key", edit.getText().toString());
        startActivity(intent);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "This is onStart!");
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