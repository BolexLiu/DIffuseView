package yfdyf.myapplication.Adapter;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

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
                break;
            case MultipleItem.TWO:
                break;
        }
    }
}
