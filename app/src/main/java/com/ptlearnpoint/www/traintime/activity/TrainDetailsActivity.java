package com.ptlearnpoint.www.traintime.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ptlearnpoint.www.traintime.LicenseFragment;
import com.ptlearnpoint.www.traintime.R;
import com.ptlearnpoint.www.traintime.adapter.TrainDetailsAdapter;
import com.ptlearnpoint.www.traintime.model.TrainDetails;

import java.util.ArrayList;

public class TrainDetailsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private TrainDetailsAdapter mAdapter;
    private Context context ;
    ArrayList<TrainDetails> trainDetailsArrayList;
    private String toolbarTitle;
    private int placeImageId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_details);
        context = this;
        trainDetailsArrayList = (ArrayList<TrainDetails>) getIntent().getSerializableExtra("list");
        toolbarTitle = getIntent().getStringExtra("place_name");
        placeImageId = getIntent().getIntExtra("place_image",0);
        loadTrainList();
        toolbarSetup();
        loadImage();



    }

    private void loadImage() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(placeImageId).into(imageView);
    }

    private void toolbarSetup() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(toolbarTitle);
    }

    private void loadTrainList() {

        mRecyclerView = (RecyclerView) findViewById(R.id.train_list);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mAdapter = new TrainDetailsAdapter(this, trainDetailsArrayList);
        mRecyclerView.setAdapter(mAdapter);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);
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

    public void openShare() {
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
