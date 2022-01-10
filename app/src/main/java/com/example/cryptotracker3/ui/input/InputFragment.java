package com.example.cryptotracker3.ui.input;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cryptotracker3.R;
import com.example.cryptotracker3.database.Investments;
import com.example.cryptotracker3.database.InvestmentsViewModel;
import com.example.cryptotracker3.databinding.FragmentInputBinding;

public class InputFragment extends Fragment {

    private InputViewModel inputViewModel;
    private InvestmentsViewModel investmentsViewModel;
    private FragmentInputBinding binding;
    private Spinner cryptoSpinner;
    private EditText editInvestmentText;
    private EditText editLowText;
    private EditText editHighText;
    private EditText editSellPercentText;
    private EditText editDaysHoldText;
    private EditText editInitialPurchaseText;
    private EditText editNotesText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inputViewModel =
                new ViewModelProvider(this).get(InputViewModel.class);

        binding = FragmentInputBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        inputViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Spinner mCryptoSpinner = binding.cryptoSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.coin_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCryptoSpinner.setAdapter(adapter);

        cryptoSpinner = binding.cryptoSpinner;
        editInvestmentText = binding.investmentText;
        editLowText = binding.lowText;
        editHighText = binding.highText;
        editSellPercentText = binding.sellPercentText;
        editDaysHoldText = binding.daysHoldText;
        editInitialPurchaseText = binding.initialPurchaseText;
        editNotesText = binding.notesText;
        investmentsViewModel = new ViewModelProvider(this).get(InvestmentsViewModel.class);
        Button submitButton = binding.submitBtn;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cryptoText = cryptoSpinner.getSelectedItem().toString();
                String investmentText = editInvestmentText.getText().toString();
                double investmentDouble = Double.parseDouble(investmentText);
                String lowText = editLowText.getText().toString();
                double lowDouble = Double.parseDouble(lowText);
                String highText = editHighText.getText().toString();
                double highDouble = Double.parseDouble(highText);
                String sellPercentText = editSellPercentText.getText().toString();
                int percentInt = Integer.parseInt(sellPercentText);
                String daysHoldText = editDaysHoldText.getText().toString();
                int daysHoldInt = Integer.parseInt(daysHoldText);
                String initialPurchaseText = editInitialPurchaseText.getText().toString();
                double initialPurchaseDouble = Double.parseDouble(initialPurchaseText);
                String notesText = editNotesText.getText().toString();

                investmentsViewModel.insert(new Investments(cryptoText, investmentDouble, lowDouble, highDouble, percentInt, daysHoldInt, initialPurchaseDouble, notesText));
                editInvestmentText.setText("");
                editLowText.setText("");
                editHighText.setText("");
                editSellPercentText.setText("");
                editDaysHoldText.setText("");
                editInitialPurchaseText.setText("");
                editNotesText.setText("");

                Toast.makeText(getActivity(), "Investments stored", Toast.LENGTH_SHORT).show();
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