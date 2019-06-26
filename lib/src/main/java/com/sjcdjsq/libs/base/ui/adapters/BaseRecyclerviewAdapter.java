package com.sjcdjsq.libs.base.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjcdjsq.libs.utils.images.ImageManager;
import com.lib.cooby.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/9/6.
 */
public abstract class BaseRecyclerviewAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    protected List<T> mList;
    protected Context mContext;
    protected final int TYPE_HEAD = 0x1111;
    protected final int TYPE_BODY = 0x2222;
    protected final int TYPE_FOOT = 0x3333;
    protected View mHeadView, mFootView;
    protected LayoutInflater mInflater;
    protected ImageManager mImageManager;
    protected OnItemClickListener mItemClickListener;

    public BaseRecyclerviewAdapter(Context context, List<T> mList) {
        if (mList == null)
            mList = new ArrayList<>();
        this.mList = mList;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        mImageManager = new ImageManager(context);
    }

    public void setList(List<T> list) {
        if (this.mList == null)
            this.mList = new ArrayList<>();
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return mList;
    }

    public void setHeadView(View headView) {
        this.mHeadView = headView;
    }

    public void setFootView(View footView) {
        this.mFootView = footView;
    }

    public View getFootView() {
        return mFootView;
    }

    public boolean isFootView() {
        if (mFootView == null)
            return false;
        return true;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeadView != null && viewType == TYPE_HEAD) return new ViewHolderHead(mHeadView);
        if (mFootView != null && viewType == TYPE_FOOT) return new ViewHolderFoot(mFootView);
        return getViewHolder(parent, viewType);
    }

    protected abstract ViewHolder getViewHolder(ViewGroup parent, int viewType);


    protected abstract void onBindView(ViewHolder holder, int position);

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEAD) return;
        if (getItemViewType(position) == TYPE_FOOT) return;
        final int p = mHeadView != null ? position - 1 : position;
        onBindView(holder, p);
        holder.itemView.setBackgroundResource(R.drawable.item_bg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.OnRecyclerItemClick(v, p);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeadView != null && position == 0)
            return TYPE_HEAD;
        if (mFootView != null && getItemCount() - 1 == position)
            return TYPE_FOOT;
        return TYPE_BODY;
    }


    @Override
    public int getItemCount() {
        int count = 0;
        if (mHeadView != null)
            count += 1;
        if (mFootView != null)
            count += 1;
        if (mList != null) {
            return mList.size() + count;
        }
        return count;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    class ViewHolderHead extends ViewHolder {

        public ViewHolderHead(View itemView) {
            super(itemView);
            if (itemView == mHeadView) return;
        }
    }

    class ViewHolderFoot extends ViewHolder {

        public ViewHolderFoot(View itemView) {
            super(itemView);
            if (itemView == mFootView) return;
        }
    }

    public interface OnItemClickListener {
        void OnRecyclerItemClick(View view, int position);
    }

}
