package com.chatapp.Adapter;

/**
 * Created by Tamer on 8/22/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatapp.Chat;
import com.chatapp.R;
import com.chatapp.UserDetails;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final Context context;
    private List<String> mDataset;

    View v;

    public MyAdapter(List<String> myDataset, Context context) {

        this.context=context;
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myview, parent, false);        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        final Intent intent = new Intent(context, Chat.class);

       final String str =mDataset.get(position);

        holder.mTextView.setText(str);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.BLUE);
                UserDetails.username=
                UserDetails.chatWith=str;
                context.startActivity(intent);

            }
        });



    }

    //Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView =(TextView) v.findViewById(R.id.text1);


        }
    }
}
