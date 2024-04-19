package ru.mirea.ivanovea.mireaproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class DownloadImageWorker extends Worker {

    public DownloadImageWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {
        String imageUrl = getInputData().getString("image_url");
        if (imageUrl == null) {
            return Result.failure();
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

//            InputStream input = new URL(imageUrl).openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            String imagePath = saveBitmapToFile(getApplicationContext(), bitmap);
            Data outputData = new Data.Builder()
                    .putString("image_path", imagePath)
                    .build();

            return Result.success(outputData);
        } catch (Exception e) {
            return Result.failure();
        }
    }

    private String saveBitmapToFile(Context context, Bitmap bitmap) {
        File outputDir = context.getCacheDir(); // context being the Activity pointer
        File outputFile = new File(outputDir, "downloaded_image.png");

        try (OutputStream out = Files.newOutputStream(outputFile.toPath())) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return outputFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
