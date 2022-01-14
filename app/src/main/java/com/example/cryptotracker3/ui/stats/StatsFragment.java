package com.example.cryptotracker3.ui.stats;

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

import com.example.cryptotracker3.database.coin.Coins;
import com.example.cryptotracker3.database.coin.CoinsViewModel;
import com.example.cryptotracker3.databinding.FragmentStatsBinding;
import com.example.cryptotracker3.ui.stats.CoinAdapter;

import java.util.List;

public class StatsFragment extends Fragment {

    private StatsViewModel statsViewModel;
    private FragmentStatsBinding binding;
    private CoinsViewModel coinsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statsViewModel =
                new ViewModelProvider(this).get(StatsViewModel.class);

        binding = FragmentStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        RecyclerView recyclerView;
        RecyclerView.Adapter myAdapter;
        recyclerView = binding.statsRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        myAdapter = new CoinAdapter();
        recyclerView.setAdapter(myAdapter);
        coinsViewModel = new ViewModelProvider(this).get(CoinsViewModel.class);
        coinsViewModel.getAllCoins().observe(getViewLifecycleOwner(), new Observer<List<Coins>>() {
            @Override
            public void onChanged(List<Coins> coins) {
                ((CoinAdapter) myAdapter).setCoins(coins);
            }
        });
        statsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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