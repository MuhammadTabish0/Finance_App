package com.tabish.finance_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class listchooseincome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listchooseincome);
        ListView choose = findViewById(R.id.choose_income_list);
        choose.setClickable(true);
        TransactionAdapter incomeAdapter;

        //
        ArrayList<Transaction> incomelist = new ArrayList<>();
        incomelist.add(new Income("Cash",R.drawable.income_cash));
        incomelist.add(new Income("Online Transfer", R.drawable.income_online));
        incomelist.add(new Income("Bank Tranfer",R.drawable.income_bank));
        incomeAdapter = new TransactionAdapter(this,incomelist);
        choose.setAdapter(incomeAdapter);
        choose.setClickable(true);
        choose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Income selectedincome = new Income(incomelist.get(position).getName(),incomelist.get(position).getImage());
                showDialog(selectedincome);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void showDialog(Income expense) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue_box, null); // Replace with your dialog layout resource
        builder.setView(dialogView);

        // Reference EditText views within the dialog
        EditText dialogue_amount = dialogView.findViewById(R.id.amount_input_dialogue);
        EditText dialogue_description = dialogView.findViewById(R.id.description_input_dialogue);

        builder.setPositiveButton("OKAY", (dialog, which) -> {
            // Handle OKAY button click
            expense.setDate();
            String description = dialogue_description.getText().toString().trim();
            String input_amount = dialogue_amount.getText().toString().trim(); // Fix here
            try {
                double amount = Double.parseDouble(input_amount);
                expense.setAmount(amount);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid amount format", Toast.LENGTH_SHORT).show();
                return; // Optionally you can handle it in a better way
            }
            expense.setDescription(description);
            historydata.history_list.add(expense);
            Intent intent = new Intent(listchooseincome.this, homepage.class);

            listchooseincome.this.startActivity(intent);
        });

        builder.setNegativeButton("CANCEL", (dialog, which) -> {
            // Handle CANCEL button click
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}