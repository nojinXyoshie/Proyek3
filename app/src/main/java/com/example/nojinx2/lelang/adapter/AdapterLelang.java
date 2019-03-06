package com.example.nojinx2.lelang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.nojinx2.lelang.Detail_paket;
import com.example.nojinx2.lelang.FormatData;
import com.example.nojinx2.lelang.R;
import com.example.nojinx2.lelang.data.DataLelang;
import com.example.nojinx2.lelang.data.DataPaket;
import com.example.nojinx2.lelang.data.Riwayat;
import com.example.nojinx2.lelang.util.Server;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class AdapterLelang extends RecyclerView.Adapter<AdapterLelang.ViewHolder> {
    private Context context;
    private List<DataLelang> datas;

    public AdapterLelang(Context context) {

        this.context = context;
        datas=new ArrayList<>();
    }

    public void setDatas(List<DataLelang> datas) {
        this.datas = datas;
    }

    public List<DataLelang> getDatas() {
        return datas;
    }

    @Override
    public AdapterLelang.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lelang_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterLelang.ViewHolder holder, int position) {
        DataLelang data = datas.get(position);
        holder.nama_ikan.setText(data.getDetail().getTabel_barang().getNama_ikan());
        holder.nama_penjual.setText("Bobot : "+data.getDetail().getTabel_barang().getBerat_ikan());
        holder.deskripsi.setText("Deskripsi :"+data.getDetail().getTabel_barang().getDeskripsi());
        holder.harga.setText("Harga saat ini : "+FormatData.doubleToRupiah(Double.parseDouble(data.getDetail().getTabel_barang().getHarga_awal())));
        Picasso.with(context).load(Server.URL_Foto + data.getDetail().getTabel_barang().getFoto_ikan())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(holder.foto_ikan, new Callback() {
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

        if (data.getStatus().equals("1")){
            holder.status.setText("Status : Masih proses lelang");
            if (data.getStatus().equals("1")){
                holder.status.setText("Status : Silahkan ambil barang di TPI karangsong");
            }

        }else {
            holder.status.setText("Status : Belum Dikonfirmasi");
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama_ikan)
        TextView nama_ikan;
        @BindView(R.id.nama_penjual)
        TextView nama_penjual;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.foto_ikan)
        ImageView foto_ikan;
        @BindView(R.id.deskripsi)
        TextView deskripsi;
        @BindView(R.id.harga)
        TextView harga;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    context.startActivity(new Intent(context, detailpesanan.class)
//                            .putExtra("data",datas.get(getAdapterPosition())));
                }
            });
        }
    }
}
