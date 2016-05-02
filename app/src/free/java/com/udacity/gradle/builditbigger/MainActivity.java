package com.udacity.gradle.builditbigger;

import android.content.Context;

import android.support.v4.util.Pair;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import com.example.rahall.jokedisplay.*;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity {
    private Context context;
    private InterstitialAd mInterstitialAd;
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        context = this;
        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                spinner.setVisibility(View.VISIBLE);
                try {
                    new EndpointsAsyncTask(false, spinner,context).execute();
                } catch (Exception e) {
                    e.printStackTrace();
                    spinner.setVisibility(View.GONE);
                }
            }
        });


    }
    @Override
    public void onResume() {
        super.onResume();
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        showInterstitial();
       /* try {
            new EndpointsAsyncTask(false).execute(new Pair<Context, String>(context, "Manfred"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/


            /*Intent myIntent = new Intent(this, DisplayJokeActivity.class);

        JokeLib jokeLib = new JokeLib();
        myIntent.putExtra("joke",jokeLib.getJoke());
        startActivity(myIntent);*/

       // Toast.makeText(this, jokeLib.getJoke(), Toast.LENGTH_SHORT).show();


    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


}
