package app.developer.parkingspaces.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.developer.parkingspaces.R;

public class RegisterActivity extends AppCompatActivity {

    EditText userName,email,pass,phone;
    Button registerBtn;
    TextView alreadyAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        buttonClicks();
    }

    private void initViews() {
        userName = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        phone = findViewById(R.id.phone);
        registerBtn = findViewById(R.id.registerBtn);
        alreadyAccountBtn = findViewById(R.id.alreadyAccountBtn);
    }

    private void buttonClicks() {
        //Already Button
        alreadyAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        //Register Button
    }


}