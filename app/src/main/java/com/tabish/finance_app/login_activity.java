package com.tabish.finance_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Variables
        TextView redirect_signup = findViewById(R.id.txtsignup);
        Button login = findViewById(R.id.btnsignin);
        EditText username = findViewById(R.id.txtusernamelogin);
        EditText password = findViewById(R.id.txtpasswordlogin);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference("users");

        // OnClickListener for login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m_username = username.getText().toString().trim();
                String m_password = password.getText().toString().trim();

                if (m_username.isEmpty() || m_password.isEmpty()) {
                    Toast.makeText(login_activity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    reference.child(m_username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                User user = dataSnapshot.getValue(User.class);
                                if (user != null && user.getPassword().equals(m_password)) {
                                    Intent homepage = new Intent(login_activity.this, com.tabish.finance_app.homepage.class);
                                    homepage.putExtra("username", m_username); // Pass the username
                                    login_activity.this.startActivity(homepage);
                                } else {
                                    Toast.makeText(login_activity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(login_activity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(login_activity.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        // OnClickListener for redirect to signup page
        redirect_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(login_activity.this, signup_activity.class);
                login_activity.this.startActivity(signup);
            }
        });

        // Apply WindowInsets to EditText
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linear_login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
