package mp.piash.tg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mp.piash.tg.R;
import mp.piash.tg.interfaces.RVClickListener;

/**
 * Created by Extreme_Piash on 1/10/2017.
 */

public class AdapterDetail extends RecyclerView.Adapter<AdapterDetail.DetailViewHolder> {
    private Context mContext;
    private List<String> mStringsTitle;
    private List<String> mStringsValues;
    private RVClickListener mRvClickListener;

    public AdapterDetail(Context mContext, List<String> mStringsTitle, List<String> mStringsValues) {
        this.mContext = mContext;
        this.mStringsTitle = mStringsTitle;
        this.mStringsValues = mStringsValues;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_detail, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        holder.mTextViewDetailTiletle.setText(mStringsTitle.get(position));
        holder.mTextViewDetailValue.setText(mStringsValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringsTitle.size();
    }

    public void setOnClickListener(RVClickListener rvClickListener){
        mRvClickListener = rvClickListener;
    }
    public class DetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       private TextView mTextViewDetailTiletle;
       private TextView mTextViewDetailValue;
        public DetailViewHolder(View itemView) {
            super(itemView);
            mTextViewDetailTiletle = (TextView)itemView.findViewById(R.id.textView_Detail_Title);
            mTextViewDetailValue = (TextView)itemView.findViewById(R.id.textVIew_Detail_Value);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mRvClickListener.onItemClicked(v, getAdapterPosition());
        }
    }
}
