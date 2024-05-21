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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class listchooseexpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listchooseexpense);
        //lists,text,buttons
        ListView choose = findViewById(R.id.choose_expense_list);
        choose.setClickable(true);
        TransactionAdapter expenseAdapter;
        //
        ArrayList<Transaction> expenselist = new ArrayList<>();
        expenselist.add(new Expense("Food",R.drawable.expense_food));
        expenselist.add(new Expense("Transport",R.drawable.expense_transport));
        expenselist.add(new Expense("Medical",R.drawable.expense_medical));
        expenselist.add(new Expense("Hotel",R.drawable.expense_hotel));
        expenselist.add(new Expense("Bill",R.drawable.expense_bill));
        expenseAdapter = new TransactionAdapter(this,expenselist);
        choose.setAdapter(expenseAdapter);
        choose.setClickable(true);
        choose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expense selectedExpense = new Expense(expenselist.get(position).getName(),expenselist.get(position).getImage());
                showDialog(selectedExpense);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void showDialog(Expense expense) {
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
            Intent intent = new Intent(listchooseexpense.this, homepage.class);

            listchooseexpense.this.startActivity(intent);
        });

        builder.setNegativeButton("CANCEL", (dialog, which) -> {
            // Handle CANCEL button click
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}