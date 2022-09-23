package mytextandspeech.com.mytextandspeech;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DBHelper dbHandler;
    UserSession session;
    TextView tName, tDOB, tAddress, tPhone, hName, hEmail, tEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Dashboard");
        session = new UserSession(MainActivity.this);
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
        setDataToForm();
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        } else if (id == R.id.nav_template) {
            startActivity(new Intent(MainActivity.this, TemplateActivity.class));
        } else if (id == R.id.nav_viewtemplate) {
            startActivity(new Intent(MainActivity.this, ViewtemplateActivity.class));
        } else if (id == R.id.nav_recyclerview) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setDataToForm() {
        dbHandler = new DBHelper(MainActivity.this);
        int userId = Integer.parseInt(session.getUserId());
        if (userId > 0) {
            Cursor mCur = dbHandler.populateUserUsingId(userId);
            if (mCur != null) {
                while (mCur.moveToNext()) {

                    String uName = mCur.getString(mCur.getColumnIndex("userName"));
                    String uGender = mCur.getString(mCur.getColumnIndex("gender"));
                    String uAddress = mCur.getString(mCur.getColumnIndex("address"));
                    String uMobNo = mCur.getString(mCur.getColumnIndex("mobileNo"));
                    String uDOB = mCur.getString(mCur.getColumnIndex("DOB"));
                    String uGName = mCur.getString(mCur.getColumnIndex("guardianName"));
                    String uGPhone = mCur.getString(mCur.getColumnIndex("guardianPhone"));
                    if (uName != null) {
                        tName = findViewById(R.id.tname);
                        tName.setText(uName);
                        hName = findViewById(R.id.hName);
                        hName.setText(uName);
                        hEmail = findViewById(R.id.hEmail);
                        hEmail.setText(uName+"@developer.com");
                        tEmail = findViewById(R.id.temail);
                        tEmail.setText(uName+"@developer.com");
                    }
                    if (uAddress != null) {
                        tAddress = findViewById(R.id.taddress);
                        tAddress.setText(uAddress);
                    }
                    if (uDOB != null) {
                        tDOB = findViewById(R.id.tdob);
                        tDOB.setText(uDOB);
                    }
                    if (uMobNo != null) {
                        tPhone = findViewById(R.id.tmobile);
                        tPhone.setText(uMobNo);
                    }

                }
            }
        }
    }
}

