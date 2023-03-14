package app.developer.parkingspaces.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import app.developer.parkingspaces.R;
import app.developer.parkingspaces.dataclass.CityArea;


public class PickerManager {
    private static PickerManager pickerManager = null;
    private static Context context=null;


    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private PickerManager()
    {}
    public PickerManager(Context context)
    {
        this.context=context;
    }

    // Static method
    // Static method to create instance of Singleton class
    public static synchronized PickerManager getInstance()
    {
        if (pickerManager == null)
            pickerManager = new PickerManager();

        return pickerManager;
    }

    //Firebase Instances
    // Initialize Firebase
   public FirebaseAuth mAuth = FirebaseAuth.getInstance();
   public DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

   //Loader Dialog
//    public Dialog loader = Utils.INSTANCE.progressDialog(context);

    public ArrayList<CityArea> cityList= new ArrayList<>();

    public Dialog progressDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.layout_progress_dialog);
        dialog.setCancelable(false);
         ImageView lottieAnimation = dialog.findViewById(R.id.gif);
         Glide.with(context).load(R.drawable.load_gif).into(lottieAnimation);
        return dialog;
    }
//    var restaurantDetails: MutableList<restaurantDetails>?=null
//    var addCartList: MutableList<fooditems>?=null
//    var allFoodList: MutableList<fooditems>?=null
//    var restaurantFoodList: MutableList<fooditems>?=null
//    var ordersList: MutableList<ordersClass>?=null
//    var incrementNumberValue=1
//    var chooseRestaurant:String?=null
//    var checkIncrementValue:Boolean = false
}
