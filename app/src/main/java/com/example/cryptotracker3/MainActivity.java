package com.example.cryptotracker3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptotracker3.database.investment.InvestmentAdapter;
import com.example.cryptotracker3.database.investment.Investments;
import com.example.cryptotracker3.database.investment.InvestmentsViewModel;
import com.example.cryptotracker3.databinding.ActivityMainBinding;
import com.example.cryptotracker3.ui.settings.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private InvestmentsViewModel investmentsViewModel;
    public ArrayList<Coin> coinArrayList = new ArrayList<>();

    public ArrayList<Coin> getCoinArrayList() {
        return coinArrayList;
    }

    public ArrayAdapter<Coin> coinArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_input, R.id.navigation_stats, R.id.navigation_news, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        try {
            getHTTPData();
        } catch (IOException e) {
            e.printStackTrace();
            coinArrayList.add(new Coin("Load Error", "Currency", 0.0));
        }

        double totalInvestments = 0 ;

        RecyclerView recyclerView = findViewById(R.id.simple_investments_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        InvestmentAdapter adapter = new InvestmentAdapter();
        recyclerView.setAdapter(adapter);
        investmentsViewModel = new ViewModelProvider(this).get(InvestmentsViewModel.class);
        investmentsViewModel.getAllData().observe(this, new Observer<List<Investments>>() {
            @Override
            public void onChanged(List<Investments> investments) {
                adapter.setInvestmentsList(investments);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.navigation_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    void getHTTPData() throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse("https://api.coingecko.com/api/v3/simple/price")).newBuilder();
        urlBuilder.addQueryParameter("ids", getString(R.string.coin_list));
        urlBuilder.addQueryParameter("vs_currencies", "gbp");
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("OkHTTPResponse", "The API call failed" + e.getMessage());
                call.cancel();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                JSONObject oCoin;
                final String myResponse = Objects.requireNonNull(response.body(), "Invalid Null API Response Received").string();
                Log.d("OkHTTPResponse", "API Call Successful");
                response.close();

                try{
                    JSONObject oJSON = new JSONObject(myResponse);
                    coinArrayList.clear();

                    double CoinValue;
                    for(Iterator<String> it = oJSON.keys(); it.hasNext();){
                        String coinName = it.next();
                        CoinValue = oJSON.getJSONObject(coinName).getDouble("gbp");
                        coinArrayList.add(new Coin(coinName, "gbp", CoinValue));
                    }
                } catch (JSONException e) {
                    Log.d("OKHTTPResponse", "JSON Format Problem");
                    e.printStackTrace();
                }

                MainActivity.this.runOnUiThread(() ->{
                    Log.d("OKHTTPResponse", myResponse);
                });
            }
        });
    }
}