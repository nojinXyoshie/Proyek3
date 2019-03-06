// Generated code from Butter Knife. Do not modify!
package com.example.nojinx2.lelang.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nojinx2.lelang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BerandaFragment_ViewBinding implements Unbinder {
  private BerandaFragment target;

  @UiThread
  public BerandaFragment_ViewBinding(BerandaFragment target, View source) {
    this.target = target;

    target.rv_penjual = Utils.findRequiredViewAsType(source, R.id.rv_penjual, "field 'rv_penjual'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BerandaFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rv_penjual = null;
  }
}
