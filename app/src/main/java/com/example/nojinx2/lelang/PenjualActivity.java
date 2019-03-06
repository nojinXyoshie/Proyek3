package com.example.nojinx2.lelang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.nojinx2.lelang.auth.Login;
import com.example.nojinx2.lelang.fragment.*;

public class PenjualActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public Toolbar getToolbar() {
        return toolbar;
    }

    String email, nama;
    SharedPreferences sharedpreferences;

    public static final String TAG_EMAIL = "email";
    public static final String TAG_NAMA = "nama";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjual);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        email = getIntent().getStringExtra(TAG_EMAIL);
        nama = getIntent().getStringExtra(TAG_NAMA);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bottom_penjual);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        TextView toolbar_text = findViewById(R.id.toolbar_text);
        //toolbar.setTitle("Riwayat");
        toolbar_text.setText("Riwayat");
        loadFragment(new RiwayatFragment());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            toolbar.setTitle("  Keluar");
            toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(Login.session_status, false);
            editor.putString(TAG_EMAIL, null);
            editor.putString(TAG_NAMA, null);
            editor.commit();

            Intent intent = new Intent(PenjualActivity.this, Login.class);
            finish();
            startActivity(intent);
            return true;
        } else if (id == R.id.action_search) {

        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            TextView toolbar_text = findViewById(R.id.toolbar_text);
            switch (item.getItemId()) {
                case R.id.navigation_riwayat:
                    fragment = new RiwayatFragment();
                    toolbar_text.setText("Riwayat");
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_lelang:
                    fragment = new LelangFragment();
                    toolbar_text.setText("Lelang");
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profil:
                    fragment = new ProfilFragment();
                    toolbar_text.setText("Profil");
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_penjual, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
