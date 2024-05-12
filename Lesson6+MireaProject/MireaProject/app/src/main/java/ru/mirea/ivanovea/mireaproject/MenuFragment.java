package ru.mirea.ivanovea.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.ivanovea.mireaproject.databinding.FragmentMenuBinding;
import ru.mirea.ivanovea.mireaproject.databinding.FragmentProfileBinding;


public class MenuFragment extends Fragment {
    private FragmentMenuBinding binding;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        binding.button.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.container,
                    new ProfileFragment()).addToBackStack(null).commit();
        });
        binding.button2.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.container,
                    new FileFragment()).addToBackStack(null).commit();
        });
    }
}