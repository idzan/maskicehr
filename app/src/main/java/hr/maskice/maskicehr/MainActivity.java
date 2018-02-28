package hr.maskice.maskicehr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {
            Bundle newBundle = new Bundle();
            newBundle.putString("link", "https://maskice.hr");
            Fragment newFragment = new WebLoader();
            newFragment.setArguments(newBundle);
            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.add(R.id.fragment_content, newFragment);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment mFragment = null;
        Bundle mBundle = new Bundle();
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.homepage) {

            mBundle.putString("link", "https://maskice.hr");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.cart_page) {

            mBundle.putString("link", "https://maskice.hr/kosarica/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.my_account_page) {

            mBundle.putString("link", "https://maskice.hr/moj-racun/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_phones) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/oprema-za-mobitele/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_universal_phone_cases) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/oprema-za-mobitele/univerzalne-futrole/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_tablets) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/oprema-za-tablet/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_cameras) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/kamere/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_adapters) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/adapteri-drzai/adapteri/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_holders) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/adapteri-drzai/drzaci/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_chargers) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/punjaci/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_headsets) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/slusalice-zvucnici/slusalice/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_speakers) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/slusalice-zvucnici/zvucnici/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_gadgets) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/gadgeti-memorije/gadgeti/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_memory) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/gadgeti-memorije/memorije/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_racunalna_oprema) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/racunalna-periferija/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_ostala_oprema) {

            mBundle.putString("link", "https://maskice.hr/kategorija-proizvoda/ostala-oprema/");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_o_nama) {

            mFragment = new OnamaFragment();

        } else if (id == R.id.nav_kontakt) {

            mBundle.putString("link", "file:///android_asset/kontakt.html");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_prodajna_mjesta) {

            mBundle.putString("link", "file:///android_asset/poslovnice.html");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_veleprodaja) {

            mBundle.putString("link", "file:///android_asset/about_app.html");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        } else if (id == R.id.nav_about_app) {

            mBundle.putString("link", "file:///android_asset/about_app.html");
            mFragment = new WebLoader();
            mFragment.setArguments(mBundle);

        }

        if (mFragment != null) {

            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.fragment_content, mFragment);
            mFragmentTransaction.commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}