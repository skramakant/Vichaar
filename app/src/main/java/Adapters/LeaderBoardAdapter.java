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
public class LeaderBoardAdapter extends CursorRecyclerViewAdapter<LeaderBoardViewHolder> {

    public LeaderBoardAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(LeaderBoardViewHolder viewHolder, Cursor cursor) {
        int i = cursor.getPosition();
        String rank = String.valueOf(i+1);
        viewHolder.getTv_rank().setText(rank.toString());
        viewHolder.getTv_person_name().setText(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PERSON_NAME)));

    }

    @Override
    public LeaderBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.leader_board_list_item,parent,false);
        return new LeaderBoardViewHolder(view);
    }
}
