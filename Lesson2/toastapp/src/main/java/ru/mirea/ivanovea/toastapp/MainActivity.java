package ru.mirea.ivanovea.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClickCount(View view){
        EditText count = findViewById(R.id.editTextText);
        Integer length = count.length();
        String output = String.format("СТУДЕНТ № 8 ГРУППА БИСО-03-19 Количество символов - %d", length);
        Toast toast = Toast.makeText(this, output, Toast.LENGTH_LONG);
        toast.show();
    }
}