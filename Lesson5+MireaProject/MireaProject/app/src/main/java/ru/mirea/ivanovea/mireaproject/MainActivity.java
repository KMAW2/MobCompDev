package ru.mirea.ivanovea.mireaproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import ru.mirea.ivanovea.mireaproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Camera.class);
            startActivity(intent);
        });

        binding.button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Audio.class);
            startActivity(intent);
        });

        binding.button3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Accelerometer.class);
            startActivity(intent);
        });
    }
}
