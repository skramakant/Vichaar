package Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vichaar.vichaar.R;

/**
 * Created by ramakant on 23/7/16.
 */
public class TopIdeasViewHolder extends RecyclerView.ViewHolder {

    private TextView idea_title;
    private TextView person_name;

    public CardView getCv_idea_tem() {
        return cv_idea_tem;
    }

    public void setCv_idea_tem(CardView cv_idea_tem) {
        this.cv_idea_tem = cv_idea_tem;
    }

    private CardView cv_idea_tem;

    public TextView getCategory() {
        return category;
    }

    public void setCategory(TextView category) {
        this.category = category;
    }

    public TextView getTvViewCount() {
        return tvViewCount;
    }

    public void setTvViewCount(TextView tvViewCount) {
        this.tvViewCount = tvViewCount;
    }

    public TextView getTvCommentCount() {
        return tvCommentCount;
    }

    public void setTvCommentCount(TextView tvCommentCount) {
        this.tvCommentCount = tvCommentCount;
    }

    public TextView getTvVoteCount() {
        return tvVoteCount;
    }

    public void setTvVoteCount(TextView tvVoteCount) {
        this.tvVoteCount = tvVoteCount;
    }

    public TextView getPerson_name() {
        return person_name;
    }

    public void setPerson_name(TextView person_name) {
        this.person_name = person_name;
    }

    public TextView getIdea_title() {
        return idea_title;
    }

    public void setIdea_title(TextView idea_title) {
        this.idea_title = idea_title;
    }

    private TextView category;
    private TextView tvVoteCount;
    private TextView tvCommentCount;
    private TextView tvViewCount;

    public TopIdeasViewHolder(View itemView) {
        super(itemView);
        idea_title = (TextView) itemView.findViewById(R.id.idea_title);
        person_name = (TextView) itemView.findViewById(R.id.person_name);
        category = (TextView) itemView.findViewById(R.id.category);
        tvVoteCount = (TextView) itemView.findViewById(R.id.tvVoteCount);
        tvCommentCount = (TextView) itemView.findViewById(R.id.tvCommentCount);
        tvViewCount = (TextView) itemView.findViewById(R.id.tvViewCount);
        cv_idea_tem = (CardView) itemView.findViewById(R.id.cv_idea_card_item);
    }
}
