package ru.mirea.ivanovea.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.ivanovea.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    TextView edit1, edit2;
    private int kol = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonthread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                } */
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = kol++;

                        TextView Result = findViewById(R.id.textthread);
                        Thread mainThread = Thread.currentThread();
                        Result.setText("Имя текущего потока: " + mainThread.getName());
                        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БИСО-03-19, НОМЕР ПО СПИСКУ: 8, МОЙ ЛЮБИМЫЙ ФИЛЬМ: La belle epoque");
                        Result.append("\n Новое имя потока: " + mainThread.getName());
                        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
                        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());

                        edit1 = findViewById(R.id.editTextthread);
                        edit2 = findViewById(R.id.editTextthread2);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                double amount = Integer.valueOf(edit1.getText().toString()) / Integer.valueOf(edit2.getText().toString());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Result.append("\n\nСреднее количество пар в день за месяц: " + amount);
                                    }
                                });
                            }
                        }).start();

                        Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы № %s номер по списку № %d ", numberThread, "БИСО-03-19", 8));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток № " + numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}