package com.tabish.finance_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class homepage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);
        //buttons,views,etc
        TextView balance =findViewById(R.id.txtbalance);
        CardView card_income = findViewById(R.id.cardaddincome);
        CardView card_expense = findViewById(R.id.cardaddexpense);
        ListView history_listview = findViewById(R.id.list_history);
        TransactionAdapter history_adapter = new TransactionAdapter(this,historydata.history_list);
        history_listview.setAdapter(history_adapter);
        //function
        Intent intent = getIntent();
        if (intent != null) {
                // Notify the adapter about the data change

                balance.setText(String.format("%s", dataamount()));
                ((TransactionAdapter) history_listview.getAdapter()).notifyDataSetChanged();
        }
        card_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listincomechoose = new Intent(homepage.this, listchooseincome.class);
                homepage.this.startActivity(listincomechoose);
            }
        });
        card_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listexpensechoose = new Intent(homepage.this, listchooseexpense.class);
                homepage.this.startActivity(listexpensechoose);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.homepagelinear), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public double dataamount(){
        double amount = 0;
        for(int i = 0;i<historydata.history_list.size();i++){
            if(historydata.history_list.get(i) instanceof Income){
                amount+=historydata.history_list.get(i).getAmount();
            }
            else{
                amount-=historydata.history_list.get(i).getAmount();
            }
        }

        return amount;
    }
}