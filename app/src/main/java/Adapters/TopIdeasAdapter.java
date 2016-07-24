package Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vichaar.vichaar.R;

import Interfaces.InterfaceOnItemClickHandler;
import Models.IdeaDetailsModel;

/**
 * Created by ramakant on 23/7/16.
 */
public class TopIdeasAdapter extends CursorRecyclerViewAdapter<TopIdeasViewHolder> {

    private Context mContext;
    private InterfaceOnItemClickHandler interfaceOnItemClickHandler;

    public TopIdeasAdapter(Context context,Cursor cursor) {
        super(context,cursor);
        mContext = context;
        interfaceOnItemClickHandler = (InterfaceOnItemClickHandler) context;
    }

    @Override
    public void onBindViewHolder(TopIdeasViewHolder viewHolder, Cursor cursor) {
        IdeaDetailsModel ideaDetailsModel = IdeaDetailsModel.getListItem(cursor);
        onItemClickHandler(viewHolder,ideaDetailsModel);

        viewHolder.getIdea_title().setText(ideaDetailsModel.getIdeaTitle());
        viewHolder.getPerson_name().setText(ideaDetailsModel.getPersonName());
        viewHolder.getCategory().setText(ideaDetailsModel.getIdeaCategory());
        viewHolder.getTvViewCount().setText(Integer.toString(ideaDetailsModel.getIdeaViewCount()));
        viewHolder.getTvVoteCount().setText(Integer.toString(ideaDetailsModel.getIdeaUpVote()));
        viewHolder.getTvCommentCount().setText(Integer.toString(ideaDetailsModel.getIdeaDownVote()));
    }

    @Override
    public TopIdeasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.top_ideas_list_item,parent,false);
        return new TopIdeasViewHolder(view);
    }

/*    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }*/

/*    @Override
    public int getItemCount() {
        return 10;
    }*/

    void onItemClickHandler(TopIdeasViewHolder viewHolder, final IdeaDetailsModel ideaDetailsModel){
        viewHolder.getCv_idea_tem().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceOnItemClickHandler.itemClickHandler(ideaDetailsModel);
            }
        });
    }

}
