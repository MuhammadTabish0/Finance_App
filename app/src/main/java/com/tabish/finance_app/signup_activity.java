package com.tabish.finance_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signup_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        Button signupbtn = findViewById(R.id.btnsignup);
        TextView tlogin = findViewById(R.id.textlogin);
        tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(signup_activity.this,com.tabish.finance_app.login_activity.class);
                signup_activity.this.startActivity(login);
            }
        });
        String money = "200";
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent(signup_activity.this,com.tabish.finance_app.login_activity.class);
                signup_activity.this.startActivity(homepage);

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.txtfirstname), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}