package com.example.foodforyou;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipsFragment extends Fragment {

    private String title;
    private String info;
    private final int imageResource;
    private RecyclerView mRecyclerView;
    private ArrayList<TipsFragment> mTipsData;
    private TipsAdapter mAdapter;


    public TipsFragment(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        // Inflate the layout for this fragment
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTipsData = new ArrayList<>();
        mAdapter = new TipsAdapter(getActivity(),mTipsData);
        mRecyclerView.setAdapter(mAdapter);

        //Get Data
        initializeData();
        return view;
    }

    private void initializeData(){
        String[] tipsList = getResources().getStringArray(R.array.tips_titles);
        String[] tipsInfo = getResources().getStringArray(R.array.tips_info);
        TypedArray tipsImageResources = getResources().obtainTypedArray(R.array.tips_images);

        mTipsData.clear();
        for (int i=0;i<tipsList.length;i++) {
            mTipsData.add(new TipsFragment(tipsList[i], tipsInfo[i],
                    tipsImageResources.getResourceId(i, 0)));
        }
        tipsImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity()).setActionBarTitle("Tips");
    }



}
