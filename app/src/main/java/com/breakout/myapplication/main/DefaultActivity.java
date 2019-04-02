package com.breakout.myapplication.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.breakout.myapplication.R;
import com.breakout.myapplication.concerts.ConcertsActivity;
import com.breakout.myapplication.favourite.FavouriteActivity;
import com.breakout.myapplication.user.UserActivity;
import com.breakout.myapplication.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


//    @BindView(R.id.main_recycler)
//    EmptyRecyclerView recyclerView;
//    @BindView(R.id.nav_listview)
//    ListView listView;

    // TODO: 02.04.2019 осознать как мутить приложуху для разных экранов

       mainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        List<NavElement> elements = new ArrayList<>();
//        elements.add(new NavElement(R.drawable.referee_128x128, "Профиль"));
//        elements.add(new NavElement(R.drawable.referee_128x128, "Профиль"));
//        elements.add(new NavElement(R.drawable.referee_128x128, "Профиль"));
//        elements.add(new NavElement(R.drawable.referee_128x128, "Профиль"));
//
//        listView.setAdapter(new DefaultAdapter(this, elements));

        DrawerLayout drawer;
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // TODO: 29.03.2019 фрагменты освоить и сделать нормально
        // TODO: 29.03.2019 настроить navigation drawer
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

    void addList() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        for(int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            int end = spanString.length();
            spanString.setSpan(new RelativeSizeSpan(1.5f), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            item.setTitle(spanString);
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.profile_img:
                startActivity(new Intent(this, UserActivity.class));
                break;
            case R.id.concert_img:
                startActivity(new Intent(this, ConcertsActivity.class));
                break;
            case R.id.friends_img:
                //startActivity
            case R.id.sign_out_img:
                //start
            case R.id.settings_img:
                //start
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
