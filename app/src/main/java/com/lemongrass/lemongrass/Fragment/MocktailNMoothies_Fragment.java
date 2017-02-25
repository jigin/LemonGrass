package com.lemongrass.lemongrass.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lemongrass.lemongrass.Activity.FoodSingle_Activity;
import com.lemongrass.lemongrass.Adapter.Recyclerview_Adapter;
import com.lemongrass.lemongrass.JSONParser;
import com.lemongrass.lemongrass.Model.ItemModel;
import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.AppDb;
import com.lemongrass.lemongrass.Util.ItemClickSupport;
import com.lemongrass.lemongrass.Util.Utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMAL SAJU VARGHESE on 14-Feb-17.
 */

public class MocktailNMoothies_Fragment extends Fragment
{
    ProgressDialog progressDialog;

    RecyclerView recyclerView;
    Recyclerview_Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    TextView catId;
    String catIdSt = "";

    JSONParser jsonParser;

    AppDb db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mocktail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        jsonParser = new JSONParser();

        db = new AppDb(getActivity());
        List<ItemModel>list = db.getItemByCat("7");

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.mocktailRecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Recyclerview_Adapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

        catId = (TextView) getActivity().findViewById(R.id.mocktailCatid);
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
