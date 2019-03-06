package com.example.nojinx2.lelang.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.lelang.R;
import com.example.nojinx2.lelang.adapter.AdapterLelang;
import com.example.nojinx2.lelang.adapter.AdapterPaket;
import com.example.nojinx2.lelang.adapter.AdapterRiwayat;
import com.example.nojinx2.lelang.auth.Login;
import com.example.nojinx2.lelang.data.DataLelang;
import com.example.nojinx2.lelang.data.DataPaket;
import com.example.nojinx2.lelang.data.Riwayat;
import com.example.nojinx2.lelang.util.MySingleton;
import com.example.nojinx2.lelang.util.Server;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LelangFragment extends Fragment {

    @BindView(R.id.rv_lelang)
    RecyclerView rv_lelang;
    private AdapterLelang adapter_lelang;
    private List<DataLelang> list_lelang;
    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lelang, container, false);
        ButterKnife.bind(this,v);
        sharedpreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        getData();
    }

    public void getData() {
        adapter_lelang = new AdapterLelang(getActivity());
        rv_lelang.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_lelang.setAdapter(adapter_lelang);
        adapter_lelang.getDatas().clear();
        adapter_lelang.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.get_penawaran, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataLelang>> token = new TypeToken<List<DataLelang>>() {
                    };
                    list_lelang = gson.fromJson(data.toString(), token.getType());
                    adapter_lelang.setDatas(list_lelang);
                    adapter_lelang.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {
//                loading_keramik.setVisibility(View.GONE);
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_pembeli", sharedpreferences.getString(TAG_ID,null));
                return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);

    }
}
