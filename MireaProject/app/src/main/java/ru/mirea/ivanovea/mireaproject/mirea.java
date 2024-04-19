    package ru.mirea.ivanovea.mireaproject;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.work.Data;
    import androidx.work.OneTimeWorkRequest;
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
            Data inputData = new Data.Builder()
                    .putString("image_url", "https://lazydog24.ru/wp-content/uploads/foto-1146.jpg")
                    .build();

            OneTimeWorkRequest downloadImageRequest = new OneTimeWorkRequest.Builder(DownloadImageWorker.class)
                    .setInputData(inputData)
                    .build();

            WorkManager.getInstance(requireContext()).enqueue(downloadImageRequest);
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
    }
