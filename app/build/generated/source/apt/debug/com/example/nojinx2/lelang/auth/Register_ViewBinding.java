// Generated code from Butter Knife. Do not modify!
package com.example.nojinx2.lelang.auth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nojinx2.lelang.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Register_ViewBinding implements Unbinder {
  private Register target;

  @UiThread
  public Register_ViewBinding(Register target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Register_ViewBinding(Register target, View source) {
    this.target = target;

    target._nameText = Utils.findRequiredViewAsType(source, R.id.input_name, "field '_nameText'", EditText.class);
    target._emailText = Utils.findRequiredViewAsType(source, R.id.input_email, "field '_emailText'", EditText.class);
    target._passwordText = Utils.findRequiredViewAsType(source, R.id.input_password, "field '_passwordText'", EditText.class);
    target._alamat = Utils.findRequiredViewAsType(source, R.id.alamat, "field '_alamat'", EditText.class);
    target._nohp = Utils.findRequiredViewAsType(source, R.id.nohp, "field '_nohp'", EditText.class);
    target._signupButton = Utils.findRequiredViewAsType(source, R.id.btn_signup, "field '_signupButton'", Button.class);
    target._loginLink = Utils.findRequiredViewAsType(source, R.id.link_login, "field '_loginLink'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Register target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target._nameText = null;
    target._emailText = null;
    target._passwordText = null;
    target._alamat = null;
    target._nohp = null;
    target._signupButton = null;
    target._loginLink = null;
  }
}
