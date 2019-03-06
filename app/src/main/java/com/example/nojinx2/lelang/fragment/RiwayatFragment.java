package com.example.nojinx2.lelang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.*;

import android.widget.Toast;
import butterknife.ButterKnife;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nojinx2.lelang.R;
import com.example.nojinx2.lelang.adapter.AdapterPaket;
import com.example.nojinx2.lelang.adapter.AdapterRiwayat;
import com.example.nojinx2.lelang.app.MyApplication;
import com.example.nojinx2.lelang.data.Riwayat;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class RiwayatFragment extends Fragment {

    private static final String TAG = RiwayatFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Riwayat> riwayatList;
    private AdapterRiwayat adapter_riwayat;

    private ShimmerFrameLayout mShimmerViewContainer;

    // URL to fetch menu json
    // this endpoint takes 2 sec before giving the response to add
    // some delay to test the Shimmer effect
    private static final String URL = "https://api.androidhive.info/json/shimmer/menu.php";

    public RiwayatFragment() {

    }

    public static RiwayatFragment newInstance(String param1, String param2) {
        RiwayatFragment fragment = new RiwayatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_riwayat, container, false);
        ButterKnife.bind(this, view);

        mShimmerViewContainer = (ShimmerFrameLayout) view.findViewById(R.id.shimmer_view_container);

        recyclerView = view.findViewById(R.id.recycler_view);
        riwayatList = new ArrayList<>();
        adapter_riwayat = new AdapterRiwayat(getContext(), riwayatList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(adapter_riwayat);

        // making http call and fetching menu json
        fetchRecipes();

        return view;
    }

    /**
     * method make volley network call and parses json
     */
    private void fetchRecipes() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getContext(), "Couldn't fetch the menu! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<Riwayat> recipes = new Gson().fromJson(response.toString(), new TypeToken<List<Riwayat>>() {
                        }.getType());

                        // adding recipes to cart list
                        riwayatList.clear();
                        riwayatList.addAll(recipes);

                        // refreshing recycler view
                        adapter_riwayat.notifyDataSetChanged();

                        // stop animating Shimmer and hide the layout
                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
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
                adapter_riwayat.getFilter().filter(newText);
                return false;
            }
        });

        super.onPrepareOptionsMenu(menu);
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

}
