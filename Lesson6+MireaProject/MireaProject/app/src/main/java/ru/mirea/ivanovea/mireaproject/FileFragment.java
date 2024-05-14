package ru.mirea.ivanovea.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ru.mirea.ivanovea.mireaproject.databinding.FragmentFileBinding;


public class FileFragment extends Fragment {
    private boolean isFileSaved = false;
    private boolean isFileLoaded = false;
    private FragmentFileBinding binding;

    public FileFragment() {
        // Required empty public constructor
    }


    public static FileFragment newInstance(String param1, String param2) {
        FileFragment fragment = new FileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        binding.loadButton.setOnClickListener(v -> {
            onLoadButtonClick();
        });
        binding.saveButton.setOnClickListener(v -> {
            onSaveButtonClick();
        });
        binding.countButton.setOnClickListener(v -> {
            if (isFileSaved || isFileLoaded) {
                onCountButtonClick();
                binding.counttextView2.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(requireContext(), "Сначала сохраните или загрузите файл", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onCountButtonClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Посчитать слова в тексте?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = binding.textEditText.getText().toString();
                String fileName = binding.fileNameEditText.getText().toString();

                if (!text.isEmpty() && !fileName.isEmpty()) {
                    String fileText = loadFromFile(fileName);
                    if (fileText != null) {
                        if (text.equals(fileText)) {
                            countWords();
                        } else {
                            Toast.makeText(requireContext(), "Сохраните измененный текст в файл", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(requireContext(), "Файл не найден", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Введите текст", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }


    private void countWords() {
        String text = binding.textEditText.getText().toString();

        if (!text.isEmpty()) {
            String[] words = text.split("\\s+");
            int wordCount = words.length;
            binding.counttextView2.setText("Количество слов в тексте: " + wordCount);
        }
    }


    public void onSaveButtonClick() {
        String fileName = binding.fileNameEditText.getText().toString();
        String text = binding.textEditText.getText().toString();

        if (!fileName.isEmpty() && !text.isEmpty()) {
            saveToFile(fileName, text);
        } else {
            if (fileName.isEmpty()){
                Toast.makeText(requireContext(), "Введите название файла", Toast.LENGTH_SHORT).show();
            } else {
                if (text.isEmpty()) {
                    Toast.makeText(requireContext(), "Введите текст", Toast.LENGTH_SHORT).show();
                }
                }
            }
        isFileSaved = true;
    }


    public void onLoadButtonClick() {
        String fileName = binding.fileNameEditText.getText().toString();

        if (!fileName.isEmpty()) {
            String quote = loadFromFile(fileName);
            if (quote != null) {
                binding.textEditText.setText(quote);
            } else {
                Toast.makeText(requireContext(), "Файл не найден", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireContext(), "Введите название файла", Toast.LENGTH_SHORT).show();
        }
        isFileLoaded = true;
    }

    private void saveToFile(String fileName, String quote) {
        File directory = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOCUMENTS),"Directory_Documents");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(quote.getBytes());
            Toast.makeText(requireContext(), "Данные успешно сохранены в файл", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Ошибка сохранения данных", Toast.LENGTH_SHORT).show();
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