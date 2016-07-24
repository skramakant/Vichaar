package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vichaar.vichaar.R;

/**
 * Created by ramakant on 24/7/16.
 */
public class CommentsViewHolder extends RecyclerView.ViewHolder {

    public TextView getTv_lead_comment() {
        return tv_lead_comment;
    }

    public void setTv_lead_comment(TextView tv_lead_comment) {
        this.tv_lead_comment = tv_lead_comment;
    }

    private TextView tv_lead_comment;

    public CommentsViewHolder(View itemView) {
        super(itemView);
        tv_lead_comment = (TextView) itemView.findViewById(R.id.tv_lead_comment);
    }
}
