package info.enjoycode.androiddegisn;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by chenyuanming on 16/3/23.
 */
public class _05FlexibleSpaceWithImageActivity extends AppCompatActivity {
    Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexible_space_with_image);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_flexible_space_with_image);
        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout.setTitle("Flexible Space With Image");
    }
}