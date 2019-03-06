// Generated code from Butter Knife. Do not modify!
package com.example.nojinx2.lelang.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nojinx2.lelang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AdapterLelang$ViewHolder_ViewBinding implements Unbinder {
  private AdapterLelang.ViewHolder target;

  @UiThread
  public AdapterLelang$ViewHolder_ViewBinding(AdapterLelang.ViewHolder target, View source) {
    this.target = target;

    target.nama_ikan = Utils.findRequiredViewAsType(source, R.id.nama_ikan, "field 'nama_ikan'", TextView.class);
    target.nama_penjual = Utils.findRequiredViewAsType(source, R.id.nama_penjual, "field 'nama_penjual'", TextView.class);
    target.status = Utils.findRequiredViewAsType(source, R.id.status, "field 'status'", TextView.class);
    target.foto_ikan = Utils.findRequiredViewAsType(source, R.id.foto_ikan, "field 'foto_ikan'", ImageView.class);
    target.deskripsi = Utils.findRequiredViewAsType(source, R.id.deskripsi, "field 'deskripsi'", TextView.class);
    target.harga = Utils.findRequiredViewAsType(source, R.id.harga, "field 'harga'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AdapterLelang.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nama_ikan = null;
    target.nama_penjual = null;
    target.status = null;
    target.foto_ikan = null;
    target.deskripsi = null;
    target.harga = null;
  }
}
