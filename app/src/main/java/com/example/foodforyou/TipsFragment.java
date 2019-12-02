package com.example.foodforyou;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipsFragment extends Fragment {


    private String[] mTitleText;
    private String[] mInfoText;
    private int[] imageResource;
    private RecyclerView mRecyclerView;
    private ArrayList<TipsFragment> mTipsData;
    private RecyclerView.Adapter mAdapter;
    private String[] placeGuide;

    public TipsFragment(String[] mTitleText,String[] mInfoText, int[] imageResource, String[] placeGuide) {
        this.mTitleText = mTitleText;
        this.mInfoText= mInfoText;
        this.imageResource = imageResource;
        this.placeGuide = placeGuide;
    }

    public String[] getTitle() {
        return mTitleText;
    }

    public String[] getInfo() {
        return mInfoText;
    }

    public String[] getplaceGuide() {
        return placeGuide;
    }

    public int[] getImageResource() {
        return imageResource;
    }


    /*String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        imageResource = new int[]{R.drawable.img_bowling, R.drawable.img_basketball,
                R.drawable.img_baseball, R.drawable.img_badminton};
        mInfoText = new String[]{
                "Navagio Beach",
                "Anse Source d'Argent Beach",
                "As Catedrais Beach",
                "La Concha Beach",
                };
        mTitleText = new String[]{
                "Here is some Baseball news!",
                "Here is some Baseball news!",
                "Here is some Baseball news!",
                "Here is some Baseball news!"
        };
        placeGuide = new String[]{
                "https://www.tripadvisor.com.my/Attraction_Review-g7777607-" +
                "d671779-Reviews-Navagio_Beach_Shipwreck_Beach-Anafonitria_Zakynthos_Ionian_Islands.html",
                "https://www.tripadvisor.com.my/Attraction_Review-g477968-d637885-Reviews-Anse_Source_D_Argent" +
                        "-La_Digue_Island.html",
                "https://www.tripadvisor.com.my/Attraction_Review-g609028-d1547522-Reviews-As_Catedrais_Beach-Ribadeo_" +
                        "Province_of_Lugo_Galicia.html",
                "https://www.tripadvisor.com.my/Attraction_Review-g187457-d675885-Reviews-La_Concha_Beach-San_Sebastian" +
                        "_Donostia_Province_of_Guipuzcoa_Basque_Country.html",
                };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new TipsAdapter(getActivity(), mTitleText,mInfoText, imageResource, placeGuide);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity()).setActionBarTitle("Tips");
    }



}
