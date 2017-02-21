package com.lemongrass.lemongrass.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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

public class Curries_Fragment extends Fragment
{
    ProgressDialog progressDialog;

    RecyclerView recyclerView;
    Recyclerview_Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    TextView catId;
    String catIdSt = "";

    List<ItemModel> list = new ArrayList<>();
    JSONParser jsonParser;

    AppDb db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_curry, container, false);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("fragCurries","onAttach");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e("fragCurries","onAttach Activity");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("fragCurries","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("fragCurries","onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fragCurries","onDestroy");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e("fragCurries","onLowMemory");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Bundle bundle = null;
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        jsonParser = new JSONParser();

        db = new AppDb(getActivity());
        list = db.getItemByCat("5");

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.curryRecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Recyclerview_Adapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

        catId = (TextView) getActivity().findViewById(R.id.curryCatid);
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

        /*if(Utils.isNetworkConnectionAvailable(getActivity()))
        {
            new GetCurries().execute();
        }
        else {
            Toast.makeText(getActivity(),R.string.noNetwork,Toast.LENGTH_LONG).show();
        }*/




    }
    /*public class GetCurries extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adapter = new Recyclerview_Adapter(getActivity(),list);
            recyclerView.setAdapter(adapter);
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> param = new ArrayList<>();
            param.add(new BasicNameValuePair("catid",catIdSt));
            JSONObject jsonObject = jsonParser.makeHttpRequest(Utils.GET_MENU_URL,"GET",param);

            Log.e("helloandroid",jsonObject.toString());

            try {
                if(jsonObject.getInt(Utils.SUCCESS)==1)
                {
                    JSONArray jArray = jsonObject.getJSONArray(Utils.DATA);
                    Log.e("hello1",jArray.toString());

                    ItemModel im;
                    for(int i = 0;i <jArray.length();i++)
                    {
                        im = new ItemModel();
                        JSONObject ob = jArray.getJSONObject(i);

                        im.setImageUrl(ob.getString(Utils.IMAGE_URL));
                        im.setDescription(ob.getString(Utils.DESCRIPTION));
                        im.setItemName(ob.getString(Utils.ITEM_NAME));
                        im.setMenuGroup(ob.getString(Utils.MENU_GROUP));
                        im.setMenuId(ob.getString(Utils.MENU_ID));
                        im.setPrice(ob.getString(Utils.PRICE));

                        list.add(im);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
    }*/
}