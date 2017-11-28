package com.ldh.mytools.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 类说明：可以复用的ViewBinder
 * <p>
 * 作  者：小蜗（Richard） on 2017/5/26
 */
public abstract class MultiTypeViewBinder<T> extends ItemViewBinder<T, ViewHolder> {

    protected Context mContext;
    protected List<T> mDatas;
    protected int     mLyaoutId;

    protected OnItemClickListener     mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    public MultiTypeViewBinder(Context context, @LayoutRes int layoutId) {
        mContext = context;
//        mDatas = ;
        mLyaoutId = layoutId;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
                                            @NonNull ViewGroup parent) {
        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, mLyaoutId);
        setListener(holder);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull T item) {
        convert(holder, item, holder.getAdapterPosition());
    }

    protected abstract void convert(ViewHolder holder, T t, int position);


    protected void setListener(final ViewHolder viewHolder) {
        viewHolder.getConvertView()
                  .setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (mOnItemClickListener != null) {
                              int position = viewHolder.getAdapterPosition();
                              mOnItemClickListener.onItemClick(v, viewHolder, position);
                          }
                      }
                  });

        viewHolder.getConvertView()
                  .setOnLongClickListener(new View.OnLongClickListener() {
                      @Override
                      public boolean onLongClick(View v) {
                          if (mOnItemLongClickListener != null) {
                              int position = viewHolder.getAdapterPosition();
                              return mOnItemLongClickListener.onItemLongClick(v, viewHolder,
                                                                              position);
                          }
                          return false;
                      }
                  });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener)
    {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);

    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }
}
