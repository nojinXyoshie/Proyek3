package com.example.nojinx2.lelang.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.nojinx2.lelang.Detail_paket;
import com.example.nojinx2.lelang.FormatData;
import com.example.nojinx2.lelang.R;
import com.example.nojinx2.lelang.data.DataPaket;
import com.example.nojinx2.lelang.util.Server;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class AdapterPaket extends RecyclerView.Adapter<AdapterPaket.ViewHolder> {
    private List<DataPaket> datas;
    private Context context;
    private List<DataPaket> orig;

    public AdapterPaket(Context context) {

        this.context = context;
        datas = new ArrayList<>();
    }

    public void setDatas(List<DataPaket> datas) {this.datas = datas;}

    public List<DataPaket> getDatas() {return datas;}

    @Override
    public AdapterPaket.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beranda_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterPaket.ViewHolder holder, int position) {
        holder.nama_ikan.setText(datas.get(position).getNamaIkan());
        holder.harga_awal.setText(FormatData.doubleToRupiah(Double.valueOf(datas.get(position).getHarga_awal())));
        DataPaket data = datas.get(position);
        Picasso.with(context).load(Server.URL_Foto + data.getFoto_ikan())
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
        holder.nama_ikan.setText(data.getNamaIkan());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final List<DataPaket> results = new ArrayList<>();
                if (orig == null) {
                    orig = datas;
                }
                if (charSequence != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final DataPaket g : orig) {
                            if (g.getNamaIkan().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                                results.add(g);
                            } else if (g.getHarga_awal().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                results.add(g);
                            }
                        }
                        oReturn.values = results;
                    }
                }
                return oReturn;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                datas = (ArrayList<DataPaket>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama_ikan)
        TextView nama_ikan;
        @BindView(R.id.harga_awal)
        TextView harga_awal;
        @BindView(R.id.foto_ikan)
        ImageView foto_ikan;
        @BindView(R.id.linear)
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, Detail_paket.class)
                            .putExtra("dataf",datas.get(getAdapterPosition())));
                }
            });
        }
    }
}
