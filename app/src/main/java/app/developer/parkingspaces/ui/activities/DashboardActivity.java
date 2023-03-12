package app.developer.parkingspaces.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import app.developer.parkingspaces.R;
import app.developer.parkingspaces.ui.fragments.cityAreaFragment;

public class DashboardActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Spinner profileSpinner;
    String  userName = FirebaseAuth
            .getInstance()
            .getCurrentUser()
            .getEmail();
    String[] profileName = {userName,"Sign Out"};
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initViews();
        fragment= getData();
        addFragmentToActivity(fragment);
        bottomNavigation();
        buttonClicks();
    }
    private void initViews() {

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        profileSpinner = findViewById(R.id.profileSpinner);

    }
    private void buttonClicks() {
        //Spinner Click Listener
        profileSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
//                ((TextView) adapterView.getChildAt(0)).setTextSize(16);
                if(profileName[position].equals("Sign Out"))
                {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getApplicationContext(), "you are logged out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter setSpinner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,profileName);
        setSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profileSpinner.setAdapter(setSpinner);

    }

    public Fragment getData() {
//        val fragment = intent.getStringExtra("fragmentCart")
//        when(fragment){
//            "addToCartFragment" -> return addToCartFragment()
//             else -> return restaurantsFragment()
//        }
        return new cityAreaFragment();
    }

    private void addFragmentToActivity(Fragment fragment){
        if (fragment == null) return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft =  fragmentManager.beginTransaction();
        ft.replace(R.id.dashBoard_FL,fragment).addToBackStack(null).commit();
    }

    private void bottomNavigation() {

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.HomeMenu:
                    addFragmentToActivity(new cityAreaFragment());
                    break;
                case R.id.bookingMenu:
                    addFragmentToActivity(new cityAreaFragment());
                    break;
                case R.id.ProfileMenu:
                    addFragmentToActivity(new cityAreaFragment());
                    break;
            }
            return true;
        });
    }

}