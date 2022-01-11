package com.example.cryptotracker3.database.investment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptotracker3.R;

import java.util.ArrayList;
import java.util.List;

public class InvestmentStatAdapter extends RecyclerView.Adapter<InvestmentStatAdapter.InvestmentHolder> {

    private List<Investments> investmentsList = new ArrayList<>();
    @NonNull
    @Override
    public InvestmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_unit, parent, false);
        return new InvestmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestmentHolder holder, int position) {
        Investments currentInvestment = investmentsList.get(position);
        holder.mcoinTextView.setText(currentInvestment.getCoin());
        holder.minitialTextView.setText(String.valueOf(currentInvestment.getInitialPurchasePrice()));
        holder.mcurrentTextView.setText("");
        holder.mchangeTextView.setText("100");
        holder.mchangePercentTextView.setText(String.valueOf(currentInvestment.getDaysToHold()));
    }

    @Override
    public int getItemCount() {
        return investmentsList.size();
    }

    public class InvestmentHolder extends RecyclerView.ViewHolder {
        private TextView mcoinTextView;
        private TextView minitialTextView;
        private TextView mcurrentTextView;
        private TextView mchangeTextView;
        private TextView mchangePercentTextView;

        public InvestmentHolder(@NonNull View itemView) {
            super(itemView);
            mcoinTextView = itemView.findViewById(R.id.coin_textview);
            minitialTextView = itemView.findViewById(R.id.initial_textview);
            mcurrentTextView = itemView.findViewById(R.id.current_textview);
            mchangeTextView = itemView.findViewById(R.id.change_textview);
            mchangePercentTextView = itemView.findViewById(R.id.change_percent_textview);
        }
    }
}
