package ru.mirea.ivanovea.mireaproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageWorker extends Worker {

    public DownloadImageWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String imageUrl = getInputData().getString("image_url");
        Bitmap bitmap = downloadImage(imageUrl);
        if (bitmap != null) {
            // Успешно загружили изображение
            // Дальнейшие действия, например, сохранение изображения в хранилище или отображение на экране
            return Result.success();
        } else {
            // Ошибка загрузки изображения
            return Result.failure();
        }
    }

    private Bitmap downloadImage(String imageUrl) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(imageUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            Log.e("DownloadImageWorker", "Error downloading image", e);
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}

