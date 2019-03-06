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

public class LelangFragment_ViewBinding implements Unbinder {
  private LelangFragment target;

  @UiThread
  public LelangFragment_ViewBinding(LelangFragment target, View source) {
    this.target = target;

    target.rv_lelang = Utils.findRequiredViewAsType(source, R.id.rv_lelang, "field 'rv_lelang'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LelangFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rv_lelang = null;
  }
}
