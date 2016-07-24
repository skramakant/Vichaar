package com.vichaar.vichaar;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import Database.DatabaseOpenHelper;
import Interfaces.InterfaceRefreshDashboard;

public class AddIdea extends AppCompatActivity implements View.OnClickListener {


    private EditText idea_title_value;
    private EditText idea_category_value;
    private EditText idea_description_value;
    private EditText idea_person_name_value;
    private EditText idea_person_email_value;
    private EditText idea_person_mobile_value;
    private Button add_idea;

    private int upVote = 0;
    private int downVote = 0;
    private int viewCount = 0;

    private long currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_idea);
        initView();
    }

    private void initView() {
        idea_title_value = (EditText) findViewById(R.id.idea_title_value);
        idea_category_value = (EditText) findViewById(R.id.idea_category_value);
        idea_description_value = (EditText) findViewById(R.id.idea_description_value);
        idea_person_name_value = (EditText) findViewById(R.id.idea_person_name_value);
        idea_person_email_value = (EditText) findViewById(R.id.idea_person_email_value);
        idea_person_mobile_value = (EditText) findViewById(R.id.idea_person_mobile_value);
        add_idea = (Button) findViewById(R.id.add_idea);
        add_idea.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_idea:
                currentDate = Calendar.getInstance().get(Calendar.MILLISECOND);
                DatabaseOpenHelper.getInstance(this).insertIdea(idea_title_value.getText().toString(),idea_category_value.getText().toString(),
                        idea_description_value.getText().toString(),
                        upVote,downVote,viewCount,
                        currentDate,currentDate,
                        idea_person_name_value.getText().toString(),
                        idea_person_email_value.getText().toString(),
                        idea_person_mobile_value.getText().toString(),
                        "","true");

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
                break;
        }
    }
}
