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



public class MainActivity extends AppCompatActivity {
    private Context context;
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        context = this;
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
        spinner.setVisibility(View.VISIBLE);
        try {
            new EndpointsAsyncTask(false,spinner,this).execute();
        } catch (Exception e) {
            e.printStackTrace();
            spinner.setVisibility(View.GONE);

        }

            /*Intent myIntent = new Intent(this, DisplayJokeActivity.class);

        JokeLib jokeLib = new JokeLib();
        myIntent.putExtra("joke",jokeLib.getJoke());
        startActivity(myIntent);*/

       // Toast.makeText(this, jokeLib.getJoke(), Toast.LENGTH_SHORT).show();


    }


}
