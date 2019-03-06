// Generated code from Butter Knife. Do not modify!
package com.example.nojinx2.lelang.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nojinx2.lelang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AdapterPaket$ViewHolder_ViewBinding implements Unbinder {
  private AdapterPaket.ViewHolder target;

  @UiThread
  public AdapterPaket$ViewHolder_ViewBinding(AdapterPaket.ViewHolder target, View source) {
    this.target = target;

    target.nama_ikan = Utils.findRequiredViewAsType(source, R.id.nama_ikan, "field 'nama_ikan'", TextView.class);
    target.harga_awal = Utils.findRequiredViewAsType(source, R.id.harga_awal, "field 'harga_awal'", TextView.class);
    target.foto_ikan = Utils.findRequiredViewAsType(source, R.id.foto_ikan, "field 'foto_ikan'", ImageView.class);
    target.linearLayout = Utils.findRequiredViewAsType(source, R.id.linear, "field 'linearLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AdapterPaket.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nama_ikan = null;
    target.harga_awal = null;
    target.foto_ikan = null;
    target.linearLayout = null;
  }
}
