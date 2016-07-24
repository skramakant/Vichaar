package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vichaar.vichaar.R;

/**
 * Created by ramakant on 24/7/16.
 */
public class LeaderBoardViewHolder extends RecyclerView.ViewHolder {

    public TextView getTv_rank() {
        return tv_rank;
    }

    public void setTv_rank(TextView tv_rank) {
        this.tv_rank = tv_rank;
    }

    public TextView getTv_person_name() {
        return tv_person_name;
    }

    public void setTv_person_name(TextView tv_person_name) {
        this.tv_person_name = tv_person_name;
    }

    private TextView tv_rank;
    private TextView tv_person_name;

    public LeaderBoardViewHolder(View itemView) {
        super(itemView);
        tv_rank = (TextView) itemView.findViewById(R.id.tv_rank);
        tv_person_name = (TextView) itemView.findViewById(R.id.tv_person_name);
    }
}
