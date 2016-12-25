package com.longwu.ijkplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longwu.ijkplayer.R;
import com.longwu.ijkplayer.bean.Result;

import java.util.List;

/**
 * Created by longwu on 2016/12/03.
 */
public class MyDreamAdapter extends RecyclerView.Adapter<MyDreamAdapter.MyViewHolder> {

    private static final String TAG = "MyDreamAdapter";
    private List<Result> datas;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public interface OnItemClickListener {
        public void onClick(View parent, int position);
    }

    public interface OnItemLongClickListener {
        public boolean onLongClick(View parent, int position);
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        this.mOnItemClickListener = l;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener l) {
        this.mOnItemLongClickListener = l;
    }

    public MyDreamAdapter(Context context, List<Result> datas) {
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private TextView title,des,list;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = (TextView) itemView.findViewById(R.id.dm_title);
            des = (TextView) itemView.findViewById(R.id.dm_des);
            list = (TextView) itemView.findViewById(R.id.dm_list);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.activity_dream_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(position+1+". "+datas.get(position).getTitle());
        List<String> str_list = datas.get(position).getList();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if(str_list.size()>0)
        for(int i=0;i<str_list.size();i++){
            if(i==0){
                sb.append("\t\t\t\t"+str_list.get(i));
            }else{
                sb.append("\n\t\t\t\t"+str_list.get(i));
            }
        }
        holder.des.setText(sb);
//        holder.des.setText(datas.get(position).getDes());

//        holder.list.setText();
//        int height = Math.abs((new Random().nextInt()) % 300);
//        if (height < 200) {
//            height += 200;
//        }
//        holder.title.setHeight(150);
//        holder.des.setHeight(260);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onClick(holder.itemView, pos);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemLongClickListener.onLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(int position, String content){
//        datas.add(position,content);
    }

    public void removeData(int position){
        datas.remove(position);
    }
}