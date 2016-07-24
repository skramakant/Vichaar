package com.vichaar.vichaar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.TextView;

import Adapters.TopIdeasAdapter;
import Database.DatabaseOpenHelper;
import Interfaces.InterfaceOnItemClickHandler;
import Models.IdeaDetailsModel;

public class NewIdeas extends AppCompatActivity implements InterfaceOnItemClickHandler {

    private RecyclerView topIdeasList;

    private TopIdeasAdapter topIdeasAdapter;

    private int ADD_IDEA_REQUEST_CODE = 100;
    private int IDEA_DETAILS_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ideas);
        initView();
    }

    private void initView() {
        topIdeasList = (RecyclerView) findViewById(R.id.top_ideas_list);
        //participateCount = (TextView) findViewById(R.id.participant_count);

        //ideasCount = (TextView) findViewById(R.id.ideas_count);

        topIdeasList.setNestedScrollingEnabled(false);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        topIdeasList.setLayoutManager(layout);


        //cursor code here
        Cursor cursor = DatabaseOpenHelper.getInstance(this).getNewIdeas();
        topIdeasAdapter = new TopIdeasAdapter(this,cursor);
        topIdeasList.setAdapter(topIdeasAdapter);


    }

    @Override
    public void itemClickHandler(IdeaDetailsModel ideaDetailsModel) {
        Intent intent = new Intent(this,IdeaDetails.class);
        //Bundle bundle = new Bundle();
        intent.putExtra("ideaDetails", ideaDetailsModel);
        startActivityForResult(intent,IDEA_DETAILS_REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getSupportActionBar().setTitle("New Ideas");
        return super.onCreateOptionsMenu(menu);
    }
}
