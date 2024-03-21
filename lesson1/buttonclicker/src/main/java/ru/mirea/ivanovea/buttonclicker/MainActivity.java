package ru.mirea.ivanovea.buttonclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button whobut;
    private Button notbut;
    private TextView mytext;
    private CheckBox mycheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notbut = findViewById(R.id.btnItsNotMe);
        whobut = findViewById(R.id.btnWhoAmI);

        mytext = findViewById(R.id.tvOut);

        mycheckbox = findViewById(R.id.checkBoxStudent);

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener(){
            @Override
            public void onClick (View v){
                mytext.setText("Мой номер по списку 9");
                if (mycheckbox.isChecked() == false) {
                    mycheckbox.setChecked(true);
                }
                else {
                    mycheckbox.setChecked(false);
                }
            }
        };
        whobut.setOnClickListener(oclBtnWhoAmI);
    }
    public void OnMyButtonClick (View view) {
        mytext.setText("Это сделал не я!");
        Toast.makeText(this, "Это сделал не я!", Toast.LENGTH_SHORT).show();
        if (mycheckbox.isChecked() == false) {
            mycheckbox.setChecked(true);
        }
        else {
            mycheckbox.setChecked(false);
        }
    }

}