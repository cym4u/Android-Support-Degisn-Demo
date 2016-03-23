package info.enjoycode.androiddegisn;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.BottomSheetBehavior.STATE_COLLAPSED;
import static android.support.design.widget.BottomSheetBehavior.STATE_DRAGGING;
import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;
import static android.support.design.widget.BottomSheetBehavior.STATE_HIDDEN;
import static android.support.design.widget.BottomSheetBehavior.STATE_SETTLING;

/**
 * Created by livin on 2016/3/3.
 */
public class _04BottomSheetsActivity  extends AppCompatActivity  {
    @Bind(R.id.coordinatorLayout)
    View rootView;

    @Bind(R.id.bottom_sheet_status)
    TextView bottom_sheet_status;

    @Bind(R.id.bottomSheet)
    View bottomSheet;

    @Bind(R.id.bottom_sheet_header)
    View bottom_sheet_header;

    @Bind(R.id.btn_peek)
    Button btn_peek;

    BottomSheetBehavior mBehavior;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        ButterKnife.bind(this);
        mBehavior = BottomSheetBehavior.from(bottomSheet);
        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            public void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState) {
                bottom_sheet_status.setText(getStatusMessage(newState));
            }

            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }

            private String getStatusMessage(int newState) {
                switch (newState) {
                    case STATE_EXPANDED:
                        return getResources().getString(R.string.status_expanded);
                    case STATE_COLLAPSED:
                        return getResources().getString(R.string.status_collapsed);
                    case STATE_DRAGGING:
                        return getResources().getString(R.string.status_dragging);
                    case STATE_SETTLING:
                        return getResources().getString(R.string.status_settling);
                    case STATE_HIDDEN:
                        return getResources().getString(R.string.status_hidden);
                    default:
                        return null;
                }
            }
        });


        // Attach view-tree observer to set the bottom sheet's peek-height once the view is laid out.
        attachViewTreeObserver();
    }

    /**
     * Once the view has been created get the height of the bottom-sheet's header-text and
     * use it to set the peek-height of the bottom-sheet.
     */
    private void attachViewTreeObserver() {
        ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    setBottomSheetPeekHeight(true);
                }
            });
        }
    }

    private static final String TAG = "MainActivity";
    boolean shouldShowPeek  = true;
    @OnClick(R.id.btn_peek)
    public void peek(View v) {
        shouldShowPeek = !shouldShowPeek;
        setBottomSheetPeekHeight(shouldShowPeek);
    }

    private void setBottomSheetPeekHeight(boolean shouldShowPeek) {
        mBehavior.setPeekHeight(shouldShowPeek ? bottom_sheet_header.getHeight() : 0);
        mBehavior.setState(STATE_COLLAPSED);
        btn_peek.setText((!shouldShowPeek? "show" : "hide") + " peek");
    }

    @OnClick(R.id.btn_toggle)
    public void toggle(View v) {
        mBehavior.setState(getNewState());
    }
    @BottomSheetBehavior.State
    private int getNewState() {
        return mBehavior.getState() == STATE_COLLAPSED ? STATE_EXPANDED : STATE_COLLAPSED;
    }
    @OnClick(R.id.bottom_sheet_header)
    public void open(View v) {
        mBehavior.setState(STATE_EXPANDED);
    }

}
