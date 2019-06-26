package com.sjcdjsq.libs.base.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public ViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}