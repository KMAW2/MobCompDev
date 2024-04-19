package ru.mirea.ivanovea.mireaproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import ru.mirea.ivanovea.mireaproject.databinding.FragmentMireaBinding;

public class mirea extends Fragment {

    private FragmentMireaBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMireaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.buttonDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownloadImageWorker();
            }
        });

        return view;
    }

    private void startDownloadImageWorker() {

        String imageUrl = "https://lazydog24.ru/wp-content/uploads/foto-1146.jpg";
        Data inputData = new Data.Builder()
                .putString("image_url", imageUrl)
                .build();

        OneTimeWorkRequest downloadImageRequest = new OneTimeWorkRequest.Builder(DownloadImageWorker.class)
                .setInputData(inputData)
                .build();

        WorkManager.getInstance(requireActivity().getApplicationContext()).enqueue(downloadImageRequest);

        WorkManager.getInstance(requireActivity().getApplicationContext()).getWorkInfoByIdLiveData(downloadImageRequest.getId())
                .observe(this, workInfo -> {
                    if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                        String imagePath = workInfo.getOutputData().getString("image_path");
                        if (imagePath != null) {
                            updateImageView(imagePath);
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateImageView(String imagePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        binding.imageView.setImageBitmap(bitmap);
    }
}
