package com.example.rahall.jokedisplay;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.example.JokeLib;
import com.example.rahall4405.application.jokebackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by rahall4405 on 4/26/16.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private boolean isTest = false;
    private OnTaskCompleted listener;

    public EndpointsAsyncTask(boolean isTest) {
        this.isTest = isTest;
    }
    public interface OnTaskCompleted{
        void onTaskCompleted(String result);
    }


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
       //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        if(!isTest) {
            Intent myIntent = new Intent(context, DisplayJokeActivity.class);

            JokeLib jokeLib = new JokeLib();
            myIntent.putExtra("joke", jokeLib.getJoke());
            context.startActivity(myIntent);
        } else {
            listener.onTaskCompleted(result);
        }
    }

    public EndpointsAsyncTask setListener(OnTaskCompleted listener) {
        this.listener = listener;
        return this;
    }
}
