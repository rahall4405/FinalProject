package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;

import com.example.rahall.jokedisplay.EndpointsAsyncTask;

/**
 * Created by rahall4405 on 4/27/16.
 * test derived from http://stackoverflow.com/questions/9963691/android-asynctask-sending-callbacks-to-ui
 *
 */
public class TestAsyncTask  extends AndroidTestCase{
    private String testResult;

    public void testAsyncTask() {
        try {

            EndpointsAsyncTask endPointsAsynkTask = new EndpointsAsyncTask(true);
            endPointsAsynkTask.setListener(new EndpointsAsyncTask.OnTaskCompleted() {

                @Override
                public void onTaskCompleted(String result) {
                    testResult = result;
                }
            }).execute(new Pair<Context, String>(mContext, "Manfred")).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(testResult,testResult != null && testResult.length() > 0 && !testResult.contains("Error") && !testResult.contains("404"));
    }
}
