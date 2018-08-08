package com.example.user.yperinterntest;

import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{

    private ArrayList<RecyclerItem> mItems;


    public RecyclerAdapter(ArrayList<RecyclerItem> items){
        this.mItems = items;
    }

    public ArrayList<RecyclerItem>  getmItems() {
        return mItems;
    }



    //click listener
    public interface OnItemClickListener{
        void OnItemClick(View view,RecyclerItem items);
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

//레이아웃을 view 로 만들기 위해서 필요
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {

        holder.mAddress.setText(mItems.get(position).getAddress());
        //int 보내기 위해 "" 이거 넣엇듬!
        holder.mPkinfo.setText(""+mItems.get(position).getPkinfo());
        holder.mDATE.setText(mItems.get(position).getTime());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null)
                     onItemClickListener.OnItemClick(v,mItems.get(position));
            }
        });

    }


    @Override

    public int getItemCount() {
        return mItems.size();
    }

    public void setNewItems(List<RecyclerItem> newItems) {
        mItems.clear();
        mItems.addAll(newItems);
    //notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView mAddress;
        private TextView mPkinfo;
        private TextView mDATE;

        public ItemViewHolder(View itemView){
            super(itemView);

            mAddress = itemView.findViewById(R.id.address);
            mPkinfo = itemView.findViewById(R.id.pkinfo);
            mDATE = itemView.findViewById(R.id.todaydate);
        }
    }

}

