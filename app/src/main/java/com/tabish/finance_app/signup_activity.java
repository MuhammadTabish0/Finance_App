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

public class signup_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        // Variables
        Button signupbtn = findViewById(R.id.btnsignup);
        TextView tlogin = findViewById(R.id.textlogin);
        EditText first_name = findViewById(R.id.txtfirstname);
        EditText last_name = findViewById(R.id.txtlastname);
        EditText username = findViewById(R.id.txtusernamelogin);
        EditText password = findViewById(R.id.txtpasswordlogin);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference("users");

        tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(signup_activity.this, com.tabish.finance_app.login_activity.class);
                signup_activity.this.startActivity(login);
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m_firstname = first_name.getText().toString().trim();
                String m_lastname = last_name.getText().toString().trim();
                String m_username = username.getText().toString().trim();
                String m_password = password.getText().toString().trim();

                if (m_firstname.isEmpty() || m_lastname.isEmpty() || m_username.isEmpty() || m_password.isEmpty()) {
                    Toast.makeText(signup_activity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    reference.child(m_username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(signup_activity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                User user = new User(m_username, m_firstname, m_lastname, m_password);
                                reference.child(m_username).setValue(user);
                                Intent homepage = new Intent(signup_activity.this, com.tabish.finance_app.homepage.class);
                                signup_activity.this.startActivity(homepage);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(signup_activity.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.txtfirstname), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
