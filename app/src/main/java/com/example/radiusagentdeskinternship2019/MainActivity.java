package com.example.radiusagentdeskinternship2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Widgets
    private RecyclerView recyclerView;
    private TextView textViewUserName, textViewUserAddress, textViewUserRidesCount, textViewUserFreeRidesCount, textViewUserCreditCount, textViewSeeMore, textViewCurrencySymbol;
    private CircleImageView circleImageViewUserProfile;
    private LinearLayout linearLayoutProgressBar;
    //Other Variables
    private String stringUserName, stringImageUrl, stringUserAddress, stringRideCount, stringFreeRides, stringCredits, stringCurrencySymbol;
    private List<UserModel> userModelsList;

    //User defined classes (Recycler Adapter)
    private RecyclerAdapter recyclerAdapter;

    //Json Data Related Variables
    private RequestQueue mRequestQueue;
    private static final String jsonUrl = "https://gist.githubusercontent.com/iranjith4/522d5b466560e91b8ebab54743f2d0fc/raw/7b108e4aaac287c6c3fdf93c3343dd1c62d24faf/radius-mobile-intern.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setIdForAllWidgets();
        userModelsList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setDataInList();

        textViewSeeMore.setOnClickListener(this);
    }

    private void setDataInList() {
        mRequestQueue = Volley.newRequestQueue(this);
        jsonPars();
    }

    private void jsonPars() {
        setDataToProfile();
    }

    private void setDataToRecyclerView(JSONObject jObject) {
        try {
            JSONArray jsonArray = jObject.getJSONArray("trips");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                UserModel userModel = new UserModel();

                userModel.setStringStartingAddress(jsonObject.getString("from"));
                userModel.setStringDestinationAddress(jsonObject.getString("to"));
                userModel.setStringStartingDate(jsonObject.getString("from_time"));
                userModel.setStringDestinationDate(jsonObject.getString("to_time"));
                userModel.setStringTravelTime(jsonObject.getString("trip_duration_in_mins"));

                JSONObject jsonObject1Cost = jsonObject.getJSONObject("cost");
                userModel.setStringCurrencySymbol(jsonObject1Cost.getString("currency_symbol"));
                userModel.setStringTotalRevenue(jsonObject1Cost.getString("value"));

                userModelsList.add(userModel);
            }
            linearLayoutProgressBar.setVisibility(View.GONE);
            recyclerAdapter = new RecyclerAdapter(userModelsList, this);
            recyclerView.setAdapter(recyclerAdapter);
            recyclerAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setDataToProfile() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jObject = jsonObject.getJSONObject("data");
                    JSONObject objectProfile = jObject.getJSONObject("profile");
                    JSONObject objectStats = jObject.getJSONObject("stats");

                    String first_name = objectProfile.getString("first_name");
                    String last_name = objectProfile.getString("last_name");
                    stringUserName = first_name + " " + last_name;
                    stringImageUrl = objectProfile.getString("middle_name");
                    stringUserAddress = objectProfile.getString("city") + ", " + objectProfile.getString("Country");
                    stringRideCount = objectStats.getString("rides");
                    stringFreeRides = objectStats.getString("free_rides");

                    JSONObject jsonObjectCreditStats = objectStats.getJSONObject("credits");
                    stringCredits = jsonObjectCreditStats.getString("value");
                    stringCurrencySymbol = jsonObjectCreditStats.getString("currency_symbol");

                    setData();
                    setDataToRecyclerView(jObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "There is some error while fetching data", Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(stringRequest);
    }

    private void setData() {
        textViewUserName.setText(stringUserName);
        textViewUserAddress.setText(stringUserAddress);
        textViewUserRidesCount.setText(stringRideCount);
        textViewUserFreeRidesCount.setText(stringFreeRides);
        textViewUserCreditCount.setText(stringCredits);
        textViewCurrencySymbol.setText(stringCurrencySymbol);

        Picasso.with(this).load(stringImageUrl)
                .centerCrop()
                .fit()
                .placeholder(R.drawable.background_profile_layout)
                .into(circleImageViewUserProfile);
    }

    private void setIdForAllWidgets() {

        recyclerView = findViewById(R.id.recycler_view_main);

        textViewUserName = findViewById(R.id.text_view_user_name_user_profile);
        textViewUserAddress = findViewById(R.id.text_view_user_address_user_profile);
        textViewUserRidesCount = findViewById(R.id.text_view_user_rides_count_user_profile);
        textViewUserFreeRidesCount = findViewById(R.id.text_view_user_free_rides_count_user_profile);
        textViewUserCreditCount = findViewById(R.id.text_view_user_credits_count_user_profile);
        textViewSeeMore = findViewById(R.id.text_view_see_more);
        textViewCurrencySymbol = findViewById(R.id.image_view_dollar_profile_layout_main);

        circleImageViewUserProfile = findViewById(R.id.circle_image_view_user_profile_image_user_profile);

        linearLayoutProgressBar = findViewById(R.id.linear_layout_progress_bar_recycler_view);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Right now there is no more data", Toast.LENGTH_SHORT).show();
    }
}
