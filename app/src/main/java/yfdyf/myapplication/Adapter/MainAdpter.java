package yfdyf.myapplication.Adapter;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import yfdyf.myapplication.Activity.ViewDetailActivity;
import yfdyf.myapplication.R;


/**
 * author：LiuShenEn on 2017/1/9 16:10
 */
public class MainAdpter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    public MainAdpter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ONE, R.layout.diffuse_view);
        addItemType(MultipleItem.TWO, R.layout.rotate_rect_view);
        addItemType(MultipleItem.THREE, R.layout.diffuse_img_view);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MultipleItem multiItemEntity) {
        switch (baseViewHolder.getItemViewType()) {
            case MultipleItem.ONE:
                baseViewHolder.getView(R.id.view_diffuse_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), ViewDetailActivity.class);
                        intent.putExtra("title", "DiffuseView");
                        if (Build.VERSION.SDK_INT >= 21) {
                            // 使用api11 新加 api的方法
                            String transitionName = view.getContext().getString(R.string.imgbreak);
                            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), view, transitionName);
                            view.getContext().startActivity(intent, transitionActivityOptions.toBundle());
                        } else {
                            view.getContext().startActivity(intent);
                        }
                    }
                });
                break;
            case MultipleItem.TWO:
                baseViewHolder.getView(R.id.view_rect_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), ViewDetailActivity.class);
                        intent.putExtra("title", "RectView");
                        if (Build.VERSION.SDK_INT >= 21) {
                            // 使用api11 新加 api的方法
                            String transitionName = view.getContext().getString(R.string.imgbreak);
                            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), view, transitionName);
                            view.getContext().startActivity(intent, transitionActivityOptions.toBundle());
                        } else {
                            view.getContext().startActivity(intent);
                        }
                    }
                });
                break;
            case MultipleItem.THREE:
                baseViewHolder.getView(R.id.img_diffuse).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), ViewDetailActivity.class);
                        intent.putExtra("title", "DiffuseImgView");
                        if (Build.VERSION.SDK_INT >= 21) {
                            // 使用api11 新加 api的方法
                            String transitionName = view.getContext().getString(R.string.imgbreak);
                            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), view, transitionName);
                            view.getContext().startActivity(intent, transitionActivityOptions.toBundle());
                        } else {
                            view.getContext().startActivity(intent);
                        }
                    }
                });
                break;
        }
    }
}
