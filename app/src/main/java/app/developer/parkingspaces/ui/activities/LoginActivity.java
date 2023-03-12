package app.developer.parkingspaces.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.developer.parkingspaces.R;

public class LoginActivity extends AppCompatActivity {

    EditText email_ET,pass_ET;
    Button loginBtn,signupBtn;
    ProgressDialog progress;
    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        buttonClicks();
    }

    private void initViews() {
        //Progress Dialog
        progress = new ProgressDialog(this);
        progress.setTitle("Logging in");
        progress.setMessage("Logging into your account...");
        progress.setCancelable(false);

        //Assigning Views
        email_ET = findViewById(R.id.email_ET);
        pass_ET = findViewById(R.id.pass_ET);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
    }

    private void buttonClicks() {
        //Login method
       loginBtn.setOnClickListener( view-> loginUser());

       //SignUp Button
       signupBtn.setOnClickListener(v->{
           startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
           finish();
       });
    }

    private void loginUser() {
        String email = email_ET.getText().toString();
        String pass = pass_ET.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please Enter the login details", Toast.LENGTH_SHORT).show();
            return;
        }

       if(!progress.isShowing()) progress.show();
       mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, task -> {
           if(task.isSuccessful()){
               if (progress.isShowing()) progress.dismiss();
               Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
               finish();
           }
           else{
               if (progress.isShowing()) progress.dismiss();
               Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
    }

    @Override
    protected void onStart() {
        super.onStart();

//        if(mAuth.getCurrentUser()!=null)
//        {
//            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
//            finish();
//        }
    }
}