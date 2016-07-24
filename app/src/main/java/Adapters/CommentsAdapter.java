package Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vichaar.vichaar.R;

import Database.DatabaseOpenHelper;

/**
 * Created by ramakant on 24/7/16.
 */
public class CommentsAdapter extends CursorRecyclerViewAdapter<CommentsViewHolder> {


    public CommentsAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder viewHolder, Cursor cursor) {

        viewHolder.getTv_lead_comment().setText(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.IDEA_COMMENT)));
    }


    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.idea_comment_list_item,parent,false);
        return new CommentsViewHolder(view);
    }
}
