package com.vichaar.vichaar;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

import Adapters.CommentsAdapter;
import Adapters.TopIdeasAdapter;
import Database.DatabaseOpenHelper;
import Models.IdeaDetailsModel;

public class IdeaDetails extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = IdeaDetails.class.getSimpleName();
    private IdeaDetailsModel ideaDetailsModel;


    private TextView idea_details_title;
    private TextView idea_details_person;
    private TextView idea_details_category;
    private TextView idea_details_description;
    private TextView idea_details_upvote;
    private Button bt_up_vote;
    private TextView idea_details_downvote;
    private Button   bt_down_vote;

    private EditText idea_add_comment;
    private Button add_comment;
    private RecyclerView comment_list;

    private CommentsAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_details);
        initView();

        if (getIntent().getExtras() != null){
            ideaDetailsModel = (IdeaDetailsModel) getIntent().getSerializableExtra("ideaDetails");
            Log.v(TAG,"got data");
        }

        setDataToView();
        increaseViewCount();
    }

    private void increaseViewCount() {
        int viewCount = ideaDetailsModel.getIdeaViewCount() + 1;
        DatabaseOpenHelper.getInstance(this).Views(viewCount,ideaDetailsModel.getId());
    }


    private void initView() {
        idea_details_title = (TextView) findViewById(R.id.idea_details_title);
        idea_details_person = (TextView) findViewById(R.id.idea_details_person);
        idea_details_category = (TextView) findViewById(R.id.idea_details_category);
        idea_details_description = (TextView) findViewById(R.id.idea_details_description);
        idea_details_upvote = (TextView) findViewById(R.id.idea_details_upvote);
        idea_details_downvote = (TextView) findViewById(R.id.idea_details_downvote);


        bt_up_vote = (Button) findViewById(R.id.bt_up_vote);
        bt_up_vote.setOnClickListener(this);

        bt_down_vote = (Button) findViewById(R.id.bt_down_vote);
        bt_down_vote.setOnClickListener(this);

        add_comment = (Button) findViewById(R.id.add_comment);
        add_comment.setOnClickListener(this);

        idea_add_comment = (EditText) findViewById(R.id.idea_add_comment);
        comment_list = (RecyclerView) findViewById(R.id.comment_list);


    }

    private void setDataToView() {
        idea_details_title.setText(ideaDetailsModel.getIdeaTitle());
        idea_details_person.setText(ideaDetailsModel.getPersonName());
        idea_details_category.setText(ideaDetailsModel.getIdeaCategory());
        idea_details_description.setText(ideaDetailsModel.getIdeaDescription());

        idea_details_upvote.setText(String.valueOf(ideaDetailsModel.getIdeaUpVote()));
        idea_details_downvote.setText(String.valueOf(ideaDetailsModel.getIdeaDownVote()));

        comment_list.setNestedScrollingEnabled(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        comment_list.setLayoutManager(layout);


        //cursor code here
        Cursor cursor = DatabaseOpenHelper.getInstance(this).getComments(ideaDetailsModel.getId());
        commentAdapter = new CommentsAdapter(this,cursor);
        comment_list.setAdapter(commentAdapter);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_up_vote:
                int upVote = ideaDetailsModel.getIdeaUpVote() + 1;
                DatabaseOpenHelper.getInstance(this).upVote(upVote,ideaDetailsModel.getId());
                idea_details_upvote.setText(String.valueOf(upVote));
                break;
            case R.id.bt_down_vote:
                int downVote = ideaDetailsModel.getIdeaDownVote() + 1;
                DatabaseOpenHelper.getInstance(this).DownVote(downVote,ideaDetailsModel.getId());
                idea_details_downvote.setText(String.valueOf(downVote));
                break;
            case R.id.add_comment:
                String comment = String.valueOf(idea_add_comment.getText());
                DatabaseOpenHelper.getInstance(this).insertComment(ideaDetailsModel.getId(),comment);
                refreshCommentField();
                break;
        }
    }

    private void refreshCommentField() {
        Cursor cursor = DatabaseOpenHelper.getInstance(this).getComments(ideaDetailsModel.getId());
        commentAdapter.swapCursor(cursor);
    }
}
