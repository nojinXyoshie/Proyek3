package com.example.nojinx2.lelang.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;

import android.widget.ListView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import com.example.nojinx2.lelang.R;
import com.example.nojinx2.lelang.adapter.AdapterPaket;
import com.example.nojinx2.lelang.app.MyApplication;
import com.example.nojinx2.lelang.data.DataPaket;
import com.example.nojinx2.lelang.util.MySingleton;
import com.example.nojinx2.lelang.util.Server;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class BerandaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = BerandaFragment.class.getSimpleName();
    SwipeRefreshLayout swipe;
    private ShimmerFrameLayout mShimmerViewContainer;
    private List<DataPaket> penjualList = new ArrayList<DataPaket>();

    public BerandaFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.rv_penjual)
    RecyclerView rv_penjual;
    AdapterPaket adapter_paket;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        ButterKnife.bind(this, view);

        swipe   = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mShimmerViewContainer = (ShimmerFrameLayout) view.findViewById(R.id.shimmer_view_container);

        // menampilkan widget refresh
        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           penjualList.clear();
                           adapter_paket.notifyDataSetChanged();
                           getPenjual();
                       }
                   }
        );

        getPenjual();

        return view;
    }

    @Override
    public void onRefresh() {
        penjualList.clear();
        adapter_paket.notifyDataSetChanged();
        getPenjual();
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

   // Action Search
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_paket.getFilter().filter(newText);
                return false;
            }
        });

        super.onPrepareOptionsMenu(menu);
    }

    public void getPenjual() {
        adapter_paket = new AdapterPaket(getActivity());
        rv_penjual.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv_penjual.setAdapter(adapter_paket);
        adapter_paket.getDatas().clear();
        adapter_paket.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.get_baranglelang, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataPaket>> token = new TypeToken<List<DataPaket>>() {
                    };
                    penjualList = gson.fromJson(data.toString(), token.getType());
                    adapter_paket.setDatas(penjualList);
                    adapter_paket.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // stop animating Shimmer and hide the layout
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        swipe.setRefreshing(false);
        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);
    }

}

