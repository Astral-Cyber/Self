package com.jxufe.self.ui.company;

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
import com.jxufe.self.databinding.FragmentCompanyBinding;

public class CompanyFragment extends Fragment {

    private CompanyViewModel companyViewModel;
    private FragmentCompanyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        companyViewModel =
                new ViewModelProvider(this).get(CompanyViewModel.class);

        binding = FragmentCompanyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCompany;
        companyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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