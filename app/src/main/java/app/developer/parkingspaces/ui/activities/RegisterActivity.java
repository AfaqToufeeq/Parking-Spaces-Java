package app.developer.parkingspaces.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.developer.parkingspaces.R;
import app.developer.parkingspaces.dataclass.User;

public class RegisterActivity extends AppCompatActivity {

    EditText userName,email,pass,phone;
    Button registerBtn;
    TextView alreadyAccountBtn;
    ProgressDialog progressDialog;
    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        buttonClicks();
    }

    private void initViews() {
        //Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Wait while loading...");
        progressDialog.setCancelable(false);

        //Assigning Views
        userName = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        phone = findViewById(R.id.phone);
        registerBtn = findViewById(R.id.registerBtn);
        alreadyAccountBtn = findViewById(R.id.alreadyAccountBtn);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void buttonClicks() {
        //Already Button
        alreadyAccountBtn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        });

        //Register Button
        registerBtn.setOnClickListener(view-> registration());
    }

    private void registration() {

        String fullName = userName.getText().toString();
        String emailAddress = email.getText().toString();
        String password = pass.getText().toString();
        String phoneNumber = phone.getText().toString();

        if (fullName.isEmpty() || password.isEmpty() || emailAddress.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill the form completely", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password should be at-least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!progressDialog.isShowing()) progressDialog.show();
        mAuth.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        //User Info
                        FirebaseUser user = mAuth.getCurrentUser();
                        registerNewUser(
                                mAuth.getUid(),
                                user,
                                fullName,
                                emailAddress,
                                password,
                                phoneNumber);
                    } else {
                        // If sign in fails, display a message to the user.
                        if(progressDialog.isShowing()) progressDialog.dismiss();
                        Toast.makeText(this, task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void registerNewUser(String uid, FirebaseUser user, String fullName, String emailAddress, String password, String phoneNumber) {

        User user1 = new User(fullName, emailAddress, password, phoneNumber);

        mDatabase.child("Customers").child(uid).child(fullName).setValue(user1)
                .addOnSuccessListener(unused -> {

                    if(progressDialog.isShowing()) progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show();
//                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                })
                .addOnFailureListener(e -> {
                    if(progressDialog.isShowing()) progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Failed to create the account "+e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}