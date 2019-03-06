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

public class AdapterLelang$MyViewHolder_ViewBinding implements Unbinder {
  private AdapterLelang.MyViewHolder target;

  @UiThread
  public AdapterLelang$MyViewHolder_ViewBinding(AdapterLelang.MyViewHolder target, View source) {
    this.target = target;

    target.nama_penjual = Utils.findRequiredViewAsType(source, R.id.nama_penjual, "field 'nama_penjual'", TextView.class);
    target.harga = Utils.findRequiredViewAsType(source, R.id.harga, "field 'harga'", TextView.class);
    target.foto_ikan = Utils.findRequiredViewAsType(source, R.id.foto_ikan, "field 'foto_ikan'", ImageView.class);
    target.linearLayout = Utils.findRequiredViewAsType(source, R.id.linear, "field 'linearLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AdapterLelang.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nama_penjual = null;
    target.harga = null;
    target.foto_ikan = null;
    target.linearLayout = null;
  }
}
