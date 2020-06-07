package com.tokaku.shoppingmall.message.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tokaku.shoppingmall.R;
import com.tokaku.shoppingmall.message.MessageFragment;


public class MessageAdapter extends RecyclerView.Adapter {
    private final Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(context).inflate( R.layout.item_message,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    private class MessageViewHolder extends RecyclerView.ViewHolder {
        private ImageView head;
        private TextView name;
        private TextView text;
        private TextView data;

        public MessageViewHolder(@NonNull View view) {
            super(view);
            head = view.findViewById(R.id.item_message_image);
            name = view.findViewById(R.id.item_message_name);
            text = view.findViewById(R.id.item_message_text);
            data = view.findViewById(R.id.item_message_data);
        }
    }
}
