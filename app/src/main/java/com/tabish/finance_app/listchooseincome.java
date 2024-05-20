package com.tabish.finance_app;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}