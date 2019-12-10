package com.example.foodforyou;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

   /* private ArrayList<TipsFragment> mTipsData;*/
    private Context mContext;
    private String[] mTitleText;
    private String[] mInfoText;
    private int[] imageResource;
    private String[] placeGuide;
    private String[] mAuthor;



    public TipsAdapter(Context mContext , String[] mTitleText, String[] mInfoText, int[] imageResource, String[] placeGuide,String[] mAuthor){
        this.mContext = mContext;
        this.imageResource = imageResource;
        this.mTitleText = mTitleText;
        this.mInfoText = mInfoText;
        this.placeGuide = placeGuide;
        this.mAuthor = mAuthor;
    }

    @Override
    public TipsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tips_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder (final ViewHolder holder, int position){
        /*TipsFragment currentTips = mTipsData.get(position);
        holder.bindTo(currentTips);*/
        holder.mTitleText.setText(mTitleText[position]);
        holder.mInfoText.setText(mInfoText[position]);
        holder.mAuthor.setText(mAuthor[position]);
        holder.mTipsImage.setImageResource(imageResource[position]);
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "View Website "
                        + mTitleText[holder.getAdapterPosition()] +
                        "\nHere is the link to the full review: " + placeGuide[holder.
                        getAdapterPosition()]);
                intent.setType("text/plain");
                mContext.startActivity(Intent.createChooser(intent, "Send To"));
            }
        });
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(placeGuide[holder.getAdapterPosition()]));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount(){
        return imageResource.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mAuthor;
        private ImageView mTipsImage;
        private ImageView mImg;
        private CardView mCardView;
        private ImageButton share;
        private RelativeLayout visit;

        ViewHolder (View itemView){
            super(itemView);

            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mCardView = itemView.findViewById(R.id.cardview);
            mTipsImage = itemView.findViewById(R.id.tipsImage);
            mImg= itemView.findViewById(R.id.img);
            mAuthor= itemView.findViewById(R.id.author);
            share = itemView.findViewById(R.id.btnShare);
            visit = itemView.findViewById(R.id.btnVisit);
        }
    }
}
