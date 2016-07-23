package Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vichaar.vichaar.R;

/**
 * Created by ramakant on 23/7/16.
 */
public class TopIdeasAdapter extends CursorRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public TopIdeasAdapter(Context context,Cursor cursor) {
        super(context,cursor);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.top_ideas_list_item,parent,false);
        return new TopIdeasViewHolder(view);
    }

/*    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }*/

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {

    }
}
