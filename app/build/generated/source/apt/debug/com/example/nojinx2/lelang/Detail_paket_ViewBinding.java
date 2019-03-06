// Generated code from Butter Knife. Do not modify!
package com.example.nojinx2.lelang;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Detail_paket_ViewBinding implements Unbinder {
  private Detail_paket target;

  @UiThread
  public Detail_paket_ViewBinding(Detail_paket target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Detail_paket_ViewBinding(Detail_paket target, View source) {
    this.target = target;

    target.nama_paket = Utils.findRequiredViewAsType(source, R.id.nama_paket, "field 'nama_paket'", TextView.class);
    target.foto_paket = Utils.findRequiredViewAsType(source, R.id.foto_paket, "field 'foto_paket'", ImageView.class);
    target.harga = Utils.findRequiredViewAsType(source, R.id.harga, "field 'harga'", TextView.class);
    target.nama_penjual = Utils.findRequiredViewAsType(source, R.id.nama_penjual, "field 'nama_penjual'", TextView.class);
    target.deskripsi = Utils.findRequiredViewAsType(source, R.id.deskripsi, "field 'deskripsi'", TextView.class);
    target.berat = Utils.findRequiredViewAsType(source, R.id.berat, "field 'berat'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Detail_paket target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nama_paket = null;
    target.foto_paket = null;
    target.harga = null;
    target.nama_penjual = null;
    target.deskripsi = null;
    target.berat = null;
  }
}
