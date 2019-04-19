package com.example.omen.battlex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    //private GetCompetitionDetailsFirebase compDetails;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.container);

        GetCompetitionDetailsFirebase compDetails = GetCompetitionDetailsFirebase.getInstance();
        System.out.println("Sleep Started");
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sleep Ended");
        //Log.d("competition", compDetails.getAllCompetitions().get(0).getMax());
        //System.out.println("abcd" + compDetails.getAllCompetitions().get(0).getMax());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupViewPager();
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_competitions) {
            // Handle the camera action
            getSupportActionBar().setTitle("Competitions");
            mViewPager.setCurrentItem(0);
        } else if (id == R.id.nav_register) {
            getSupportActionBar().setTitle("Register");
            mViewPager.setCurrentItem(1);
        } else if (id == R.id.nav_sponsors) {
            getSupportActionBar().setTitle("Sponsors");
            mViewPager.setCurrentItem(2);
        } else if (id == R.id.nav_ambassadors) {
            getSupportActionBar().setTitle("Brand Ambassador");
            mViewPager.setCurrentItem(3);
        } else if (id == R.id.nav_team_leader_login) {
            getSupportActionBar().setTitle("Team Leader Login");
        } else if (id == R.id.nav_announcements) {
            getSupportActionBar().setTitle("Announcements");
            mViewPager.setCurrentItem(4);
        }
        else if(id == R.id.nav_contact_us){
            getSupportActionBar().setTitle("Contact Us");
            mViewPager.setCurrentItem(5);
        }
        else if(id == R.id.nav_about_us){
            getSupportActionBar().setTitle("About Us");
            mViewPager.setCurrentItem(6);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setupViewPager(){
        SectionStatePagerAdapter adapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Competitions());
        adapter.addFragment(new Register());
        adapter.addFragment(new Sponsors());
        adapter.addFragment(new BrandAmbassador());
        adapter.addFragment(new Announcements());
        adapter.addFragment(new ContactUs());
        adapter.addFragment(new AboutUs());

        mViewPager.setAdapter(adapter);

    }

    public void competitionOnClick(View v){
        Intent intent = new Intent(MainActivity.this, ActvityDetails.class);
        intent.putExtra("tag", v.getTag().toString());
        startActivity(intent);
    }



}
