package ru.mirea.ivanovea.notebook;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText fileNameEditText;
    private EditText quoteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileNameEditText = findViewById(R.id.fileNameEditText);
        quoteEditText = findViewById(R.id.quoteEditText);
    }

    public void onSaveButtonClick(View view) {
        String fileName = fileNameEditText.getText().toString();
        String quote = quoteEditText.getText().toString();

        if (!fileName.isEmpty() && !quote.isEmpty()) {
            saveToFile(fileName, quote);
        } else {
            Toast.makeText(this, "Введите название файла и цитату", Toast.LENGTH_SHORT).show();
        }
    }

    public void onLoadButtonClick(View view) {
        String fileName = fileNameEditText.getText().toString();

        if (!fileName.isEmpty()) {
            String quote = loadFromFile(fileName);
            if (quote != null) {
                quoteEditText.setText(quote);
            } else {
                Toast.makeText(this, "Файл не найден", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Введите название файла", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveToFile(String fileName, String quote) {
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Directory_Documents");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(quote.getBytes());
            Toast.makeText(this, "Данные успешно сохранены в файл", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка сохранения данных", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String loadFromFile(String fileName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Directory_Documents", fileName);
        if (file.exists()) {
            FileInputStream fis = null;
            BufferedReader reader = null;
            try {
                fis = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(fis));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                return stringBuilder.toString().trim();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}