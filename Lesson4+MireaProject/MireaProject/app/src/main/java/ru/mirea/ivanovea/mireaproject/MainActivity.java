package ru.mirea.ivanovea.mireaproject;

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

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new mirea())
                .commit();
    }
}
