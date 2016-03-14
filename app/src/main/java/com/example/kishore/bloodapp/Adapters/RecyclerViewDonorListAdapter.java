package com.example.kishore.bloodapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kishore.bloodapp.Models.DonorsCredentials;
import com.example.kishore.bloodapp.R;

import java.util.ArrayList;

/**
 * Created by NIRANJAN on 13-03-2016.
 */
public class RecyclerViewDonorListAdapter extends RecyclerView.Adapter<RecyclerViewDonorListAdapter.CustomViewHolder>{

    Context context;
    ArrayList<DonorsCredentials> listOfDonors;

    public RecyclerViewDonorListAdapter(Context context) {
        this.context = context;
        listOfDonors = new ArrayList<>();
    }

    public void updateRecyclerView(ArrayList<DonorsCredentials> listOfDonors) {
        this.listOfDonors = listOfDonors;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row_recycler_view, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.name.setText(listOfDonors.get(position).getName());
        holder.emailId.setText(listOfDonors.get(position).getMailId());
    }

    @Override
    public int getItemCount() {
        return listOfDonors.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView name, emailId;

        public CustomViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameOfDonor);
            emailId = (TextView) itemView.findViewById(R.id.emailId);
        }
    }

}
