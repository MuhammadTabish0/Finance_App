package com.tabish.finance_app;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class listchoose_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listchooseexpense);
        //lists,text,buttons
        ListView choose = findViewById(R.id.choose_expense_list);
        choose.setClickable(true);
        ExpenseAdapter expenseAdapter;
        //
        ArrayList<Expense> expenselist = new ArrayList<>();
        expenselist.add(new Expense("Food",R.drawable.expense_food));
        expenselist.add(new Expense("Transport",R.drawable.expense_transport));
        expenselist.add(new Expense("Medical",R.drawable.expense_medical));
        expenselist.add(new Expense("Hotel",R.drawable.expense_hotel));
        expenselist.add(new Expense("Bill",R.drawable.expense_bill));
        expenseAdapter = new ExpenseAdapter(this,expenselist);
        choose.setAdapter(expenseAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}