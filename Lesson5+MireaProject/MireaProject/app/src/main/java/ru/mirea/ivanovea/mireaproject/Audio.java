package ru.mirea.ivanovea.mireaproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

import ru.mirea.ivanovea.mireaproject.R;
import ru.mirea.ivanovea.mireaproject.databinding.AudioBinding;

public class Audio extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 200;
    private final String TAG = MainActivity.class.getSimpleName();
    private boolean isWork;
    private Button recordButton = null;
    private Button playButton = null;
    private Button sendButton = null;
    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    boolean mStartPlaying = true;
    boolean mStartRecording = true;
    String recordFilePath;
    private AudioBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AudioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recordButton = binding.button5;
        playButton = binding.button6;
        playButton.setEnabled(false);

        sendButton = binding.button7;
        sendButton.setEnabled(false);

        recordFilePath = (new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),
                "/audiorecordtest.3gp")).getAbsolutePath();

        int audioPermissionStatus = ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO);
        int storagePermissionStatus = ContextCompat.checkSelfPermission(this, android.Manifest.permission.
                WRITE_EXTERNAL_STORAGE);
        if (audioPermissionStatus == PackageManager.PERMISSION_GRANTED && storagePermissionStatus
                == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
        }
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mStartRecording) {
                    recordButton.setText("Stop recording");
                    playButton.setEnabled(false);
                    sendButton.setEnabled(false);
                    startRecording();
                    mStartRecording = !mStartRecording;
                } else {
                    recordButton.setText("Start recording");
                    playButton.setEnabled(true);
                    stopRecording();
                    mStartRecording = !mStartRecording;
                    sendButton.setEnabled(true);
                }
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStartPlaying) {
                    playButton.setText("Stop playing");
                    recordButton.setEnabled(false);
                    sendButton.setEnabled(false);
                    startPlaying();
                    mStartPlaying = !mStartPlaying;
                } else {
                    playButton.setText("Start playing");
                    recordButton.setEnabled(true);
                    sendButton.setEnabled(true);
                    stopPlaying();
                    mStartPlaying = !mStartPlaying;
                }
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //всплывает "отправлено"
            }
        });
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(recordFilePath);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
        recorder.start();
    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }

    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(recordFilePath);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        player.release();
        player = null;
    }

}
