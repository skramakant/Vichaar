package com.vichaar.vichaar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import Adapters.TopIdeasAdapter;
import Database.DatabaseOpenHelper;
import Interfaces.InterfaceOnItemClickHandler;
import Interfaces.InterfaceRefreshDashboard;
import Models.IdeaDetailsModel;

public class DashboardDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, InterfaceOnItemClickHandler,InterfaceRefreshDashboard{

    public static String TAG = DashboardDrawerActivity.class.getSimpleName();
    private RecyclerView topIdeasList;
    private TextView participateCount;
    private TextView ideasCount;
    private TopIdeasAdapter topIdeasAdapter;

    private int ADD_IDEA_REQUEST_CODE = 100;
    private int IDEA_DETAILS_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initView();
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initView() {
        topIdeasList = (RecyclerView) findViewById(R.id.top_ideas_list);
        //participateCount = (TextView) findViewById(R.id.participant_count);

        ideasCount = (TextView) findViewById(R.id.ideas_count);

        topIdeasList.setNestedScrollingEnabled(false);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        topIdeasList.setLayoutManager(layout);


        //cursor code here
        Cursor cursor = DatabaseOpenHelper.getInstance(this).getTopFiveIdeas();
        topIdeasAdapter = new TopIdeasAdapter(this,cursor);
        topIdeasList.setAdapter(topIdeasAdapter);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard_drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all_ideas) {
            // Handle the camera action
            Intent intent = new Intent(this,NewIdeas.class);
            intent.putExtra("TAB_ALL_IDEAS","all_ideas");
            startActivity(intent);
            Log.v(TAG,"All Ideas click");
        } else if (id == R.id.top_voted) {
            Intent intent = new Intent(this,NewIdeas.class);
            intent.putExtra("TAB_TOP_VOTED","top_voted");
            startActivity(intent);
            Log.v(TAG,"Top Voted click");
        } else if (id == R.id.new_ideas) {
            Intent intent = new Intent(this,NewIdeas.class);
            intent.putExtra("TAB_NEW_IDEAS","new_ideas");
            startActivity(intent);
            Log.v(TAG,"Newbies click");
        } else if (id == R.id.leader_board) {
            Log.v(TAG,"LeaderBoard click");
        } else if (id == R.id.processed_ideas) {
            Log.v(TAG,"Processed click");
        } else if (id == R.id.funded_ideas) {
            Log.v(TAG,"Funded click");
        }else if(id == R.id.add_idea){
            Log.v(TAG,"Add idea");
            Intent intent = new Intent(this,AddIdea.class);
            startActivityForResult(intent,ADD_IDEA_REQUEST_CODE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == ADD_IDEA_REQUEST_CODE){
                //Cursor cursor = DatabaseOpenHelper.getInstance(this).getIdeaDetails();
                //topIdeasAdapter.swapCursor(cursor);
            }
        }
    }

    @Override
    public void itemClickHandler(IdeaDetailsModel ideaDetailsModel) {
        Intent intent = new Intent(this,IdeaDetails.class);
        //Bundle bundle = new Bundle();
        intent.putExtra("ideaDetails", ideaDetailsModel);
        startActivityForResult(intent,IDEA_DETAILS_REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = DatabaseOpenHelper.getInstance(this).getTopFiveIdeas();
        topIdeasAdapter.swapCursor(cursor);
    }

    @Override
    public void refreshDashBoard(int totalIdeasCount) {
        ideasCount.setText(Integer.toString(totalIdeasCount));
    }
}
