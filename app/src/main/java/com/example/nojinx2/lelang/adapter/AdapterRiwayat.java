package com.example.nojinx2.lelang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.nojinx2.lelang.FormatData;
import com.example.nojinx2.lelang.R;
import com.example.nojinx2.lelang.data.DataPaket;
import com.example.nojinx2.lelang.data.Riwayat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterRiwayat extends RecyclerView.Adapter<AdapterRiwayat.MyViewHolder> {
    private Context context;
    private List<Riwayat> datas;
    private List<Riwayat> orig;

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final List<Riwayat> results = new ArrayList<>();
                if (orig == null) {
                    orig = datas;
                }
                if (charSequence != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Riwayat g : orig) {
                            if (g.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
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
                datas = (ArrayList<Riwayat>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, price, chef, timestamp;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            chef = view.findViewById(R.id.chef);
            description = view.findViewById(R.id.description);
            price = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.thumbnail);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }


    public AdapterRiwayat(Context context, List<Riwayat> cartList) {
        this.context = context;
        this.datas = cartList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.riwayat_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Riwayat recipe = datas.get(position);
        holder.name.setText(recipe.getName());
        holder.chef.setText("By " + recipe.getChef());
        holder.description.setText(recipe.getDescription());
        holder.price.setText(FormatData.doubleToRupiah(Double.valueOf(recipe.getPrice())));
        holder.timestamp.setText(recipe.getTimestamp());

        Glide.with(context)
                .load(recipe.getThumbnail())
                .into(holder.thumbnail);
    }
    // riwayat
    @Override
    public int getItemCount() {
        return datas.size();
    }
}
