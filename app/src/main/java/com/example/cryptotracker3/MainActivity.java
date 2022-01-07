package com.example.cryptotracker3;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cryptotracker3.database.InvestmentAdapter;
import com.example.cryptotracker3.database.Investments;
import com.example.cryptotracker3.database.InvestmentsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptotracker3.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private InvestmentsViewModel investmentsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_input, R.id.navigation_stats, R.id.navigation_news)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        RecyclerView recyclerView = findViewById(R.id.simple_investments_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        InvestmentAdapter adapter = new InvestmentAdapter();
        recyclerView.setAdapter(adapter);
        investmentsViewModel = new ViewModelProvider(this).get(InvestmentsViewModel.class);
        investmentsViewModel.getAllData().observe(this, new Observer<List<Investments>>() {
            @Override
            public void onChanged(List<Investments> investments) {
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_LONG).show();
                adapter.setInvestmentsList(investments);
            }
        });
    }

}