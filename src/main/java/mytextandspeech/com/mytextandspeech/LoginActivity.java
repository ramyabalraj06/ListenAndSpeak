package mytextandspeech.com.mytextandspeech;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static android.Manifest.permission.READ_CONTACTS;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity  extends AppCompatActivity  {
    Button b1,b2,b3;
    EditText ed1,ed2;

    TextView tx1;
    int counter = 3;
    private DBHelper dbHandler;
    private UserSession session;
    Boolean isUerExists = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new DBHelper(LoginActivity.this);
        session = new UserSession(LoginActivity.this);
        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);

        b2 = (Button) findViewById(R.id.button2);
        tx1 = (TextView) findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);
        b3 = (Button) findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString() != null && ed2.getText().toString() != null) {
                    isUerExists = checkUser(ed1.getText().toString(), ed2.getText().toString());
                    if (ed1.getText().toString().equals("admin") &&
                            ed2.getText().toString().equals("admin")) {
                        session.setUserId("1");
                        Toast.makeText(getApplicationContext(),
                                "Welcome Home!!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        //   Intent i = new Intent(LoginActivity.this, Menu.class);
                        startActivity(i);
                        finish();
                    } else if (isUerExists == true) {

                        Toast.makeText(getApplicationContext(),
                                "Welcome Home!!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        //   Intent i = new Intent(LoginActivity.this, Menu.class);
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                        tx1.setVisibility(View.VISIBLE);
                        tx1.setBackgroundColor(Color.RED);
                        counter--;
                        tx1.setText(Integer.toString(counter));

                        if (counter == 0) {
                            b1.setEnabled(false);
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Fill all User Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Register.class);
                //   Intent i = new Intent(LoginActivity.this, Menu.class);
                startActivity(i);
                finish();
            }
        });
    }

    public boolean checkUser(String ed1, String ed2) {
        boolean isUserExists = false;
        if (ed1.equals(ed2)) {
            Cursor mCur = dbHandler.populateUser(ed1);
            if (mCur != null) {
                while (mCur.moveToNext()) {
                    int userId = Integer.parseInt(mCur.getString(mCur.getColumnIndex("userId")));
                    if (userId > 0) {
                        session.setUserId(String.valueOf(userId));
                        isUserExists = true;
                    } else {
                        session.setUserId("1");
                        isUserExists = true;
                    }
                }
            }
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Invalid User Credentials", Toast.LENGTH_SHORT).show();
        }
        return isUserExists;
    }
}
//extends AppCompatActivity {
//    public EditText usernameEditText;
//    public EditText passwordEditText;
//    public Button loginButton;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        ViewPager viewPager = findViewById(R.id.viewPager);
//
//        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
//        pagerAdapter.addFragmet(new LoginFragment());
//        pagerAdapter.addFragmet(new RegisterFragment());
//        pagerAdapter.beginTransaction().replace(
//                R.id.my_container_in_xml, new MyFragment(), 0).commit();
//        viewPager.setAdapter(pagerAdapter);
//    }
//
//    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
//        private ArrayList<Fragment> fragmentList = new ArrayList<>();
//
//        public AuthenticationPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//
//        @Override
//        public Fragment getItem(int i) {
//            return fragmentList.get(i);
//        }
//
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }
//
//        void addFragmet(Fragment fragment) {
//            fragmentList.add(fragment);
//        }
//    }
//}