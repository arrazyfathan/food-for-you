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
    private String[] mAuthor;

    public TipsFragment(String[] mTitleText,String[] mInfoText, int[] imageResource, String[] placeGuide,String[] mAuthor) {
        this.mTitleText = mTitleText;
        this.mInfoText= mInfoText;
        this.imageResource = imageResource;
        this.placeGuide = placeGuide;
        this.mAuthor = mAuthor;
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

    public String[] getmAuthor() {
        return mAuthor;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        imageResource = new int[]{R.drawable.img_bowling, R.drawable.img_basketball,
                R.drawable.img_baseball, R.drawable.img_badminton};
        mAuthor= new String[]{
                "Virgina Maulita Putri - wolipop-detik.com",
                "Septia Shidqiyyah - liputan6.com",
                "dr. Fitrina Aprilia - halodoc.com",
                "Novita Joseph - hellosehat.com"
        };
        mInfoText = new String[]{
                "Memiliki tubuh yang ideal merupakan idaman banyak wanita. Banyak cara yang bisa dilakukan untuk memiliki ..." ,
                "Sudah menjadi rahasia umum, jika setiap orang pasti menginginkan tubuh ideal dengan berat badan sekecil ... ",
                "Makananan sehat adalah makanan yang seharusnya mengandung beragam nutrisi yang dibutuhkan oleh ...",
                "akan makananan sehat merupakan salah satu hal yang harus dilakukan untuk menjaga kesehatan tubuh. Bukan hanya ..." ,
                };
        mTitleText = new String[]{
                "11 Tips Diet Sehat untuk Turunkan Berat Badan Secara Aman dan Alami",
                "8 Cara Diet Sehat Menurunkan Berat Badan yang Baik dan Benar Agar Tidak Membahayakan Tubuh",
                "Makanan Sehat",
                "13 Tips Makan Sehat untuk Orang yang Super Sibuk"
        };
        placeGuide = new String[]{
                "https://wolipop.detik.com/health-and-diet/d-4731356/11-tips-diet-sehat-untuk-turunkan-berat-badan-secara-aman-dan-alami",
                "https://www.liputan6.com/health/read/3677875/8-cara-diet-sehat-menurunkan-berat-badan-yang-baik-dan-benar-agar-tidak-membahayakan-tubuh",
                "https://www.halodoc.com/kesehatan/makanan-sehat",
                "https://hellosehat.com/hidup-sehat/nutrisi/makanan-sehat-untuk-orang-sibuk-2/"


                };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new TipsAdapter(getActivity(), mTitleText,mInfoText, imageResource, placeGuide,mAuthor);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity()).setActionBarTitle("Tips");
    }



}
