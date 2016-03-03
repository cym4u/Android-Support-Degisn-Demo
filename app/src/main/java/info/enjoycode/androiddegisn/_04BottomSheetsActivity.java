package info.enjoycode.androiddegisn;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by livin on 2016/3/3.
 */
public class _04BottomSheetsActivity  extends AppCompatActivity implements View.OnClickListener {
    private TextView mSwap;
    private int mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
    public BottomSheetBehavior behavior;
    private LinearLayout bottomSheetContent;
    ImageView blackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheets);
        mSwap = (TextView)findViewById(R.id.swap_text);
        mSwap.setOnClickListener(this);
        bottomSheetContent = (LinearLayout)findViewById(R.id.bottom_sheet_content);
        blackView = (ImageView)findViewById(R.id.black_view);

        behavior = BottomSheetBehavior.from(bottomSheetContent);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_COLLAPSED||newState == BottomSheetBehavior.STATE_HIDDEN){
//                    blackView.setBackgroundColor(Color.TRANSPARENT);
                    blackView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
                blackView.setVisibility(View.VISIBLE);
                ViewCompat.setAlpha(blackView, slideOffset);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        int uiMode = getResources().getConfiguration().uiMode;
        int dayNightUiMode = uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (dayNightUiMode == Configuration.UI_MODE_NIGHT_NO) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_NO;
        } else if (dayNightUiMode == Configuration.UI_MODE_NIGHT_YES) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_YES;
        } else {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.swap_text:
                if (mDayNightMode==AppCompatDelegate.MODE_NIGHT_NO)
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
                break;
        }
    }
}
