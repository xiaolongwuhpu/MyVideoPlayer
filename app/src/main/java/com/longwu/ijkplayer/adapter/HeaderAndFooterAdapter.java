package com.longwu.ijkplayer.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.longwu.ijkplayer.R;
import com.longwu.ijkplayer.bean.HomeItem;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HeaderAndFooterAdapter extends BaseQuickAdapter<HomeItem, BaseViewHolder> {

    public HeaderAndFooterAdapter(int dataSize, List mDataList) {
        super(dataSize, mDataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.text, item.getTitle());
        helper.setImageResource(R.id.icon, item.getImageResource());
    }


}
