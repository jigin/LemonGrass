package com.lemongrass.lemongrass.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.lemongrass.lemongrass.Util.ItemClickSupport;
import com.lemongrass.lemongrass.Util.Utils;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMAL SAJU VARGHESE on 09-Feb-17.
 */

public class All_Fragment extends Fragment
{
    private static final int PERMISSION_REQUEST_CODE = 100;
    ProgressDialog progressDialog;

    RecyclerView recyclerView;
    Recyclerview_Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    List<ItemModel>list = new ArrayList<>();
    JSONParser jsonParser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        jsonParser = new JSONParser();

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerh);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Recyclerview_Adapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

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

        if(Utils.isNetworkAvailable(getActivity()))
        {
            new GetAllFood().execute();
        }
        else {
            Toast.makeText(getActivity(),R.string.noNetwork,Toast.LENGTH_LONG).show();
        }

        if(ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED);
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_NETWORK_STATE))
            {

            }
            else {
                ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.ACCESS_NETWORK_STATE},PERMISSION_REQUEST_CODE);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case PERMISSION_REQUEST_CODE:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(Utils.isNetworkAvailable(getActivity()))
                    {
                        new GetAllFood().execute();
                    }
                    else {
                        Toast.makeText(getActivity(),R.string.noNetwork,Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(),"You declined the pemission which is manditory.",Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public class GetAllFood extends AsyncTask<String,String,String>
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

            if (Utils.hasInternetConnection(getActivity())) {

                List<NameValuePair> param = new ArrayList<>();
                JSONObject jsonObject = jsonParser.makeHttpRequest(Utils.ALL_FOOD_URL, "POST", param);

                //Log.e("helloandroid", jsonObject.toString());

                try {
                    if (jsonObject.getInt(Utils.SUCCESS) == 1) {
                        JSONArray jArray = jsonObject.getJSONArray(Utils.DATA);
                        //Log.e("hello1", jArray.toString());

                        ItemModel im;
                        for (int i = 0; i < jArray.length(); i++) {
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



            }
            else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),R.string.networkDown,Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;

        }
    }
}