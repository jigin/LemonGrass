package com.lemongrass.lemongrass.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemongrass.lemongrass.Adapter.Recyclerview_Adapter;
import com.lemongrass.lemongrass.R;

/**
 * Created by AMAL SAJU VARGHESE on 09-Feb-17.
 */

public class All_Fragment extends Fragment
{
    RecyclerView recyclerView;
    Recyclerview_Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerh);
        recyclerView.setHasFixedSize(false);
        layoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Recyclerview_Adapter();
        recyclerView.setAdapter(adapter);
    }
}