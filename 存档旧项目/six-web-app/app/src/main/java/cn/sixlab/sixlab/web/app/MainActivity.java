package cn.sixlab.sixlab.web.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sixlab.sixlab.web.app.ft.MainFragment;
import cn.sixlab.sixlab.web.app.ft.MovieFragment;
import cn.sixlab.sixlab.web.app.ft.ShowFragment;
import cn.sixlab.sixlab.web.app.ft.TimeFragment;
import cn.sixlab.sixlab.web.app.util.BaseFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BaseFragment fragment;
    private int mTitle;
    private static final String CACHE_FRAGMENT_ID = "CACHE_FRAGMENT_ID";

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        startFragment(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        startFragment(item);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void clickFab(View view) {
        if (null != fragment) {
            fragment.fabClick();
        }else{
            Snackbar.make(view, "Hello", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    public void startFragment(MenuItem menuItem){
        int id;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (null==menuItem){
            id = sp.getInt(CACHE_FRAGMENT_ID, R.id.nav_ft_03);
        }else{
            id=menuItem.getItemId();
        }

        fragment = null;
        switch (id){
            case R.id.nav_ft_01:
                fragment = new MainFragment();
                mTitle = R.string.app_name;
                break;
            case R.id.nav_ft_02:
                fragment = new TimeFragment();
                mTitle = R.string.ft_item_02;
                break;
            case R.id.nav_ft_03:
                fragment = new MovieFragment();
                mTitle = R.string.ft_item_03;
                break;
            case R.id.nav_ft_04:
                fragment = new ShowFragment();
                mTitle = R.string.ft_item_04;
                break;
            case R.id.nav_ft_05:
                fragment = new MovieFragment();
                mTitle = R.string.ft_item_05;
                break;
            case R.id.nav_ft_06:
                fragment = new MovieFragment();
                mTitle = R.string.ft_item_06;
                break;
            default:
                fragment = new MovieFragment();
                mTitle = R.string.ft_item_03;
                id = R.id.nav_ft_03;
        }
        navigationView.setCheckedItem(id);

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(CACHE_FRAGMENT_ID, id);
        editor.commit();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, fragment);
        ft.commit();
        setTitle(mTitle);
        navigationView.setCheckedItem(id);
    }
}
