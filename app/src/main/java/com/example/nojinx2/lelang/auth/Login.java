package com.example.nojinx2.lelang.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.BindView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.lelang.Detail_paket;
import com.example.nojinx2.lelang.HomeActivity;
import com.example.nojinx2.lelang.PenjualActivity;
import com.example.nojinx2.lelang.R;
import com.example.nojinx2.lelang.app.MyApplication;
import com.example.nojinx2.lelang.fragment.*;
import com.example.nojinx2.lelang.util.Server;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    ProgressDialog pDialog;
    Button link_register, btn_login;
    EditText input_email, input_password;
//    @BindView(R.id.input_email) EditText _emailText;
//    @BindView(R.id.input_password) EditText _passwordText;
//    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "login.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public static final String TAG_NAMA_PEMBELI = "nama_pembeli";
    public static final String TAG_NAMA_PENJUAL = "nama_penjual";

    public final static String TAG_LEVEL = "level";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_ID_PEMBELI = "id_pembeli";
    public final static String TAG_ID_PENJUAL = "id_penjual";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id_pembeli, id_penjual, email, level;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        btn_login = (Button) findViewById(R.id.btn_login);
        //link_register = (TextView) findViewById(R.id.link_signup);
        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);

        // Cek session login jika TRUE maka langsung buka MainActivity
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id_pembeli = sharedpreferences.getString(TAG_ID_PEMBELI, null);
        id_penjual = sharedpreferences.getString(TAG_ID_PENJUAL, null);
        email = sharedpreferences.getString(TAG_EMAIL, null);
        level = sharedpreferences.getString(TAG_LEVEL, null);

        if (session) {
            if (level.equals("pembeli")) {
                Intent intent = new Intent(Login.this, HomeActivity.class);
                intent.putExtra(TAG_ID_PEMBELI, id_pembeli);
                intent.putExtra(TAG_EMAIL, email);
                intent.putExtra(TAG_LEVEL, level);
                finish();
                startActivity(intent);
            } else if (level.equals("penjual")) {
                Intent intent = new Intent(Login.this, PenjualActivity.class);
                intent.putExtra(TAG_ID_PENJUAL, id_penjual);
                intent.putExtra(TAG_EMAIL, email);
                intent.putExtra(TAG_LEVEL, level);
                finish();
                startActivity(intent);
            }
        }

        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        btn_login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
//        progressDialog.show();
        //pDialog.show();

        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        // TODO: Implement your own authentication logic here.

        // mengecek kolom yang kosong
        if (email.trim().length() > 0 && password.trim().length() > 0) {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
                checkLogin(email, password);
            } else {
                Toast.makeText(getApplicationContext() ,"No Internet Connection", Toast.LENGTH_LONG).show();
            }
        } else {
            // Prompt user to enter credentials
            Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        //onLoginFailed();
                        pDialog.dismiss();
                    }
                }, 3000);
    }

    private void checkLogin(final String email, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        String email = jObj.getString(TAG_EMAIL);
                        String id_pembeli = jObj.getString(TAG_ID_PEMBELI);
                        String id_penjual = jObj.getString(TAG_ID_PEMBELI);
                        String level = jObj.getString(TAG_LEVEL);
                        String nama_pembeli = jObj.getString(TAG_NAMA_PEMBELI);
                        String nama_penjual = jObj.getString(TAG_NAMA_PEMBELI);

                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_ID_PEMBELI, id_pembeli);
                        editor.putString(TAG_EMAIL, email);
                        editor.putString(TAG_LEVEL, level);
                        //editor.putString("TAG_ID_PEMBELI", id_pembeli);
                        editor.putString(TAG_NAMA_PEMBELI, nama_pembeli);
                        editor.commit();
                        if(level.equals("pembeli")) {
                            // Memanggil main activity
                            Intent intent = new Intent(Login.this, HomeActivity.class);
                            intent.putExtra(TAG_ID_PEMBELI, id_pembeli);
                            intent.putExtra(TAG_EMAIL, email);
                            intent.putExtra(TAG_LEVEL, level);
                            finish();
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        btn_login.setEnabled(true);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
