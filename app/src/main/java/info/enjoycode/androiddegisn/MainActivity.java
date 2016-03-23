package info.enjoycode.androiddegisn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String SELECTED_ITEM_ID = "selected_item_id";
    private static final String FIRST_TIME = "first_time";
    private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mSelectedId;
    private boolean mUserSawDrawer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        mDrawer = (NavigationView) findViewById(R.id.main_drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDrawerLayout.openDrawer(GravityCompat.START);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        Toast.makeText(this, item.getTitle() + "被点击了", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.navigation_item_0:
                startActivity(_01TextInputLayoutActivity.class);
                break;
            case R.id.navigation_item_1:
                startActivity(_02TabLayoutActivity.class);
                break;
            case R.id.navigation_item_2:
                startActivity(_03CollapsingToolbarLayoutActivity.class);
                break;
            case R.id.navigation_item_3:
                startActivity(_04BottomSheetsActivity.class);
                break;
            case R.id.navigation_item_4:
                startActivity(_05FlexibleSpaceWithImageActivity.class);
                break;
        }


        return false;
    }

    private void startActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

}
