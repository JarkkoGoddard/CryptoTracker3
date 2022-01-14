package com.example.cryptotracker3.ui.stats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptotracker3.R;
import com.example.cryptotracker3.database.coin.Coins;

import java.util.ArrayList;
import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {
    private List<Coins> coinsList = new ArrayList<>();

    @NonNull
    @Override
    public CoinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_unit, parent, false);
        return new CoinAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinAdapter.ViewHolder holder, int position) {
        holder.coinTextView.setText(coinsList.get(position).getCoins());
        holder.priceTextView.setText(String.format("%.2f", coinsList.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return coinsList.size();
    }

    public void setCoins(List<Coins> coins){
        this.coinsList = coins;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView coinTextView;
        private TextView priceTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coinTextView = itemView.findViewById(R.id.coin_textview);
            priceTextView = itemView.findViewById(R.id.initial_textview);
        }
    }
}
