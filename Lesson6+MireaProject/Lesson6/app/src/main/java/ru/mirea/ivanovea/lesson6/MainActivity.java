package ru.mirea.ivanovea.lesson6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText groupNumberEditText;
    EditText listNumberEditText;
    EditText favoriteMovieEditText;
    Button saveButton;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupNumberEditText = findViewById(R.id.editTextText);
        listNumberEditText = findViewById(R.id.editTextText2);
        favoriteMovieEditText = findViewById(R.id.editTextText3);
        saveButton = findViewById(R.id.button);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        groupNumberEditText.setText(sharedPreferences.getString("groupNumber", ""));
        listNumberEditText.setText(sharedPreferences.getString("listNumber", ""));
        favoriteMovieEditText.setText(sharedPreferences.getString("favoriteMovie", ""));

        saveButton.setOnClickListener(v -> saveData());
    }

    private void saveData() {
        String groupNumber = groupNumberEditText.getText().toString();
        String listNumber = listNumberEditText.getText().toString();
        String favoriteMovie = favoriteMovieEditText.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("groupNumber", groupNumber);
        editor.putString("listNumber", listNumber);
        editor.putString("favoriteMovie", favoriteMovie);
        editor.apply();
    }
}
