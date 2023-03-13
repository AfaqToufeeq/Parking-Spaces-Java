package app.developer.parkingspaces.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.developer.parkingspaces.R;
import app.developer.parkingspaces.adapters.CityAreaAdapter;
import app.developer.parkingspaces.dataclass.CityArea;
import app.developer.parkingspaces.interfaces.onAreaItemClick;
import app.developer.parkingspaces.utils.PickerManager;


public class CityAreaFragment extends Fragment implements onAreaItemClick {
    PickerManager pm = PickerManager.getInstance();
    ConstraintLayout layoutEmptyList;
    RecyclerView areaRV;
    CityAreaAdapter adapter;
    Dialog loader;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_city_area, container, false);
        initViews(v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonClicks();
        buildRecyclerView();
        onbackPressed();

    }

    private void initViews(View v) {

        layoutEmptyList = v.findViewById(R.id.layout_emptyList);
        areaRV = v.findViewById(R.id.areaRecyclerview);
    }

    private void buttonClicks() {

    }

    private void buildRecyclerView() {

        areaRV.setHasFixedSize(true);
        areaRV.setLayoutManager(new LinearLayoutManager(requireActivity()));

        getAreaDataFromFirebase();
    }

    private void getAreaDataFromFirebase() {

//        if(!pm.loader.isShowing()) pm.loader.show();
        pm.cityList.clear();

        FirebaseDatabase.getInstance().getReference("Restaurants").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot snapshot1 : snapshot.getChildren())
                    {
                        CityArea cityArea = snapshot1.getValue(CityArea.class);
                        pm.cityList.add(cityArea);
                    }

                    setVisibility();  //visibility

                    //setting Adapter
                    areaRV.setAdapter(new CityAreaAdapter(
                            requireActivity(),
                            pm.cityList,
                            CityAreaFragment.this
                    ));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireActivity(),error.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void onbackPressed() {

    }

    private void setFragment(Fragment fragment){
        if (fragment == null) return;

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft =  fragmentManager.beginTransaction();
        ft.replace(R.id.dashBoard_FL,fragment).addToBackStack("myFragment").commit();
    }
    private void setVisibility() {
        if(!pm.cityList.isEmpty())
        {
            layoutEmptyList.setVisibility(View.GONE);
            areaRV.setVisibility(View.VISIBLE);
        }
        else{
            layoutEmptyList.setVisibility(View.VISIBLE);
            areaRV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLongClick(int position) {

    }
}