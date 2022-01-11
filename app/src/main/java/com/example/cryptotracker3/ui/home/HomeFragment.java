package com.example.cryptotracker3.ui.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptotracker3.database.investment.InvestmentAdapter;
import com.example.cryptotracker3.database.investment.Investments;
import com.example.cryptotracker3.database.investment.InvestmentsViewModel;
import com.example.cryptotracker3.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private InvestmentsViewModel investmentsViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                textView.setText(s);
            }
        });

        RecyclerView recyclerView = binding.simpleInvestmentsRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        InvestmentAdapter adapter = new InvestmentAdapter();
        recyclerView.setAdapter(adapter);
        investmentsViewModel = new ViewModelProvider(this).get(InvestmentsViewModel.class);
        investmentsViewModel.getAllData().observe(getViewLifecycleOwner(), new Observer<List<Investments>>() {
            @Override
            public void onChanged(List<Investments> investments) {
                adapter.setInvestmentsList(investments);
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