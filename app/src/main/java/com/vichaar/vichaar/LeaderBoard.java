package com.vichaar.vichaar;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import Adapters.LeaderBoardAdapter;
import Adapters.TopIdeasAdapter;
import Database.DatabaseOpenHelper;

public class LeaderBoard extends AppCompatActivity {


    private RecyclerView topIdeasList;

    private LeaderBoardAdapter leaderBoardAdapter;

    private int ADD_IDEA_REQUEST_CODE = 100;
    private int IDEA_DETAILS_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
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
        Cursor cursor = null;
        cursor = DatabaseOpenHelper.getInstance(this).getLeaderBoard();
        //Cursor cursor = DatabaseOpenHelper.getInstance(this).getNewIdeas();
        if(cursor != null){
            leaderBoardAdapter = new LeaderBoardAdapter(this,cursor);
            topIdeasList.setAdapter(leaderBoardAdapter);
        }
    }
}
