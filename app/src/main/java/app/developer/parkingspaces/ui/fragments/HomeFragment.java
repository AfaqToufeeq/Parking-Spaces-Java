package app.developer.parkingspaces.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import app.developer.parkingspaces.R;
import app.developer.parkingspaces.dataclass.CityArea;
import app.developer.parkingspaces.utils.PickerManager;


public class HomeFragment extends Fragment {
    Spinner citySpinner;
    PickerManager pm = PickerManager.getInstance();
    String[] city= new String[4];
    CardView carParking_CV,bikeParking_CV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        initViews(v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addCities();
        clickButtons();
    }

    private void initViews(View v) {
        citySpinner = v.findViewById(R.id.citySpinner);
        bikeParking_CV = v.findViewById(R.id.bikeParking_CV);
        carParking_CV=v.findViewById(R.id.carParking_CV);
    }

    private void addCities() {
        pm.cityList.add(new CityArea(null,"Faisalabad",null,null,null));
        pm.cityList.add(new CityArea(null,"Lahore",null,null,null));
        pm.cityList.add(new CityArea(null,"Islamabad",null,null,null));
        pm.cityList.add(new CityArea(null,"Kabul",null,null,null));
        int i=0;
        while (i<pm.cityList.size())
        {
            city[i] = pm.cityList.get(i).getAreaName();
            i++;
        }
    }

    private void setFragment(Fragment fragment){
        if (fragment == null) return;

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft =  fragmentManager.beginTransaction();
        ft.replace(R.id.dashBoard_FL,fragment).addToBackStack("myFragment").commit();
    }

    private void clickButtons() {
        //City Spinner
        citySpinnerMethod();

        carParking_CV.setOnClickListener(v-> setFragment(new CityAreaFragment()));
}

    private void citySpinnerMethod() {
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
//                ((TextView) adapterView.getChildAt(0)).setTextSize(16);
                if("Faisalabad".equals(pm.cityList.get(position).getAreaName()))
                {
                    Toast.makeText(requireContext(), pm.cityList.get(position).getAreaName(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter setCitySpinner = new ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,city);
        setCitySpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(setCitySpinner);

    }
}