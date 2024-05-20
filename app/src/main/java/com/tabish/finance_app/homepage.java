package com.tabish.finance_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);
        //buttons,views,etc
        CardView card_income = findViewById(R.id.cardaddincome);
        CardView card_expense = findViewById(R.id.cardaddexpense);
        //function
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
}