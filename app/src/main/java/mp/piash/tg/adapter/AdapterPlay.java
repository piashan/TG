package mp.piash.tg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mp.piash.tg.R;

/**
 * Created by Extreme_Piash on 1/3/2017.
 */

public class AdapterPlay extends RecyclerView.Adapter<AdapterPlay.ViewHolderPlay> {

    private Context mContext;
    private List<String> mStringList;

    public AdapterPlay(Context mContext, List<String> mStringList) {
        this.mContext = mContext;
        this.mStringList = mStringList;
    }

    @Override
    public ViewHolderPlay onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_play, parent, false);
        return new ViewHolderPlay(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPlay holder, int position) {

        holder.mTextViewPlayTitle.setText(mStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }

    public class ViewHolderPlay extends RecyclerView.ViewHolder {
        private TextView mTextViewPlayTitle;
        private ImageView mImageViewPlay;
        public ViewHolderPlay(View itemView) {
            super(itemView);
            mTextViewPlayTitle = (TextView)itemView.findViewById(R.id.textViewPlayTitle);
            mImageViewPlay = (ImageView)itemView.findViewById(R.id.imageViewPlay);
        }
    }
}
