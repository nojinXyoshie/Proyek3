package com.example.nojinx2.lelang;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.lelang.adapter.AdapterPaket;
import com.example.nojinx2.lelang.app.MyApplication;
import com.example.nojinx2.lelang.data.DataPaket;
import com.example.nojinx2.lelang.util.MySingleton;
import com.example.nojinx2.lelang.util.Server;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.nojinx2.lelang.auth.Login.TAG_ID_PEMBELI;
import static com.example.nojinx2.lelang.auth.Login.TAG_NAMA_PEMBELI;
import static com.example.nojinx2.lelang.auth.Login.my_shared_preferences;

public class Detail_paket extends AppCompatActivity {

    SwipeRefreshLayout swipe;
    Button btn_lelang;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    AdapterPaket adapter;
    int success;
    private List<DataPaket> penjualList = new ArrayList<DataPaket>();
    EditText txt_id_barang, txt_id_penjual, txt_id_pembeli, txt_nama_pembeli, txt_bobot, txt_deskripsi, txt_harga_awal, txt_harga;
    String id_barang, id_penjual, id_pembeli, nama_pembeli, nama_barang, bobot, ddeskripsi, harga_barang;

    private static final String TAG = Detail_paket.class.getSimpleName();

    private static String url_simpan_update 	 = Server.URL + "master/simpan_update_penawaran.php";

    public static final String TAG_ID_BARANG       = "id_barang";
    //public static final String TAG_ID_PEMBELI       = "id_pembeli";
    public static final String TAG_ID_PENJUAL       = "id_penjual";
    public static final String TAG_HARGA_AWAL     = "harga_awal";
    public static final String TAG_HARGA   = "harga";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    DataPaket data;
    @BindView(R.id.nama_paket)
    TextView nama_paket;
    @BindView(R.id.foto_paket)
    ImageView foto_paket;
    @BindView(R.id.harga)
    TextView harga;
    @BindView(R.id.nama_penjual)
    TextView nama_penjual;
    @BindView(R.id.deskripsi)
    TextView deskripsi;
    @BindView(R.id.berat)
    TextView berat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_paket);
        ButterKnife.bind(this);
        data = (DataPaket) getIntent().getSerializableExtra("dataf");
        TextView toolbar_text = findViewById(R.id.toolbar_text);
        toolbar_text.setText("Detail Lelang");
        btn_lelang = (Button) findViewById(R.id.btn_lelang);

        nama_paket.setText(data.getNamaIkan());
        harga.setText(FormatData.doubleToRupiah(Double.valueOf(data.getHarga_awal())));
        berat.setText(data.getBerat_ikan());
        deskripsi.setText(data.getDeskripsi());
        Picasso.with(getApplicationContext()).load(Server.URL_Foto + data.getFoto_ikan())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(foto_paket, new Callback() {
                    @Override
                    public void onSuccess() {
//                        holder.loading_image.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
//                        holder.foto_seller.setImageDrawable(context.getResources().getDrawable(R.drawable.noimage));
//                        holder.loading_image.setVisibility(View.GONE);
                    }
                });


        StringRequest request = new StringRequest(Request.Method.POST, Server.get_paket_byid, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.e("response",response);
                try {
                    JSONArray data = new JSONArray(response);
                    if (data.length()==1){
                        nama_penjual.setText(data.getJSONObject(0).getString("nama_penjual"));
                        id_penjual = data.getJSONObject(0).getString("id_penjual");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_penjual",data.getIdPenjual());
                return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

        btn_lelang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id_barang = data.getIdBarang();
                String id_penjual = data.getIdPenjual();
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences(my_shared_preferences,0);
                String id_pembeli = sharedPreferences.getString(TAG_ID_PEMBELI, null);
                String nama_pembeli = sharedPreferences.getString(TAG_NAMA_PEMBELI, null);
                String harga = (FormatData.doubleToRupiah(Double.valueOf(data.getHarga_awal())));
                String bobot = data.getBerat_ikan();
                String deskripsi = data.getDeskripsi();
                DialogForm(id_barang,id_penjual,id_pembeli,nama_pembeli,bobot,deskripsi,harga,"","Simpan");
            }
        });

    }


    // untuk mengosongi edittext pada form
    private void kosong(){
        txt_id_barang.setText(null);
        txt_id_penjual.setText(null);
        txt_id_pembeli.setText(null);
        txt_nama_pembeli.setText(null);
        txt_harga_awal.setText(null);
        txt_bobot.setText(null);
        txt_deskripsi.setText(null);
        txt_harga.setText(null);

    }

    // untuk menampilkan dialog form biodata
    private void DialogForm(String id_barangx, String id_penjualx, String id_pembelix, String nama_pembelix,String bobotx, String deskripsix,
                            String harga_awalx, String hargax, String button) {
        dialog = new AlertDialog.Builder(Detail_paket.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_penawaran, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Form Penawaran");


        txt_id_barang = (EditText) dialogView.findViewById(R.id.txt_id_barang);
        txt_id_penjual = (EditText) dialogView.findViewById(R.id.txt_id_penjual);
        txt_id_pembeli = (EditText) dialogView.findViewById(R.id.txt_id_pembeli);
        txt_nama_pembeli = (EditText) dialogView.findViewById(R.id.txt_nama_pembeli);
        txt_harga_awal = (EditText) dialogView.findViewById(R.id.txt_harga_awal);
        txt_bobot  = (EditText) dialogView.findViewById(R.id.txt_bobot);
        txt_deskripsi  = (EditText) dialogView.findViewById(R.id.txt_deskripsi);
        txt_harga  = (EditText) dialogView.findViewById(R.id.txt_harga);


        //if (!id_barangx.isEmpty()){
            txt_id_barang.setText(id_barangx);
            txt_id_penjual.setText(id_penjualx);
            txt_id_pembeli.setText(id_pembelix);
            txt_nama_pembeli.setText(nama_pembelix);
            txt_harga_awal.setText(harga_awalx);
            txt_bobot.setText(bobotx);
            txt_deskripsi.setText(deskripsix);
            txt_harga.setText(hargax);

        //} else {
        //    kosong();
        //}

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                id_barang = txt_id_barang.getText().toString();
                id_penjual = txt_id_penjual.getText().toString();
                id_pembeli = txt_id_pembeli.getText().toString();
                nama_pembeli = txt_nama_pembeli.getText().toString();
                nama_barang = txt_harga_awal.getText().toString();
                bobot  = txt_bobot.getText().toString();
                ddeskripsi  = txt_deskripsi.getText().toString();
                harga_barang  = txt_harga.getText().toString();


                simpan_update();
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                kosong();
            }
        });

        dialog.show();
    }

    // fungsi untuk menyimpan penawaran
    private void simpan_update() {

        StringRequest strReq = new StringRequest(Request.Method.POST, url_simpan_update, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/update", jObj.toString());

                        kosong();

                        Toast.makeText(Detail_paket.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        //adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(Detail_paket.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(Detail_paket.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                    params.put("id_barang", id_barang);
                    params.put("id_penjual", id_penjual);
                    params.put("id_pembeli", id_pembeli);
                    params.put("nama_pembeli", nama_pembeli);
                    params.put("nama_barang", nama_barang);
                    params.put("bobot", bobot);
                    params.put("deskripsi", ddeskripsi);
                    params.put("harga_barang", harga_barang);


                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}
