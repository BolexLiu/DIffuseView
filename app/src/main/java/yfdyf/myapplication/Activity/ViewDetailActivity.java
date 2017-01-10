package yfdyf.myapplication.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;

import yfdyf.myapplication.R;
import yfdyf.myapplication.View.DiffuseImgView;
import yfdyf.myapplication.View.DiffuseView;
import yfdyf.myapplication.View.RotateRectView;

/**
 * author：LiuShenEn on 2017/1/10 14:26
 */
public class ViewDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private MaterialMenuDrawable materialMenu;
    private TextView titleView;
    private String title;
    private CardView cardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_view_detail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        titleView = (TextView) findViewById(R.id.titleView);
        cardview = (CardView) findViewById(R.id.card_view);
        getInTitle();
        initToolBar();
        setupWindowAnimations();
    }

    private void getInTitle() {
        title = getIntent().getStringExtra("title");
    }

    private void initToolBar() {
        mToolbar.setTitle(title);
        titleView.setText(title);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.REGULAR);
        materialMenu.setIconState(MaterialMenuDrawable.IconState.ARROW);
        mToolbar.setNavigationIcon(materialMenu);
        View view;
        switch (title) {
            case "DiffuseView":
                view = new DiffuseView(ViewDetailActivity.this);
                break;
            case "DiffuseImgView":
                view = new DiffuseImgView(ViewDetailActivity.this);
                break;
           default:
                view = new RotateRectView(ViewDetailActivity.this);
                break;
        }
        view.setBackgroundColor(0xffffff);
        cardview.addView(view);
    }

    private void setupWindowAnimations() {
        Slide slide = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            slide = new Slide();
            slide.setDuration(1000);
            getWindow().setExitTransition(slide);
        }
    }
}