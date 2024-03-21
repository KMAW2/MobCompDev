package ru.mirea.ivanovea.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowDialog(View view) {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onDateClicked(View view) {
        Snackbar.make(view, "DatePicker", 5).show();
        MyDateDialogFragment dialogFragment = new MyDateDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
        Toast.makeText(getApplicationContext(), "Вы открыли \"Календарь\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onTimeClicked(View view) {
        Snackbar.make(view, "TimePicker", 5).show();
        MyTimeDialogFragment dialogFragment = new MyTimeDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
        Toast.makeText(getApplicationContext(), "Вы открыли \"Часы\"!",
                Toast.LENGTH_LONG).show();
    }

    public void onProgressClicked(View view) {
        Snackbar.make(view, "ProgressPicker", 5).show();
        MyProgressDialogFragment dialogFragment = new MyProgressDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
        Toast.makeText(getApplicationContext(), "Вы открыли \"Окно загрузки\"!",
                Toast.LENGTH_LONG).show();
    }
}