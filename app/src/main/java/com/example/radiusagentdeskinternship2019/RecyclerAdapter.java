package com.example.radiusagentdeskinternship2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private List<UserModel> listUserData;
    private Context context;

    public RecyclerAdapter(List<UserModel> listUserData, Context context) {
        this.listUserData = listUserData;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_see_more, parent, false);

        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.setData(listUserData.get(position).getStringStartingAddress(), listUserData.get(position).getStringDestinationAddress(), listUserData.get(position).getStringStartingDate(), listUserData.get(position).getStringDestinationDate(), listUserData.get(position).getStringTravelTime(), listUserData.get(position).getStringTotalRevenue());
    }

    @Override
    public int getItemCount() {
        return listUserData.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private TextView textViewStartingAddress, textViewDestinationAddress, textViewTotalRevenue, textViewTravelTime, textViewStartingDate, textViewDestinationDate;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            setIdForAllWidgets(itemView);
        }

        private void setIdForAllWidgets(View itemView) {
            textViewStartingAddress = itemView.findViewById(R.id.text_view_ride_starting_point_past_rides_recycler_data);
            textViewDestinationAddress = itemView.findViewById(R.id.text_view_ride_destination_point_past_rides_recycler_data);
            textViewTotalRevenue = itemView.findViewById(R.id.text_view_total_amount_in_dollars);
            textViewTravelTime = itemView.findViewById(R.id.text_view_travel_time_recycler_data);
            textViewStartingDate = itemView.findViewById(R.id.text_view_ride_starting_date_time_past_rides_recycler_data);
            textViewDestinationDate = itemView.findViewById(R.id.text_view_ride_destination_date_time_past_rides_recycler_data);
        }

        private void setData(String stringStartingAddress, String stringDestinationAddress, String stringStartingDate, String stringDestinationDate, String stringTravelTime, String stringTotalRevenue) {
            textViewStartingAddress.setText(stringStartingAddress);
            textViewDestinationAddress.setText(stringDestinationAddress);
            textViewTotalRevenue.setText(stringTotalRevenue);
            textViewTravelTime.setText(stringTravelTime+" min");
        }
    }
}
