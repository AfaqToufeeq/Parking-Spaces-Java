package app.developer.parkingspaces.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.developer.parkingspaces.R;
import app.developer.parkingspaces.dataclass.CityArea;
import app.developer.parkingspaces.interfaces.onAreaItemClick;

public class CityAreaAdapter extends RecyclerView.Adapter<CityAreaAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CityArea> cityList;
    private onAreaItemClick clickListener;

    public CityAreaAdapter(Context context, ArrayList<CityArea> cityList, onAreaItemClick clickListener) {
        this.context = context;
        this.cityList = cityList;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public CityAreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.area_layout, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAreaAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView areaImage_IV;
        public TextView areaTitle_TV, areaDes_TV, area_ID;

        public ViewHolder(View itemView) {
            super(itemView);
            this.areaTitle_TV = itemView.findViewById(R.id.areaTitle_TV);
            this.areaDes_TV = itemView.findViewById(R.id.areaDes_TV);
            this.area_ID = itemView.findViewById(R.id.area_ID);
            this.areaImage_IV = itemView.findViewById(R.id.area_IV);
        }
    }
}