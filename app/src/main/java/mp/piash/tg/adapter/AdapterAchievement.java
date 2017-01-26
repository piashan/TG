package mp.piash.tg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mp.piash.tg.R;

/**
 * Created by piash on 1/25/17.
 */

public class AdapterAchievement  extends RecyclerView.Adapter<AdapterAchievement.ViewHolderAchievement>{

    private Context mContext;
    private List<String> mDataset;

    public AdapterAchievement(Context mContext, List<String> mDataset) {
        this.mContext = mContext;
        this.mDataset = mDataset;
    }

    @Override
    public ViewHolderAchievement onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_achievement, parent, false);
        return new ViewHolderAchievement(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAchievement holder, int position) {


       /* if (mDataset.get(position ) == 110){
            holder.mTextViewTitle.setText("Complete High School");
        }else if (mDataset.get(position ) == 111){
            holder.mTextViewTitle.setText("Complete Study at college");
        }else if (mDataset.get(position ) == 112){
            holder.mTextViewTitle.setText("Complete Study at University");
        }else if (mDataset.get(position ) == 113){
            holder.mTextViewTitle.setText("Complete MBA");
        }else {
            holder.mTextViewTitle.setText(mDataset.get(position).toString());
        }*/
        holder.mTextViewTitle.setText(mDataset.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolderAchievement extends RecyclerView.ViewHolder {

        private TextView mTextViewTitle;
        public ViewHolderAchievement(View itemView) {
            super(itemView);
            mTextViewTitle = (TextView)itemView.findViewById(R.id. txtView_Adapter_Achievement_Title);

        }
    }
}
