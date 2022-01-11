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

public class InvestmentAdapter extends RecyclerView.Adapter<InvestmentAdapter.InvestmentHolder> {

    private List<Investments> investmentsList = new ArrayList<>();
    @NonNull
    @Override
    public InvestmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_investments, parent, false);
        return new InvestmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestmentHolder holder, int position) {
        Investments currentInvestment = investmentsList.get(position);
        holder.coinTextView.setText(currentInvestment.getCoin());
        holder.amountTextView.setText(String.format("%.2f", currentInvestment.getInvestment() / currentInvestment.getInitialPurchasePrice()));
        holder.investTextView.setText(String.valueOf(currentInvestment.getInvestment()));
        holder.percentageTextView.setText("100");
        holder.daysTextView.setText(String.valueOf(currentInvestment.getDaysToHold()));
    }

    @Override
    public int getItemCount() {
        return investmentsList.size();
    }

    public void setInvestmentsList(List<Investments> investments){
        this.investmentsList = investments;
        notifyDataSetChanged();
    }

    static class InvestmentHolder extends RecyclerView.ViewHolder{
        private TextView coinTextView;
        private TextView amountTextView;
        private TextView investTextView;
        private TextView percentageTextView;
        private TextView daysTextView;

        public InvestmentHolder(@NonNull View itemView) {
            super(itemView);
            coinTextView = itemView.findViewById(R.id.coin_textview);
            amountTextView = itemView.findViewById(R.id.amount_textview);
            investTextView = itemView.findViewById(R.id.investment_textview);
            percentageTextView = itemView.findViewById(R.id.percentage_textview);
            daysTextView = itemView.findViewById(R.id.days_textview);
        }
    }
}
