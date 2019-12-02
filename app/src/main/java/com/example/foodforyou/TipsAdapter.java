package com.example.foodforyou;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    private ArrayList<TipsFragment> mTipsData;
    private Context mContext;


    TipsAdapter(Context context, ArrayList<TipsFragment> tipsData){
        this.mTipsData = tipsData;
        this.mContext = context;
    }

    @Override
    public TipsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tips_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder (TipsAdapter.ViewHolder holder, int position){
        TipsFragment currentTips = mTipsData.get(position);
        holder.bindTo(currentTips);
    }

    @Override
    public int getItemCount(){
        return mTipsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mAuthor;
        private ImageView mTipsImage;
        private ImageView mDate;
        private TextView mPublish;
        private ImageView mImg;
        private CardView mCardView;
        private Button share;
        private Button visit;

        ViewHolder (View itemView){
            super(itemView);

            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mCardView = itemView.findViewById(R.id.cardview);
            mTipsImage = itemView.findViewById(R.id.tipsImage);
            mImg= itemView.findViewById(R.id.img);
            mAuthor= itemView.findViewById(R.id.author);
            mDate= itemView.findViewById(R.id.date);
            mPublish= itemView.findViewById(R.id.publishedAt);
            share = itemView.findViewById(R.id.btnShare);
            visit = itemView.findViewById(R.id.btnVisit);
        }

        void bindTo(TipsFragment currentTips){
            mTitleText.setText(currentTips.getTitle());
            mInfoText.setText(currentTips.getInfo());
            Glide.with(mContext).load(currentTips.getImageResource()).into(mTipsImage);
        }
    }
}
