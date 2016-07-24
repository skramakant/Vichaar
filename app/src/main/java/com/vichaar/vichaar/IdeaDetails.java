package com.vichaar.vichaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import Models.IdeaDetailsModel;

public class IdeaDetails extends AppCompatActivity {
    public static String TAG = IdeaDetails.class.getSimpleName();
    private IdeaDetailsModel ideaDetailsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_details);
        if (getIntent().getExtras() != null){
            ideaDetailsModel = (IdeaDetailsModel) getIntent().getCharSequenceExtra("ideaDetails");
            Log.v(TAG,"got data");
        }
    }
}
