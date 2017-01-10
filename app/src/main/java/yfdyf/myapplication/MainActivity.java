package yfdyf.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;

import java.util.ArrayList;

import yfdyf.myapplication.Adapter.MainAdpter;
import yfdyf.myapplication.Adapter.MultipleItem;

import static yfdyf.myapplication.R.id.toolbar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView my_content;
    private Toolbar mToolbar;
    private MaterialMenuDrawable materialMenu;
    private DrawerLayout drawerLayout;
    boolean isDrawerOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_main_md);
        my_content = (RecyclerView) findViewById(R.id.my_content);
        mToolbar = (Toolbar) findViewById(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        initToolBar();
        initRecycler();
    }

    private void initToolBar() {
        mToolbar.setTitle("主页");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }

            }
        });
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.REGULAR);
        mToolbar.setNavigationIcon(materialMenu);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                materialMenu.setTransformationOffset(
                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                        isDrawerOpened ? 2 - slideOffset : slideOffset
                );
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isDrawerOpened = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawerOpened = false;
            }
        });
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        my_content.setLayoutManager(layoutManager);
        ArrayList<MultipleItem> listMultipleItem = new ArrayList<>();
        listMultipleItem.add(new MultipleItem(1));
        listMultipleItem.add(new MultipleItem(2));
        listMultipleItem.add(new MultipleItem(3));
        MainAdpter adapter = new MainAdpter(listMultipleItem);
        adapter.openLoadAnimation();
        my_content.setAdapter(adapter);
    }

}
