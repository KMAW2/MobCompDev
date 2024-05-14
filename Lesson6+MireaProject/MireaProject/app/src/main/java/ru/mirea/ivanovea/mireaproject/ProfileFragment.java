package ru.mirea.ivanovea.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.mirea.ivanovea.mireaproject.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    SharedPreferences sharedPreferences;
    private FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);

        binding.editTextText.setText(sharedPreferences.getString("name", ""));
        binding.editTextText2.setText(sharedPreferences.getString("surname", ""));
        binding.editTextText3.setText(sharedPreferences.getString("age", ""));
        binding.button3.setOnClickListener(v -> {
            saveData();
        });
    }

    private void saveData() {
        String name = binding.editTextText.getText().toString();
        String surname = binding.editTextText2.getText().toString();
        String age = binding.editTextText3.getText().toString();

        if (!name.isEmpty() && !surname.isEmpty() && !age.isEmpty()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("surname", surname);
            editor.putString("age", age);
            editor.apply();
        } else {
            Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show();
        }
    }
}