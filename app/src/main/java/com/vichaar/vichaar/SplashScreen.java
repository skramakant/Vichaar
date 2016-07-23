package com.vichaar.vichaar;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    //Declare the Handler as a member variable

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Pass a new instance of StartMainActivityRunnable with reference to 'this'.
        mHandler.postDelayed(new StartMainActivityRunnable(this), SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove any delayed Runnable(s) and prevent them from executing.
        mHandler.removeCallbacksAndMessages(null);
        //Eagerly clear mHandler allocated memory
        mHandler = null;

    }

    //Create a static nested class that extends Runnable to start the main Activity
    private static class StartMainActivityRunnable implements Runnable {
        //Make sure we keep the source Activity as a WeakReference (more on that later)
        private WeakReference mActivity;

        private StartMainActivityRunnable(Activity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void run() {
            //Check that the reference is valid and execute the code
            if (mActivity.get() != null) {
                Activity activity = (Activity) mActivity.get();
                Intent mainIntent = new Intent(activity, DashboardDrawerActivity.class);
                activity.startActivity(mainIntent);
                activity.finish();
            }
        }
    }
}
