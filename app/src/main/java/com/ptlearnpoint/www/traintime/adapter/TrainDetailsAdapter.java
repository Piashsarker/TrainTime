package com.ptlearnpoint.www.traintime.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ptlearnpoint.www.traintime.ClipBoardManager;
import com.ptlearnpoint.www.traintime.R;
import com.ptlearnpoint.www.traintime.model.TrainDetails;

import java.util.ArrayList;

/**
 * Created by PT on 1/7/2017.
 */

public class TrainDetailsAdapter extends RecyclerView.Adapter<TrainDetailsAdapter.ViewHolder> {
    private Context context ;
    private ArrayList<TrainDetails> trainDetailsArrayList;

    public TrainDetailsAdapter(Context context , ArrayList<TrainDetails> trainDetailsArrayList){
        this.context = context ;
        this.trainDetailsArrayList = trainDetailsArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_train_details,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.trainName.setText("Name: "+trainDetailsArrayList.get(position).getTrainName());
            holder.trainTime.setText("Time: "+trainDetailsArrayList.get(position).getTrainTime());
            holder.trainOffDay.setText("OFF Day: "+trainDetailsArrayList.get(position).getOffDay());
            holder.trainTicketCost.setText("Ticket: "+trainDetailsArrayList.get(position).getTicketPrice());
            holder.share.setText("Share");
            holder.copy.setText("Copy Text");

           holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShare();
            }
        });
          holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipBoardManager clipBoardManager = new ClipBoardManager();
                String text ="Train Name: "+ trainDetailsArrayList.get(position).getTrainName()+". Time: "+trainDetailsArrayList.get(position).getTrainTime();
                clipBoardManager.copyToClipboard(context,text);
                Snackbar.make(v,"Copy To Clipboard",Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainDetailsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView trainName , trainTime, trainOffDay , trainTicketCost, copy, share ;

        public ViewHolder(View itemView) {
            super(itemView);

            trainName = (TextView) itemView.findViewById(R.id.train_name);
            trainTime = (TextView) itemView.findViewById(R.id.train_time);
            trainOffDay = (TextView) itemView.findViewById(R.id.off_day);
            trainTicketCost = (TextView) itemView.findViewById(R.id.ticket_cost);
            copy = (TextView) itemView.findViewById(R.id.copy);
            share = (TextView) itemView.findViewById(R.id.share);
        }
    }
    public void openShare() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        String appLink = "https://play.google.com/store/apps/details?id="+context.getPackageName();
        sharingIntent.setType("text/plain");
        String shareBodyText = "Check Out The Simple FlashLight Android App. \n Link: "+appLink +" \n" +
                " #FlashlightApp #Android";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"FlashForOlderVersion Light Android App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        context.startActivity(Intent.createChooser(sharingIntent, "Share"));
    }
}
