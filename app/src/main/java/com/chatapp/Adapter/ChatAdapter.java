package com.chatapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatapp.R;

import java.util.List;

/**
 * Created by Tamer on 8/24/2017.
 */

public class ChatAdapter extends   RecyclerView.Adapter<ChatAdapter.MyHolder> {
    private List<String> mDataset;
    private List<String> sDataset;



    View v;

    public ChatAdapter(List<String> mDataset, List<String> sDataset) {
        this.mDataset = mDataset;
        this.sDataset = sDataset;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagevoew, parent, false);        // set the view's size, margins, paddings and layout parameters
        ChatAdapter.MyHolder vh = new ChatAdapter.MyHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final String str =mDataset.get(position);
        final String st =mDataset.get(position);
        holder.mTextView.setText(str);
        holder.senderTextView.setText(st);
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class MyHolder extends MyAdapter.ViewHolder{
        public TextView mTextView;
        public TextView senderTextView;

    public MyHolder(View v) {
        super(v);
        mTextView =(TextView) v.findViewById(R.id.recevedmessage);
        senderTextView=(TextView) v.findViewById(R.id.sender);


    }
}

}
