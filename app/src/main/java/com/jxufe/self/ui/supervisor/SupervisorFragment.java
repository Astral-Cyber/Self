package com.jxufe.self.ui.supervisor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.jxufe.self.databinding.FragmentSupervisorBinding;

public class SupervisorFragment extends Fragment {

    private SupervisorViewModel supervisorViewModel;
    private FragmentSupervisorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        supervisorViewModel =
                new ViewModelProvider(this).get(SupervisorViewModel.class);

        binding = FragmentSupervisorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSupervisor;
        supervisorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}