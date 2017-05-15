package com.asian5restaurant.lemongrass.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.asian5restaurant.lemongrass.Activity.FoodSingle_Activity;
import com.asian5restaurant.lemongrass.Adapter.Recyclerview_Adapter;
import com.asian5restaurant.lemongrass.JSONParser;
import com.asian5restaurant.lemongrass.Model.ItemModel;
import com.asian5restaurant.lemongrass.R;
import com.asian5restaurant.lemongrass.Util.AppDb;
import com.asian5restaurant.lemongrass.Util.ItemClickSupport;
import com.asian5restaurant.lemongrass.Util.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMAL SAJU VARGHESE on 14-Feb-17.
 */

public class TeaNCoffee_Fragment extends Fragment
{
    ProgressDialog progressDialog;

    RecyclerView recyclerView;
    Recyclerview_Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    TextView catId;
    String catIdSt = "";

    List<ItemModel>list = new ArrayList<>();
    JSONParser jsonParser;

    AppDb db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tea_coffee, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        jsonParser = new JSONParser();

        db = new AppDb(getActivity());
        list = db.getItemByCat("11");

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.teacoffeeRecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Recyclerview_Adapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

        catId = (TextView) getActivity().findViewById(R.id.teacoffeeCatid);
        catIdSt = catId.getText().toString();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                TextView t = (TextView) v.findViewById(R.id.item_id);
                String id = t.getText().toString();

                Intent in = new Intent(getActivity(), FoodSingle_Activity.class);
                in.putExtra(Utils.MENU_ID,id);
                startActivity(in);
            }
        });
    }
}
