package ru.mirea.ivanovea.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import ru.mirea.ivanovea.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        text1 = findViewById(R.id.datatext);
        text2 = findViewById(R.id.datatext2);

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.datatext.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.datatext.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.datatext.setText("runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.datatext.postDelayed(runn3, 2000);
                    binding.datatext.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        text2.setText("Сначала - runOnUiThread - запускает action в UI-потоке \n " +
                "Затем - post - добавляет action в очередь сообщений \n " +
                "После этого - postDelayed - добавляет action в очередь сообщений и " +
                "устанавливает задержки на запуск action \n" +
                "android:maxLines='10' - определяет максимальное количество строк в TextView \n" +
                "android:lines='10' - текст будет обрезаться, если количество строк " +
                "в тексте превышает установленное значение (может использоваться одновременно с " +
                "предыдущим атрибутом)");
    }
}
