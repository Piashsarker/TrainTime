package com.ptlearnpoint.www.traintime.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ptlearnpoint.www.traintime.LicenseFragment;
import com.ptlearnpoint.www.traintime.R;
import com.ptlearnpoint.www.traintime.adapter.PlaceListAdapter;
import com.ptlearnpoint.www.traintime.model.Place;
import com.ptlearnpoint.www.traintime.model.TrainDetails;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private PlaceListAdapter mAdapter;
    private Context context ;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        papulatePlaceListData();

    }

    private void papulatePlaceListData() {

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        final ArrayList<Place> data = fillWithPlaceData();

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mAdapter = new PlaceListAdapter(this, data);
        mRecyclerView.setAdapter(mAdapter);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);

        mAdapter.setOnItemClickListener(new PlaceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                ArrayList<TrainDetails> trainDetailsArrayList = data.get(position).getTrainDetailsList();
                intent = new Intent(MainActivity.this, TrainDetailsActivity.class);
                intent.putExtra("list",trainDetailsArrayList);
                intent.putExtra("place_name",data.get(position).getFrom()+"-"+data.get(position).getTo());
                intent.putExtra("place_image",data.get(position).getPlaceImageId());
                startActivity(intent);
            }
        });
    }

    private ArrayList<Place> fillWithPlaceData() {

        ArrayList<Place> data = new ArrayList<>();

        ArrayList<TrainDetails> forOne = fillOneData();

        data.add(new Place("Dhaka","B.Baria",120,R.drawable.wall,forOne));
        data.add(new Place("B.Baria","Dhaka",123,R.drawable.wall, forOne));
        data.add(new Place("Chittagong","B.Baria",300,R.drawable.wall,forOne));
        data.add(new Place("B.Baria","Chittagong",300,R.drawable.wall,forOne));
        data.add(new Place("Shylet","B.Baria",230,R.drawable.wall,forOne));
        data.add(new Place("B.Baria","Shylet",340,R.drawable.wall,forOne));

        return data ;

    }

    private ArrayList<TrainDetails> fillOneData() {
        ArrayList<TrainDetails> list = new ArrayList<>();

        list.add(new TrainDetails("Train Name","TT0002","10.10AM","Firday",50));
        list.add(new TrainDetails("Train Two","TT0045","10.10AM","Friday",333));

        return list ;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.item_share:
                openShare();
                break;
            case R.id.item_rate_app:
                openRate();
                break ;
            case R.id.item_submit_bug:
                openSubmitBug();
                break ;
            case R.id.item_license:
                openLisence();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openSubmitBug() {
        String to = "sarkerpt@gmail.com";
        String subject = "FlashForOlderVersion Light For Android - Bug Report";

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    private void openRate() {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    private void openShare() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        String appLink = "https://play.google.com/store/apps/details?id="+context.getPackageName();
        sharingIntent.setType("text/plain");
        String shareBodyText = "Check Out The Simple FlashLight Android App. \n Link: "+appLink +" \n" +
                " #FlashlightApp #Android";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"FlashForOlderVersion Light Android App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(sharingIntent, "Share"));
    }

    private void openLisence() {
        LicenseFragment licensesFragment = new LicenseFragment();
        licensesFragment.show(getSupportFragmentManager().beginTransaction(), "dialog_licenses");
    }
}
